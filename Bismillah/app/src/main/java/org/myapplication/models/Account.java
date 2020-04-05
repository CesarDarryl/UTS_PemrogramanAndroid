package org.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int balance;
    private int curbalance;


    private List<Transaction> transactions;

    public int getCurbalance() {
        return curbalance;
    }

    public void setCurbalance(int curbalance) {
        this.curbalance = curbalance;
    }

    public Account(String name) {
        this.name = name;
        this.balance = 0;
        this.curbalance = 100000;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    //TODO : Add new type of getType (tipe transaksinya)
    //rencana : jadi setiap benda di ada harganya dan di set di sini
    //Berlaku untuk ADD,UPDATE,DELETE
    public int acounting()
    {
        int price = getBalance();
        int current = getCurbalance();
        int total = 0;
        total = current - price;
        setCurbalance(total);
        return total;
    }

    public void addTransaction(Transaction transaction) {
            if (transaction.getType() == Transaction.Type.GUN) {
                balance += transaction.getAmount()*75000;
            } else if (transaction.getType() == Transaction.Type.HEAL) {
                balance += transaction.getAmount()*10000;
            } else if (transaction.getType() == Transaction.Type.DAGING) {
                balance += transaction.getAmount()*7000;
            } else if (transaction.getType() == Transaction.Type.SWORD){
                balance += transaction.getAmount()*40000;
            } else if (transaction.getType() == Transaction.Type.CHEST){
                balance += transaction.getAmount()*5000;
            }
            this.transactions.add(transaction);
            setBalance(balance);
    }

    //jadi saat remove, balance harus berkurang
    // balance -= transaction.getAmount(); karena total yg harus di bayar di kurangi
    public void removeTransaction(int index) {
        Transaction transaction = transactions.get(index);
        if (transaction.getType() == Transaction.Type.GUN) {
            balance -= transaction.getAmount()*75000;
        } else if (transaction.getType() == Transaction.Type.HEAL){
            balance -= transaction.getAmount()*10000;
        } else if (transaction.getType() == Transaction.Type.DAGING) {
            balance -= transaction.getAmount()*7000;
        } else if (transaction.getType() == Transaction.Type.SWORD){
            balance -= transaction.getAmount()*40000;
        } else if (transaction.getType() == Transaction.Type.CHEST){
            balance -= transaction.getAmount()*5000;
        }
        this.transactions.remove(transaction);
    }
}
