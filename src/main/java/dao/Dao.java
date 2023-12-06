package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    boolean delete(K id);
    List<E> findAll();
    E save(E e);
    boolean update(E e);
    Optional<E> findById(K id);
}
