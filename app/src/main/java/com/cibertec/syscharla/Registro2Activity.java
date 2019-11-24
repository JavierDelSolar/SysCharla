package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

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
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro2Activity extends AppCompatActivity implements View.OnClickListener {


    ImageButton ibCamaraRE;
    Button btn_RegistrarRE;
    TextInputEditText tieNombresRE;
    TextInputEditText tieApellidosRE;
    TextInputEditText tieNroCelularRE;
    TextInputEditText tieDireccionRE;
    ImageButton ibCarpetaRE;

    Usuario usuario = null;
    String rutaFoto;
    de.hdodenhof.circleimageview.CircleImageView ivFoto;
    //ImageView ivFoto;
    private static final int REQUEST_TOMAR_FOTO = 100;
    private static final int REQUEST_PERMISO_CAMARA = 200;
    private static final int REQUEST_CONFIGURACION = 300;
    private static final int REQUEST_PERMISO_GALERIA = 400;
    private static final int REQUEST_ABRIR_GALERIA = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //obtener parametros.
        Bundle parametros = this.getIntent().getExtras();
        usuario = (Usuario) parametros.getSerializable("Usuario");


        btn_RegistrarRE = (Button) findViewById(R.id.btn_RegistrarRE);
        tieNombresRE = (TextInputEditText) findViewById(R.id.tie_NombresRE);
        tieApellidosRE = (TextInputEditText) findViewById(R.id.tie_ApellidosRE);
        tieNroCelularRE = (TextInputEditText) findViewById(R.id.tie_NombresRE);
        tieDireccionRE = (TextInputEditText) findViewById(R.id.tie_DireccionRE);


        ibCamaraRE = (ImageButton) findViewById(R.id.ibCamaraRE);
        ibCarpetaRE = (ImageButton) findViewById(R.id.ibCarpetaRE);
        ivFoto = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.ivFotoDP);
        //ivFoto = (ImageView) findViewById(R.id.ivFotoDP);
        ibCamaraRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirCamara();
            }
        });
        ibCarpetaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        btn_RegistrarRE.setOnClickListener(this);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TOMAR_FOTO && resultCode == this.RESULT_OK) {
            //  Bundle extras = data.getExtras();
            //Bitmap imgeBitmap = (Bitmap) extras.get("data");
            //ivFoto.setImageBitmap(imgeBitmap);
            mostrarImagen();
        }
        if (requestCode == REQUEST_ABRIR_GALERIA && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            ivFoto.setImageURI(selectedImage);
        }
    }
    private void mostrarImagen() {
        int targetW = ivFoto.getWidth();
        int targetH = ivFoto.getHeight();

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
        ivFoto.setImageBitmap(bitmap);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    Bitmap rotarSiSeRequiere(Bitmap bitmap) {
        Bitmap bmpRotado = null;
        InputStream in = null;

        try {
            in = getContentResolver().openInputStream(Uri.fromFile(new File(rutaFoto)));
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Si se necesita mostrar alguna explicación

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // me han denegado el permiso una vez
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
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

            Uri photoUri = FileProvider.getUriForFile(this, "com.cibertec.syscharla.fileprovider", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
        }
    }
    private File CreateImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmdd").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        rutaFoto = image.getPath();
        return image;
    }

    private void SolicitarPermisoCamara() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISO_CAMARA);
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
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    if (!showRationale) {
                        openConfiguration();
                    } else {
                        Toast.makeText(this, "Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case REQUEST_PERMISO_GALERIA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirGaleria();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (!showRationale) {
                        openConfiguration();
                    } else {
                        Toast.makeText(this, "Requerimos el permiso para acceder a la galaría",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
    private void openConfiguration() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
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
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CONFIGURACION);
    }

    private void abrirGaleria() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
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
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISO_GALERIA);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_RegistrarRE) {

            // validar registros
            if (tieNombresRE.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Nombres", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tieApellidosRE.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Apellidos", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tieDireccionRE.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Dirección", Toast.LENGTH_SHORT).show();
                return;
            }
            RegistrarUsuario();
        }
    }

    public String convertImageToBase64(Bitmap bm) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        assert bm != null;
        bm.compress(Bitmap.CompressFormat.JPEG, 60, bos);
        byte[] data = bos.toByteArray();
        return Base64.encodeToString(data, Base64.DEFAULT + '"');
    }

    private void RegistrarUsuario() {

   /*     progressDialog = new ProgressDialog(RetrofitActivity6.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();*/
        // obtener imagen
        String image = "";
        try {
            Bitmap bitmap = ((BitmapDrawable) ivFoto.getDrawable()).getBitmap();
            image = convertImageToBase64(bitmap);

        } catch (Exception ex) {
        }
        if (usuario != null) {
            int retval = 0;
            usuario.setNombres(tieNombresRE.getText().toString());
            usuario.setApellidos(tieApellidosRE.getText().toString());
            usuario.setDireccion(tieDireccionRE.getText().toString());
            usuario.setLatitud("");
            usuario.setLongitud("");
            usuario.setTipoUsuario(2);
            usuario.setFoto(image);
            usuario.setCelular(tieNroCelularRE.getText().toString());

            Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
            Call<Usuario> call = usuario_i.InsertUsuario(usuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LogueoActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
