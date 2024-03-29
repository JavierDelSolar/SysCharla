package com.cibertec.syscharla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.syscharla.Adapters.ExpositorAdapter;
import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Clases.Expositor;
import com.cibertec.syscharla.Clases.UsuarioCharla;
import com.cibertec.syscharla.Interfaces.Charla_I;
import com.cibertec.syscharla.Interfaces.Expositor_I;
import com.cibertec.syscharla.Interfaces.UsuarioCharla_I;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharlaDetalleActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvTituloCharlaDC;
    TextView tvFechaHoraDC;
    TextView tvDescripcionDC;
    TextView tvEstadoCharla;
    RecyclerView rvListaExpositores;
    TextView tvDireccCharlaDC;
    Button btn_SuscribirseDC;
    Button btn_VerProductosDC;
    ImageView ivFotoCharDE;
    Variables objUtil = Variables.getInstance();
    ImageButton ib_MapaCD;
    TextView tvHoraDC;
    TextView tvCuposDC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charla_detalle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        tvTituloCharlaDC = (TextView) findViewById(R.id.tvTituloCharlaDC);
        tvFechaHoraDC = (TextView) findViewById(R.id.tvFechaHoraDC);
        tvDescripcionDC = (TextView) findViewById(R.id.tvDescripcionDC);
        tvEstadoCharla = (TextView) findViewById(R.id.tvEstadoCharla);
        tvDireccCharlaDC = (TextView) findViewById(R.id.tvDireccCharlaDC);
        tvHoraDC = (TextView) findViewById(R.id.tv_HoraDC);
        tvCuposDC = (TextView) findViewById(R.id.tvCuposDC);
        rvListaExpositores = (RecyclerView) findViewById(R.id.rv_ListaExpositores);
        btn_SuscribirseDC = (Button) findViewById(R.id.btn_SuscribirseDC);
        btn_VerProductosDC = (Button) findViewById(R.id.btn_VerProductosCD);
        ib_MapaCD = (ImageButton) findViewById(R.id.ib_MapaCD);

        ivFotoCharDE = (ImageView) findViewById(R.id.ivFotoCharDE);
        btn_SuscribirseDC.setOnClickListener(this);
        btn_VerProductosDC.setOnClickListener(this);
        ib_MapaCD.setOnClickListener(this);
        // verificar estado de charla.

        tvTituloCharlaDC.setText(objUtil.charla.getNombre().toString());
        tvDescripcionDC.setText(objUtil.charla.getDescripcion().toString());
        if(objUtil.charla.getFechaHora().length() == 19) {
            String Fecha = objUtil.charla.getFechaHora().toString().substring(0, 10);
            tvFechaHoraDC.setText(Fecha);
            String hora = objUtil.charla.getFechaHora().toString().substring(11, 16);
           tvHoraDC.setText(hora);
        }
        if(objUtil.charla.getCupos() == 0){
            tvCuposDC.setText("NO HAY CUPOS DISPONIBLES");
            btn_SuscribirseDC.setVisibility(View.GONE);
        }else{
            tvCuposDC.setText("CUPOS DISPONIBLES");
        }
        tvDireccCharlaDC.setText(objUtil.charla.getDireccion());
        Picasso.with(getApplicationContext()).load(objUtil.charla.getFoto()).error(R.drawable.charlafoto).fit().into(ivFotoCharDE);


        getCharla(objUtil.charla.getIDCharla());
    }

    private void getCharla(int IDCharla) {
        try {
     /*   Charla_I charla_i = RetrofitClient.getClient().create(Charla_I.class);
        Call<Charla> charlaCall = charla_i.getCharlaxId(objUtil.charla.getIDCharla());
        charlaCall.enqueue(new Callback<Charla>() {
            @Override
            public void onResponse(Call<Charla> call, Response<Charla> response) {
                if(response.isSuccessful()){
                    objUtil.charla = response.body();
                    tvTituloCharlaDC.setText(objUtil.charla.getNombre().toString());
                    tvDescripcionDC.setText(objUtil.charla.getDescripcion().toString());
                    tvFechaHoraDC.setText(objUtil.charla.getFechaHora().toString());
                    tvDireccCharlaDC.setText(objUtil.charla.getDireccion());
                    Picasso.with(getApplicationContext()).load(objUtil.charla.getFoto()).error(R.drawable.charlafoto).fit().into(ivFotoCharDE);
*/
            // VARIFICAR
            UsuarioCharla_I usuarioCharla_i = RetrofitClient.getClient().create(UsuarioCharla_I.class);
            Call<UsuarioCharla> calluse = usuarioCharla_i.getListUsuarioCharla(objUtil.usuario.getIDUsuario(), objUtil.charla.getIDCharla());
            calluse.enqueue(new Callback<UsuarioCharla>() {
                @Override
                public void onResponse(Call<UsuarioCharla> call, Response<UsuarioCharla> response) {
                    if (response.isSuccessful()) {
                        UsuarioCharla usuarioCharla = response.body();
                        if (usuarioCharla != null) {
                            if (usuarioCharla.getIDUsuarioCharla() > 0) {
                                //if(usuarioCharla.get)
                                // tvEstadoCharla.setText("INSCRITO");
                                tvEstadoCharla.setVisibility(View.VISIBLE);
                                btn_VerProductosDC.setVisibility(View.VISIBLE);
                                btn_SuscribirseDC.setVisibility(View.GONE);
                            } else {
                                //tvEstadoCharla.setText("INSCRITO");
                                tvEstadoCharla.setVisibility(View.GONE);
                                btn_VerProductosDC.setVisibility(View.GONE);
                                btn_SuscribirseDC.setVisibility(View.VISIBLE);
                            }
                        } else {
                            // tvEstadoCharla.setText("INSCRITO");
                            tvEstadoCharla.setVisibility(View.GONE);
                            btn_VerProductosDC.setVisibility(View.GONE);
                            btn_SuscribirseDC.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<UsuarioCharla> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            // EXPOSITORES
            ListaExpositores(objUtil.charla.getIDCharla());
//                }
//            }
//            @Override
//            public void onFailure(Call<Charla> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void ListaExpositores(int IDCharla) {
        try {

            Expositor_I expositor_i = RetrofitClient.getClient().create(Expositor_I.class);
            Call<List<Expositor>> call = expositor_i.getListaExpositorCharlaId(IDCharla);
            call.enqueue(new Callback<List<Expositor>>() {
                @Override
                public void onResponse(Call<List<Expositor>> call, Response<List<Expositor>> response) {
                    if (response.isSuccessful()) {


                        List<Expositor> listaExpositor = response.body();
                        ExpositorAdapter adapter = new ExpositorAdapter(R.layout.item_lista_expositores,
                                listaExpositor, getApplicationContext());
                        rvListaExpositores.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rvListaExpositores.setAdapter(adapter);

                    } else {
                        Toast.makeText(getApplicationContext(), "No existe", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Expositor>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this,LogueoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    UsuarioCharla usuarioCharla = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_SuscribirseDC:

                break;
        }
        if (v == btn_SuscribirseDC) {
            // validar que ya no se encuentre inscrito
            try {
                if (objUtil.charla != null) {
                    usuarioCharla = new UsuarioCharla();
                    usuarioCharla.setIDUsuarioCharla(0);
                    usuarioCharla.setIDCharla(objUtil.charla.getIDCharla());
                    usuarioCharla.setIDUsuario(objUtil.usuario.getIDUsuario());
                    usuarioCharla.setAsistencia(0);
                    usuarioCharla.setJustificacion("");
                }
                if (usuarioCharla != null) {
                    UsuarioCharla_I usuarioCharla_i = RetrofitClient.getClient().create(UsuarioCharla_I.class);
                    Call<UsuarioCharla> call = usuarioCharla_i.getListUsuarioCharla(objUtil.usuario.getIDUsuario(), objUtil.charla.getIDCharla());
                    call.enqueue(new Callback<UsuarioCharla>() {
                        @Override
                        public void onResponse(Call<UsuarioCharla> call, Response<UsuarioCharla> response) {
                            if (response.isSuccessful()) {
                                UsuarioCharla Lista = response.body();
                                if (Lista != null) {
                                    Toast.makeText(getApplicationContext(), "Usuario ya se encuentra inscrito.", Toast.LENGTH_LONG).show();
                                } else {
                                    // INSERTAR CHARLA PARA USUARIO.
                                    UsuarioCharla_I usuarioCharla_i = RetrofitClient.getClient().create(UsuarioCharla_I.class);
                                    Call<UsuarioCharla> call2 = usuarioCharla_i.InsertUsuarioCharla(usuarioCharla);
                                    call2.enqueue(new Callback<UsuarioCharla>() {
                                        @Override
                                        public void onResponse(Call<UsuarioCharla> call, Response<UsuarioCharla> response) {

                                            if (response.isSuccessful()) {

                                                LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                                                View view2 = inflater.inflate(R.layout.alertdialog_inscripcion, null, false);

                                                new AlertDialog.Builder(CharlaDetalleActivity.this).setView(view2)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @TargetApi(11)
                                                            public void onClick(DialogInterface dialog, int id) {

                                                                btn_VerProductosDC.setVisibility(View.VISIBLE);
                                                                tvEstadoCharla.setVisibility(View.VISIBLE);
                                                                btn_SuscribirseDC.setVisibility(View.GONE);
                                                                dialog.cancel();
                                                            }

                                                        }).show();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UsuarioCharla> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UsuarioCharla> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v == btn_VerProductosDC) {
            Intent intent = new Intent(getApplicationContext(), ProductoActivity.class);
            // Bundle bundle= new Bundle();
            //bundle.putSerializable("charla", charla);
            //intent.putExtras(bundle);
            startActivity(intent);

        } else if (v == ib_MapaCD) {
            //Toast.makeText(getApplicationContext(), "INGRESO", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MapCharlaActivity.class);
            //intent.putExtra("longitud", );
            startActivity(intent);

        }
    }
}