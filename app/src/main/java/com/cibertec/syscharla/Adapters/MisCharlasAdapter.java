package com.cibertec.syscharla.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.CharlaActivity;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MisCharlasAdapter extends RecyclerView.Adapter<MisCharlasAdapter.MisCharlasVH> {

    private ArrayList<Charla> charlas;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public MisCharlasAdapter(ArrayList<Charla> charlas) {
        this.charlas = charlas;
    }

    public class MisCharlasVH extends RecyclerView.ViewHolder{

        public TextView tvTitulo, tvExpositor, tvFecha;
        public CardView cvCharla;
        public ImageView ivCharla;

        public MisCharlasVH(@NonNull View v) {
            super(v);
            cvCharla = v.findViewById(R.id.cvCharla);
            tvTitulo = v.findViewById(R.id.tvTitulo);
            tvFecha = v.findViewById(R.id.tvFecha);
            tvExpositor = v.findViewById(R.id.tvExpositor);
            ivCharla = v.findViewById(R.id.ivCharla);
        }

    }

    @NonNull
    @Override
    public MisCharlasAdapter.MisCharlasVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mis_charlas, viewGroup, false);
        MisCharlasVH vh = new MisCharlasVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MisCharlasVH holder, final int pos){
        holder.tvTitulo.setText(charlas.get(pos).getNombre());
        holder.tvExpositor.setText(charlas.get(pos).getExpositor());
        holder.tvFecha.setText(dateFormat.format(charlas.get(pos).getFechahora()));
        holder.ivCharla.setImageResource(charlas.get(pos).getIdFoto());

        holder.cvCharla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Charla charla = charlas.get(pos);
                Intent intent = new Intent(view.getContext(), CharlaActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("charla", charla);
                intent.putExtras(bundle);
                intent.putExtra("Opcion", "Mis Charlas");

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return charlas.size();
    }

}
