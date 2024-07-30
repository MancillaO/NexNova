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

    /************************ DATOS DE LA BASE DE DATOS *************************************/

    private Context context;
    private static final String DATABASE_NAME = "ProLender";
    private static final int DATABASE_VERSION = 1;

    /****************************************************************************************/

    /*********************** ATRIBTOS Y NOMBRE DE LAS TABLAS *********************************/

    private static final String TABLE_CLIENTE = "clientes";
    private static final String CAMPO_ID = "id_cliente";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_APAT = "apat";
    private static final String CAMPO_AMAT = "amat";
    private static final String CAMPO_FECHANAC = "fechanac";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_TEL = "tel";
    private static final String CAMPO_RFC = "rfc";
    private static final String CAMPO_IMAGEN = "imagen"; // Nuevo campo para la imagen

    private static final String TABLE_SOLICITUD = "solicitud";
    private static final String CAMPO_ID_SOLICITUD = "id_solicitud";
    private static final String CAMPO_OCUPACION = "ocupacion";
    private static final String CAMPO_FECHA_SOLICITUD = "fecha_solicitud";
    private static final String CAMPO_MONTO = "monto";
    private static final String CAMPO_INGRESOS = "ingreso";

    /****************************************************************************************/

    // Constructor de la clase
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Método para crear las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearCliente = "CREATE TABLE " + TABLE_CLIENTE + "("
                + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAMPO_NOMBRE + " TEXT, "
                + CAMPO_APAT + " TEXT, "
                + CAMPO_AMAT + " TEXT, "
                + CAMPO_FECHANAC + " TEXT, "
                + CAMPO_EMAIL + " TEXT, "
                + CAMPO_TEL + " TEXT, "
                + CAMPO_RFC + " TEXT, "
                + CAMPO_IMAGEN + " BLOB);"; // Añadir el campo de imagen como BLOB
        db.execSQL(crearCliente);

        Log.d("MyDatabaseHelper", "Table created: " + crearCliente);

        String crearSolicitud = "CREATE TABLE " + TABLE_SOLICITUD + "("
                + CAMPO_ID_SOLICITUD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAMPO_OCUPACION + " TEXT, "
                + CAMPO_FECHA_SOLICITUD + " TEXT, "
                + CAMPO_MONTO + " TEXT, "
                + CAMPO_INGRESOS + " TEXT);";
        db.execSQL(crearSolicitud);

        Log.d("MyDatabaseHelper", "Table created: " + crearCliente);
    }

    // Actualizar la base de datos, eliminar tabla si ya existe y crear de nuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLICITUD);
        onCreate(db);
    }

    /****************** Métodos para manipular la tabla Cliente *********************************/

    public void addCliente(String nombre, String apat, String amat, String fecha, String email, String tel, String rfc, byte[] imagen) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(CAMPO_NOMBRE, nombre);
        cv.put(CAMPO_APAT, apat);
        cv.put(CAMPO_AMAT, amat);
        cv.put(CAMPO_FECHANAC, fecha);
        cv.put(CAMPO_EMAIL, email);
        cv.put(CAMPO_TEL, tel);
        cv.put(CAMPO_RFC, rfc);
        cv.put(CAMPO_IMAGEN, imagen); // Añadir la imagen

        // Inserta los valores en la tabla
        long result = db.insert(TABLE_CLIENTE, null, cv);
        Log.d("MyDatabaseHelper", "Insert result: " + result);

        // Muestra un mensaje si la inserción fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Registro Fallido ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
    }

    public void addSolicitud(String id_cliente, String ocupacion, String fecha, String monto, String ingresos) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(CAMPO_ID_SOLICITUD, id_cliente);
        cv.put(CAMPO_OCUPACION, ocupacion);
        cv.put(CAMPO_FECHA_SOLICITUD, fecha);
        cv.put(CAMPO_MONTO, monto);
        cv.put(CAMPO_INGRESOS, ingresos);

        // Inserta los valores en la tabla
        long result = db.insert(TABLE_SOLICITUD, null, cv);
        Log.d("MyDatabaseHelper", "Insert result: " + result);

        // Muestra un mensaje si la inserción fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Registro Fallido ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para leer todos los datos de la base de datos
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_CLIENTE;  // Consulta SQL para seleccionar todos los datos
        SQLiteDatabase db = this.getReadableDatabase();   // Obtiene la base de datos en modo lectura

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);  // Ejecuta la consulta SQL
        }
        return cursor; // Devuelve el cursor con los datos
    }

    // Método para actualizar un registro de la base de datos
    public void UpdateData(String row_id, String nombre, String apat, String amat, String fecha, String email, String tel, String rfc, byte[] imagen) {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues(); // Contenedor para los valores

        // Pone los valores en el contenedor
        cv.put(CAMPO_NOMBRE, nombre);
        cv.put(CAMPO_APAT, apat);
        cv.put(CAMPO_AMAT, amat);
        cv.put(CAMPO_FECHANAC, fecha);
        cv.put(CAMPO_EMAIL, email);
        cv.put(CAMPO_TEL, tel);
        cv.put(CAMPO_RFC, rfc);
        cv.put(CAMPO_IMAGEN, imagen); // Añadir la imagen

        // Actualiza el registro en la base de datos y obtiene el resultado
        long result = db.update(TABLE_CLIENTE, cv, "id_cliente=?", new String[]{row_id});

        // Muestra un mensaje si la actualización fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para eliminar un registro de la base de datos
    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtiene la base de datos en modo escritura

        // Elimina el registro y obtiene el resultado
        long result = db.delete(TABLE_CLIENTE, "id_cliente=?", new String[]{row_id});

        // Muestra un mensaje si la eliminación fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para eliminar todos los registros de la base de datos
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtiene la base de datos en modo escritura
        db.execSQL("DELETE FROM " + TABLE_CLIENTE);  // Ejecuta una consulta SQL para eliminar todos los registros
    }

    /********************************************************************************************/


}
