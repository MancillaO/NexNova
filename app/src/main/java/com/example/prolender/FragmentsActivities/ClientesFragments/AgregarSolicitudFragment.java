package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

import java.util.Calendar;

public class AgregarSolicitudFragment extends Fragment {

    private EditText campoFecha;
    private ImageView selectDateButton;
    EditText ocupacion, montoSoli, ingreso, id_solicitud, id_cliente;

    public AgregarSolicitudFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_solicitud, container, false);

        id_solicitud = view.findViewById(R.id.campoID);
        id_cliente = view.findViewById(R.id.campoID_cliente);
        ocupacion = view.findViewById(R.id.campoOcupacionSoli);
        montoSoli = view.findViewById(R.id.campoMonto);
        ingreso = view.findViewById(R.id.campoIngreso);

        Button btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
                // Convierte la imagen seleccionada a un array de bytes

                // Inserta los datos del cliente para agregarlos a la base de datos
                myDB.addSolicitud(
                        id_solicitud.getText().toString().trim(),
                        id_cliente.getText().toString().trim(),
                        ocupacion.getText().toString().trim(),
                        campoFecha.getText().toString().trim(),
                        montoSoli.getText().toString().trim(),
                        ingreso.getText().toString().trim());


                Fragment clientesFragment = new ClientesFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, clientesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        campoFecha = view.findViewById(R.id.campoFecha);
        selectDateButton = view.findViewById(R.id.selectDateButton);

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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
                // Update the EditText with the selected date
                campoFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
