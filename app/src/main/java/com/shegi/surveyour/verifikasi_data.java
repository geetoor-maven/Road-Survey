package com.shegi.surveyour;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import com.shegi.surveyour.mMysql.MysqlClient;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import static com.shegi.surveyour.R.id.tableview;
import static com.shegi.surveyour.model.spaceCrafts.id_;

public class verifikasi_data extends AppCompatActivity {
    public static final String ROOT_URL = "http://projekccnt.com/pln/pln/";
    TableView<String[]> tb;
    TableHelper tableHelper;
    String selesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_data);


        WebView webView = new WebView(this);
        webView.loadUrl("https://projekccnt.com/pln/pln/view_table.php");

        tableHelper=new TableHelper(this);

        tb=(TableView<String[]>)findViewById(tableview);

        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("#035b71"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,tableHelper.getSpaceProbeHeaders()));


        new MysqlClient(verifikasi_data.this).retrieve(tb);


        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @SuppressLint("NewApi")
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(verifikasi_data.this,"Sedang mengambil data ....", Toast.LENGTH_SHORT).show();
                String ids = clickedData[0];
                String idss = String.valueOf(ids);
                Intent intent = new Intent(verifikasi_data.this, verifikasi_data_tim_pemeliharaan.class);
                intent.putExtra(id_, idss);
                startActivityForResult(intent,1);

            }

        });

    }
}
