package com.cibertec.syscharla.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Clases.CharlaProducto;
import com.cibertec.syscharla.Clases.Interes;
import com.cibertec.syscharla.Clases.Producto;
import com.cibertec.syscharla.Interfaces.Interes_I;
import com.cibertec.syscharla.R;
import com.cibertec.syscharla.RetrofitClient;
import com.cibertec.syscharla.Variables;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharlaProductoAdapter extends RecyclerView.Adapter<CharlaProductoAdapter.ViewHolder> {

    private int layout;
    private List<CharlaProducto> listaCharlaProductos;
    private OnItemClickListener itemClickListener;
    private Context context;
    Variables objUtil = Variables.getInstance();

    public CharlaProductoAdapter(int layout, List<CharlaProducto> listaCharlaProductos,  Context  context,
                                 OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.listaCharlaProductos = listaCharlaProductos;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }
    @NonNull
    @Override
    public CharlaProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(this.layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharlaProductoAdapter.ViewHolder viewHolder,int i) {
        viewHolder.bind(this.listaCharlaProductos.get(i), this.itemClickListener);
    }
    @Override
    public int getItemCount() {
        return this.listaCharlaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvProductos;
        public TextView tvNombre;
        public TextView tvCosto;
        public CheckBox chkInteres;
        public ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvProductos = itemView.findViewById(R.id.cvProductos);
            tvNombre = itemView.findViewById(R.id.tvNombreDP);
            tvCosto =   itemView.findViewById(R.id.tvCostoDP);
            chkInteres   =   itemView.findViewById(R.id.chkInteres);
            ivFoto = itemView.findViewById(R.id.ivFotoDP);
        }
        public void bind(final CharlaProducto charlaProducto, final OnItemClickListener listener){

            tvNombre.setText(charlaProducto.getProducto().getNombre());
            if(charlaProducto.getInteres() == 0)
                chkInteres.setChecked(false);
            else
                chkInteres.setChecked(true);
            tvCosto.setText("S/." + String.valueOf(charlaProducto.getProducto().getCosto()));
            //Picasso.with(context).load(charlaProducto.getProducto().getFoto()).error(R.drawable.charlafoto).fit().into(ivFoto);
            Picasso.with(context).load(charlaProducto.getProducto().getFoto()).error(R.drawable.charlafoto).fit().into(ivFoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(charlaProducto, getAdapterPosition());
                }
            });

            chkInteres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(CharlaProductoAdapter.this.context,
                                "CHECKEADO",
                                Toast.LENGTH_LONG).show();
                        InsertarInteres(charlaProducto);
                    } else {
                        Toast.makeText(CharlaProductoAdapter.this.context,
                                "NO CHECKEADO",
                                Toast.LENGTH_LONG).show();
                        EliminarIntereses(charlaProducto);
                    }
                }
            });
        }
    }

    private void InsertarInteres(CharlaProducto charlaProducto){
        Interes interes = new Interes();
        interes.setIDCharlaProducto(charlaProducto.getIDCharlaProducto());
        interes.setIDUsuario(objUtil.usuario.getIDUsuario());

        try {
            Interes_I interes_i = RetrofitClient.getClient().create(Interes_I.class);
            Call<Interes> call = interes_i.InsertInteres(interes);
            call.enqueue(new Callback<Interes>() {
                @Override
                public void onResponse(Call<Interes> call, Response<Interes> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CharlaProductoAdapter.this.context, "Se agrego a sus Intereses.", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(CharlaProductoAdapter.this.context, "No se registro.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Interes> call, Throwable t) {
                    Toast.makeText(CharlaProductoAdapter.this.context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(CharlaProductoAdapter.this.context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void EliminarIntereses(CharlaProducto charlaProducto){
        try {
            Interes_I interes_i = RetrofitClient.getClient().create(Interes_I.class);
            Call<Interes> call = interes_i.DeleteInteres(objUtil.usuario.getIDUsuario(),charlaProducto.getIDCharlaProducto());
            call.enqueue(new Callback<Interes>() {
                @Override
                public void onResponse(Call<Interes> call, Response<Interes> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CharlaProductoAdapter.this.context, "Se elimino a sus Intereses.", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(CharlaProductoAdapter.this.context, "No se elimino.", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<Interes> call, Throwable t) {
                    Toast.makeText(CharlaProductoAdapter.this.context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(CharlaProductoAdapter.this.context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CharlaProducto charlaProducto, int i);
    }
}