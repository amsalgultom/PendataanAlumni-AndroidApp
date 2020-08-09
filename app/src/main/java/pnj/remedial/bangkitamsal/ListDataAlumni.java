package pnj.remedial.bangkitamsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pnj.remedial.bangkitamsal.adapter.AdapterAlumni;
import pnj.remedial.bangkitamsal.database.DatabaseAlumni;
import pnj.remedial.bangkitamsal.model.AlumniModel;

public class ListDataAlumni extends AppCompatActivity {

    ListView listView;
    AdapterAlumni adapterAlumni;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_penduduk);
        listView = findViewById(R.id.listView);
        adapterAlumni = new AdapterAlumni(this, R.layout.item_alumni);
        listView.setAdapter(adapterAlumni);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlumniModel model = (AlumniModel) parent.getAdapter().getItem(position);
                Intent intent = new Intent(ListDataAlumni.this, TambahDataActivity.class);
                intent.putExtra("nim", model.getNim());
                intent.putExtra("nama_alumni", model.getNama_alumni());
                intent.putExtra("tempat_lahir", model.getTempat_lahir());
                intent.putExtra("tanggal_lahir", model.getTanggal_lahir());
                intent.putExtra("alamat", model.getAlamat());
                intent.putExtra("agama", model.getAgama());
                intent.putExtra("tlp", model.getTlp());
                intent.putExtra("tahun_masuk", model.getTahun_masuk());
                intent.putExtra("tahun_lulus", model.getTahun_lulus());
                intent.putExtra("pekerjaan", model.getPekerjaan());
                intent.putExtra("jabatan", model.getJabatan());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getdata();
    }
    void getdata(){

        database = new DatabaseAlumni(this).getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM tb_alumni", null);
        ArrayList<AlumniModel> data = new ArrayList<>();
        adapterAlumni.clear();
        data.clear();

        if(cursor.moveToFirst()) {
            do{
                AlumniModel model = new AlumniModel();
                model.setNim(cursor.getString(0));
                model.setNama_alumni(cursor.getString(1));
                model.setTempat_lahir(cursor.getString(2));
                model.setTanggal_lahir(cursor.getString(3));
                model.setAlamat(cursor.getString(4));
                model.setAgama(cursor.getString(5));
                model.setTlp(cursor.getString(6));
                model.setTahun_masuk(cursor.getString(7));
                model.setTahun_lulus(cursor.getString(8));
                model.setPekerjaan(cursor.getString(9));
                model.setJabatan(cursor.getString(10));

                data.add(model);

            }while (cursor.moveToNext());
            cursor.close();
            database.close();

            adapterAlumni.addAll(data);

        }

    }
}
