package com.example.uts_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uts_project.entities.mahasiswa;
import com.example.uts_project.models.MahasiswaModel;

import java.util.ArrayList;

public class LihatMahasiswaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Handler handler = new Handler();
    Runnable refresh;
    // Data
    private MahasiswaModel mMhs;
    private ArrayList<mahasiswa> allMhs;
    private ArrayList<String> daftarMhs;
    // Komponen
    private ListView lstDaftarMhs;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_mahasiswa);

        this.initData();
        this.initComponents();
    }

    private void initData() {
        this.daftarMhs = new ArrayList<>();
        this.mMhs = new MahasiswaModel(this);
        this.allMhs = this.mMhs.selectAll();
        for(mahasiswa m : allMhs) {
            this.daftarMhs.add(m.getNama());
        }
    }

    private void initComponents()
    {
        this.lstDaftarMhs = (ListView) this.findViewById(R.id.lstDaftarMhs);
        this.btnOk = (Button) this.findViewById(R.id.btnOk);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.daftarMhs);
        this.lstDaftarMhs.setAdapter(adapter);

        this.lstDaftarMhs.setOnItemClickListener(this);
    }

    public void button_onClick(View view) {
        Button b = (Button) view;
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        if(b == this.btnOk)
            this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            int id = this.allMhs.get(i).getId();
            Intent intent = new Intent(this, DetailMahasiswaActivity.class);
            intent.putExtra("selectedContactId", id);
            this.startActivity(intent);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Refresh please", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
