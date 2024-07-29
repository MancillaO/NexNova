package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.R;

public class AgregarDireccionFragment extends Fragment {
    EditText calle, numInt, numExt, colonia, estado, cp, tpPropi;

    public AgregarDireccionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_direccion, container, false);

        calle = view.findViewById(R.id.campoCalle);
        numInt = view.findViewById(R.id.campoNumInt);
        numExt = view.findViewById(R.id.campoNumExt);
        colonia = view.findViewById(R.id.campoColonia);
        estado = view.findViewById(R.id.campoEstado);
        cp = view.findViewById(R.id.campoCP);
        tpPropi = view.findViewById(R.id.campoTpPropiedad);

        Button btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Fragment clientesFragment = new ClientesFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, clientesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
