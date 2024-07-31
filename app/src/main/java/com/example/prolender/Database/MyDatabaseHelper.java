package com.example.prolender.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.prolender.R;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private String id_clienteDireccion;

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

    // Tabla de direcciones
    public static String TABLA_DIRECCION = "direccion";
    public static String ID_DIRECCION = "id_direccion";
    public static String ID_CLIENTE = "id_cliente";
    public static String CALLE = "calle";
    public static String NUM_INT = "numInt";
    public static String NUM_EXT = "numExt";
    public static String COLONIA = "colonia";
    public static String ESTADO = "estado";
    public static String CODIGO_POSTAL = "codigoPostal";
    public static String TIPO_PROPIEDAD = "tpPropiedad";


    // Tabla de solicitudes
    private static final String TABLE_SOLICITUD = "solicitud";
    private static final String CAMPO_ID_SOLICITUD = "id_solicitud";
    private static final String CAMPO_ID_CLIENTE = "id_cliente";
    private static final String CAMPO_OCUPACION = "ocupacion";
    private static final String CAMPO_FECHA_SOLICITUD = "fecha_solicitud";
    private static final String CAMPO_MONTO = "monto";
    private static final String CAMPO_INGRESOS = "ingreso";

    // Tabla de prestamos
    public static String TABLA_PRESTAMO = "prestamo";
    public static String ID_PRESTAMO = "id_prestamo";
    public static String ID_SOLICITUD = "id_solicitud";
    public static String MONTO_PRESTAMO = "montoPrestamo";
    public static String FECHA_CORTR = "fechaCortr";
    public static String PLAZO_PAGO = "plazoPago";
    public static String METODO_PAGO = "metodoPago";
    public static String FECHA_DEPOSITO = "fechaDeposito";

    // Tabla de Pagos
    public static String TABLA_PAGO = "pago";
    public static String ID_PAGO = "id_pago";
    public static String ID_PRESTAMO_PAGO = "id_prestamoPago";
    public static String NUM_PAGO = "numPago";
    public static String FECHA_PAGO = "fechaPago";
    public static String MONTO_PAGO = "montoPago";
    public static String NUEVO_SALDO = "nuevoSaldo";
    public static String MONTO_PAGADO = "montoPagado";


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
                + CAMPO_ID + " INTEGER PRIMARY KEY, "
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

        String crearDireccion = "CREATE TABLE " + TABLA_DIRECCION + "("
                + ID_DIRECCION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_CLIENTE + " INTEGER, "
                + CALLE + " TEXT, "
                + NUM_INT + " TEXT, "
                + NUM_EXT + " TEXT, "
                + COLONIA + " TEXT, "
                + ESTADO + " TEXT, "
                + CODIGO_POSTAL + " TEXT, "
                + TIPO_PROPIEDAD + " TEXT);";

        db.execSQL(crearDireccion);
        Log.d("MyDatabaseHelper", "Table created: " + crearDireccion);


        String crearSolicitud = "CREATE TABLE " + TABLE_SOLICITUD + "("
                + CAMPO_ID_SOLICITUD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAMPO_ID_CLIENTE + " INTEGER, "
                + CAMPO_OCUPACION + " TEXT, "
                + CAMPO_FECHA_SOLICITUD + " TEXT, "
                + CAMPO_MONTO + " TEXT, "
                + CAMPO_INGRESOS + " TEXT);";
        db.execSQL(crearSolicitud);

        Log.d("MyDatabaseHelper", "Table created: " + crearCliente);

        String crearPrestamo = "CREATE TABLE " + TABLA_PRESTAMO + "("
                + ID_PRESTAMO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_SOLICITUD + " INTEGER, "
                + MONTO_PRESTAMO + " TEXT, "
                + FECHA_CORTR + " TEXT, "
                + PLAZO_PAGO + " TEXT, "
                + METODO_PAGO + " TEXT, "
                + FECHA_DEPOSITO + " TEXT);";
        db.execSQL(crearPrestamo);

        Log.d("MyDatabaseHelper", "Table created: " + crearPrestamo);


    }

    // Actualizar la base de datos, eliminar tabla si ya existe y crear de nuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_DIRECCION);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLICITUD);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PRESTAMO);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PAGO);
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
        /*
        if (result == -1) {
            Toast.makeText(context, "Registro Fallido ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }

         */
    }

    public void addDireccion(String calle, String numInt, String numExt, String colonia, String estado, String codigoPostal, String tpPropiedad) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(ID_CLIENTE, id_clienteDireccion);
        cv.put(CALLE, calle);
        cv.put(NUM_INT, numInt);
        cv.put(NUM_EXT, numExt);
        cv.put(COLONIA, colonia);
        cv.put(ESTADO, estado);
        cv.put(CODIGO_POSTAL, codigoPostal);
        cv.put(TIPO_PROPIEDAD, tpPropiedad);

        // Inserta los valores en la tabla
        long result = db.insert(TABLA_DIRECCION, null, cv);
        Log.d("MyDatabaseHelper", "Insert result: " + result);

        id_clienteDireccion = String.valueOf(result);

        // Muestra un mensaje si la inserción fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Registro Fallido ", Toast.LENGTH_SHORT).show();
        } else {
            alertAprobado();
            //Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }

    }

    public void addSolicitud(String id_cliente ,String ocupacion, String fecha, String monto, String ingresos) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(CAMPO_ID_CLIENTE, id_cliente);
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

    public void addPrestamo(String id_prestamo, String id_solicitud, String montoPrestamo, String fechaCortr, String plazoPago, String metodoPago, String fechaDeposito) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(ID_PRESTAMO, id_prestamo);
        cv.put(ID_SOLICITUD, id_solicitud);
        cv.put(MONTO_PRESTAMO, montoPrestamo);
        cv.put(FECHA_CORTR, fechaCortr);

        // Inserta los valores en la tabla
        long result = db.insert(TABLA_PRESTAMO, null, cv);
        Log.d("MyDatabaseHelper", "Insert result: " + result);

        // Muestra un mensaje si la inserción fue exitosa o fallida
        if (result == -1) {
            Toast.makeText(context, "Registro Fallido ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPago(String id_pago, String id_prestamoPago, String numPago, String fechaPago, String montoPago, String nuevoSaldo, String montoPagado) {
        SQLiteDatabase db = this.getWritableDatabase();  // Obtiene la base de datos en modo escritura
        ContentValues cv = new ContentValues();  // Contenedor para los valores

        // Asigna los valores a los campos de la tabla
        cv.put(ID_PAGO, id_pago);
        cv.put(ID_PRESTAMO_PAGO, id_prestamoPago);
        cv.put(NUM_PAGO, numPago);
        cv.put(FECHA_PAGO, fechaPago);
        cv.put(MONTO_PAGO, montoPago);
        cv.put(NUEVO_SALDO, nuevoSaldo);
        cv.put(MONTO_PAGADO, montoPagado);

        // Inserta los valores en la tabla
        long result = db.insert(TABLA_PAGO, null, cv);
        Log.d("MyDatabaseHelper", "Insert result: " + result);

        // Muestra un mensaje si la inserción fue exitosa
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

    // MyDatabaseHelper.java
    public Cursor getSolicitudById(String solicitudId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT ocupacion, fecha_solicitud, monto, ingreso FROM " + TABLE_SOLICITUD + " WHERE id_solicitud = ?";
        return db.rawQuery(query, new String[]{solicitudId});
    }

    public Cursor getClientById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CLIENTE + " WHERE " + CAMPO_ID + " = ?", new String[]{id});
    }



    /************************ Funcion para hacer un alert dialog  ***************************/

    public void alertAprobado() {
        // Verifica si el contexto no es nulo y es una instancia de una Actividad
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;

            // Infla la vista desde el layout XML
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.aprovado_dialog, null);

            // Encuentra el botón en la vista inflada
            Button aprovadoDone = view.findViewById(R.id.aprovadoDone);

            // Crea y configura el AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view);
            AlertDialog dialog = builder.create();

            // Configura el botón para cerrar el diálogo y mostrar un Toast
            aprovadoDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    //Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show();
                }
            });

            // Establece un fondo transparente si es necesario
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            // Muestra el diálogo
            dialog.show();
        }
    }

    /****************************************************************************************/

}
