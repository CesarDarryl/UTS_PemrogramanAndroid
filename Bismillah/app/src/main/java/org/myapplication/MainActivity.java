package org.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.myapplication.adapters.TransactionAdapter;
import org.myapplication.models.Account;
import org.myapplication.models.Session;
import org.myapplication.models.Transaction;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TransactionAdapter.OnItemTransactionListener{

    public static final String TRANSACTION_KEY = "TRANSACTION";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;

    private TextView welcomeText;
    private TextView balanceText;
    private RecyclerView transactionsView;
    private TransactionAdapter adapter;
    private Account account;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    private Session session;
    boolean confirm = true;
    private Button resetButton;
    private TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        welcomeText = findViewById(R.id.text_welcome);
        balanceText = findViewById(R.id.text_balance);
        transactionsView = findViewById(R.id.rv_transactions);
        resetButton = findViewById(R.id.button);
        currency = findViewById(R.id.text_curbalance);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Tambahkan event click fab di sini
                confirm = true;
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                intent.putExtra(TRANSACTION_KEY, new Transaction());
                startActivityForResult(intent, INSERT_REQUEST);
            }
        });

        account = Application.getAccount();

        adapter = new TransactionAdapter(account.getTransactions(),this);
        transactionsView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionsView.setLayoutManager(layoutManager);

        welcomeText.setText(String.format("Welcome   %s", account.getName()));
        balanceText.setText(formatRupiah.format(account.getBalance()));
        currency.setText(formatRupiah.format(account.getCurbalance()));

        session = Application.getSession();

        if (!session.isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.
                SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                adapter.notifyDataSetChanged();
                account.removeTransaction(index);
                resetButton.setVisibility(View.INVISIBLE); //To set visible
                balanceText.setText(formatRupiah.format(account.getBalance()));
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(transactionsView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTransactionClicked(int index, Transaction item) {
        Intent intent = new Intent(this, SaveActivity.class);
        intent.putExtra(TRANSACTION_KEY, item);
        intent.putExtra(INDEX_KEY, index);
        if(confirm)
        {
//            startActivityForResult(intent, UPDATE_REQUEST);
            account.removeTransaction(index);
            startActivityForResult(intent, INSERT_REQUEST);
        }else
            {
                startActivityForResult(intent, INSERT_REQUEST);
                confirm = true;
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
            session.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if (requestCode == INSERT_REQUEST) {
                account.addTransaction(transaction);
            }
            adapter.notifyDataSetChanged();
            balanceText.setText(formatRupiah.format(account.getBalance()));
            resetButton.setVisibility(View.VISIBLE); //To set visible
        }
    }

    public void handleSendToInvent(View view) {
        if(adapter.getItemCount() == 0)
        {
            Toast.makeText(this, "ANDA BELUM BELI SESUATU", Toast.LENGTH_SHORT).show();
            resetButton.setVisibility(View.INVISIBLE); //To set visible
            confirm = true;
        }else
            {
                if(account.getCurbalance() >= account.getBalance())
                {
                    Toast.makeText(this, "SUKSES MASUK INVENTORY", Toast.LENGTH_SHORT).show();
                    resetButton.setVisibility(View.INVISIBLE); //To set visible
                    confirm = false;
                    int total = account.acounting();
                    //put calling method clear
                    adapter.clear();
                    balanceText.setText(formatRupiah.format(0));
                    account.setBalance(0);
                    currency.setText(formatRupiah.format(total));
                }else{
                    Toast.makeText(this, "UANG ANDA TIDAK CUKUP", Toast.LENGTH_SHORT).show();
                }
            }
    }
}
