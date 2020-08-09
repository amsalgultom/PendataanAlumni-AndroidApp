package pnj.remedial.bangkitamsal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAlumni extends SQLiteOpenHelper {


    public DatabaseAlumni(@Nullable Context context) {
        super(context, "db_alumni", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_alumni (nim TEXT PRIMARY KEY, nama_alumni TEXT, " +
                "tempat_lahir TEXT, tanggal_lahir TEXT, alamat TEXT, agama TEXT, " +
                "tlp TEXT, tahun_masuk TEXT, tahun_lulus TEXT, pekerjaan TEXT, jabatan TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
