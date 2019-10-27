package com.cibertec.syscharla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.cibertec.syscharla.Adapters.ProductoAdapter;
import com.cibertec.syscharla.Adapters.ProductoAdapter;
import com.cibertec.syscharla.Clases.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    private List<Producto> listaProductos;
    private RecyclerView rvListaProductos;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        llenarProductos();
        rvListaProductos = findViewById(R.id.rvProductos);

        adapter = new ProductoAdapter(R.layout.item_lista_producto,
                this.listaProductos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int i) {
                Intent intent = new Intent(getApplicationContext(), DetalleProductoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Producto", producto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        rvListaProductos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListaProductos.setAdapter(adapter);


    }

    private void llenarProductos() {

        listaProductos = new ArrayList<Producto>();
        Producto producto = new Producto("Java Fundamentals Developers", 1000.00, "Desarrollo de los Fundamentos de Java", R.drawable.javadeve, 1);
        listaProductos.add(producto);
        producto = new Producto("Java Web Developers", 1600.00, "Desarrollo Web de Java", R.drawable.javafunda, 0);
        listaProductos.add(producto);
    }
}





