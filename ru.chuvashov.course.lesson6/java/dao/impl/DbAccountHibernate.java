package dao.impl;

import dao.Dao;
import dao.exception.DbHibernateException;
import exceptions.UnknownAccountException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Account;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DbAccountHibernate implements Dao<Account> {

    private static SessionFactory sessionFactory;

    public DbAccountHibernate() {
        initialization();
    }

    @Override
    public void create(Account item) throws DbHibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error creating the accounts", e);
        }
    }

    @Override
    public void update(Account item) throws DbHibernateException, UnknownAccountException {
        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.update(item);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                throw new DbHibernateException("Error updating the accounts", e);
            }
        }
    }

    @Override
    public Account read(int id) throws DbHibernateException, UnknownAccountException {

        if (id < 1) {
            throw new UnknownAccountException("Некорректный идентификатор акаауета на входе: " + id);
        }
        Account result;
        try {
            Session session = sessionFactory.openSession();
            result = session.get(Account.class, id);
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error reading the accounts", e);
        }
        if (result == null) {
            throw new UnknownAccountException("Произошла ошибка при получении объекта с указанным id: " + id);
        }
        return result;
    }

    @Override
    public void delete(Account item) throws DbHibernateException, UnknownAccountException {
        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                session.delete(item);
                session.close();
            } catch (Exception e) {
                throw new DbHibernateException("Error reading the accounts", e);
            }
        }
    }

    @Override
    public List<Account> getListAccounts() throws DbHibernateException {
        List<Account> accounts = null;
        try {
            Session session = sessionFactory.openSession();
            accounts = (List<Account>) session.createQuery("From Account").list();
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error reading the accounts", e);
        }
        if (accounts == null) {
            throw new DbHibernateException("Error reading the accounts");
        } else {
            return accounts;
        }
    }

    private void initialization() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Account.class);
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }
}


