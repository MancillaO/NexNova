package com.example.prolender.FragmentsActivities.PrestamosFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

public class VerSolicitudFragment extends Fragment {

    private MyDatabaseHelper myDatabaseHelper;
    private EditText editextBuscar;
    private ImageView buscarClienteIcon;
    private TextView textViewOcupacion, textViewFecha, textViewMonto, textViewIngreso;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_solicitud, container, false);

        Button btnRegistrar = view.findViewById(R.id.btnAprovar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment agregarPrestamoFragment = new AgregarPrestamoFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, agregarPrestamoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        // Inicializar MyDatabaseHelper
        myDatabaseHelper = new MyDatabaseHelper(getContext());

        // Inicializar vistas
        editextBuscar = view.findViewById(R.id.editextBuscar);
        buscarClienteIcon = view.findViewById(R.id.buscar_cliente_icon);
        textViewOcupacion = view.findViewById(R.id.campoOcupacion);
        textViewFecha = view.findViewById(R.id.campoFecha);
        textViewMonto = view.findViewById(R.id.campoMontoS);
        textViewIngreso = view.findViewById(R.id.campoIngreso);

        // Configurar el click listener para el ImageView
        buscarClienteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarSolicitud();
            }
        });

        return view;
    }

    private void buscarSolicitud() {
        String solicitudId = editextBuscar.getText().toString().trim();
        if (!solicitudId.isEmpty()) {
            Cursor cursor = myDatabaseHelper.getSolicitudById(solicitudId);
            if (cursor != null && cursor.moveToFirst()) {
                // Obtener datos de la solicitud
                String ocupacion = cursor.getString(cursor.getColumnIndex("ocupacion"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha_solicitud"));
                String monto = cursor.getString(cursor.getColumnIndex("monto"));
                String ingresos = cursor.getString(cursor.getColumnIndex("ingreso"));

                // Mostrar datos en los TextView
                textViewOcupacion.setText(ocupacion);
                textViewFecha.setText(fecha);
                textViewMonto.setText(monto);
                textViewIngreso.setText(ingresos);

                cursor.close();
            } else {
                Toast.makeText(getContext(), "Solicitud no encontrada", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Por favor ingrese un ID de solicitud", Toast.LENGTH_SHORT).show();
        }
    }
}
