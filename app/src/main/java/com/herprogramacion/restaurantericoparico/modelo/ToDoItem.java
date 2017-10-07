package com.herprogramacion.restaurantericoparico.modelo;

public class ToDoItem {
  private String item;
    private String description;
  private String username;
  private boolean completed;

    private float vol;
    private int image;
    private String imagedir;
    private float rating;
    private int positions;
    private String url;
    private String geo;
    private String panoid;

  public ToDoItem(){ }

  public ToDoItem(String item, String username, String description, float vol, int image, String imagedir, float rating, int positions, String url, String geo, String panoid) {
      this.username = username;
      this.item = item;
      this.completed = false;
      this.description = description;
      this.vol =vol;
      this.image = image;
      this.rating = rating;
      this.positions =positions;
      this.url =url;
      this.geo = geo;
      this.imagedir= imagedir;
      this.panoid=panoid;
  }

  //getters & setters

  public String getUsername() {
      return username;
  }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrecio() {
        return vol;
    }

    public void setPrecio(float precio) {
        this.vol = precio;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int position) {
        this.positions = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }


    public void setUsername(String username) {
      this.username = username;
  }

  public String getItem() {
      return item;
  }

  public void setItem(String item) {
      this.item = item;
  }

  public boolean isCompleted() {
      return completed;
  }

  public void setCompleted(boolean completed) {
      this.completed = completed;
  }
    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public String getImagedir() {
        return imagedir;
    }

    public void setImagedir(String imagedir) {
        this.imagedir = imagedir;
    }

    public String getPanoid() {
        return panoid;
    }

    public void setPanoid(String panoid) {
        this.panoid = panoid;
    }


}

