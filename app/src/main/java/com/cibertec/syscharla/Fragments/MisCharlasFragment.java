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

        charlas = new ArrayList<Charla>();

        charlas.add(new Charla("Prueba1"));
        charlas.add(new Charla("Prueba2"));

        rvMisCharlas = view.findViewById(R.id.rvMisCharlas);
        mcLayoutManager= new LinearLayoutManager(getActivity());
        mcAdapter = new MisCharlasAdapter(charlas, R.layout.fragment_mis_charlas, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        rvMisCharlas.setHasFixedSize(true);
        rvMisCharlas.setLayoutManager(mcLayoutManager);
        rvMisCharlas.setAdapter(mcAdapter);

        return view;
    }

}
