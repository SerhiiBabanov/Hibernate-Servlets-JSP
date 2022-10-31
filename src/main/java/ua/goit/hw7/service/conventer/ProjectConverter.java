package ua.goit.hw7.service.conventer;

import ua.goit.hw7.model.dao.ProjectDao;
import ua.goit.hw7.model.dto.ProjectDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ProjectConverter implements Converter<ProjectDto, ProjectDao> {
    @Override
    public ProjectDto from(ProjectDao projectDao) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectDao.getId());
        projectDto.setName(projectDao.getName());
        projectDto.setGit_url(projectDao.getGit_url());
        projectDto.setCost(projectDao.getCost());
        LocalDate date = Instant.ofEpochMilli(projectDao.getDate()).atZone(ZoneId.systemDefault()).toLocalDate();
        projectDto.setDate(date);
        return projectDto;
    }

    @Override
    public ProjectDao to(ProjectDto projectDto) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(projectDto.getId());
        projectDao.setName(projectDto.getName());
        projectDao.setGit_url(projectDto.getGit_url());
        projectDao.setCost(projectDto.getCost());
        Instant instant = projectDto.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant();
        projectDao.setDate(instant.toEpochMilli());
        return projectDao;
    }
}
