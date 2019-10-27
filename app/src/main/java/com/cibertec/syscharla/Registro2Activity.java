package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registro2Activity extends AppCompatActivity {


    ImageButton ibCamaraRE;
    de.hdodenhof.circleimageview.CircleImageView ivFoto;

    private static final int REQUEST_TOMAR_FOTO = 100;
    private static final int REQUEST_PERMISO_CAMARA = 200;
    private static final int REQUEST_CONFIGURACION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ibCamaraRE = (ImageButton) findViewById(R.id.ibCamaraRE);
        ivFoto = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.ivFotoDP);
        ibCamaraRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirCamara();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TOMAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgeBitmap = (Bitmap) extras.get("data");
            ivFoto.setImageBitmap(imgeBitmap);
        }
    }
    private void AbrirCamara()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Si se necesita mostrar alguna explicación

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // me han denegado el permiso una vez
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Abrir Camara").setMessage("Es necesario aceptar el persmiso")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SolicitarPermisoCamara();
                            }
                        }).setNegativeButton("Cancelar", null);
                builder.show();
            } else
                SolicitarPermisoCamara();

        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
            }
        }
    }
    private void SolicitarPermisoCamara() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISO_CAMARA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISO_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AbrirCamara();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    if (!showRationale) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("Abrir Configuración")
                                .setMessage("Si desea usar esta funcionalidad, gestione el permiso.")
                                .setPositiveButton("Abrir Configuración", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AbrirConfiguracion();
                                    }
                                }).setNegativeButton("Cancelar", null);
                        builder.show();
                    }else{
                        Toast.makeText(this,"Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private void AbrirConfiguracion() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CONFIGURACION);
    }
}
