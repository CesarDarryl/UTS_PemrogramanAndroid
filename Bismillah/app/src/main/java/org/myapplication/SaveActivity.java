package org.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.myapplication.models.Transaction;

public class SaveActivity extends AppCompatActivity {

    private EditText descriptionInput;
    private EditText amountInput;
    private RadioGroup typeRadioGroup;
    private Transaction item;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        descriptionInput = findViewById(R.id.input_description);
        amountInput = findViewById(R.id.input_amount);
        typeRadioGroup = findViewById(R.id.group_type);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            item = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            descriptionInput.setText(item.getDescription());
            amountInput.setText(String.valueOf(item.getAmount()));

            //TODO : This section is handle Radio Button.
            //you can change it to the item that we need
            if (item.getType() == Transaction.Type.GUN){
                typeRadioGroup.check(R.id.radio_gun);//this is Binding to the .xml
            } else if (item.getType() == Transaction.Type.HEAL){
                typeRadioGroup.check(R.id.radio_heal);//this is Binding to the .xml
            } else if (item.getType() == Transaction.Type.DAGING){
                typeRadioGroup.check(R.id.radio_daging);//this is Binding to the .xml
            } else if (item.getType() == Transaction.Type.SWORD){
                typeRadioGroup.check(R.id.radio_sword);//this is Binding to the .xml
            }else if (item.getType() == Transaction.Type.CHEST){
                typeRadioGroup.check(R.id.radio_box);//this is Binding to the .xml
            }
        }
    }

    private Transaction.Type getCheckedType(){
        if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_gun){
            return Transaction.Type.GUN;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_heal){
            return Transaction.Type.HEAL;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_daging){
            return Transaction.Type.DAGING;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_sword){
            return Transaction.Type.SWORD;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_box){
            return Transaction.Type.CHEST;
        }
        //TODO : ADD ELSE IF TO ADDING A NEW ITEM
        return Transaction.Type.EMPTY;
    }

    public void handleSubmit(View view) {
        String description = descriptionInput.getText().toString();
        int amount = Integer.parseInt(amountInput.getText().toString());
        Transaction.Type type = getCheckedType();

        item.setDescription(description);
        item.setAmount(amount);
        item.setType(type);

        if (description.isEmpty()){
            descriptionInput.setError("Isi terlebih dahulu");
        } else if (String.valueOf(amount).isEmpty()){
            amountInput.setError("Isi terlebih dahulu");
        } else if (getCheckedType() == Transaction.Type.EMPTY){
            Toast.makeText(getApplicationContext(), "Pilih jenis transaksi dahulu",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.TRANSACTION_KEY, item);
            intent.putExtra(MainActivity.INDEX_KEY, index);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
