package dao;

import dao.exception.DaoExceptionJson;
import dao.exception.DbHibernateException;

public interface DaoFactory<T> {
    Dao<T> getDao() throws DaoExceptionJson, DbHibernateException;
}
