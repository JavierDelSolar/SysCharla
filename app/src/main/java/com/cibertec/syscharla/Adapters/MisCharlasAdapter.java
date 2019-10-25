package com.cibertec.syscharla.Adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.util.ArrayList;

public class MisCharlasAdapter extends RecyclerView.Adapter<MisCharlasAdapter.MisCharlasVH> {

    private ArrayList<Charla> charlas;

    public MisCharlasAdapter(ArrayList<Charla> charlas) {
        this.charlas = charlas;
    }

    public class MisCharlasVH extends RecyclerView.ViewHolder{
        //public ImageView ivCharla;
        public TextView tvTitulo, tvDescripcion;
        public CardView cvCharla;

        public MisCharlasVH(@NonNull View v) {
            super(v);
            //ivCharla = v.findViewById(R.id.ivCharla);
            cvCharla = v.findViewById(R.id.cvCharla);
            tvTitulo = v.findViewById(R.id.tvTitulo);
            tvDescripcion = v.findViewById(R.id.tvDescripcion);
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
    public void onBindViewHolder(MisCharlasVH holder, int pos) {
        holder.tvTitulo.setText(charlas.get(pos).getNombre());
        holder.tvDescripcion.setText(charlas.get(pos).getDescripcion());
        holder.cvCharla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return charlas.size();
    }

}
