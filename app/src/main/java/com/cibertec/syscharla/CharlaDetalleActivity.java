package com.cibertec.syscharla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cibertec.syscharla.Clases.Charla;

import java.text.SimpleDateFormat;

public class CharlaDetalleActivity extends AppCompatActivity {


    TextView tvTituloCharlaDC;
    TextView tvFechaHoraDC;
    TextView tvDescripcionDC;
    TextView tvNombreExpositorDC;
    TextView tvDescrExpositor;
    TextView tvDireccCharlaDC;
    Button btn_SuscribirseDC;
    ImageView ivFotoExpoDC;
    ImageView ivFotoCharDE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charla_detalle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvTituloCharlaDC = (TextView) findViewById(R.id.tvTituloCharlaDC);
        tvFechaHoraDC= (TextView)findViewById(R.id.tvFechaHoraDC);
        tvDescripcionDC = (TextView)findViewById(R.id.tvDescripcionDC);
        tvNombreExpositorDC =(TextView) findViewById(R.id.tvNombreExpositorDC);
        tvDescrExpositor = (TextView)findViewById(R.id.tvDescrExpositor);
        tvDireccCharlaDC = (TextView)findViewById(R.id.tvDireccCharlaDC);
        btn_SuscribirseDC = (Button) findViewById(R.id.btn_SuscribirseDC);
        ivFotoExpoDC = (ImageView)findViewById(R.id.ivFotoExpoDC);
        ivFotoCharDE = (ImageView)findViewById(R.id.ivFotoCharDE);

        Bundle bundle = getIntent().getExtras();
        Charla charla = null;

        if(bundle != null) {
            charla = (Charla) bundle.getSerializable("charla");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String dateString = format.format(charla.getFecha());

            tvTituloCharlaDC.setText(charla.getNombre().toString());
            tvDescripcionDC.setText(charla.getDescripcion().toString());
            tvNombreExpositorDC.setText(charla.getExpositor().toString());
            tvFechaHoraDC.setText(dateString);
            ivFotoCharDE.setImageResource(charla.getIdFoto());
            ivFotoExpoDC.setImageResource(charla.getIdFotoExpositor());

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
