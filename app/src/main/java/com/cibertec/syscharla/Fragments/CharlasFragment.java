package com.cibertec.syscharla.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cibertec.syscharla.Adapters.CharlaAdapter;
import com.cibertec.syscharla.CharlaDetalleActivity;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharlasFragment extends Fragment {
    private RecyclerView rvListaCharlas;
    private List<Charla> listaCharlas;
    private CharlaAdapter adapter;

    public CharlasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_charlas, container, false);


        try {
            llenarDatos();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rvListaCharlas = rootView.findViewById(R.id.rvListaCharlas);

        adapter = new CharlaAdapter(R.layout.item_lista_charla,
                this.listaCharlas, new CharlaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Charla charla, int i) {
                Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("charla", charla);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        rvListaCharlas.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListaCharlas.setAdapter(adapter);

        return rootView;
    }

    private void llenarDatos() throws ParseException {

        listaCharlas = new ArrayList<Charla>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date;
        Charla charla;
        date = format.parse( "16/12/2019 15:20" );
        charla = new Charla("CIBERSEGURIDAD","Principios de seguridad, Control de acceso, Seguridad de red y perimetral, Malware, Criptografia, vulnerabilidades, hardening, etc.",
                "Karla Sanchéz",date,R.mipmap.ciberseguridad,R.drawable.karla);
        listaCharlas.add(charla);

        date = format.parse( "18/12/2019 18:30" );
        charla = new Charla("DISEÑO DE REDES","Diseñar red que reúna los requisitos del cliente con relación al rendimiento, seguridad, capacidad y escalabilidad de la red.",
                "Kensil Grajales",date,R.mipmap.disenoredes,R.drawable.kensil);
        listaCharlas.add(charla);

        date = format.parse( "25/12/2019 18:30" );
        charla = new Charla("VIRTUALIZACIÓN VMware","instalación de VMware vSphere visión 5.x y 6.0. Aprenderá VMware vSphere® de una forma entretenida, practica, divertida  y eficaz",
                "José Hinostroza",date,R.mipmap.virtualizacion,R.drawable.jose);
        listaCharlas.add(charla);

    }

}
