package com.cibertec.syscharla.Adapters;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class CharlaAdapter extends RecyclerView.Adapter<CharlaAdapter.ViewHolder>  {

    private int layout;
    private List<Charla> listaCharlas;
    private OnItemClickListener itemClickListener;
    private Context context;

    public CharlaAdapter(int layout, List<Charla> listaCharlas, Context  context,OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.listaCharlas = listaCharlas;
        this.itemClickListener = itemClickListener;
        this.context = context;
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

        viewHolder.bind(this.listaCharlas.get(i), this.itemClickListener);

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCharlaLC = itemView.findViewById(R.id.cvCharlaLC);
            ivFotoLstCharla = itemView.findViewById(R.id.ivFotoLstCharla);
            tvTituloCharlaLC = itemView.findViewById(R.id.tvTituloCharlaLC);
            tvFechaHoraLC = itemView.findViewById(R.id.tvFechaHoraLC);
            tvDescripcionLC = itemView.findViewById(R.id.tvDescripcionLC);
        }
        public void bind(final Charla charla, final OnItemClickListener listener){

            // Picasso.with(context).load(charla.getFoto()).into(ivFotoLstCharla);
            Picasso.get().load(charla.getFoto()).error(R.drawable.charlafoto).fit().into(ivFotoLstCharla);
            // ivFotoLstCharla.setImageResource(charla.getIdFoto());
            tvTituloCharlaLC.setText(charla.getNombre());

            //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            //String dateString = format.format(  charla.getFechaHora());

            tvFechaHoraLC.setText(charla.getFechaHora());
            tvDescripcionLC.setText(charla.getDescripcion());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(charla, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Charla charla, int i);
    }
}
