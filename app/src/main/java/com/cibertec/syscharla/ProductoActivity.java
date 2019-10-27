package com.cibertec.syscharla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.syscharla.Adapters.ProductoAdapter;
import com.cibertec.syscharla.Clases.Producto;
import java.util.List;

public class ProductoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RecyclerView rvListaProductos;
    private RecyclerView.LayoutManager layoutManager;
    private List<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        rvListaProductos = findViewById(R.id.rvListaProductos);
        layoutManager = new LinearLayoutManager(this);
        ProductoAdapter adapter = new ProductoAdapter(R.layout.item_lista_producto, listaProductos, new ProductoAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(ProductoActivity.this, "Producto Seleccionado: " + producto.getNombre(), Toast.LENGTH_LONG).show();
            }
        });
        rvListaProductos.setLayoutManager(layoutManager);
        rvListaProductos.setAdapter(adapter);
        rvListaProductos.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Producto producto = this.listaProductos.get(position);
        Toast.makeText(ProductoActivity.this,
                producto.getNombre(), Toast.LENGTH_LONG).show();


        Intent intent = new Intent(ProductoActivity.this, DetalleProductoActivity.class);
        intent.putExtra("mi Producto es: ", producto);
        startActivity(intent);
    }
}




