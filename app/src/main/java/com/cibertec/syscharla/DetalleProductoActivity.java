package com.cibertec.syscharla;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cibertec.syscharla.Clases.Producto;

public class DetalleProductoActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvCosto;
    private TextView tvEstado;
    private ImageView ivFoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        this.inicializarUI();

       Producto producto   = (Producto) getIntent().getSerializableExtra("Producto");
        cargarUI(producto);
    }
    private void inicializarUI(){
        this.tvNombre = findViewById(R.id.tvNombreDP);
        this.tvDescripcion = findViewById(R.id.tvDescripcionDP);
        this.tvCosto = findViewById(R.id.tvCostoDP);
      //  this.tvEstado = findViewById(R.id.tvEstado);
        this.ivFoto = findViewById(R.id.ivFotoDP);
    }

    private void cargarUI(Producto producto){
        this.tvNombre.setText(producto.getNombre());
        this.tvDescripcion.setText(producto.getDescripcion());
       this.tvCosto.setText("S/." + String.valueOf(producto.getCosto()));
        this.ivFoto.setImageResource(producto.getIdFoto());
    //   this.tvEstado.setText(producto.getEstado());

      /*  int icono = getResources()
                .getIdentifier(producto.getNombre(), "mipmap",
                        getPackageName());
        this.ivFoto.setImageResource(icono);*/

    }
}
