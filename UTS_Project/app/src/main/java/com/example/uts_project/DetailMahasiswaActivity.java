package com.example.uts_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_project.entities.mahasiswa;
import com.example.uts_project.models.MahasiswaModel;

public class DetailMahasiswaActivity extends AppCompatActivity {

    // Data
    private MahasiswaModel mMhs;
    private mahasiswa selectedMhs;
    // Komponen
    private EditText txtNama;
    private EditText txtAlamat;

    private Button btnUbah;
    private Button btnHapus;
    private Button btnKembali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        this.initData();
        this.initComponents();
    }
    private void initData() {
        this.mMhs = new MahasiswaModel(this);
        int selectedContactId = this.getIntent().getIntExtra("selectedContactId", -1);
        this.selectedMhs = this.mMhs.selectOne(selectedContactId);
    }

    private void initComponents()
    {
        this.txtNama = (EditText) this.findViewById(R.id.txtNama);
        this.txtAlamat = (EditText) this.findViewById(R.id.txtAlamat);

        this.btnUbah = (Button) this.findViewById(R.id.btnUbah);
        this.btnHapus = (Button) this.findViewById(R.id.btnHapus);
        this.btnKembali = (Button) this.findViewById(R.id.btnKembali);

        // Isi teks pada komponen saat Activity baru dimunculkan
        this.txtNama.setText(this.selectedMhs.getNama());
        this.txtAlamat.setText(this.selectedMhs.getAlamat());
    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if (b == this.btnUbah) {
            this.ubahMhs();
        } else if (b == this.btnHapus) {
            this.hapusMhs();
        } else if (b == this.btnKembali) {
            this.finish();
        }
    }

    private  void ubahMhs(){
        String nama = this.txtNama.getText().toString();
        String alamat = this.txtAlamat.getText().toString();

        this.selectedMhs.setNama(nama);
        this.selectedMhs.setAlamat(alamat);
        this.mMhs.update(this.selectedMhs);
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

            this.resetFields("Data berhasil diperbarui!", false);
            Intent i = new Intent(this, LihatMahasiswaActivity.class);
            this.startActivity(i);}
    }


    private void hapusMhs() {
        this.mMhs.delete(this.selectedMhs);
        this.resetFields("Data Berhasil dihapus..", true);
        this.btnHapus.setEnabled(false);

        Intent i = new Intent(this, LihatMahasiswaActivity.class);
        this.startActivity(i);
    }

    private void resetFields(String pesan, boolean clear) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
        if(clear)
        {
            this.txtNama.setText("");
            this.txtAlamat.setText("");
        }
    }
}
