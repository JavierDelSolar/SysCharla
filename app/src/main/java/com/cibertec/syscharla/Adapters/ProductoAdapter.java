package com.cibertec.syscharla.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Producto;
import com.cibertec.syscharla.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private int layout;
    private List<Producto> listaProductos;
    private OnItemClickListener itemClickListener;

    public ProductoAdapter(int layout, List<Producto>   listaProductos, OnItemClickListener itemClickListener){
        this.layout =   layout  ;
        this.listaProductos  =   listaProductos;
        this.itemClickListener  =   itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View    v = LayoutInflater.from(parent.getContext()).inflate(layout,parent  ,   false   );
        ViewHolder vh = new ProductoAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        holder.bind(listaProductos.get(position), itemClickListener);

    }
    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Producto producto, int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre;
        public TextView tvDescripcion;
        public TextView tvCosto;
        public TextView tvEstado;
        public ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvCosto =   itemView.findViewById(R.id.tvCosto);
            tvEstado    =   itemView.findViewById(R.id.tvEstado);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }

        public void bind(final Producto producto, final OnItemClickListener listener){
            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
            tvCosto.setText(producto.getCosto());
            tvEstado.setText(producto.getEstado());
            ivFoto.setImageResource(producto.getIdFoto());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(producto, getAdapterPosition());
                }
            });
        }
    }


}