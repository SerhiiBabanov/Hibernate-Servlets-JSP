package ua.goit.hw7.service.conventer;

import ua.goit.hw7.model.dao.DeveloperDao;
import ua.goit.hw7.model.dto.DeveloperDto;

public class DeveloperConverter implements Converter<DeveloperDto, DeveloperDao> {
    @Override
    public DeveloperDto from(DeveloperDao developerDao) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(developerDao.getId());
        developerDto.setName(developerDao.getName());
        developerDto.setUsername(developerDao.getUsername());
        developerDto.setSalary(developerDao.getSalary());
        return developerDto;
    }

    @Override
    public DeveloperDao to(DeveloperDto developerDto) {
        DeveloperDao developerDao = new DeveloperDao();
        developerDao.setId(developerDto.getId());
        developerDao.setName(developerDto.getName());
        developerDao.setUsername(developerDto.getUsername());
        developerDao.setSalary(developerDto.getSalary());
        return developerDao;
    }
}
