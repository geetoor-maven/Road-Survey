package com.shegi.surveyour;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shegi.surveyour.helper.Api;
import java.util.HashMap;
import java.util.Map;

public class teampemeliharaan extends AppCompatActivity {
    private EditText user,pass;
    private Button masuk;
    Api objek;

    public teampemeliharaan(){
        objek = new Api();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teampemeliharaan);

        user = findViewById(R.id.edit1);
        pass = findViewById(R.id.edit2);
        masuk = findViewById(R.id.btnmasuk);
        CheckBox cek = findViewById(R.id.cekpass);
        cek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });


    }
    private void login() {
        final ProgressDialog loading = ProgressDialog.show(this,"Sedang Masuk....","Mohon tunggu...",false,false);

        StringRequest request = new StringRequest(Request.Method.POST,objek.login() ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")){
                            startActivity(new Intent(getApplicationContext(),verifikasi_data.class));
                            loading.dismiss();
                        }else {
                            loading.dismiss();
                            Intent intent = new Intent(teampemeliharaan.this, BgHubungiAdmin.class);
                            startActivity(intent);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String >params = new HashMap<>();
                params.put("email",user.getText().toString());
                params.put("password",pass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

}
