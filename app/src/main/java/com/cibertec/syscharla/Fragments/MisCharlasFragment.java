package com.cibertec.syscharla.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.cibertec.syscharla.Adapters.MisCharlasAdapter;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.util.ArrayList;

public class MisCharlasFragment extends Fragment {

    private RecyclerView rvMisCharlas;
    private RecyclerView.Adapter mcAdapter;
    private RecyclerView.LayoutManager mcLayoutManager;
    private ArrayList<Charla> charlas;

    public MisCharlasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mis_charlas, container, false);

        charlas = new ArrayList<>();

        charlas.add(new Charla(1, "Prueba1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(2, "Prueba2", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(3, "Prueba3", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(4, "Prueba4", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(5, "Prueba5", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(6, "Prueba6", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(7, "Prueba7", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(8, "Prueba8", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(9, "Prueba9", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(10, "Prueba10", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(11, "Prueba11", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(12, "Prueba12", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));
        charlas.add(new Charla(13, "Prueba13", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."));

        rvMisCharlas = view.findViewById(R.id.rvMisCharlas);
        mcLayoutManager= new LinearLayoutManager(getActivity());
        mcAdapter = new MisCharlasAdapter(charlas);
        rvMisCharlas.setHasFixedSize(true);
        rvMisCharlas.setLayoutManager(mcLayoutManager);
        rvMisCharlas.setAdapter(mcAdapter);

        return view;
    }

}
