package com.example.prolender.FragmentsActivities.ConfiguracionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.prolender.R;

public class ConfigFragment extends Fragment implements View.OnClickListener {

    private LinearLayout btnCerrar;

    public ConfigFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        btnCerrar = view.findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnCerrar) {
            getActivity().finishAffinity();
        }
    }
}
