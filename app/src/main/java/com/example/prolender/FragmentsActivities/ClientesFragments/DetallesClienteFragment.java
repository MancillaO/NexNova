package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

public class DetallesClienteFragment extends Fragment {

    private static final String ARG_CLIENT_ID = "clientId";

    private String clientId;

    private TextView idTextView;
    private TextView nombreTextView;
    private TextView apatTextView;
    private TextView amatTextView;
    private TextView fechaTextView;
    private TextView emailTextView;
    private TextView telTextView;
    private TextView rfcTextView;

    public DetallesClienteFragment() {
        // Required empty public constructor
    }

    public static DetallesClienteFragment newInstance(String clientId) {
        DetallesClienteFragment fragment = new DetallesClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clientId = getArguments().getString(ARG_CLIENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_cliente, container, false);

        idTextView = view.findViewById(R.id.campoID);
        nombreTextView = view.findViewById(R.id.campoNombre);
        apatTextView = view.findViewById(R.id.campoAppat);
        amatTextView = view.findViewById(R.id.campoAmat);
        fechaTextView = view.findViewById(R.id.campoFecha);
        emailTextView = view.findViewById(R.id.campoCorreo);
        telTextView = view.findViewById(R.id.campoNumero);
        rfcTextView = view.findViewById(R.id.campoRFC);

        if (clientId != null) {
            loadClientDetails(clientId);
        }

        return view;
    }

    private void loadClientDetails(String clientId) {
        MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
        Cursor cursor = myDB.getClientById(clientId);

        if (cursor.moveToFirst()) {
            idTextView.setText(cursor.getString(0)); // ID
            nombreTextView.setText(cursor.getString(1)); // Nombre
            apatTextView.setText(cursor.getString(2)); // Apellido Paterno
            amatTextView.setText(cursor.getString(3)); // Apellido Materno
            fechaTextView.setText(cursor.getString(4)); // Fecha
            emailTextView.setText(cursor.getString(5)); // Email
            telTextView.setText(cursor.getString(6)); // Teléfono
            rfcTextView.setText(cursor.getString(7)); // RFC
        }

        cursor.close();
    }
}

