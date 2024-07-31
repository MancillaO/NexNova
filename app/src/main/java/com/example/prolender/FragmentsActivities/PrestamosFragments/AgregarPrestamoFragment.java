package com.example.prolender.FragmentsActivities.PrestamosFragments;
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
import com.example.prolender.FragmentsActivities.ClientesFragments.ClientesFragment;
import com.example.prolender.R;

import java.util.Calendar;

public class AgregarPrestamoFragment extends Fragment {

    EditText campoMonto, campoPlazo, campoMetodo;
    private EditText campoFechaC;
    private EditText campoFechaD;
    private ImageView selectDateButtonD;
    private ImageView selectDateButtonC;

    public AgregarPrestamoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_prestamo, container, false);

        campoMonto = view.findViewById(R.id.campoMonto);
        campoPlazo = view.findViewById(R.id.campoPlazo);
        campoMetodo = view.findViewById(R.id.campoMetodo);
        campoFechaC = view.findViewById(R.id.campoFechaC);
        campoFechaD = view.findViewById(R.id.campoFechaD);

        Button btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String montoStr = campoMonto.getText().toString().trim();
                String plazoStr = campoPlazo.getText().toString().trim();
                String metodoStr = campoMetodo.getText().toString().trim();
                String fechaCStr = campoFechaC.getText().toString().trim();
                String fechaDStr = campoFechaD.getText().toString().trim();

                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());

                myDB.addPrestamo(montoStr, fechaCStr, plazoStr, metodoStr, fechaDStr);

                Fragment prestamosFragment = new PrestamosFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, prestamosFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        campoFechaC = view.findViewById(R.id.campoFechaC);
        selectDateButtonC = view.findViewById(R.id.selectDateButtonC);
        campoFechaD = view.findViewById(R.id.campoFechaD);
        selectDateButtonD = view.findViewById(R.id.selectDateButtonD);

        selectDateButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogC();
            }
        });

        selectDateButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogD();
            }
        });
        return view;
    }

    private void showDatePickerDialogC() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update the EditText with the selected date
                campoFechaC.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showDatePickerDialogD() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update the EditText with the selected date
                campoFechaD.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

}