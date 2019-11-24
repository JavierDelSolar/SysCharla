package com.cibertec.syscharla.Fragments;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiPerfilFragment extends Fragment implements View.OnClickListener{

    Button btnConfigurar;
    ImageButton ibCamaraMP;
    ImageButton ibCarpetaMP;

    TextInputEditText tieNombresMP;
    TextInputEditText tieEmailMP;
    TextInputEditText tieDireccionMP;
    TextInputEditText tieApellidosMP;
    TextInputEditText tieNroCelularMP;
    FloatingActionButton fabGrabar;
    de.hdodenhof.circleimageview.CircleImageView ivFotoMP;
    ImageView imageView5;

    private static final int REQUEST_TOMAR_FOTO = 100;
    private static final int REQUEST_PERMISO_CAMARA = 200;
    private static final int REQUEST_CONFIGURACION = 300;
    private static final int REQUEST_PERMISO_GALERIA = 400;
    private static final int REQUEST_ABRIR_GALERIA = 500;
    String rutaFoto;
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
        ibCarpetaMP = (ImageButton)rootView.findViewById(R.id.ibCarpetaMP);
        ivFotoMP = (de.hdodenhof.circleimageview.CircleImageView)rootView.findViewById(R.id.ivFotoMP);
        imageView5 = (ImageView)rootView.findViewById(R.id.imageView5);
        fabGrabar.setOnClickListener(this);

        // OBTERNER DATOS

        if(objVar.usuario != null) {

            tieNombresMP.setText(objVar.usuario.getNombres());
            tieApellidosMP.setText(objVar.usuario.getApellidos());
            tieEmailMP.setText(objVar.usuario.getCorreo());
            tieDireccionMP.setText(objVar.usuario.getDireccion());
            tieNroCelularMP.setText(objVar.usuario.getCelular());
            String foto = objVar.usuario.getFoto();
            try {
               // Toast.makeText(getActivity(), objVar.usuario.getFoto(), Toast.LENGTH_SHORT).show();

                Picasso.with(getContext()).load( foto).error(R.mipmap.perfil).into(ivFotoMP);
            } catch (Exception ex) {
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        ibCamaraMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirCamara();
            }
        });
        ibCarpetaMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        btnConfigurar = (Button)rootView.findViewById(R.id.btnConfigurarMP);
        btnConfigurar.setOnClickListener(this);
        return rootView;
    }
