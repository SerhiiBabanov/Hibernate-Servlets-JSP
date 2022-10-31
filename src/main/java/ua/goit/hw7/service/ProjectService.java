package ua.goit.hw7.service;

import ua.goit.hw7.model.dao.ProjectDao;
import ua.goit.hw7.model.dto.ProjectDto;
import ua.goit.hw7.repository.DeveloperRepository;
import ua.goit.hw7.repository.ProjectRepository;
import ua.goit.hw7.service.conventer.ProjectConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectConverter projectConverter;

    public ProjectService(ProjectRepository projectRepository,
                          DeveloperRepository developerRepository,
                          ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
        this.projectConverter = projectConverter;
    }

    public ProjectDto create(ProjectDto projectDto) {
        ProjectDao projectDao = projectRepository.save(projectConverter.to(projectDto));
        return projectConverter.from(projectDao);
    }

    public Optional<ProjectDto> getById(Long id) {
        Optional<ProjectDao> projectDao = projectRepository.findById(id);
        return projectDao.map(projectConverter::from);
    }

    public ProjectDto update(ProjectDto projectDto) {
        ProjectDao projectDao = projectRepository.update(projectConverter.to(projectDto));
        return projectConverter.from(projectDao);
    }

    public void delete(ProjectDto projectDto) {
        projectRepository.delete(projectConverter.to(projectDto));
    }

    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(projectConverter::from)
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getProjectsByDeveloperId(Long id) {

        return developerRepository.findById(id).orElseThrow(RuntimeException::new)
                .getProjects().stream()
                .map(projectConverter::from)
                .collect(Collectors.toList());
    }

}
