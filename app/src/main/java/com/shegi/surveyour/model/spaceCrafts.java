package com.shegi.surveyour.model;

import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class spaceCrafts {

public static SharedPreferences.Editor editor;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tgl_masuk")
    @Expose
    private String tgl_masuk;
    @SerializedName("tgl_exp")
    @Expose
    private String tgl_exp;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("satuan_material")
    @Expose
    private String satuan_material;
    @SerializedName("vol_kerja")
    @Expose
    private String vol_kerja;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("foto_sebelum")
    @Expose
    private String foto_sebelum;
    @SerializedName("telah_selesai")
    @Expose
    private String selesai;
    public static final String id_ = "id";


    public spaceCrafts(String id, String tgl_masuk, String tgl_exp, String pekerjaan, String satuan_material,String vol_kerja, String material, String foto_sebelum ,String selesai){
        this.id = id;
        this.tgl_masuk = tgl_masuk;
        this.tgl_exp = tgl_exp;
        this.pekerjaan = pekerjaan;
        this.satuan_material = satuan_material;
        this.vol_kerja = vol_kerja;
        this.material = material;
        this.foto_sebelum = foto_sebelum;
        this.selesai = selesai;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public String getTgl_exp() {
        return tgl_exp;
    }

    public void setTgl_exp(String tgl_exp) {
        this.tgl_exp = tgl_exp;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getSatuan_material(){
        return satuan_material;
    }

    public void setSatuan_Material(String satuan_material) {
        this.satuan_material = satuan_material;
    }

    public String getVol_kerja() {
        return vol_kerja;
    }

    public void setVol_kerja(String vol_kerja) {
        this.vol_kerja = vol_kerja;
    }

    public String getMaterial() {
        return material;
    }

    public void set_Material(String material) {
        this.material = material;
    }
    public String getFoto_sebelum(){
        return foto_sebelum;
    }
    public void setFoto_sebelum(String foto_sebelum){
        this.foto_sebelum = foto_sebelum;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai)
    {
       this.selesai = selesai;
    }

}
