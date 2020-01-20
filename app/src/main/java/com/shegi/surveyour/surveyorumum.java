package com.shegi.surveyour;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.transition.TransitionManager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shegi.surveyour.helper.Api;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class surveyorumum extends AppCompatActivity {


    private TextView text, text1;
    private CardView cardView, cardView1, cardView2;
    private Button kirim, pilihfle;
    ViewGroup viewGroup;
    private ImageView imageView1, imageView2, imageView3, imageView4;
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txtlatitude, txtlongtitude,tanggal;
    TextView temuan;
    Bitmap bitmap;
    private EditText nama, alamat, detail_kerja;
    String savenama, saveulp, savetemuan;
    Api objek;
    private int GALLERY = 1;
    private int CAMERA = 101;
    DatePickerDialog datePickerDialog;
    Camera camera;



    public surveyorumum() {
        objek = new Api();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyorumum);
        setFinishOnTouchOutside(true);

        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        detail_kerja = findViewById(R.id.detail_kerja);
        tanggal = findViewById(R.id.tanggal);
        pilihfle = findViewById(R.id.btnpilihfile);
        kirim = findViewById(R.id.kirim);
        text = (TextView) findViewById(R.id.textsudahdipilih);
        text1 = findViewById(R.id.temuan);
        cardView = findViewById(R.id.visibiliti2);
        cardView1 = findViewById(R.id.visibiliti3);
        cardView2 = findViewById(R.id.card5);
        viewGroup = findViewById(R.id.relativeumum);
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.imagemuncul);
        imageView4 = findViewById(R.id.munculgambar);
        txt1 = findViewById(R.id.karebosi);
        temuan = findViewById(R.id.ulp);
        txt2 = findViewById(R.id.daya);
        txt3 = findViewById(R.id.maros);
        txt4 = findViewById(R.id.pangkep);
        txt5 = findViewById(R.id.kontruksi);
        txt6 = findViewById(R.id.row);
        txt7 = findViewById(R.id.material);
        txt8 = findViewById(R.id.proteksi);
        txt9 = findViewById(R.id.dll);
        txtlatitude = findViewById(R.id.txtlatitude);
        txtlongtitude = findViewById(R.id.txtlongtitude);
        imageView3 = findViewById(R.id.img2);


        //set tanggal waktu
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalsekarang = simpleDateFormat.format(new Date());
        tanggal.setText(tanggalsekarang);



        txtlatitude.setText(getIntent().getStringExtra("Latitude"));
        txtlongtitude.setText(getIntent().getStringExtra("Longtitude"));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        savenama = sharedPreferences.getString("Savenama", "");
        nama.setText(savenama);
        saveulp = sharedPreferences.getString("Saveulp", "ULP");
        temuan.setText(saveulp);
        savetemuan = sharedPreferences.getString("Savetemuan", "TEMUAN");
        text1.setText(savetemuan);

        final boolean[] viisivle = new boolean[1];

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int tahun = calendar.get(Calendar.YEAR);
                final int bulan = calendar.get(Calendar.MONTH);
                final int hari = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(surveyorumum.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggal.setText(year+"-"+(month+1)+"-"+dayOfMonth);

                    }
                },tahun,bulan,hari);
                datePickerDialog.show();
            }
        });


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(surveyorumum.this, MapsActivity.class);
                startActivity(intent);
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
                savenama = nama.getText().toString();
                saveulp = temuan.getText().toString();
                savetemuan = text1.getText().toString();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(surveyorumum.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Savenama", savenama);
                editor.putString("Saveulp", saveulp);
                editor.putString("Savetemuan", savetemuan);
                editor.apply();

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(viewGroup);
                viisivle[0] = !viisivle[0];
                cardView1.setVisibility(viisivle[0] ? View.VISIBLE : View.GONE);


            }
        });
        txt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                text1.setText("DLL");
            }
        });
        txt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                text1.setText("PROTEKSI");
            }
        });
        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                text1.setText("MATERIAL");
            }
        });
        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                text1.setText("ROW");
            }
        });
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                text1.setText("KONTRUKSI");
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.pangkep));
                imageView2.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.GONE);
                temuan.setText("PANGKEP");
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.maros));
                imageView2.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.GONE);
                temuan.setText("MAROS");
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.daya));
                imageView2.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.GONE);
                temuan.setText("DAYA");
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.karebosi));
                imageView2.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.GONE);
                temuan.setText("KAREBOSI");
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(viewGroup);
                viisivle[0] = !viisivle[0];
                cardView.setVisibility(viisivle[0] ? View.VISIBLE : View.GONE);


            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Regist();


            }
        });
        pilihfle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA);
            }
        }

    }


    private void Regist() {


        final ProgressDialog loading = ProgressDialog.show(this, "Sedang mengirim....", "Mohon tunggu...", false, false);


        final String nama_surveyour = this.nama.getText().toString().trim();
        final String detail_kerja = this.detail_kerja.getText().toString().trim();
        final String ulpp = this.temuan.getText().toString().trim();
        final String temuann = this.text1.getText().toString().trim();
        final String alamatt = this.alamat.getText().toString().trim();
        final String tanggall = this.tanggal.getText().toString().trim();
        final String latitude = this.txtlatitude.getText().toString().trim();
        final String longtitude = this.txtlongtitude.getText().toString().trim();

        if (latitude.isEmpty()) {

            loading.dismiss();
            AlertDialog.Builder dialog = new AlertDialog.Builder(surveyorumum.this);
            dialog.setTitle("Titik kordinat belum di input....!!");
            dialog.setMessage("Silahkan input kembali ");
            dialog.setCancelable(true);
            dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    loading.dismiss();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, objek.registumum(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    response.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            loading.dismiss();
                            Intent intent = new Intent(surveyorumum.this, BgDataTersimpan.class);
                            startActivity(intent);
                            nama.setText("");
                            text1.setText("PILIH TEMUAN");
                            temuan.setText("PILIH ULP");
                            alamat.setText("");
                            tanggal.setText("");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        loading.dismiss();
                        AlertDialog.Builder dialog = new AlertDialog.Builder(surveyorumum.this);
                        dialog.setTitle("Pastikan data sudah lengkap");
                        dialog.setMessage("Klik Ya Untuk Melanjutkan");
                        dialog.setCancelable(true);
                        dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();

                    AlertDialog.Builder dialog = new AlertDialog.Builder(surveyorumum.this);
                    dialog.setTitle("Data Belum Lengkap....!!");
                    dialog.setMessage("Klik Ya Untuk Melanjutkan");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String image = getimageToString(bitmap);
                    params.put("nama_surveyor", nama_surveyour);
                    params.put("detail_kerja", detail_kerja);
                    params.put("ulp", ulpp);
                    params.put("jenis_temuan", temuann);
                    params.put("alamat", alamatt);
                    params.put("tgl_masuk", tanggall);
                    params.put("latitude", latitude);
                    params.put("longtitude", longtitude);
                    params.put("foto_sebelum", image);

                    return params;
                }
            };
            MySingeton.getInstance(surveyorumum.this).addToRequestQuee(stringRequest);
        }
    }

    public String getimageToString(Bitmap bmp){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodeImage;

    }


//darisini
    //foto
private void showPictureDialog() {
    AlertDialog.Builder picturedialog = new AlertDialog.Builder(this);
    picturedialog.setTitle("Pilih Gambar : ");
    String[] picturedialogitems = {"Camera"};
    picturedialog.setItems(picturedialogitems, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case 0:
                    takePhotoFromCamera();
                    break;
                case 1:
                    choosePhotoFromGallary();
                    break;
            }
        }
    });
    picturedialog.show();
}

    public void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED){
            return;
        }
        if (requestCode == GALLERY){
            if (data!=null){
                Uri contentUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentUri);
                    imageView4.setImageBitmap(bitmap);
                    text.setText("Gambar telah di pilih.....");

                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(surveyorumum.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA){
            bitmap = (Bitmap)data.getExtras().get("data");
            imageView4.setImageBitmap(bitmap);
            text.setText("Gambar telah di pilih.....");
        }
    }

}
