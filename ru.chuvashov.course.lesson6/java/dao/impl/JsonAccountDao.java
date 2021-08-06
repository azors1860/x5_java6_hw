package dao.impl;

import dao.Dao;
import dao.exception.DaoExceptionJson;
import exceptions.UnknownAccountException;
import org.codehaus.jackson.map.ObjectMapper;
import model.Account;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Arrays;


public class JsonAccountDao implements Dao<Account> {

    private final static File file = new File("ru.chuvashov.course.lesson6/resources/accounts.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonAccountDao() throws DaoExceptionJson {
        initialization();
    }

    @Override
    public void create(Account item) throws DaoExceptionJson {

        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        List<Account> accounts = getListAccounts();
        accounts.add(item);
        writerFileJson(accounts);
    }

    @Override
    public void update(Account item) throws UnknownAccountException, DaoExceptionJson {

        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        List<Account> accounts = getListAccounts();
        int searchId = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == item.getId()) {
                searchId = i;
                break;
            }
        }
        if (searchId == -1) {
            throw new UnknownAccountException("Аккаунт с указанным id не найден");
        }
        accounts.set(searchId, item);
        writerFileJson(accounts);
    }

    @Override
    public void delete(Account item) throws DaoExceptionJson, UnknownAccountException {

        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        List<Account> accounts = getListAccounts();
        boolean isReplacedAccount = accounts.removeIf(temp -> temp.getId() == item.getId());
        if (!isReplacedAccount) {
            throw new UnknownAccountException("Аккаунт с указанным id не найден");
        } else {
            writerFileJson(accounts);
        }
    }

    @Override
    public Account read(int id) throws UnknownAccountException, DaoExceptionJson {
        Account account = null;
        List<Account> accounts = getListAccounts();
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                account = acc;
            }
        }

        if (account == null) {
            throw new UnknownAccountException("Аккаунт не найден");
        }
        return account;
    }

    @Override
    public List<Account> getListAccounts() throws DaoExceptionJson {
        List<Account> accounts;
        try {
            accounts = Arrays.asList(mapper.readValue(file, Account[].class));
        } catch (IOException e) {
            throw new DaoExceptionJson("Error getting the accounts", e);
        }

        if (accounts == null) {
            throw new DaoExceptionJson("Failed to initialize the object");
        } else {
            return accounts;
        }
    }

    private void writerFileJson(Object array) throws DaoExceptionJson {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, array);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new DaoExceptionJson("Failed to writing the file");
        }
    }
    private void initialization() throws DaoExceptionJson {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            if (!file.exists()) {
                file.createNewFile();
                Account[] accounts = {
                        (new Account(1, "Иванов Иван", 500)),
                        (new Account(2, "Валентинов Валентин", 600)),
                        (new Account(3, "Константинов Константин", 700)),
                        (new Account(4, "Михайлов Михаил", 800)),
                        (new Account(5, "Гай Игоорь", 900)),
                        (new Account(6, "Носов Николай", 1000)),
                        (new Account(7, "Николаев Константин", 10100)),
                        (new Account(8, "Михайлов Алексей", 2300)),
                        (new Account(9, "Константинов Шон", 15005)),
                        (new Account(10, "Бабушкин Михаил", 0))
                };
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                mapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, accounts);
                fileOutputStream.close();
            }
        } catch (IOException e) {
            throw new DaoExceptionJson("Error initialization file", e);
        }
    }
}
