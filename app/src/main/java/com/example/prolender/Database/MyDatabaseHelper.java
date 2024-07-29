package com.example.prolender.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ProLender.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_NAME = "Clientes";
    private static final String CAMPO_ID = "id_cliente";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_APAT = "apat";
    private static final String CAMPO_AMAT = "amat";
    private static final String CAMPO_FECHANAC = "fechanac";
    private static final String CAMPO_EMAIL = "emal";
    private static final String CAMPO_TEL = "tel";
    private static final String CAMPO_RFC = "rfc";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAMPO_NOMBRE + "TEXT, " +
                CAMPO_APAT + "TEXT, " +
                CAMPO_AMAT + "TEXT, " +
                CAMPO_FECHANAC + "TEXT, " +
                CAMPO_EMAIL + "TEXT, " +
                CAMPO_TEL + "TEXT, " +
                CAMPO_RFC + "TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addCliente (String nombre, String apat, String amat, String fecha, String email, String tel, String rfc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, nombre);
        cv.put(CAMPO_APAT, apat);
        cv.put(CAMPO_AMAT, amat);
        cv.put(CAMPO_FECHANAC, fecha);
        cv.put(CAMPO_EMAIL, email);
        cv.put(CAMPO_TEL, tel);
        cv.put(CAMPO_RFC, rfc);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Registro Fallido", Toast.LENGTH_SHORT).show();
            }else {
            Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }

        Cursor readAllData(){
            String query = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if(db != null){
                cursor = db.rawQuery(query, null);
            }
            return cursor;
        }

        void UpdateData(String row_id, String nombre, String apat, String amat, String fecha, String email, String tel, String rfc) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(CAMPO_NOMBRE, nombre);
            cv.put(CAMPO_APAT, apat);
            cv.put(CAMPO_AMAT, amat);
            cv.put(CAMPO_FECHANAC, fecha);
            cv.put(CAMPO_EMAIL, email);
            cv.put(CAMPO_TEL, tel);
            cv.put(CAMPO_RFC, rfc);

            long result = db.update(TABLE_NAME, cv, "id_cliente=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteOneRow(String row_id){
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME, "id_cliente=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}


