package com.cibertec.syscharla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cibertec.syscharla.Adapters.CharlaProductoAdapter;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Clases.CharlaProducto;
import com.cibertec.syscharla.Clases.Interes;
import com.cibertec.syscharla.Interfaces.CharlaProducto_I;
import com.cibertec.syscharla.Interfaces.Interes_I;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {

    private List<CharlaProducto> listaCharlaProductos = null;
    //  private Charla charla = null;
    private RecyclerView rvListaProductos;
    private CheckBox chkInteres;
    private CharlaProductoAdapter adapter;

    Variables objUtil = Variables.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        rvListaProductos = findViewById(R.id.rvProductos);
        LlenarProductosInteres();


    }
/*
private void InsertarInteres(){
    try {
        Interes interes = new Interes();
        interes.setIDCharlaProducto(charlaProducto.getIDCharlaProducto());
        interes.setIDUsuario(objUtil.usuario.getIDUsuario());

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
private void DeleteIntereses(){

*/

    //}
    private void LlenarProductosInteres() {
        listaCharlaProductos = new ArrayList<>();
        try {
            CharlaProducto_I charlaProducto_i = RetrofitClient.getClient().create(CharlaProducto_I.class);
            Call<List<CharlaProducto>> call = charlaProducto_i.ListarCharlaProductoInteres(objUtil.usuario.getIDUsuario(), objUtil.charla.getIDCharla());
            call.enqueue(new Callback<List<CharlaProducto>>() {
                @Override
                public void onResponse(Call<List<CharlaProducto>> call, Response<List<CharlaProducto>> response) {
                    if (response.isSuccessful()) {
                        listaCharlaProductos = response.body();
                        adapter = new CharlaProductoAdapter(R.layout.item_lista_charlaproducto,
                                listaCharlaProductos, getApplicationContext(),new CharlaProductoAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(CharlaProducto charlaProducto, int i) {
                                Intent intent = new Intent(getApplicationContext(), DetalleProductoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("charlaProducto", charlaProducto);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                        rvListaProductos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rvListaProductos.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "No existe Productos Asignados.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<CharlaProducto>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }





  /*  private void llenarProductos() {

        listaProductos = new ArrayList<Producto>();
        Producto producto = new Producto("Java Fundamentals Developers", 1000.00, "Desarrollo de los Fundamentos de Java", R.drawable.javadeve, 1);
        listaProductos.add(producto);
        producto = new Producto("Java Web Developers", 1600.00, "Desarrollo Web de Java", R.drawable.javafunda, 0);
        listaProductos.add(producto);
    }*/
}





