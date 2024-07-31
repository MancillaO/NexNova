package com.example.prolender.FragmentsActivities.PrestamosFragments;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

import java.util.Calendar;

public class DetallesPrestamoFragment extends Fragment {

    private static final String ARG_PRESTAMO_ID = "prestamoId";

    private String prestamoId;

    private EditText campoFechaC;
    private ImageView selectDateButtonC;
    private EditText campoFechaD;
    private ImageView selectDateButtonD;
    private EditText campoID;
    private EditText campoMonto;
    private EditText campoPlazo;
    private EditText campoMetodo;
    private EditText campoIdCliente;

    public DetallesPrestamoFragment() {
        // Required empty public constructor
    }

    public static DetallesPrestamoFragment newInstance(String prestamoId) {
        DetallesPrestamoFragment fragment = new DetallesPrestamoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRESTAMO_ID, prestamoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prestamoId = getArguments().getString(ARG_PRESTAMO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_prestamo, container, false);

        campoFechaC = view.findViewById(R.id.campoFechaC);
        selectDateButtonC = view.findViewById(R.id.selectDateButtonC);
        campoFechaD = view.findViewById(R.id.campoFechaD);
        selectDateButtonD = view.findViewById(R.id.selectDateButtonD);
        campoID = view.findViewById(R.id.campoID);
        campoMonto = view.findViewById(R.id.campoMonto);
        campoPlazo = view.findViewById(R.id.campoPlazo);
        campoMetodo = view.findViewById(R.id.campoMetodo);
        campoIdCliente = view.findViewById(R.id.campoIDCliente);

        if (prestamoId != null) {
            loadPrestamoDetails(prestamoId);
        }

        selectDateButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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

    private void loadPrestamoDetails(String prestamoId) {
        MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
        Cursor cursor = null;

        try {
            cursor = myDB.getPrestamoById(prestamoId);

            if (cursor != null && cursor.moveToFirst()) {
                campoID.setText(cursor.getString(0));
                campoMonto.setText(cursor.getString(1));
                campoFechaC.setText(cursor.getString(2));
                campoPlazo.setText(cursor.getString(3));
                campoMetodo.setText(cursor.getString(4));
                campoFechaD.setText(cursor.getString(5));
                campoIdCliente.setText(cursor.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            myDB.close();
        }
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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
                campoFechaD.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
