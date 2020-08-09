package pnj.remedial.bangkitamsal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.remedial.bangkitamsal.database.DatabaseAlumni;

public class TambahDataActivity extends AppCompatActivity {

    EditText edtNim, edtNamaAlumni, edtTempatLahir, edtAlamat,
            edtAgama, edtTlp, edtTahunMasuk, edtTahunLulus, edtPekerjaan, edtJabatan;
    TextView edtTanggalLahir;
    Button actionSimpan, actionHapus;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        edtNim = findViewById(R.id.edtNim);
        edtNamaAlumni = findViewById(R.id.edtNamaAlumni);
        edtTempatLahir = findViewById(R.id.edtTempatLahir);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtAgama = findViewById(R.id.edtAgama);
        edtTlp = findViewById(R.id.edtTlp);
        edtTahunMasuk = findViewById(R.id.edtTahunMasuk);
        edtTahunLulus = findViewById(R.id.edtTahunLulus);
        edtPekerjaan = findViewById(R.id.edtPekerjaan);
        edtJabatan = findViewById(R.id.edtJabatan);
        actionSimpan = findViewById(R.id.actionSimpan);
        actionHapus = findViewById(R.id.actionHapus);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            actionSimpan.setText("UPDATE");
            edtNim.setEnabled(false);
            edtNim.setText(extras.getString("nim", ""));
            edtNamaAlumni.setText(extras.getString("nama_alumni", ""));
            edtTempatLahir.setText(extras.getString("tempat_lahir", ""));
            edtTanggalLahir.setText(extras.getString("tanggal_lahir", ""));
            edtAlamat.setText(extras.getString("alamat", ""));
            edtAgama.setText(extras.getString("agama", ""));
            edtTlp.setText(extras.getString("tlp", ""));
            edtTahunMasuk.setText(extras.getString("tahun_masuk", ""));
            edtTahunLulus.setText(extras.getString("tahun_lulus", ""));
            edtPekerjaan.setText(extras.getString("pekerjaan", ""));
            edtJabatan.setText(extras.getString("jabatan", ""));

        }else {
            actionHapus.setVisibility(View.GONE);
        }

        edtTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Calendar myCalendar = Calendar.getInstance();
                new DatePickerDialog(TambahDataActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        actionSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
        actionHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            edtTanggalLahir.setText(simpleDateFormat.format(calendar.getTime().getTime()));
        }
    };

    void simpan() {

        if (edtNim.getText().toString().length() > 0 && edtNamaAlumni.getText().toString().length() > 0 &&
                edtTempatLahir.getText().toString().length() > 0 && edtTanggalLahir.getText().toString().length() > 0 &&
                edtAlamat.getText().toString().length() > 0 && edtAgama.getText().toString().length() > 0 &&
                edtTlp.getText().toString().length() > 0 && edtTahunMasuk.getText().toString().length() > 0 &&
                edtTahunLulus.getText().toString().length() > 0 && edtPekerjaan.getText().toString().length() > 0 &&
                edtJabatan.getText().toString().length() > 0) {

            database = new DatabaseAlumni(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                contentValues.put("nim", edtNim.getText().toString());
            }

//            contentValues.put("nim", edtNim.getText().toString());
            contentValues.put("nama_alumni", edtNamaAlumni.getText().toString());
            contentValues.put("tempat_lahir", edtTempatLahir.getText().toString());
            contentValues.put("tanggal_lahir", edtTanggalLahir.getText().toString());
            contentValues.put("alamat", edtAlamat.getText().toString());
            contentValues.put("agama", edtAgama.getText().toString());
            contentValues.put("tlp", edtTlp.getText().toString());
            contentValues.put("tahun_masuk", edtTahunMasuk.getText().toString());
            contentValues.put("tahun_lulus", edtTahunLulus.getText().toString());
            contentValues.put("pekerjaan", edtPekerjaan.getText().toString());
            contentValues.put("jabatan", edtJabatan.getText().toString());

            if (extras == null) {

                long insert = database.insert("tb_alumni", null, contentValues);
                if (insert != -1) {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }else{
                long update = database.update("tb_alumni", contentValues, "nim=?", new String[]{""+edtNim.getText().toString()});
                if (update != -1) {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
            }
        }
            database.close();
    }
    }

    void hapus(){
        database = new DatabaseAlumni(this).getWritableDatabase();
        long hapus = database.delete("tb_alumni", "nim=?", new String[]{""+edtNim.getText().toString()});
        if (hapus != -1) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
