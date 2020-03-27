package com.example.uts_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnLihatDataMahasiswa;
    private Button btnTambahDataMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnTambahDataMahasiswa(View view) {
        Intent intent = new Intent(this, TambahMahasiswaActivity.class);
        this.startActivity(intent);
    }

    public void btnLihatDataMahasiswa(View view) {
        Intent intent = new Intent(this, LihatMahasiswaActivity.class);
        this.startActivity(intent);
    }
}