//    private Bitmap get_imagen(String url) {
//        Bitmap bm = null;
//        try {
//            URL _url = new URL(url);
//            URLConnection con = _url.openConnection();
//            con.connect();
//            InputStream is = con.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();
//        } catch (IOException e) {
//
//        }
//        return bm;
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TOMAR_FOTO && resultCode == getActivity().RESULT_OK) {
            //  Bundle extras = data.getExtras();
            //Bitmap imgeBitmap = (Bitmap) extras.get("data");
            //ivFoto.setImageBitmap(imgeBitmap);
            mostrarImagen();
        }
        if (requestCode == REQUEST_ABRIR_GALERIA && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            ivFotoMP.setImageURI(selectedImage);
        }
    }
    private void mostrarImagen() {
        int targetW = ivFotoMP.getWidth();
        int targetH = ivFotoMP.getHeight();

        // obtener dimensiones del bitmap
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        int fotoW = bmpOptions.outWidth;
        int fotoH = bmpOptions.outHeight;

        int scaleFactor = Math.min(fotoW / targetW, fotoH / targetH);

        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;
        bmpOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(rutaFoto, bmpOptions);
        Bitmap rotado = bitmap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            rotado = rotarSiSeRequiere(bitmap);
        }
        ivFotoMP.setImageBitmap(bitmap);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    Bitmap rotarSiSeRequiere(Bitmap bitmap) {
        Bitmap bmpRotado = null;
        InputStream in = null;

        try {
            in = getActivity().getContentResolver().openInputStream(Uri.fromFile(new File(rutaFoto)));
            ExifInterface exifInterface = new ExifInterface(in);
            int rotation = 0;
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotation = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotation = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotation = 270;
                    break;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);

            bmpRotado = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bmpRotado;
    }
    private void AbrirCamara()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Si se necesita mostrar alguna explicación

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                // me han denegado el permiso una vez
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
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
            dispatachTakePhoto();
            //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            //  startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
            // }
        }
    }
    private void dispatachTakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //  if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        File photoFile = null;
        try {
            photoFile = CreateImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (photoFile != null) {

            Uri photoUri = FileProvider.getUriForFile(getActivity(), "com.cibertec.syscharla.fileprovider", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
        }
    }
    private File CreateImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmdd").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        rutaFoto = image.getPath();
        return image;
    }

    private void SolicitarPermisoCamara() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISO_CAMARA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
           /* case REQUEST_PERMISO_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AbrirCamara();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    if (!showRationale) {
                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("Abrir Configuración")
                                .setMessage("Si desea usar esta funcionalidad, gestione el permiso.")
                                .setPositiveButton("Abrir Configuración", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AbrirConfiguracion();
                                    }
                                }).setNegativeButton("Cancelar", null);
                        builder.show();
                    } else {
                        Toast.makeText(this, "Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;*/
            case REQUEST_PERMISO_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AbrirCamara();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA);
                    if (!showRationale) {
                        openConfiguration();
                    } else {
                        Toast.makeText(getActivity(), "Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case REQUEST_PERMISO_GALERIA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirGaleria();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (!showRationale) {
                        openConfiguration();
                    } else {
                        Toast.makeText(getActivity(), "Requerimos el permiso para acceder a la galaría",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
    private void openConfiguration() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity())
                .setTitle("Abrir Configuración")
                .setMessage("Si desea usar esta funcionalidad, gestione el permiso.")
                .setPositiveButton("Abrir Configuración", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AbrirConfiguracion();
                    }
                }).setNegativeButton("Cancelar", null);
        builder.show();
    }
    private void AbrirConfiguracion() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CONFIGURACION);
    }

    private void abrirGaleria() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity())
                        .setTitle("Abrir Galeria")
                        .setMessage("Es necesario aceptar el permiso")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                solicitarPermisoGaleria();
                            }
                        })
                        .setNegativeButton("Cancelar", null);
                builder.show();
            } else
                solicitarPermisoGaleria();
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            String[] mimeTypes = {"images/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(intent, REQUEST_ABRIR_GALERIA);
        }
    }
    private void solicitarPermisoGaleria() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISO_GALERIA);
    }
    public String convertImageToBase64(Bitmap bm) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        assert bm != null;
        bm.compress(Bitmap.CompressFormat.JPEG, 60, bos);
        byte[] data = bos.toByteArray();
        return Base64.encodeToString(data, Base64.DEFAULT + '"');
    }
    @Override
    public void onClick(View v) {

        if(v == btnConfigurar){
            Intent intent = new Intent(getActivity(), PrivacidadActivity.class);
            startActivity(intent);
        }
        else if(v==fabGrabar){

            String image = "";
            try {
                Bitmap bitmap = ((BitmapDrawable) ivFotoMP.getDrawable()).getBitmap();
                image = convertImageToBase64(bitmap);

            } catch (Exception ex) {
            }


            Usuario usuario = new Usuario();
            usuario.setIDUsuario(objVar.usuario.getIDUsuario());
            usuario.setNombres(tieNombresMP.getText().toString());
            usuario.setApellidos(tieApellidosMP.getText().toString());
            usuario.setCelular(tieNroCelularMP.getText().toString());
            usuario.setDireccion(tieDireccionMP.getText().toString());
            usuario.setLatitud("");
            usuario.setLongitud("");
            usuario.setFoto(image);

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

    //Grabar datos



}

