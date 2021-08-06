package dao;

import dao.exception.DaoException;
import exceptions.UnknownAccountException;

import java.util.List;

public interface Dao<T> {
    /**
     * Создаёт новый объект
     *
     * @param item - Объект который должен быть создан.
     */
    void create(T item) throws DaoException;

    /**
     * Обновляет (изменяет) существующий объект в базе.
     *
     * @param item - Объект который должен быть изменен.
     */
    void update(T item) throws UnknownAccountException, DaoException;

    /**
     * Возвращает объект с соответствующим идентификатором из параметра (из БД).
     *
     * @param id - Идентификартор объекта.
     * @return - Объект, идентификатор которого равен идентификатору указанному в параметре.
     */
    T read(int id) throws UnknownAccountException, DaoException;

    /**
     * Удаляет переданный объект из БД.
     *
     * @param item - Объект, который должен быть удалён из базы.
     */
    void delete(T item) throws DaoException, UnknownAccountException;

    /**
     * Метод для получения всех объектов, имеющихся в БД.
     *
     * @return Список всех аккаунтов.
     */
    List<T> getListAccounts() throws DaoException;
}
