package dao.exception;

/**
 * Выбрасывается, чтобы указать что, возникли проблемы с чтением информации из файла и преобразования информалии
 * в список аккаунтов, либо, если возникли проблемы при преобразовании списка аккаунта в текст и записи его в файл.
 */
public class DaoExceptionJson extends DaoException {

    public DaoExceptionJson(String message, Throwable e) {
        super(message, e);
    }

    public DaoExceptionJson(String message) {
        super(message);
    }
}
