package ua.goit.hw7.service.conventer;

public interface Converter<E, T> {
    E from(T entity);

    T to(E entity);
}
