package com.cibertec.syscharla.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CharlaAdapter extends RecyclerView.Adapter<CharlaAdapter.ViewHolder>  {

    private int layout;
    private List<Charla> listaCharlas;

    public CharlaAdapter(int layout, List<Charla> listaCharlas) {
        this.layout = layout;
        this.listaCharlas = listaCharlas;
    }

    @NonNull
    @Override
    public CharlaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(this.layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharlaAdapter.ViewHolder viewHolder,
                                 int i) {
        viewHolder.ivFotoLstCharla.setImageResource(listaCharlas.get(i).getIdFoto());
        viewHolder.tvTituloCharlaLC.setText(listaCharlas.get(i).getNombre());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dateString = format.format(  listaCharlas.get(i).getFechahora());
//        Date date       = format.parse ( "2009-12-31" );
        viewHolder.tvFechaHoraLC.setText(dateString);
        viewHolder.tvDescripcionLC.setText(listaCharlas.get(i).getDescripcion());
        viewHolder.tvExpositorLC.setText(listaCharlas.get(i).getExpositor());
    }

    @Override
    public int getItemCount() {
        return this.listaCharlas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvCharlaLC;
        public ImageView ivFotoLstCharla;
        public TextView tvTituloCharlaLC;
        public TextView tvFechaHoraLC;
        public TextView tvDescripcionLC;
        public TextView tvExpositorLC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCharlaLC = itemView.findViewById(R.id.cvCharlaLC);
            ivFotoLstCharla = itemView.findViewById(R.id.ivFotoLstCharla);
            tvTituloCharlaLC = itemView.findViewById(R.id.tvTituloCharlaLC);
            tvFechaHoraLC = itemView.findViewById(R.id.tvFechaHoraLC);
            tvDescripcionLC = itemView.findViewById(R.id.tvDescripcionLC);
            tvExpositorLC = itemView.findViewById(R.id.tvExpositorLC);
        }
    }
}
