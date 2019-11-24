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

import java.util.List;

public class MisCharlasAdapter extends RecyclerView.Adapter<MisCharlasAdapter.ViewHolder> {

    private int layout;
    private List<Charla> listaCharlas;
    private OnItemClickListener itemClickListener;
    private Context context;

    public MisCharlasAdapter(int layout, List<Charla> listaCharlas, Context  context, OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.listaCharlas = listaCharlas;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }
    @NonNull
    @Override
    public MisCharlasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(this.layout, viewGroup, false);

        return new ViewHolder(view);
    }

    public void setData(List<Charla> mData) {
        this.listaCharlas = mData;
    }

    @Override
    public void onBindViewHolder(@NonNull MisCharlasAdapter.ViewHolder viewHolder,
                                 int i) {

        viewHolder.bind(this.listaCharlas.get(i), this.itemClickListener);

    }

    @Override
    public int getItemCount() {
        return this.listaCharlas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitulo, tvFecha, tvDescripcion;
        public CardView cvCharla;
        public ImageView ivCharla;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCharla = itemView.findViewById(R.id.cvCharla);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ivCharla = itemView.findViewById(R.id.ivCharla);
        }
        public void bind(final Charla charla, final OnItemClickListener listener){

            tvTitulo.setText(charla.getNombre());
            tvFecha.setText(charla.getFechaHora());
            tvDescripcion.setText(charla.getDescripcion());
            Picasso.get().load(charla.getFoto()).error(R.drawable.charlafoto).fit().into(ivCharla);

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
