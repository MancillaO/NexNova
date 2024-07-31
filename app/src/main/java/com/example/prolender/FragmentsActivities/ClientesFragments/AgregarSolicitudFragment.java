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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

import java.util.Calendar;

public class AgregarSolicitudFragment extends Fragment {

    private EditText campoFecha;
    private ImageView selectDateButton;
    EditText ocupacion, montoSoli, ingreso, id_cliente;

    public AgregarSolicitudFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_solicitud, container, false);

        id_cliente = view.findViewById(R.id.campoID_cliente);
        ocupacion = view.findViewById(R.id.campoOcupacionSoli);
        montoSoli = view.findViewById(R.id.campoMonto);
        ingreso = view.findViewById(R.id.campoIngreso);

        Button btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos de los campos
                String idClienteStr = id_cliente.getText().toString().trim();
                String ocupacionStr = ocupacion.getText().toString().trim();
                String fechaStr = campoFecha.getText().toString().trim();
                String montoSoliStr = montoSoli.getText().toString().trim();
                String ingresoStr = ingreso.getText().toString().trim();

                // Valida los campos vacíos
                if (idClienteStr.isEmpty() || ocupacionStr.isEmpty() || fechaStr.isEmpty() ||
                        montoSoliStr.isEmpty() || ingresoStr.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que id_cliente solo contenga números
                if (!idClienteStr.matches("\\d+")) {
                    Toast.makeText(getContext(), "El campo ID Cliente solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que ocupación solo contenga letras
                if (!ocupacionStr.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    Toast.makeText(getContext(), "El campo Ocupación solo debe contener letras", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que monto solicitado solo contenga números
                if (!montoSoliStr.matches("\\d+")) {
                    Toast.makeText(getContext(), "El campo Monto Solicitado solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que ingreso mensual solo contenga números
                if (!ingresoStr.matches("\\d+")) {
                    Toast.makeText(getContext(), "El campo Ingreso Mensual solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crea una instancia de MyDatabaseHelper
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());

                // Inserta los datos de la solicitud para agregarlos a la base de datos
                myDB.addSolicitud(
                        idClienteStr,
                        ocupacionStr,
                        fechaStr,
                        montoSoliStr,
                        ingresoStr);

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
                // Actualiza el EditText con la fecha seleccionada
                campoFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
