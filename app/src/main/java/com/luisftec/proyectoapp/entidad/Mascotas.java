package com.luisftec.proyectoapp.entidad;

public class Mascotas {

    private  int masc_id;
    private  String masc_nombre;
    private  String masc_color;

    private  String masc_raza;
    private  int  masc_edad;
    private  String masc_genero;
    private  String masc_fecha;
    private  int esp_id;

    public Mascotas(String masc_nombre, String masc_color, String masc_raza, int masc_edad, String masc_genero, String masc_fecha, int esp_id) {
        this.masc_nombre = masc_nombre;
        this.masc_color = masc_color;
        this.masc_raza = masc_raza;
        this.masc_edad = masc_edad;
        this.masc_genero = masc_genero;
        this.masc_fecha = masc_fecha;
        this.esp_id = esp_id;
    }

    public Mascotas(int masc_id, String masc_nombre, String masc_color, String masc_raza, int masc_edad, String masc_genero, String masc_fecha, int esp_id) {
        this.masc_id = masc_id;
        this.masc_nombre = masc_nombre;
        this.masc_color = masc_color;
        this.masc_raza = masc_raza;
        this.masc_edad = masc_edad;
        this.masc_genero = masc_genero;
        this.masc_fecha = masc_fecha;
        this.esp_id = esp_id;
    }

    public int getMasc_id() {
        return masc_id;
    }

    public void setMasc_id(int masc_id) {
        this.masc_id = masc_id;
    }

    public String getMasc_nombre() {
        return masc_nombre;
    }

    public void setMasc_nombre(String masc_nombre) {
        this.masc_nombre = masc_nombre;
    }

    public String getMasc_color() {
        return masc_color;
    }

    public void setMasc_color(String masc_color) {
        this.masc_color = masc_color;
    }

    public String getMasc_raza() {
        return masc_raza;
    }

    public void setMasc_raza(String masc_raza) {
        this.masc_raza = masc_raza;
    }

    public int getMasc_edad() {
        return masc_edad;
    }

    public void setMasc_edad(int masc_edad) {
        this.masc_edad = masc_edad;
    }

    public String getMasc_genero() {
        return masc_genero;
    }

    public void setMasc_genero(String masc_genero) {
        this.masc_genero = masc_genero;
    }

    public String getMasc_fecha() {
        return masc_fecha;
    }

    public void setMasc_fecha(String masc_fecha) {
        this.masc_fecha = masc_fecha;
    }

    public int getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(int esp_id) {
        this.esp_id = esp_id;
    }
}
