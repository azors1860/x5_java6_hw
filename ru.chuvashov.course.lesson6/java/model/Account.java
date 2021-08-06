package model;

import exceptions.NotEnoughMoneyException;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String holder;
    @Column
    private int amount;

    public Account() {
    }

    public Account(String holder, int amount) {
        this.holder = holder;
        this.amount = amount;
    }

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * @throws NotEnoughMoneyException В случае если параметр имеет отрицательное значение
     */
    public void setAmount(int amount) throws NotEnoughMoneyException {
        if (amount < 0) {
            throw new NotEnoughMoneyException("Отрицательный баланс");
        }
        this.amount = amount;
    }
}
