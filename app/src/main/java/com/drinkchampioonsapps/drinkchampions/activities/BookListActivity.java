package com.drinkchampioonsapps.drinkchampions.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.adapters.BookAdapter;
import com.drinkchampioonsapps.drinkchampions.models.Book;
import com.drinkchampioonsapps.drinkchampions.net.BookClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class BookListActivity extends ActionBarActivity {
    public static String year = "2016";
    public static final String BOOK_DETAIL_KEY = "book";
    private ListView lvBooks;
    private BookAdapter bookAdapter;
    private BookClient client;
    private ProgressBar progress;
    public TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        ArrayList<Book> aBooks = new ArrayList<Book>();

        // initialize the adapter
        bookAdapter = new BookAdapter(this, aBooks);

        // attach the adapter to the ListView
        lvBooks.setAdapter(bookAdapter);
        progress = (ProgressBar) findViewById(R.id.progress);
        tvresult = (TextView) findViewById(R.id.tvresult);
        tvresult.setText("");

        setupBookSelectedListener();

        fetchBooks(".json");
    }

    public void setupBookSelectedListener() {
        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the detail view passing book as an extra
                Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                intent.putExtra(BOOK_DETAIL_KEY, bookAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks(String query) {
        // Show progress bar before making network request


        Log.e("***oooo***",query);


        progress.setVisibility(ProgressBar.VISIBLE);
        client = new BookClient();
        client.getBooks(query, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    Log.e("***oooo***",response.toString());
                    // hide progress bar
                    progress.setVisibility(ProgressBar.GONE);
                    int cont=0;
                    JSONObject docso = null;
                    JSONObject docso2 = null;
                    JSONArray docso3 = null;




                    if(response != null) {
                        // Get the docs json array

                        docso = response.getJSONObject("MRData");

                        docso2 = docso.getJSONObject("DriverTable");

                        docso3 = docso2.getJSONArray("Drivers");



                        Log.e("***oooo***",docso.toString());

                        Log.e("***oooo***",docso2.toString());

                        Log.e("***oooo***",docso3.toString());

                        // Parse json array into array of model objects
                        final ArrayList<Book> books = Book.fromJson(docso3);



                        // Remove all books from the adapter
                        bookAdapter.clear();
                        // Load model objects into the adapter

                        for (Book book : books) {
                            bookAdapter.add(book); // add book through the adapter
                            cont++;
                        }
                        bookAdapter.notifyDataSetChanged();

                        if (bookAdapter.isEmpty()) {
                            tvresult.setText("No se encontraron resultados");
                        } else if (cont==1) {
                            tvresult.setText(cont+" resultado");
                        }else{
                            tvresult.setText(cont+" resultados");
                        }

                    }

                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progress.setVisibility(ProgressBar.GONE);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Fetch the data remotely

                query=(query+".json");

                fetchBooks(query);
                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                // Set activity title to search query
                BookListActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_return) {
            fetchBooks(".json");
            return true;
        }
        */
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_return:
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc3:
                year = "2008";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc4:
                year = "2009";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc5:
                year = "2010";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc6:
                year = "2011";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc7:
                year = "2012";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc8:
                year = "2013";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc9:
                year = "2014";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc10:
                year = "2015";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc11:
                year = "2016";
                fetchBooks(".json");
                return true;
            case R.id.MnuOpc12:
                year = "2017";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
