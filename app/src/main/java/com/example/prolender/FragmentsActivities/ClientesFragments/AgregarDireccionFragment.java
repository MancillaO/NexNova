package com.example.prolender.FragmentsActivities.ClientesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prolender.Database.MyDatabaseHelper;
import com.example.prolender.R;

public class AgregarDireccionFragment extends Fragment {
    EditText calle, numInt, numExt, colonia, estado, cp, tpPropi;

    public AgregarDireccionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                // Obtiene los datos de los campos
                String calleStr = calle.getText().toString().trim();
                String numIntStr = numInt.getText().toString().trim();
                String numExtStr = numExt.getText().toString().trim();
                String coloniaStr = colonia.getText().toString().trim();
                String estadoStr = estado.getText().toString().trim();
                String cpStr = cp.getText().toString().trim();
                String tpPropiStr = tpPropi.getText().toString().trim();

                // Valida los campos vacíos
                if (calleStr.isEmpty() || numIntStr.isEmpty() || numExtStr.isEmpty() ||
                        coloniaStr.isEmpty() || estadoStr.isEmpty() || cpStr.isEmpty() || tpPropiStr.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que numInt y numExt solo contengan números
                if (!numIntStr.matches("\\d+")) {
                    Toast.makeText(getContext(), "El campo Número Interior solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!numExtStr.matches("\\d+")) {
                    Toast.makeText(getContext(), "El campo Número Exterior solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que estado solo contenga letras
                if (!estadoStr.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    Toast.makeText(getContext(), "El campo Estado solo debe contener letras", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valida que tpPropi solo pueda ser "propia" o "renta"
                if (!tpPropiStr.equalsIgnoreCase("propia") && !tpPropiStr.equalsIgnoreCase("alquilada")) {
                    Toast.makeText(getContext(), "El campo Tipo de Propiedad solo puede ser 'propia' o 'renta'", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crea una instancia de MyDatabaseHelper
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());

                // Inserta los datos de la dirección para agregarlos a la base de datos
                myDB.addDireccion(
                        calleStr,
                        numIntStr,
                        numExtStr,
                        coloniaStr,
                        estadoStr,
                        cpStr,
                        tpPropiStr);

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
