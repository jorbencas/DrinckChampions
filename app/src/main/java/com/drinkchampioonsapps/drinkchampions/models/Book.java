package com.drinkchampioonsapps.drinkchampions.models;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {

    private String name;
    private String title;
    private String permanentNumber;
    private String datebirth;
    private String nacionalidad;
    private String alias;
    private String link;

    public String getPermanentNumber() {
        return permanentNumber;
    }
    public String getDatebirth() {
        return datebirth;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }
    public String getLink() {
        return link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }


    // Get medium sized book cover from covers API
    public String getCoverUrl() {
        Log.e("http://formula1.lne.es/media/pilotos/medium/"+formateo(name.toLowerCase())+"-"+formateo(alias.toLowerCase())+".jpg", "getCoverUrl: ");
        return "http://formula1.lne.es/media/pilotos/medium/"+formateo(name.toLowerCase())+"-"+formateo(alias.toLowerCase())+".jpg";
    }

    // Get large sized book cover from covers API
    public String getLargeCoverUrl() {
        return "http://formula1.lne.es/media/pilotos/medium/"+formateo(name.toLowerCase())+"-"+formateo(alias.toLowerCase())+".jpg";
    }

    // Returns a Book given the expected JSON
    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            book.title = jsonObject.has("driverId") ? jsonObject.getString("driverId") : "";
            book.permanentNumber = jsonObject.has("permanentNumber") ? jsonObject.getString("permanentNumber") : "";
            book.name = jsonObject.has("givenName") ? jsonObject.getString("givenName") : "";
            book.alias = jsonObject.has("familyName") ? jsonObject.getString("familyName") : "";
            book.datebirth=jsonObject.has("dateOfBirth") ? jsonObject.getString("dateOfBirth") : "";
            book.nacionalidad=jsonObject.has("nationality") ? jsonObject.getString("nationality") : "";
            book.link=jsonObject.has("url") ? jsonObject.getString("url") : "";

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray("author_name");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Book> fromJson(JSONArray jsonArray) {


        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }


        return books;
    }

    public static String formateo(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùüuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }
}
