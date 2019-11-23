package com.cibertec.syscharla.Fragments;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;
import com.cibertec.syscharla.LogueoActivity;
import com.cibertec.syscharla.PrivacidadActivity;
import com.cibertec.syscharla.R;
import com.cibertec.syscharla.RetrofitClient;
import com.cibertec.syscharla.Variables;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiPerfilFragment extends Fragment implements View.OnClickListener{

    Button btnConfigurar;
    ImageButton ibCamaraMP;

    TextInputEditText tieNombresMP;
    TextInputEditText tieEmailMP;
    TextInputEditText tieDireccionMP;
    TextInputEditText tieApellidosMP;
    TextInputEditText tieNroCelularMP;
    FloatingActionButton fabGrabar;

    de.hdodenhof.circleimageview.CircleImageView ivFotoMP;
    private static final int REQUEST_TOMAR_FOTO = 100;
    private static final int REQUEST_PERMISO_CAMARA = 200;
    private static final int REQUEST_CONFIGURACION = 300;

    Variables objVar = Variables.getInstance();

    public MiPerfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mi_perfil, container, false);

        tieNombresMP = (TextInputEditText)rootView.findViewById(R.id.tie_NombresMP);
        tieEmailMP = (TextInputEditText)rootView.findViewById(R.id.tie_EmailMP);
        tieDireccionMP = (TextInputEditText)rootView.findViewById(R.id.tie_DireccionMP);
        tieApellidosMP = (TextInputEditText)rootView.findViewById(R.id.tie_ApellidosMP);
        tieNroCelularMP = (TextInputEditText)rootView.findViewById(R.id.tie_NroCelularMP);
        fabGrabar =  (FloatingActionButton) rootView.findViewById(R.id.fab_GrabarDatos);

        ibCamaraMP = (ImageButton)rootView.findViewById(R.id.ibCamaraMP);
        ivFotoMP = (de.hdodenhof.circleimageview.CircleImageView)rootView.findViewById(R.id.ivFotoMP);
        fabGrabar.setOnClickListener(this);

        // OBTERNER DATOS

        if(objVar.usuario != null){
            tieNombresMP.setText(objVar.usuario.getNombres());
            tieApellidosMP.setText(objVar.usuario.getApellidos());
            tieEmailMP.setText(objVar.usuario.getCorreo());
            tieDireccionMP.setText(objVar.usuario.getDireccion());
            tieNroCelularMP.setText(objVar.usuario.getCelular());
        }
        ibCamaraMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirCamara();
            }
        });
        btnConfigurar = (Button)rootView.findViewById(R.id.btnConfigurarMP);
        btnConfigurar.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {

        if(v == btnConfigurar){
            Intent intent = new Intent(getActivity(), PrivacidadActivity.class);
            startActivity(intent);
        }
        else if(v==fabGrabar){
            Usuario usuario = new Usuario();
            usuario.setIDUsuario(objVar.usuario.getIDUsuario());
            usuario.setNombres(tieNombresMP.getText().toString());
            usuario.setApellidos(tieApellidosMP.getText().toString());
            usuario.setCelular(tieNroCelularMP.getText().toString());
            usuario.setDireccion(tieDireccionMP.getText().toString());
            usuario.setLatitud("");
            usuario.setLongitud("");
            usuario.setFoto("");

            // validar registros
            if(tieNombresMP.getText().toString().length() == 0){
                Toast.makeText(getActivity(),"Ingrese Nombres",Toast.LENGTH_SHORT).show();
                return;
            }
            if(tieApellidosMP.getText().toString().length() == 0){
                Toast.makeText(getActivity(),"Ingrese Apellidos",Toast.LENGTH_SHORT).show();
                return;
            }
            if(tieDireccionMP.getText().toString().length() == 0){
                Toast.makeText(getActivity(),"Ingrese Dirección",Toast.LENGTH_SHORT).show();
                return;
            }
            Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
            Call<Usuario> call = usuario_i.updateusuario(usuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Actualización Exitoso", Toast.LENGTH_LONG).show();
                        objVar.usuario = response.body();

                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TOMAR_FOTO && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgeBitmap = (Bitmap) extras.get("data");
            ivFotoMP.setImageBitmap(imgeBitmap);
        }
    }
    private void AbrirCamara()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Si se necesita mostrar alguna explicación

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                // me han denegado el permiso una vez
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
            }
        }
    }
    private void SolicitarPermisoCamara() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISO_CAMARA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISO_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AbrirCamara();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA);
                    if (!showRationale) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
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
                        Toast.makeText(getActivity(),"Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private void AbrirConfiguracion() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CONFIGURACION);
    }

    //Grabar datos



}
