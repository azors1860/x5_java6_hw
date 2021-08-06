package dao.exception;

/**
 * Выбрасывается, чтобы указать что, возникли проблемы с чтением информации из БД, либо при записи информации в БД.
 */
public class DbHibernateException extends DaoException {

    public DbHibernateException(String message, Throwable e) {
        super(message, e);
    }

    public DbHibernateException(String message) {
        super(message);
    }
}
