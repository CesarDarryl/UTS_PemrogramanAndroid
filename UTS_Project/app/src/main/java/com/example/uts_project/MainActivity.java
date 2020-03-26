package com.example.uts_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String data1[], data2[];
    int images[] = {R.drawable.onepiece, R.drawable.blackclover, R.drawable.naruto};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        //get resource to store string variabel
        data1 = getResources().getStringArray(R.array.list_Komik);
        data2 = getResources().getStringArray(R.array.description);

        recyclerView = findViewById(R.id.recyclerview);
        //initialize new class
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data1,data2,images);
        recyclerView.setAdapter(simpleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void handleButtonLihatFilm(View view) {
        //TODO : Intent kan ke Page lain
    }
}
