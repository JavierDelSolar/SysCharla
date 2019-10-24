package com.cibertec.syscharla.Adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.util.List;

public class MisCharlasAdapter extends RecyclerView.Adapter<MisCharlasAdapter.MisCharlasVH> {

    private List<Charla> charlas;
    private int layout;
    private AdapterView.OnItemClickListener itemClickListener;

    public MisCharlasAdapter(List<Charla> charlas, int layout, AdapterView.OnItemClickListener itemClickListener) {
        this.charlas = charlas;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public static class MisCharlasVH extends RecyclerView.ViewHolder{
        public ImageView ivCharla;
        public TextView tvTitulo;

        public MisCharlasVH(@NonNull View v) {
            super(v);
            ivCharla = v.findViewById(R.id.ivCharla);
            tvTitulo = v.findViewById(R.id.tvTitulo);
        }

        public void bind(final Charla charlas, final AdapterView.OnItemClickListener listener){
            this.tvTitulo.setText(Html.fromHtml("<b>" +  charlas.getNombre() + "</b>"));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }

    @NonNull
    @Override
    public MisCharlasAdapter.MisCharlasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        MisCharlasVH vh = new MisCharlasVH(v);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MisCharlasAdapter.MisCharlasVH holder, int pos) {
            holder.bind(charlas.get(pos), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return charlas.size();
    }

}
