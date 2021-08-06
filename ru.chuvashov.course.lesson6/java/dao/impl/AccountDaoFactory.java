package dao.impl;

import dao.Dao;
import dao.DaoFactory;
import dao.DaoType;
import dao.exception.DaoExceptionJson;
import model.Account;

public class AccountDaoFactory implements DaoFactory<Account> {

    private final DaoType type;

    public AccountDaoFactory(DaoType typeDao) {
        this.type = typeDao;
    }


    @Override
    public Dao<Account> getDao() throws DaoExceptionJson {
        Dao<Account> dao;
        switch (type) {
            case db:
                dao = new DbAccountHibernate();
                break;
            case json:
                dao = new JsonAccountDao();
                break;
            default:
                throw new UnsupportedOperationException("Ошибка: не найден DAO c данным значением :" + type);
        }
        return dao;
    }
}