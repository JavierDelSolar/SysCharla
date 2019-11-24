package com.cibertec.syscharla;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cibertec.syscharla.Clases.CharlaProducto;
import com.cibertec.syscharla.Clases.Interes;
import com.cibertec.syscharla.Clases.Producto;
import com.cibertec.syscharla.Interfaces.Interes_I;
import com.cibertec.syscharla.Interfaces.Producto_I;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleProductoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvCosto;
    private Button btnInteres;
    private TextView tvEstado;
    private ImageView ivFoto;
    CharlaProducto charlaProducto;
    Variables objUtil = Variables.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        this.inicializarUI();

        charlaProducto   = (CharlaProducto) getIntent().getSerializableExtra("charlaProducto");
        Picasso.with(getApplicationContext()).load(charlaProducto.getProducto().getFoto()).error(R.drawable.charlafoto).fit().into(ivFoto);
        tvNombre.setText(charlaProducto.getProducto().getNombre());

        CargarProducto();

        btnInteres.setOnClickListener(this);
    }
    private void inicializarUI(){
        btnInteres = findViewById(R.id.btn_Interes);
        this.tvNombre = findViewById(R.id.tvNombreDP);
        this.tvDescripcion = findViewById(R.id.tvDescripcionDP);
        this.tvCosto = findViewById(R.id.tvCostoDP);
        this.ivFoto = findViewById(R.id.ivFotoDP);
    }

    private void CargarProducto(){
        try {
            Producto_I producto_i = RetrofitClient.getClient().create(Producto_I.class);
            Call<Producto> call = producto_i.getListaProductoxId(charlaProducto.getIDProducto());
            call.enqueue(new Callback<Producto>() {
                @Override
                public void onResponse(Call<Producto> call, Response<Producto> response) {
                    if (response.isSuccessful()) {
                        Producto producto = response.body();
                        //  Toast.makeText(getApplicationContext(), "Producto  Existe.", Toast.LENGTH_LONG).show();
                        tvNombre.setText(producto.getNombre());
                        tvDescripcion.setText(producto.getDescripcion());
                        tvCosto.setText("S/." + String.valueOf(producto.getCosto()));
                        Picasso.with(getApplicationContext()).load(producto.getFoto()).error(R.drawable.charlafoto).fit().into(ivFoto);
                        if(charlaProducto.getInteres() == 0){
                            btnInteres.setVisibility(View.VISIBLE);
                        }
                        else
                            btnInteres.setVisibility(View.GONE);

                    } else
                        Toast.makeText(getApplicationContext(), "Producto no Existe.", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<Producto> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnInteres){
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
                            Toast.makeText(getApplicationContext(), "Se agrego a sus Intereses.", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se registro.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Interes> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
