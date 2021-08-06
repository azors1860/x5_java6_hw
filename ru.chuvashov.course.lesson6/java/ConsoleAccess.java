import dao.DaoType;
import dao.exception.DaoException;
import exceptions.NotEnoughMoneyException;
import exceptions.UnknownAccountException;
import service.AccountServiceImpl;

import java.util.Scanner;

public class ConsoleAccess {

    public void consoleAppUse() {
        try {
            AccountServiceImpl accountServiceImpl = new AccountServiceImpl(DaoType.db);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String str = scanner.nextLine();
                if (str.equals("exit")) {
                    break;
                } else if (str.matches("balance [0-9]+")) {
                    String stringId = str.split(" ")[1];
                    int id = Integer.parseInt(stringId);
                    accountServiceImpl.getBalance(id);
                } else if (str.matches("withdraw [0-9]+ [0-9]+")) {
                    String[] array = str.split(" ");
                    int id = Integer.parseInt(array[1]);
                    int sum = Integer.parseInt(array[2]);
                    accountServiceImpl.withDraw(id, sum);
                } else if (str.matches("deposite [0-9]+ [0-9]+")) {
                    String[] array = str.split(" ");
                    int id = Integer.parseInt(array[1]);
                    int sum = Integer.parseInt(array[2]);
                    accountServiceImpl.deposit(id, sum);
                } else if (str.matches("transfer [0-9]+ [0-9]+ [0-9]+")) {
                    String[] array = str.split(" ");
                    int id1 = Integer.parseInt(array[1]);
                    int id2 = Integer.parseInt(array[2]);
                    int sum = Integer.parseInt(array[3]);
                    accountServiceImpl.transfer(id1, id2, sum);
                } else {
                    throw new UnsupportedOperationException("Команда не найдена");
                }
            }
        } catch (UnknownAccountException | NotEnoughMoneyException | DaoException e) {
            e.printStackTrace();
        }
    }
}
