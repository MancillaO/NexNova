package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

public class AgregarClientesFragment extends Fragment {

    EditText nombre, apat, amat, fechaNac, email, tel, rfc;
    private EditText campoFecha;
    private ImageView selectDateButton, selectImageButton;
    private Bitmap selectedImageBitmap;
    private static final int PICK_IMAGE_REQUEST = 1;

    public AgregarClientesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_clientes, container, false);

        nombre = view.findViewById(R.id.campoNombre);
        apat = view.findViewById(R.id.campoAppat);
        amat = view.findViewById(R.id.campoAmat);
        fechaNac = view.findViewById(R.id.campoFecha);
        email = view.findViewById(R.id.campoCorreo);
        tel = view.findViewById(R.id.campoNumero);
        rfc = view.findViewById(R.id.campoRFC);
        campoFecha = view.findViewById(R.id.campoFecha);
        selectDateButton = view.findViewById(R.id.selectDateButton);

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button btnContinuar = view.findViewById(R.id.btnContinuarCliente);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos de los campos
                String nombreStr = nombre.getText().toString().trim();
                String apatStr = apat.getText().toString().trim();
                String amatStr = amat.getText().toString().trim();
                String fechaNacStr = fechaNac.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String telStr = tel.getText().toString().trim();
                String rfcStr = rfc.getText().toString().trim();

                // Valida los campos vacíos
                if (nombreStr.isEmpty() || apatStr.isEmpty() || amatStr.isEmpty() ||
                        fechaNacStr.isEmpty() || emailStr.isEmpty() || telStr.isEmpty() || rfcStr.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que los campos de nombre no contengan números
                if (!nombreStr.matches("[a-zA-Z]+") || !apatStr.matches("[a-zA-Z]+") || !amatStr.matches("[a-zA-Z]+")) {
                    Toast.makeText(getContext(), "Nombre, Apellido Paterno y Apellido Materno no deben contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que el número de teléfono tenga exactamente 10 dígitos
                if (!telStr.matches("\\d{10}")) {
                    Toast.makeText(getContext(), "El número de teléfono debe tener 10 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crea una instancia de MyDatabaseHelper
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
                // Convierte la imagen seleccionada a un array de bytes
                byte[] imagenBytes = convertImageToBytes(selectedImageBitmap);

                // Inserta los datos del cliente en la base de datos
                myDB.addCliente(
                        nombreStr,
                        apatStr,
                        amatStr,
                        fechaNacStr,
                        emailStr,
                        telStr,
                        rfcStr,
                        imagenBytes);  // Pasa la imagen en formato de bytes

                // Navega al fragmento de agregar dirección
                Fragment agregarDireccionFragment = new AgregarDireccionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, agregarDireccionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                campoFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                selectedImageBitmap = BitmapFactory.decodeStream(inputStream);
                selectImageButton.setImageBitmap(selectedImageBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] convertImageToBytes(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }
}
