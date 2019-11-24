package com.cibertec.syscharla.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.cibertec.syscharla.Adapters.CharlaAdapter;
import com.cibertec.syscharla.Adapters.MisCharlasAdapter;
import com.cibertec.syscharla.CharlaDetalleActivity;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Interfaces.Charla_I;
import com.cibertec.syscharla.R;
import com.cibertec.syscharla.RetrofitClient;
import com.cibertec.syscharla.Variables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisCharlasFragment extends Fragment {

    private RecyclerView rvMisCharlas;
    private MisCharlasAdapter adapter;
    private RecyclerView.LayoutManager mcLayoutManager;
    private List<Charla> listaCharlas;
    private ImageView ivFiltro;
    Variables objUtil = Variables.getInstance();


    public MisCharlasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_charlas, container, false);

        rvMisCharlas = view.findViewById(R.id.rvMisCharlas);
        ivFiltro = view.findViewById(R.id.ivFiltro);

        ListarMisCharlas("111",1, true);


        ivFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //* Dialog por Layout *//*
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                builder.setTitle("Filtrar y/u Ordenar");
                final View dialogView = inflater.inflate(R.layout.dialog_mis_charlas, null);
                builder.setView(dialogView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String evento;
                                int orden = 1;
                                CheckBox cbEventoHoy = dialogView.findViewById(R.id.cbEventoHoy);
                                CheckBox cbEventoPosterior = dialogView.findViewById(R.id.cbEventoPosterior);
                                CheckBox cbEventoPasados = dialogView.findViewById(R.id.cbEventoPasados);
                                RadioButton rbOrdenFechaAsc = dialogView.findViewById(R.id.rbOrdenFechaAsc);
                                RadioButton rbOrdenFechaDesc = dialogView.findViewById(R.id.rbOrdenFechaDesc);
                                RadioButton rbOrdenAlfaAsc = dialogView.findViewById(R.id.rbOrdenAlfaAsc);
                                RadioButton rbOrdenAlfaDesc = dialogView.findViewById(R.id.rbOrdenAlfaDesc);
                                evento = (cbEventoHoy.isChecked())?"1":"0";
                                evento += (cbEventoPosterior.isChecked())?"1":"0";
                                evento += (cbEventoPasados.isChecked())?"1":"0";
                                orden = (rbOrdenFechaAsc.isChecked())?1:orden;
                                orden = (rbOrdenFechaDesc.isChecked())?2:orden;
                                orden = (rbOrdenAlfaAsc.isChecked())?3:orden;
                                orden = (rbOrdenAlfaDesc.isChecked())?4:orden;
                                ListarMisCharlas(evento,orden, false);
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create();
                builder.show();
            }
        });


        return view;
    }
    private void ListarMisCharlas(String Tipo, int OrderBy, final boolean prueba) {
        try {

            String sFechaActual = "";
            Date FechaActual = new Date();
            String strDateFormat = "yyyyMMdd";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            sFechaActual= objSDF.format(FechaActual);

            listaCharlas = new ArrayList<>();
            Charla_I charla_i = RetrofitClient.getClient().create(Charla_I.class);
            Call<List<Charla>> call = charla_i.getListrMisCharlasxFechaxOrden(objUtil.usuario.getIDUsuario(),Tipo,sFechaActual,OrderBy);
            call.enqueue(new Callback<List<Charla>>() {
                @Override
                public void onResponse(Call<List<Charla>> call, Response<List<Charla>> response) {
                    if(response.isSuccessful()) {
                        listaCharlas.clear();
                        listaCharlas = response.body();
                        if(prueba){
                            adapter = new MisCharlasAdapter(R.layout.item_mis_charlas,
                                    listaCharlas, getActivity(), new MisCharlasAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Charla charla, int i) {
                                    Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
                                    objUtil.charla = charla;
                                    startActivity(intent);
                                }
                            });
                            rvMisCharlas.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvMisCharlas.setAdapter(adapter);
                        }else{
                            int cant = listaCharlas.size();
                            adapter.setData(listaCharlas);
                            adapter.notifyDataSetChanged();
                            //Toast.makeText(getContext(), "Recargando..." + cant, Toast.LENGTH_LONG).show();
                        }

                    }else
                    {
                        Toast.makeText(getActivity(), response.message(), Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Charla>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
