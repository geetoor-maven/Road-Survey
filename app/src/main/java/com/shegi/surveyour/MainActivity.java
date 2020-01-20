package com.shegi.surveyour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> names;
    private CardView cardView,cardView1;
    private ImageView imageView;
    private ViewGroup viewGroup;
    private TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         cardView = (CardView) findViewById(R.id.visibiliti1);
         cardView1 = (CardView) findViewById(R.id.visibiliti2);
         imageView = (ImageView) findViewById(R.id.img1);
        viewGroup = (ViewGroup) findViewById(R.id.relative1);
        final boolean[] viisivle = new boolean[1];
        txt1 = (TextView)findViewById(R.id.suryorumum);
        txt2 =(TextView) findViewById(R.id.surveyorteknis);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, surveyorumum.class);
                startActivity(intent);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, surveyorteknis.class);
                startActivity(intent);
            }
        });
         imageView.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.KITKAT)
             @Override
             public void onClick(View v) {
                 TransitionManager.beginDelayedTransition(viewGroup);
                 viisivle[0] = !viisivle[0];
                 cardView1.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);
                 cardView.setVisibility(viisivle[0] ? View.VISIBLE: View.GONE);
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     imageView.setImageDrawable(getDrawable(R.drawable.spinner1));
                 }

             }
         });


        CardView  cardView = (CardView) findViewById(R.id.card2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, teampemeliharaan.class);
                startActivity(i);
            }
        });



    }



}