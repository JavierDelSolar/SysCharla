package com.cibertec.syscharla.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.cibertec.syscharla.Adapters.CharlaAdapter;
import com.cibertec.syscharla.CharlaDetalleActivity;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Interfaces.Charla_I;
import com.cibertec.syscharla.R;
import com.cibertec.syscharla.RetrofitClient;
import com.cibertec.syscharla.Variables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharlasFragment extends Fragment {
    private RecyclerView rvListaCharlas;
    private List<Charla> listaCharlas;
    private CharlaAdapter adapter;
    Variables objUtil = Variables.getInstance();
    SearchView simpleSearchView;


    public CharlasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_charlas, container, false);

        rvListaCharlas = rootView.findViewById(R.id.rvListaCharlas);
        simpleSearchView = (SearchView) rootView.findViewById(R.id.sv_BusquedaMC);

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0)
                ListarCharlasxNombre(query);
                else
                    ListarCharlas();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        simpleSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ListarCharlas();
                return true;
            }
        });
        ListarCharlas();

        return rootView;
    }

    private void ListarCharlas() {
        try {
            listaCharlas = new ArrayList<>();
            Charla_I charla_i = RetrofitClient.getClient().create(Charla_I.class);
            Call<List<Charla>> call = charla_i.getListCharlaActuales();
            call.enqueue(new Callback<List<Charla>>() {
                @Override
                public void onResponse(Call<List<Charla>> call, Response<List<Charla>> response) {
                    if (response.isSuccessful()) {
                        //   Toast.makeText(getActivity(), "INGRESO ", Toast.LENGTH_LONG).show();
                        // List<Charla> Lista = response.body();
                        listaCharlas = response.body();
                        adapter = new CharlaAdapter(R.layout.item_lista_charla,
                                listaCharlas, getActivity(), new CharlaAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Charla charla, int i) {
                                Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
                                objUtil.charla = charla;
                                startActivity(intent);
                            }
                        });
                        rvListaCharlas.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rvListaCharlas.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "No existe", Toast.LENGTH_LONG).show();
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

    private void ListarCharlasxNombre(String Nombre) {
        try {
            listaCharlas = new ArrayList<>();
            Charla_I charla_i = RetrofitClient.getClient().create(Charla_I.class);
            Call<List<Charla>> call = charla_i.getListarCharlaxNombre(Nombre);
            call.enqueue(new Callback<List<Charla>>() {
                @Override
                public void onResponse(Call<List<Charla>> call, Response<List<Charla>> response) {
                    if (response.isSuccessful()) {
                        listaCharlas = response.body();
                        adapter = new CharlaAdapter(R.layout.item_lista_charla,
                                listaCharlas, getActivity(), new CharlaAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Charla charla, int i) {
                                Intent intent = new Intent(getActivity(), CharlaDetalleActivity.class);
                                objUtil.charla = charla;
                                startActivity(intent);
                            }
                        });
                        rvListaCharlas.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rvListaCharlas.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "No existe", Toast.LENGTH_LONG).show();
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
