package com.cibertec.syscharla.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.Expositor;
import com.cibertec.syscharla.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExpositorAdapter extends RecyclerView.Adapter<ExpositorAdapter.ViewHolder> {

    private int layout;
    private List<Expositor> listaExpositor;
    private Context context;


    public ExpositorAdapter(int layout, List<Expositor> listaExpositor, Context  context) {
        this.layout = layout;
        this.listaExpositor = listaExpositor;
        this.context = context;
    }


    @NonNull
    @Override
    public ExpositorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.layout, parent, false);

        return new ExpositorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpositorAdapter.ViewHolder holder, int position) {
        holder.bind(this.listaExpositor.get(position));
    }

    @Override
    public int getItemCount() {
        return  listaExpositor.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView ivFotoExpoDC;
        public TextView tvNombreExpositorDC;
        public TextView tvDescrExpositor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFotoExpoDC = itemView.findViewById(R.id.ivFotoExpoDC);
            tvNombreExpositorDC = itemView.findViewById(R.id.tvNombreExpositorDC);
            tvDescrExpositor = itemView.findViewById(R.id.tvDescrExpositor);
        }
        public void bind(final Expositor expositor) {

            tvNombreExpositorDC.setText(expositor.getNombre());
            tvDescrExpositor.setText(expositor.getDescripcion());
            Picasso.with(context).load(expositor.getFoto().toString()).error(R.drawable.charlafoto).fit().into(ivFotoExpoDC);

        }
    }
}
