package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prolender.Database.CustomAdapter;
import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

import java.util.ArrayList;

public class ClientesFragment extends Fragment {

    private LinearLayout btnAgregar;
    private LinearLayout btnAgregarS;
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> id, nombre, apat, amat, fecha, email, tel, rfc, image;
    CustomAdapter customAdapter;

    public ClientesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        btnAgregar = view.findViewById(R.id.add_cliente);
        btnAgregar.setOnClickListener(v -> openAgregarClienteFragment());
        btnAgregarS = view.findViewById(R.id.add_solicitud);
        btnAgregarS.setOnClickListener(v -> openAgregarSolicitudFragment());

        myDB = new MyDatabaseHelper(getActivity());

        id = new ArrayList<>();
        nombre = new ArrayList<>();
        apat = new ArrayList<>();
        amat = new ArrayList<>();
        fecha = new ArrayList<>();
        email = new ArrayList<>();
        tel = new ArrayList<>();
        rfc = new ArrayList<>();
        image = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), id, nombre, apat, amat, fecha, email, tel, rfc, image);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            // Handle no data case
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                nombre.add(cursor.getString(1));
                apat.add(cursor.getString(2));
                amat.add(cursor.getString(3));
                fecha.add(cursor.getString(4));
                email.add(cursor.getString(5));
                tel.add(cursor.getString(6));
                rfc.add(cursor.getString(7));
                image.add(cursor.getString(8));
            }
        }
    }

    private void openAgregarClienteFragment() {
        Fragment agregarClienteFragment = new AgregarClientesFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, agregarClienteFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openAgregarSolicitudFragment() {
        Fragment agregarSolicitudFragment = new AgregarSolicitudFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, agregarSolicitudFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
