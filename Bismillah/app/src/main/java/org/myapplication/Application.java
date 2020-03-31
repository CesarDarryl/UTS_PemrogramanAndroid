package org.myapplication;

import org.myapplication.models.Account;
import org.myapplication.models.Session;

public class Application extends android.app.Application {
    private static Account account;
    private static Session session;

    @Override
    public void onCreate(){
        super.onCreate();
        account = new Account("M Darryl MC");
        session = new Session(this);
    }

    public static Session getSession(){
        return session;
    }

    public static Account getAccount() {
        return account;
    }
}

//TODO : NAMBAHIN ADAPTERS MODELS , xml nya : CONTENT MAIN SAMA ITEM TRANSACTION
