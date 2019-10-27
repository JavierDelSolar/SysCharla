package com.cibertec.syscharla.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Producto;
import com.cibertec.syscharla.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private int layout;
    private List<Producto> listaProductos;
    private OnItemClickListener itemClickListener;

    public ProductoAdapter(int layout, List<Producto> listaProductos, OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.listaProductos = listaProductos;
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(this.layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder viewHolder,
                                 int i) {

        viewHolder.bind(this.listaProductos.get(i), this.itemClickListener);

    }

    @Override
    public int getItemCount() {
        return this.listaProductos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvProductos;
        public TextView tvNombre;
     //   public TextView tvDescripcion;
        public TextView tvCosto;
        public CheckBox chkInteres;
        public ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvProductos = itemView.findViewById(R.id.cvProductos);
            tvNombre = itemView.findViewById(R.id.tvNombreDP);
         //   tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvCosto =   itemView.findViewById(R.id.tvCostoDP);
            chkInteres   =   itemView.findViewById(R.id.chkInteres);
            ivFoto = itemView.findViewById(R.id.ivFotoDP);
        }
        public void bind(final Producto producto, final OnItemClickListener listener){

            tvNombre.setText(producto.getNombre());
            if(producto.getEstado() == 0)
                chkInteres.setChecked(false);
            else
                chkInteres.setChecked(true);
            ivFoto.setImageResource(producto.getIdFoto());
            tvCosto.setText("S/." + String.valueOf(producto.getCosto()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto, getAdapterPosition());
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Producto producto, int i);
    }
}