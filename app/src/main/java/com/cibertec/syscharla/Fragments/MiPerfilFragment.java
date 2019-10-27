package com.cibertec.syscharla.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cibertec.syscharla.PrivacidadActivity;
import com.cibertec.syscharla.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiPerfilFragment extends Fragment implements View.OnClickListener{

    Button btnConfigurar;

    public MiPerfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mi_perfil, container, false);

        btnConfigurar = (Button)rootView.findViewById(R.id.btnConfigurarMP);
        btnConfigurar.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {

        if(v == btnConfigurar){
            Intent intent = new Intent(getActivity(), PrivacidadActivity.class);
            startActivity(intent);
         //   Toast.makeText(getActivity(),"entro",Toast.LENGTH_SHORT).show();

        }
    }
}
