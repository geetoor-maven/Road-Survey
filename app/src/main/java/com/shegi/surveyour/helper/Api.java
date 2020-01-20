package com.shegi.surveyour.helper;



public class Api {

    private String localhost = "http://192.168.30.23/";
    private String server = "http://projekccnt.com/";


    private String login = "pln/pln/admin.php";
    private String registtekis = "pln/pln/RegisterTeknis.php";
    private String registumum = "pln/pln/Register.php";
    private String spinnerpekerjaan = "pln/pln/spinnerpekerjaan.php";
    private String spinermaterial = "pln/pln/spinnermaterial.php";
    private String spinnersatuan = "pln/pln/spinnersatuan.php";
    private  String Singel_data = "pln/pln/view_table.php";
    private String editdata = "pln/pln/editdata.php";
    private String spinnersatuanpekerjaan = "pln/pln/spinnersatuanpekerjaan.php";

    public String getServer(){
        return this.localhost;
    }
    public String getServerasli(){
        return this.server;
    }


    public String login(){
            return this.getServerasli()+this.login;
        }
    public String registteknis(){
        return this.getServerasli()+this.registtekis;
    }
    public String registumum(){
        return this.getServerasli()+this.registumum;
    }
    public String spinnermaterial(){
        return this.getServerasli()+this.spinermaterial;
    }
    public String spinnersatuan(){
        return this.getServerasli()+this.spinnersatuan;
    }
    public String Singel_data(){
        return this.getServerasli()+this.Singel_data;
    }
    public String editdata(){
        return this.getServerasli()+this.editdata;
    }
    public String Spinnersatuanpekerjaan(){
        return this.getServerasli()+this.spinnersatuanpekerjaan;
    }
}
