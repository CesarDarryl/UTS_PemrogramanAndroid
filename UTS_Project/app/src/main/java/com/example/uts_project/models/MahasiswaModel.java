package com.example.uts_project.models;

import android.content.Context;
import android.database.Cursor;

import com.example.uts_project.entities.mahasiswa;
import com.example.uts_project.liblaries.DbHelper;

import java.util.ArrayList;

public class MahasiswaModel {
    private Context context;
    private DbHelper db;

    public MahasiswaModel(Context context) {
        this.context = context;
        this.db = new DbHelper(this.context);
    }

    public ArrayList<mahasiswa> selectAll() {
        String sql = "SELECT * FROM kontak";
        ArrayList<mahasiswa> semuaKontak = new ArrayList<>();
        Cursor cursor = this.db.executeRead(sql);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String nama = cursor.getString(1);
                String alamat = cursor.getString(2);

                mahasiswa m = new mahasiswa();
                m.setId(id);
                m.setNama(nama);
                m.setAlamat(alamat);

                semuaKontak.add(m);
            }
            while (cursor.moveToNext());
        }
        return semuaKontak;
    }

    public void insert(mahasiswa m) {
        int id = m.getId();
        String nama = m.getNama();
        String alamat = m.getAlamat();

        String sql = "INSERT INTO kontak(nama, alamat) VALUES('" + nama + "', '" + alamat + "')";
        this.db.executeWrite(sql);
    }

    public void update (mahasiswa m) {
        if (m.getId() < 0)
            return;
        int id = m.getId();
        String nama = m.getNama();
        String alamat = m.getAlamat();

        String sql = "UPDATE kontak SET nama = '" + nama + "', alamat = '" + alamat + "' WHERE id = '" + id + "'";
        this.db.executeWrite(sql);
    }

    public mahasiswa selectOne(int id) {
        String sql = "SELECT * FROM kontak WHERE id = '" + id + "'";
        Cursor cursor = this.db.executeRead(sql);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            mahasiswa m = new mahasiswa();
            m.setId(cursor.getInt(0));
            m.setNama(cursor.getString(1));
            m.setAlamat(cursor.getString(2));

            return m;
        }
        return null;
    }

    public void delete(mahasiswa m) {
        if (m.getId() < 0)
            return;
        String sql = "DELETE FROM kontak WHERE id = '" + m.getId() + "'";
        this.db.executeWrite(sql);
    }
}
