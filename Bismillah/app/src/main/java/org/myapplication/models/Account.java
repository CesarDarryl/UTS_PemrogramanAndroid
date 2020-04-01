package org.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int balance;
    private List<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void addTransaction(Transaction transaction) {
        if (transaction.getType() == Transaction.Type.CREDIT) {
            balance += transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.DEBIT) {
            balance -= transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.DAGING) {
            balance += transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.SWORD){
            balance += transaction.getAmount();
        }
        this.transactions.add(transaction);
    }

    //jadi saat remove, balance harus berkurang
    // balance -= transaction.getAmount(); karena total yg harus di bayar di kurangi
    public void removeTransaction(int index) {
        Transaction transaction = transactions.get(index);
        if (transaction.getType() == Transaction.Type.CREDIT) {
            balance -= transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.DEBIT){
            balance += transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.DAGING) {
            balance -= transaction.getAmount();
        } else if (transaction.getType() == Transaction.Type.SWORD){
            balance -= transaction.getAmount();
        }
        this.transactions.remove(transaction);
    }

    public void updateTransaction(int index, Transaction transaction) {
        this.transactions.set(index, transaction);
        this.balance = 0;
        for (Transaction t : this.transactions) {
            // pada update, statment nya masing2 harga . jadi nantik harga di kali tiap harga per type mengikuti di update nya
            //statment nya sama sperti add transaction
            if (t.getType() == Transaction.Type.CREDIT) {
                balance += t.getAmount();
            } else if (transaction.getType() == Transaction.Type.DEBIT){
                balance -= t.getAmount();
            } else if (transaction.getType() == Transaction.Type.DAGING) {
                balance += t.getAmount();
            } else if (transaction.getType() == Transaction.Type.SWORD){
                balance += t.getAmount();
            }
        }
    }
}
