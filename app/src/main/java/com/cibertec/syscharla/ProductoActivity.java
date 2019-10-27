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

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Producto> listaProductos;
    private RecyclerView rvListaProductos;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        llenarProductos();
        rvListaProductos = findViewById(R.id.rvProductos);
        layoutManager = new LinearLayoutManager(this);

        ProductoAdapter adapter = new ProductoAdapter(this,R.layout.item_lista_producto,
                listaProductos);


        rvListaProductos.setLayoutManager(layoutManager);
        rvListaProductos.setAdapter(adapter);
        rvListaProductos.setOnItemClickListener(this);
    }

    private void llenarProductos(){

        listaProductos =   new ArrayList<Producto>();
        Producto    producto    =   new Producto("Java Fundamentals Developers", 1000, "Desarrollo de los Fundamentos de Java",R.mipmap.ciberseguridad,"Suscrito");
        listaProductos.add(producto);
        producto    =   new Producto("Java Web Developers", 1600, "Desarrollo Web de Java",R.mipmap.ciberseguridad,"Suscrito");
        listaProductos.add(producto);
    }

        @Override
        public void onItemClick(AdapterView<?> par, View view, int position, long id) {
            Producto producto = this.listaProductos.get(position);
            Toast.makeText(ProductoActivity.this,
                    producto.getNombre(), Toast.LENGTH_LONG).show();


            Intent intent = new Intent(ProductoActivity.this, DetalleProductoActivity.class);
            intent.putExtra("Mi producto", producto);
            startActivity(intent);
        }
    }





