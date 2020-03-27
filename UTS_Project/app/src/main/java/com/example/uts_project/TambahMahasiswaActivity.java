package com.example.uts_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_project.entities.mahasiswa;
import com.example.uts_project.models.MahasiswaModel;

public class TambahMahasiswaActivity extends AppCompatActivity {

    // Data
    private MahasiswaModel mMhs;
    // Komponen
    private EditText txtNama;
    private EditText txtAlamat;

    private Button btnSimpan;
    private Button btnBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        this.initData();
        this.initComponents();
    }

    private void initData() {
        this.mMhs = new MahasiswaModel(this);
    }

    private void initComponents()
    {
        this.txtNama = (EditText) this.findViewById(R.id.txtNama);
        this.txtAlamat = (EditText) this.findViewById(R.id.txtAlamat);

        this.btnSimpan = (Button) this.findViewById(R.id.btnSimpan);
        this.btnBatal = (Button) this.findViewById(R.id.btnBatal);
    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b == this.btnSimpan)
        {
            this.tambahMhs();
        }         else if(b == this.btnBatal)
        {
            this.finish();
        }
    }

    private void tambahMhs() {
        String nama = this.txtNama.getText().toString();
        String alamat = this.txtAlamat.getText().toString();

        mahasiswa mhsBaru = new mahasiswa();
        mhsBaru.setNama(nama);
        mhsBaru.setAlamat(alamat);

        if(nama.equals("") && alamat.equals("")){
            Toast.makeText(getApplicationContext(), "Input yang anda masukkan kosong",
                    Toast.LENGTH_SHORT).show();
        }
        else if(nama.equals("")){
            Toast.makeText(getApplicationContext(), "Input yang anda masukkan kosong",
                    Toast.LENGTH_SHORT).show();
        }
        else if(alamat.equals("")){
            Toast.makeText(getApplicationContext(), "Input yang anda masukkan kosong",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            this.mMhs.insert(mhsBaru);

            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            this.btnBatal.setText("Kembali");
        }
    }
}
