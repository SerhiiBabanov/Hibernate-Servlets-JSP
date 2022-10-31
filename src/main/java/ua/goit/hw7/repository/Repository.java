package ua.goit.hw7.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T save(T entity);

    void delete(T entity);

    Optional<T> findById(Long id);

    T update(T entity);

    List<T> findAll();

    List<T> findByListOfID(List<Long> idList);

}
