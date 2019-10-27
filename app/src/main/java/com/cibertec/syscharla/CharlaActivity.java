package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cibertec.syscharla.Clases.Charla;

import java.text.SimpleDateFormat;

import static android.view.View.GONE;

public class CharlaActivity extends AppCompatActivity {

    private TextView tvTitulo, tvDescripcion, tvExpositor, tvFecha, tvDireccion;
    private ImageView ivCharla;
    private Button btnProductos;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charla);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvExpositor = findViewById(R.id.tvExpositor);
        tvFecha = findViewById(R.id.tvFecha);
        tvDireccion = findViewById(R.id.tvDireccion);
        ivCharla = findViewById(R.id.ivCharla);
        btnProductos = findViewById(R.id.btnProductos);


        String opcion = getIntent().getStringExtra("Opcion");
        Bundle bundle = getIntent().getExtras();
        Charla charla = null;
        if(opcion != null){
            switch (opcion){
                case "Mis Charlas":
                    //btnAsistencia.setVisibility(GONE);
                    if(bundle != null){
                        charla = (Charla)bundle.getSerializable("charla");
                        final int id = charla.getId();
                        tvTitulo.setText(charla.getNombre());
                        tvDescripcion.setText(charla.getDescripcion());
                        tvExpositor.setText(charla.getExpositor());
                        ivCharla.setImageResource(charla.getIdFoto());
                        tvFecha.setText((dateFormat.format(charla.getFechahora())));
                        tvDireccion.setText(charla.getDireccion());

                        btnProductos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(), ProductoActivity.class);
                                intent.putExtra("id", id);
                                view.getContext().startActivity(intent);
                            }
                        });
                    }
                    break;
            }
        }


    }
}
