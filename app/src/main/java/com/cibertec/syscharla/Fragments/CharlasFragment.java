package com.cibertec.syscharla.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cibertec.syscharla.Adapters.CharlaAdapter;
import com.cibertec.syscharla.CharlaDetalleActivity;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;
import com.cibertec.syscharla.Utility.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharlasFragment extends Fragment {
     RecyclerView rvListaCharlas;
     List<Charla> listaCharlas;
     CharlaAdapter adapter;


    public CharlasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_charlas, container, false);


        try {
            // llenarDatos();
            ListAllCharla();
        } catch (ParseException e) {
            Log.d("Server Response1",""+e.getMessage());
            e.printStackTrace();
            e.getErrorOffset();
            e.getCause();

        }

       rvListaCharlas = rootView.findViewById(R.id.rvListaCharlas);
//        adapter = new CharlaAdapter(R.layout.item_lista_charla, listaCharlas, new CharlaAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Charla charla, int i) {
//
//            }
//        });
//
//        adapter = new CharlaAdapter(R.layout.item_lista_charla, this.listaCharlas, new CharlaAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Charla charla, int i) {
//                Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
//                Bundle bundle= new Bundle();
//                bundle.putSerializable("charla", charla);
//                intent.putExtras(bundle);
//
//                startActivity(intent);
//            }
//        });
////
//        listaCharlas = new ArrayList<>();
//




        return rootView;
    }

    private void ListAllCharla()   throws ParseException {
        Call<List<Charla>> call = RetrofitUtil.getCharlaApi().getListAllCharla();
        call.enqueue(new Callback<List<Charla>>() {
            @Override
            public void onResponse(Call<List<Charla>> call, Response<List<Charla>> response) {
                if(!response.isSuccessful()) {
                    Log.d("Server Response1",""+ response.code());
                    Log.d("Server Response2",""+ response.message());
                }else{

                    listaCharlas  = new ArrayList<>();
                    listaCharlas.addAll(response.body());
                    Log.d("Server Response3",""+ response.message());



                    adapter = new CharlaAdapter(R.layout.item_lista_charla, listaCharlas, new CharlaAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Charla charla, int i) {
                Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("charla", charla);
                intent.putExtras(bundle);
                        }
                    });




                    rvListaCharlas.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvListaCharlas.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                  //
                }
              //  Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onFailure(Call<List<Charla>> call, Throwable t) {

                Log.d("Server Response1",""+ t.getMessage());
                Log.d("Server Response2",""+ t.getCause());
                t.printStackTrace();
                t.getMessage();
                t.getCause();
            }
        });
    }

    private void llenarDatos() throws ParseException {

        listaCharlas = new ArrayList<Charla>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date;
        Charla charla;
        date = format.parse( "16/12/2019 15:20" );
//        charla = new Charla(1,
//                "CIBERSEGURIDAD",
//                "24/11/1984",
//                "jr.n",
//                "1.75226",
//                "-102336",
//                100,
//                "http:/sdfsdfsf",
//
//                "Principios de seguridad, Control de acceso, Seguridad de red y perimetral, Malware, Criptografia, vulnerabilidades, hardening, etc.",
//                "asdasdads",
//                "asdads",
//
//                "Karla Sanchéz",
//                true,
//                1,
//                2
//                );
//        listaCharlas.add(charla);

//        date = format.parse( "18/12/2019 18:30" );
//        charla = new Charla("DISEÑO DE REDES","Diseñar red que reúna los requisitos del cliente con relación al rendimiento, seguridad, capacidad y escalabilidad de la red.",
//                "Kensil Grajales",date,R.mipmap.disenoredes,R.drawable.kensil);
//        listaCharlas.add(charla);
//
//        date = format.parse( "25/12/2019 18:30" );
//        charla = new Charla("VIRTUALIZACIÓN VMware","instalación de VMware vSphere visión 5.x y 6.0. Aprenderá VMware vSphere® de una forma entretenida, practica, divertida  y eficaz",
//                "José Hinostroza",date,R.mipmap.virtualizacion,R.drawable.jose);
//        listaCharlas.add(charla);

    }

}
