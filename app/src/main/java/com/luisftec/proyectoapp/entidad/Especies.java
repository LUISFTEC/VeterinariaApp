package com.luisftec.proyectoapp.entidad;

public class Especies {

    private  int esp_id;
    private  String esp_nombre;


    public Especies(int esp_id, String esp_nombre) {
        this.esp_id = esp_id;
        this.esp_nombre = esp_nombre;
    }

    public int getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(int esp_id) {
        this.esp_id = esp_id;
    }

    public String getEsp_nombre() {
        return esp_nombre;
    }

    public void setEsp_nombre(String esp_nombre) {
        this.esp_nombre = esp_nombre;
    }

    @Override
    public String toString() {
        return esp_nombre;
    }
}
