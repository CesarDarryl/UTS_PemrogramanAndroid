package com.example.uts_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String list1[], list2[];
    int images[] = {R.drawable.onepiece, R.drawable.blackclover, R.drawable.naruto};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get resource to store string variabel
        list1 = getResources().getStringArray(R.array.list_Komik);
        list2 = getResources().getStringArray(R.array.description);

        recyclerView = findViewById(R.id.recyclerview);
        //initialize new class
        SimpleAdapter simpleAdapter = new SimpleAdapter(list1[],list2[],images);
    }
}
