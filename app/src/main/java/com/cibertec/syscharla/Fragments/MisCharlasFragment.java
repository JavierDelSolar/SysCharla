package com.cibertec.syscharla.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.cibertec.syscharla.Adapters.MisCharlasAdapter;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MisCharlasFragment extends Fragment implements DialogInterface.OnClickListener{

    private RecyclerView rvMisCharlas;
    private RecyclerView.Adapter mcAdapter;
    private RecyclerView.LayoutManager mcLayoutManager;
    private ArrayList<Charla> charlas;
    private ImageView ivFiltro;

    public MisCharlasFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mis_charlas, container, false);

        charlas = new ArrayList<>();

        String descripcion = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eligendi non quis exercitationem culpa nesciunt nihil aut nostrum explicabo reprehenderit optio amet ab temporibus asperiores quasi cupiditate. Voluptatum ducimus voluptates voluptas?." +
                "\n Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eligendi non quis exercitationem culpa nesciunt nihil aut nostrum explicabo reprehenderit optio amet ab temporibus asperiores quasi cupiditate. Voluptatum ducimus voluptates voluptas?." +
                "\n Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eligendi non quis exercitationem culpa nesciunt nihil aut nostrum explicabo reprehenderit optio amet ab temporibus asperiores quasi cupiditate. Voluptatum ducimus voluptates voluptas?";

        Random r = new Random();
        String[] titulo = {"Ciberseguridad adasd asdadadasdasd", "Ethical Hacking", "Java"};
        String[] expositor = {"Expositor 1", "Expositor 2", "Expositor 3"};
        String[] direccion = {"Direccion 1", "Direccion 2", "Direccion 3"};
        int[] imagen = {R.drawable.ciberseguridad, R.drawable.ethical_hacking, R.drawable.java};

        //Date fecha = new Date();
        Date fecha = new Date(119, 10, 15, 10, 30);

        for(int i=1; i <= 20 ; i++){
            int num = r.nextInt(3-0);
            charlas.add(new Charla(i, titulo[num] + ": " +i, descripcion, expositor[num], direccion[num], imagen[num], fecha));
        }

        rvMisCharlas = view.findViewById(R.id.rvMisCharlas);
        mcLayoutManager= new LinearLayoutManager(getActivity());
        mcAdapter = new MisCharlasAdapter(charlas);
        rvMisCharlas.setHasFixedSize(true);
        rvMisCharlas.setLayoutManager(mcLayoutManager);
        rvMisCharlas.setAdapter(mcAdapter);

        ivFiltro = view.findViewById(R.id.ivFiltro);

        ivFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                /* Dialog por Layout */
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                builder.setTitle("Filtrar y/u Ordenar");
                builder.setView(inflater.inflate(R.layout.dialog_mis_charlas, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                CheckBox cbEventoHoy = view.findViewById(R.id.cbEventoHoy);
                                CheckBox cbEventoPosterior = view.findViewById(R.id.cbEventoPosterior);
                                CheckBox cbEventoPasados = view.findViewById(R.id.cbEventoPasados);
                                RadioButton rbOrdenFechaDesc = view.findViewById(R.id.rbOrdenFechaDesc);
                                RadioButton rbOrdenFechaAsc = view.findViewById(R.id.rbOrdenFechaAsc);
                                RadioButton rbOrdenAlfaDesc = view.findViewById(R.id.rbOrdenAlfaDesc);
                                RadioButton rbOrdenAlfaAsc = view.findViewById(R.id.rbOrdenAlfaAsc);

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


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
