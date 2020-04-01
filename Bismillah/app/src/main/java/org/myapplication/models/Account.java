package org.myapplication.models;

import android.content.Context;
import android.widget.Toast;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int balance;
    private List<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        this.balance = 100000;
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
//        if (transaction.getType() == Transaction.Type.GUN) {
//            balance += transaction.getAmount()*75000;
//        } else if (transaction.getType() == Transaction.Type.HEAL) {
//            balance += transaction.getAmount()*10000;
//        } else if (transaction.getType() == Transaction.Type.DAGING) {
//            balance += transaction.getAmount()*7000;
//        } else if (transaction.getType() == Transaction.Type.SWORD){
//            balance += transaction.getAmount()*40000;
//        } else if (transaction.getType() == Transaction.Type.CHEST){
//            balance += transaction.getAmount()*5000;
//        }
//        this.transactions.add(transaction);
            if (transaction.getType() == Transaction.Type.GUN) {
                balance -= transaction.getAmount()*75000;
            } else if (transaction.getType() == Transaction.Type.HEAL) {
                balance -= transaction.getAmount()*10000;
            } else if (transaction.getType() == Transaction.Type.DAGING) {
                balance -= transaction.getAmount()*7000;
            } else if (transaction.getType() == Transaction.Type.SWORD){
                balance -= transaction.getAmount()*40000;
            } else if (transaction.getType() == Transaction.Type.CHEST){
                balance -= transaction.getAmount()*5000;
            }
            this.transactions.add(transaction);
    }

    //jadi saat remove, balance harus berkurang
    // balance -= transaction.getAmount(); karena total yg harus di bayar di kurangi
    public void removeTransaction(int index) {
//        Transaction transaction = transactions.get(index);
//        if (transaction.getType() == Transaction.Type.GUN) {
//            balance -= transaction.getAmount()*75000;
//        } else if (transaction.getType() == Transaction.Type.HEAL){
//            balance -= transaction.getAmount()*10000;
//        } else if (transaction.getType() == Transaction.Type.DAGING) {
//            balance -= transaction.getAmount()*7000;
//        } else if (transaction.getType() == Transaction.Type.SWORD){
//            balance -= transaction.getAmount()*40000;
//        } else if (transaction.getType() == Transaction.Type.CHEST){
//            balance -= transaction.getAmount()*5000;
//        }
//        this.transactions.remove(transaction);
        Transaction transaction = transactions.get(index);
        if (transaction.getType() == Transaction.Type.GUN) {
            balance += transaction.getAmount()*75000;
        } else if (transaction.getType() == Transaction.Type.HEAL){
            balance += transaction.getAmount()*10000;
        } else if (transaction.getType() == Transaction.Type.DAGING) {
            balance += transaction.getAmount()*7000;
        } else if (transaction.getType() == Transaction.Type.SWORD){
            balance += transaction.getAmount()*40000;
        } else if (transaction.getType() == Transaction.Type.CHEST){
            balance += transaction.getAmount()*5000;
        }
        this.transactions.remove(transaction);
    }

    public void updateTransaction(int index, Transaction transaction) {
        this.transactions.set(index, transaction);
        this.balance = 100000;
//        for (Transaction t : this.transactions) {
//            // pada update, statment nya masing2 harga . jadi nantik harga di kali tiap harga per type mengikuti di update nya
//            //statment nya sama sperti add transaction
//            if (t.getType() == Transaction.Type.GUN) {
//                balance += t.getAmount()*75000;
//            } else if (transaction.getType() == Transaction.Type.HEAL){
//                balance += t.getAmount()*10000;
//            } else if (transaction.getType() == Transaction.Type.DAGING) {
//                balance += t.getAmount()*7000;
//            } else if (transaction.getType() == Transaction.Type.SWORD){
//                balance += t.getAmount()*40000;
//            } else if (transaction.getType() == Transaction.Type.CHEST){
//                balance += t.getAmount()*5000;
//            }
//        }
        for (Transaction t : this.transactions) {
            // pada update, statment nya masing2 harga . jadi nantik harga di kali tiap harga per type mengikuti di update nya
            //statment nya sama sperti add transaction
            if (t.getType() == Transaction.Type.GUN) {
                balance -= t.getAmount()*75000;
            } else if (transaction.getType() == Transaction.Type.HEAL){
                balance -= t.getAmount()*10000;
            } else if (transaction.getType() == Transaction.Type.DAGING) {
                balance -= t.getAmount()*7000;
            } else if (transaction.getType() == Transaction.Type.SWORD){
                balance -= t.getAmount()*40000;
            } else if (transaction.getType() == Transaction.Type.CHEST){
                balance -= t.getAmount()*5000;
            }
        }
    }
}
