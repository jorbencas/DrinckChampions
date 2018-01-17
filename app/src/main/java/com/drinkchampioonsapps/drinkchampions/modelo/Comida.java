package com.drinkchampioonsapps.drinkchampions.modelo;

public class Comida {
    private float precio;
    private String nombre;
    private int idDrawable;
    private String descripcion;
    private float rating;
    private int position;

    public String getPanoID() {
        return panoID;
    }
    public void setPanoID(String panoID) {
        this.panoID = panoID;
    }
    private String panoID;
    private String url;
    private String geo;

    public Comida(float precio, String nombre, int idDrawable , String descripcion , float rating , int position,String url, String geo, String panoID) {
        this.precio = precio;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.descripcion = descripcion;
        this.rating = rating;
        this.position = position;
        this.geo = geo;
        this.url =url;
        this.panoID=panoID;
    }
    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public String getDescripcion(){
        return  descripcion;
    }

    public float getRating() {
        return rating;
    }

    public int getPosition(){return position;}

    public String getUrl() {
        return url;
    }

    public String getGeo() {
        return geo;
    }
}

