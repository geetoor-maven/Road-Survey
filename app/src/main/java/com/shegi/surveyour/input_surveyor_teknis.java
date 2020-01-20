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
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class input_surveyor_teknis extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    SearchableSpinner  spinner,spinner1,spinner2,spinner3;
    private AdView mAdView,mAdView1,mAdView2,mAdView3;
    public static List<String> lst= null;
    public static List<String> list = null;
    public static List<String> list1 = null;
    public static List<String> list2 = null;
    String Url="http://projekccnt.com/pln/pln/spinnerpekerjaan.php";
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,munculgambar,imgatasmaterial,imgbawahmaterial;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5;
    ViewGroup viewGroup;
    TextView tanggal,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,satuankerja,latitude,longtitude,munculradiobutton;
    EditText editText1,editText2,feder,detailkerja,alamat,lokasi,keterangan,txtnama,jmlvolumematerial;
    Button pilihfile,kirim;
    float jumlah;

    Bitmap bitmap;
    RadioGroup radioGroup;
    RadioButton padam,tidakpadam;
    DatePickerDialog datePickerDialog;

    Api objek;
    private int GALLERY = 1, CAMERA = 101;

    public  input_surveyor_teknis(){
        objek = new Api();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_surveyor_teknis);
        new GetData().execute();
        new GetData1().execute();
        new GetData2().execute();
        new GetData3().execute();

        spinner= (SearchableSpinner) findViewById(R.id.country1);
        spinner1 = (SearchableSpinner) findViewById(R.id.country2);
        spinner2 = (SearchableSpinner) findViewById(R.id.country3);
        spinner3 = (SearchableSpinner) findViewById(R.id.country4);

        spinner.setTitle("Pilih Pekerjaan");
        spinner1.setTitle("Pilih Material");
        spinner2.setTitle("Pilih Satuan Material");
        spinner3.setTitle("pilih Satuan Pekerjaan");

        mAdView = (AdView) findViewById(R.id.adView);
        mAdView1 = (AdView) findViewById(R.id.adView1);
        mAdView2 = (AdView) findViewById(R.id.adView2);
        mAdView3 = (AdView) findViewById(R.id.adView3);



        AdRequest adRequest3 = new AdRequest.Builder()
                .build();
        mAdView3.loadAd(adRequest3);
        spinner3.setOnItemSelectedListener(this);


        AdRequest adRequest2 = new AdRequest.Builder()
                .build();
        mAdView2.loadAd(adRequest2);
        spinner2.setOnItemSelectedListener(this);

        AdRequest adRequest1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(adRequest1);
        spinner1.setOnItemSelectedListener(this);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        spinner.setOnItemSelectedListener(this);

        jmlvolumematerial = findViewById(R.id.jmlvolumematerial);
        imgatasmaterial = findViewById(R.id.imgatasmaterial);
        imgbawahmaterial = findViewById(R.id.imgbawahmaterial);

        txtnama = findViewById(R.id.txtnama);
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.imagemuncul);
        imageView3 = findViewById(R.id.imgatas);
        imageView4 = findViewById(R.id.imgbawah);
        imageView5 =findViewById(R.id.img2);
        imageView6 = findViewById(R.id.imgatas1);
        imageView7 = findViewById(R.id.imgbawah1);
        imageView8 =findViewById(R.id.img6);
        imageView9 = findViewById(R.id.img7);
        cardView1 =findViewById(R.id.visibiliti1);
        cardView2 = findViewById(R.id.visibiliti2);
        cardView3 = findViewById(R.id.visibiliti3);
        cardView4 = findViewById(R.id.visibiliti4);
        cardView5 = findViewById(R.id.card6);
        viewGroup = findViewById(R.id.relativeumum);
        txt1 = findViewById(R.id.karebosi);
        txt2 = findViewById(R.id.daya);
        txt3 = findViewById(R.id.maros);
        txt4 = findViewById(R.id.pangkep);
        txt5 =findViewById(R.id.ulp);
        txt6 =findViewById(R.id.pilihtemuan);
        txt7 = findViewById(R.id.kontruksi);
        txt8 = findViewById(R.id.row);
        txt9 = findViewById(R.id.material);
        txt10 = findViewById(R.id.proteksi);
        txt11 = findViewById(R.id.dll);
        txt12 = findViewById(R.id.statusurgensi);
        txt13 = findViewById(R.id.parah);
        txt14 = findViewById(R.id.sedang);
        txt15 = findViewById(R.id.ringan);
        txt16 = findViewById(R.id.timpekerja);
        txt17 = findViewById(R.id.timpdkb);
        txt18 = findViewById(R.id.timkhs);
        txt19 = findViewById(R.id.timrow);
        txt20 = findViewById(R.id.textsudahdipilih1);
        editText2 = findViewById(R.id.jmlvolume);
        editText1 = findViewById(R.id.jmlselection);
        pilihfile = findViewById(R.id.btnpilihfile1);
        kirim = findViewById(R.id.kirim);
        feder = findViewById(R.id.feder);
        detailkerja = findViewById(R.id.detailkerja);
        alamat = findViewById(R.id.alamat);
        tanggal = findViewById(R.id.tanggal);
        latitude = findViewById(R.id.latitude);
        longtitude = findViewById(R.id.longtitude);
        munculgambar = findViewById(R.id.gambarmuncul);
        munculradiobutton = (TextView)findViewById(R.id.radio) ;


        //set tanggal waktu
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalsekarang = simpleDateFormat.format(new Date());
        tanggal.setText(tanggalsekarang);


        //permision camera android 6 >

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA);
            }
        }

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int tahun = calendar.get(Calendar.YEAR);
                final int bulan = calendar.get(Calendar.MONTH);
                final int hari = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(input_surveyor_teknis.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggal.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },tahun,bulan,hari);
                datePickerDialog.show();
            }
        });


        radioGroup = findViewById(R.id.radiogroup);
        lokasi = findViewById(R.id.lokasi);
        keterangan = findViewById(R.id.keterangan);
        latitude.setText(getIntent().getStringExtra("Latitude"));
        longtitude.setText(getIntent().getStringExtra("Longtitude"));


       /* //simpanvariabel
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        saveulp =sharedPreferences.getString("saveulp", "PILIH ULP");
        txt5.setText(saveulp);
        savefeder = sharedPreferences.getString("savefeder", "");
        feder.setText(savefeder);
        savesection = sharedPreferences.getString("savesection", "");
        editText1.setText(savesection);
        savetemuan = sharedPreferences.getString("savetemuan", "PILIH TEMUAN");
        txt6.setText(savetemuan);*/


        //sampaisini
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radiobtn1){
            padam = findViewById(R.id.radiobtn1);
            String agg = new Integer(checkedId).toString();
            munculradiobutton.setText("Padam");
        }
        if (checkedId == R.id.radiobtn2){
            tidakpadam = findViewById(R.id.radiobtn2);
            String agg = new Integer(checkedId).toString();
            munculradiobutton.setText("Tidak padam");
        }
    }
});

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_surveyor_teknis.this,Mapsurveyorteknis.class);
                startActivity(intent);
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

               /* saveulp = txt5.getText().toString();
                savefeder = feder.getText().toString();
                savesection = editText1.getText().toString();
                savetemuan = txt6.getText().toString();

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(input_surveyor_teknis.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("Saveulp",saveulp);
                editor.putString("savefeder", savefeder);
                editor.putString("savesection", savesection);
                editor.putString("savetemuan", savetemuan);

                editor.apply();*/
            }
        });
        imgatasmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah + 1;
                jmlvolumematerial.setText(" "+jumlah);
            }
        });

        imgbawahmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah - 1;
                jmlvolumematerial.setText(" "+ jumlah);
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah - 1;
                editText2.setText(" "+ jumlah);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah + 1;
                editText2.setText(" "+ jumlah);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah - 1;
                editText1.setText(" "+ jumlah);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah = jumlah + 1 ;
                editText1.setText(" "+ jumlah);
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final boolean[] viisivle = new boolean[1];

                viisivle[0] = !viisivle[0];
                cardView4.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);

            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final boolean[] viisivle = new boolean[1];

                viisivle[0] = !viisivle[0];
                cardView3.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);

            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final boolean[] viisivle = new boolean[1];

                viisivle[0] = !viisivle[0];
                cardView2.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);

            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final boolean[] viisivle = new boolean[1];
                viisivle[0] = !viisivle[0];
                cardView1.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);

            }
        });
        txt19.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView4.setVisibility(View.GONE);
                txt16.setText("ROW");
            }
        });
        txt18.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView4.setVisibility(View.GONE);
                txt16.setText("KHS");
            }
        });
        txt17.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView4.setVisibility(View.GONE);
                txt16.setText("PDKB");
            }
        });
        txt15.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView3.setVisibility(View.GONE);
                txt12.setText("RINGAN");
            }
        });
        txt14.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView3.setVisibility(View.GONE);
                txt12.setText("SEDANG");
            }
        });
        txt13.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView3.setVisibility(View.GONE);
                txt12.setText("PARAH");
            }
        });
        txt11.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView2.setVisibility(View.GONE);
                txt6.setText("DLL");
            }
        });
        txt10.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView2.setVisibility(View.GONE);
                txt6.setText("PROTEKSI");
            }
        });
        txt9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView2.setVisibility(View.GONE);
                txt6.setText(" MATERIAL");
            }
        });
        txt8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView2.setVisibility(View.GONE);
                txt6.setText("ROW");
            }
        });
        txt7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                cardView2.setVisibility(View.GONE);
                txt6.setText("KONTRUKSI");
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.pangkep));
                imageView2.setVisibility(View.VISIBLE);
                cardView1.setVisibility(View.GONE);
                txt5.setText("PANGKEP");
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.maros));
                imageView2.setVisibility(View.VISIBLE);
                cardView1.setVisibility(View.GONE);
                txt5.setText("MAROS");
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.daya));
                imageView2.setVisibility(View.VISIBLE);
                cardView1.setVisibility(View.GONE);
                txt5.setText("DAYA");
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imageView2.setImageDrawable(getDrawable(R.drawable.karebosi));
                imageView2.setVisibility(View.VISIBLE);
                cardView1.setVisibility(View.GONE);
                txt5.setText("KAREBOSI");
            }
        });
        
        
        //tombolkirim
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();


            }
        });

        //tombolFoto
        pilihfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();

            }
        });
        
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = spinner1.getSelectedItem().toString();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
    //pencarian spinner satuanpekerjaan
    class GetData3 extends AsyncTask<String,String,String> {
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
            list2=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(objek.Spinnersatuanpekerjaan(),
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    list2.add(json.getString("satuan"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, list2);
                            spinner3.setAdapter(adapter);
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


    //pencariansatuan
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
            list=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(objek.spinnersatuan(),
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    list.add(json.getString("satuan"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, list);
                            spinner2.setAdapter(adapter);
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

    //pencarianmaterial
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
            list1=new ArrayList<String>();
            JsonArrayRequest movieReq = new JsonArrayRequest(objek.spinnermaterial(),
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject json = response.getJSONObject(i);
                                    list1.add(json.getString("nama_material"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ArrayAdapter adapter=new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, list1);
                            spinner1.setAdapter(adapter);
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

    //pencarianpekerjaan
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
                            spinner.setAdapter(adapter);
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

    private void Regist() {
        //progres dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Sedang mengirim....", "Mohon tunggu...", false, false);
        //finalllll
        final String namaa = this.txtnama.getText().toString().trim();
        final String ulpp = this.txt5.getText().toString().trim();
        final String federr = this.feder.getText().toString().trim();
        final String sectionn = this.editText1.getText().toString().trim();
        final String jenis_temuann = this.txt6.getText().toString().trim();
        final String uraian_pekerjaann = this.spinner.getSelectedItem().toString().trim();
        final String satuan_pekerjaan = this.spinner3.getSelectedItem().toString().trim();
        final String detail_kerjaa = this.detailkerja.getText().toString().trim();
        final String satuan_materiall = this.spinner1.getSelectedItem().toString().trim();
        final String satuan_kerjaa = this.spinner2.getSelectedItem().toString().trim();
        final String vol_kerjaa = this.editText2.getText().toString().trim();
        final String vol_material = this.jmlvolumematerial.getText().toString().trim();
        final String statuss = this.txt12.getText().toString().trim();
        final String pemadamann = this.munculradiobutton.getText().toString().trim();
        final String tim_pemeliharaa = this.txt16.getText().toString().trim();
        final String alamatt = this.alamat.getText().toString().trim();
        final String tgl_masukk = this.tanggal.getText().toString().trim();
        final String latitudee = this.latitude.getText().toString().trim();
        final String longtitudee = this.longtitude.getText().toString().trim();

        //kondisi apabila tidak menginput salah satu
        if (latitudee.isEmpty()) {

            loading.dismiss();
            AlertDialog.Builder dialog = new AlertDialog.Builder(input_surveyor_teknis.this);
            dialog.setTitle("Titik kordinat belum di input....!!");
            dialog.setMessage("Silahkan input ulang ");
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
            StringRequest stringRequest = new StringRequest(Request.Method.POST, objek.registteknis(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(input_surveyor_teknis.this, "Register Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(input_surveyor_teknis.this, BgDataTersimpan.class);
                            startActivity(intent);
                            loading.dismiss();
                            txt5.setText("Pilih Ulp");
                            feder.setText("");
                            editText1.setText("0");
                            txt6.setText("Pilih Temuan");
                            detailkerja.setText("");
                            editText2.setText("0");
                            txt12.setText("Status Urgensi");
                            txt16.setText("Pilih Tim");
                            alamat.setText("Alamat");
                            tanggal.setText("2019-1-1");
                            latitude.setText("");
                            longtitude.setText("");
                            lokasi.setHint("Lokasi Pekerjaan");
                            keterangan.setHint("Keterangan");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        loading.dismiss();
                        AlertDialog.Builder dialog = new AlertDialog.Builder(input_surveyor_teknis.this);
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
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(input_surveyor_teknis.this);
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
                    String image = imageToString(bitmap);
                    params.put("nama_surveyor", namaa);
                    params.put("ulp", ulpp);
                    params.put("feder", federr);
                    params.put("section", sectionn);
                    params.put("jenis_temuan", jenis_temuann);
                    params.put("pekerjaan", uraian_pekerjaann);
                    params.put("satuan_kerja", satuan_pekerjaan);
                    params.put("detail_kerja", detail_kerjaa);
                    params.put("material", satuan_materiall);
                    params.put("satuan_material", satuan_kerjaa);
                    params.put("vol_kerja", vol_kerjaa);
                    params.put("vol_material", vol_material);
                    params.put("pemadaman", pemadamann);
                    params.put("urgensi", statuss);
                    params.put("tim_pemelihara", tim_pemeliharaa);
                    params.put("alamat", alamatt);
                    params.put("tgl_masuk", tgl_masukk);
                    params.put("latitude", latitudee);
                    params.put("longtitude", longtitudee);
                    params.put("foto_sebelum", image);
                    return params;
                }
            };
            MySingeton.getInstance(this).addToRequestQuee(stringRequest);
        }
    }

    //FOTOOOOO
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
                    munculgambar.setImageBitmap(bitmap);
                    txt20.setText("Gambar telah di pilih.....");

                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(input_surveyor_teknis.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA){
            bitmap = (Bitmap)data.getExtras().get("data");
            munculgambar.setImageBitmap(bitmap);
            txt20.setText("Gambar telah di pilih.....");
        }
    }


    //imagetoString
    private String imageToString (Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodeImage;
    }




}
