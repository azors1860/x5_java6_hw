package service;

import dao.exception.DaoException;
import exceptions.NotEnoughMoneyException;
import exceptions.UnknownAccountException;

public interface AccountService {

    /**
     * Снять с указанного счёта указанную сумму денег.
     *
     * @param accountId - Идентификатор аккаунта.
     * @param amount    - Сумма, которая будет списана со счёта.
     */
    void withDraw(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoException;

    /**
     * Выводит баланс указанного аккаунта в консоль.
     *
     * @param accountId - Идентификатор аккаунта.
     */
    void getBalance(int accountId)
            throws UnknownAccountException, DaoException;

    /**
     * Пополнить указанный на указанную сумму денег.
     *
     * @param accountId - Идентификатор аккаунта.
     * @param amount    - Сумма, на которую будет пополнен счёт.
     */
    void deposit(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoException;

    /**
     * Перевести деньги с одного счёта на другой (указаны в параметре) на указанную в параметре сумму.
     *
     * @param from   - Идентификатор аккаунта, со счёта которого будут списаны деньги.
     * @param to     - Идентификатор аккаунта, на счёт которого будут зачислены деньги.
     * @param amount - Сумма денег для перевода.
     */
    void transfer(int from, int to, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoException;
}
