package com.shegi.surveyour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.shegi.surveyour.helper.Api;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class edit_verifikasi_data_tim_pemelihara extends AppCompatActivity implements SearchableSpinner.OnItemSelectedListener {
TextView text,textid,munculuraian,munculmaterial,munculsatuan;
ImageView muncul,atas,bawah;
EditText volume;
Button simpan,pilih_file;
SearchableSpinner spinneruraian,spinnermaterial,spinnersatuan;
    private AdView mAdView,mAdView1,mAdView2;
    public static List<String> lst= null;
    public static List<String> list = null;
    public static List<String> list1 = null;
Api objek;
private String id;
float jumlah;
private int GALLERY = 1, CAMERA = 101;
Bitmap bitmap;
public  edit_verifikasi_data_tim_pemelihara(){
        objek = new Api();
    }
    public final static  String Url = "http://projekccnt.com/pln/pln/spinnerpekerjaan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_verifikasi_data_tim_pemelihara);

        new GetData().execute();
        new GetData1().execute();
        new GetData2().execute();

        spinneruraian= (SearchableSpinner) findViewById(R.id.editcountry1);
        spinnermaterial = (SearchableSpinner) findViewById(R.id.editcountry2);
        spinnersatuan = (SearchableSpinner) findViewById(R.id.editcountry3);


        spinneruraian.setTitle("Pilih Pekerjaan");
        spinnermaterial.setTitle("Pilih Material");
        spinnersatuan.setTitle("Pilih Satuan Material");


        mAdView = (AdView) findViewById(R.id.adViewedit1);
        mAdView1 = (AdView) findViewById(R.id.adViewedit2);
        mAdView2 = (AdView) findViewById(R.id.adViewedit3);


        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        spinneruraian.setOnItemSelectedListener(this);


        AdRequest adRequest1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(adRequest1);
        spinnermaterial.setOnItemSelectedListener(this);


        AdRequest adRequest2 = new AdRequest.Builder()
                .build();
        mAdView2.loadAd(adRequest2);
        spinnersatuan.setOnItemSelectedListener(this);


        volume = findViewById(R.id.jmlvolume);
        simpan = findViewById(R.id.btnsimpan);
        pilih_file = findViewById(R.id.pilihfilebutton);
        text = findViewById(R.id.textsudahdipilih2);
        muncul = findViewById(R.id.munculgambar1);
        textid = findViewById(R.id.txtid);
        atas = findViewById(R.id.imgatas);
        bawah = findViewById(R.id.imgbawah);
        munculuraian = findViewById(R.id.munculuraian);
        munculmaterial = findViewById(R.id.munculmaterial);
        munculsatuan = findViewById(R.id.munculsatuan);

        textid.setText(getIntent().getStringExtra("id"));


        bawah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah - 1;
                volume.setText(""+jumlah);
            }
        });
        atas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              jumlah = jumlah + 1;
              volume.setText(""+jumlah);
            }
        });

    simpan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Savedata();
        }
    });


        pilih_file.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = spinneruraian.getSelectedItem().toString().trim();
//        String text1 = spinnermaterial.getSelectedItem().toString().trim();
//        String text2 = spinnersatuan.getSelectedItem().toString().trim();
//        munculuraian.setText(text);
//        munculmaterial.setText(text2);
//        munculsatuan.setText(text1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    //pencarianspinnersatuan

    class GetData2 extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
        @Override
        protected String doInBackground(String... params) {
            list1=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(objek.spinnersatuan(),
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    list1.add(json.getString("satuan"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, list1);
                            spinnersatuan.setAdapter(adapter);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(movieReq);
            return null;
        }

    }

    //pencarianspinnermaterial

    class GetData1 extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
        @Override
        protected String doInBackground(String... params) {
            list=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(objek.spinnermaterial(),
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    list.add(json.getString("nama_material"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, list);
                            spinnermaterial.setAdapter(adapter);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(movieReq);
            return null;
        }

    }

    //pencarianspinneruraian

    class GetData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
        @Override
        protected String doInBackground(String... params) {
            lst=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(Url,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    lst.add(json.getString("nama_pekerjaan"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, lst);
                            spinneruraian.setAdapter(adapter);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(movieReq);
            return null;
        }

    }

    private void Savedata() {

        final ProgressDialog loading = ProgressDialog.show(this,"Sedang mengirim....","Mohon tunggu...",false,false);
        final String iddata = this.textid.getText().toString().trim();
        final String uraian_pekerjaann = this.spinneruraian.getSelectedItem().toString().trim();
        final String satuan_kerja = this.spinnersatuan.getSelectedItem().toString().trim();
        final String volumee = this.volume.getText().toString().trim();
        final String material = this.spinnermaterial.getSelectedItem().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, objek.editdata(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                loading.dismiss();
                                Toast.makeText(edit_verifikasi_data_tim_pemelihara.this,"sukses",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(edit_verifikasi_data_tim_pemelihara.this, BgDataTersimpan.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            loading.dismiss();
                            AlertDialog.Builder dialog = new AlertDialog.Builder(edit_verifikasi_data_tim_pemelihara.this);
                            dialog.setTitle("Upss Data Belum Lengkap...");
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
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                AlertDialog.Builder dialog = new AlertDialog.Builder(edit_verifikasi_data_tim_pemelihara.this);
                dialog.setTitle("Ada Kesalahan Mohon Teliti...");
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                String imagee = getimageToString(bitmap);
                params.put("id",iddata);
                params.put("pekerjaan",uraian_pekerjaann);
                params.put("satuan_material",satuan_kerja);
                params.put("vol_kerja",volumee);
                params.put("material",material);
                params.put("foto_sesudah",imagee);

                return params;
            }
        };
        MySingeton.getInstance(edit_verifikasi_data_tim_pemelihara.this).addToRequestQuee(stringRequest);
    }

    public String getimageToString(Bitmap bmp){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodeImage;

    }


    private void showPictureDialog() {
        AlertDialog.Builder picturedialog = new AlertDialog.Builder(this);
        picturedialog.setTitle("Pilih Gambar : ");
        String[] picturedialogitems = {"Camera"};
        picturedialog.setItems(picturedialogitems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        //choosePhotoFromGallary();
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
                   muncul.setImageBitmap(bitmap);
                    text.setText("Gambar telah di pilih.....");

                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA){
            bitmap = (Bitmap)data.getExtras().get("data");
            muncul.setImageBitmap(bitmap);
            text.setText("Gambar telah di pilih.....");
        }
    }



}
