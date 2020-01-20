package com.shegi.surveyour;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shegi.surveyour.APi.Api;
import com.shegi.surveyour.model.spaceCrafts;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.shegi.surveyour.model.spaceCrafts.id_;


public class verifikasi_data_tim_pemeliharaan extends AppCompatActivity {
    String id;
   TextView textulp,textsatuan,textvolume,textmaterial;
   EditText idd;
    ImageView munculfoto;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_data_tim_pemeliharaan);
        id = getIntent().getStringExtra(id_);
        textulp = findViewById(R.id.txtulp);
        textsatuan = findViewById(R.id.txtsatuan);
        textvolume = findViewById(R.id.txtvolume);
        textmaterial = findViewById(R.id.txtmaterial);
        idd = findViewById(R.id.id);
        munculfoto = findViewById(R.id.munculfoto);

        edit = findViewById(R.id.btnedit);

        textulp.setTextSize(15);
        textsatuan.setTextSize(15);
        textvolume.setTextSize(15);
        textmaterial.setTextSize(15);


        bindData();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(verifikasi_data_tim_pemeliharaan.this,edit_verifikasi_data_tim_pemelihara.class);
               String iddata = idd.getText().toString();
               String volume = textvolume.getText().toString();
               intent.putExtra("id",iddata);
               intent.putExtra("volume",volume);
               startActivity(intent);

            }
        });



    }

    private void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(verifikasi_data.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        Call<List<spaceCrafts>> call = service.getSingelData(id);
        call.enqueue(new Callback<List<spaceCrafts>>() {
            @Override
            public void onResponse(Call<List<spaceCrafts>> call, Response<List<spaceCrafts>> response) {
                if (response.isSuccessful()){
                    for (int i=0; i<response.body().size(); i++){
                        idd.setText(response.body().get(i).getId());
                        textulp.setText(response.body().get(i).getPekerjaan());
                        textsatuan.setText(response.body().get(i).getMaterial());
                        textvolume.setText(response.body().get(i).getVol_kerja());
                        textmaterial.setText(response.body().get(i).getSatuan_material());
                        Glide.with(verifikasi_data_tim_pemeliharaan.this).load(response.body().get(i).getFoto_sebelum())
                                .fitCenter()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(munculfoto);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<spaceCrafts>> call, Throwable t) {

            }
        });
    }




}
