package dao.exception;

/**
 * Выбрасывается, чтобы указать что, возникли проблемы c получением или записью информации Dao.
 */
public class DaoException extends Exception {

    public DaoException(String message, Throwable e) {
        super(message, e);
    }

    public DaoException(String message) {
        super(message);
    }
}
