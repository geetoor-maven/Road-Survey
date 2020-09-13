package com.shegi.surveyour.mMysql;
import android.content.Context;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.shegi.surveyour.TableHelper;
import com.shegi.surveyour.helper.Api;
import com.shegi.surveyour.model.spaceCrafts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
public class MysqlClient {
    private final Context c;
    private SimpleTableDataAdapter adapter;
    Api objek;
    public MysqlClient(Context c){
        this.c = c;
        objek = new Api();
    }


    public void retrieve(final TableView tb){
        final ArrayList<spaceCrafts> spacecrafts=new ArrayList<>();
        AndroidNetworking.get(objek.Singel_data())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        spaceCrafts s;
                        try {
                            for (int i=0;i<response.length();i++){
                                jo=response.getJSONObject(i);
                                String id=jo.getString("id");
                                String tgl_masuk=jo.getString("tgl_masuk");
                                String tgl_exp=jo.getString("tgl_exp");
                                String  pekerjaan= jo.getString("pekerjaan");
                                String satuan_material = jo.getString("satuan_material");
                                String vol_kerja = jo.getString("vol_kerja");
                                String material = jo.getString("material");
                                String foto_sebelum = jo.getString("foto_sebelum");
                                String selesai = jo.getString("telah_selesai");
                                s = new spaceCrafts(id,tgl_masuk,tgl_exp,pekerjaan,satuan_material,vol_kerja,material,foto_sebelum, selesai);
                                s.setId(id);
                                s.setTgl_masuk(tgl_masuk);
                                s.setTgl_exp(tgl_exp);
                                s.setPekerjaan(pekerjaan);
                                s.setSatuan_Material(satuan_material);
                                s.setVol_kerja(vol_kerja);
                                s.set_Material(material);
                                s.setFoto_sebelum(foto_sebelum);
                                s.setSelesai(selesai);


                                spacecrafts.add(s);
                            }
                            adapter = new SimpleTableDataAdapter(c,new TableHelper(c).returnSpaceProbesArray(spacecrafts));
                            adapter.setTextSize(15);

                            tb.setDataAdapter(adapter);


                        }catch (JSONException e){
                            Toast.makeText(c,"failed = "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(c,"failed = "+anError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
