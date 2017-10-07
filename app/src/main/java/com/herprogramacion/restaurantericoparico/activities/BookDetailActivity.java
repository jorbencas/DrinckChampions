package com.herprogramacion.restaurantericoparico.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.adapters.DBAdapter;
import com.herprogramacion.restaurantericoparico.models.Book;

import com.squareup.picasso.Picasso;


public class BookDetailActivity extends ActionBarActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvPublisher;
    private TextView tvPageCount;
    private TextView tvlink;
    private Button btnsave;
    private String copylink;
    private String name;
    private String alias;
    private String date;
    private String nacio;
    private String image;
    private DatabaseReference databaseReference;
    private DBAdapter mDbHelper;
    private Long mRowId;
    private Book bookf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);


        mDbHelper = new DBAdapter(this);
        mDbHelper.open();

        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvPublisher = (TextView) findViewById(R.id.tvPublisher);
        tvPageCount = (TextView) findViewById(R.id.tvPageCount);
        tvlink = (TextView) findViewById(R.id.tvlink);
        btnsave = (Button) findViewById(R.id.btnsave);
        // Use the book to populate the data into our views
        Book book = (Book) getIntent().getSerializableExtra(BookListActivity.BOOK_DETAIL_KEY);
        loadBook(book);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
    }


    // Populate data for the book
    private void loadBook(Book book) {
        //change activity title
        this.setTitle(book.getTitle());
        bookf = book;

        // Populate data
        Picasso.with(this).load(Uri.parse(book.getLargeCoverUrl())).error(R.drawable.ic_nocover).into(ivBookCover);
        tvTitle.setText(book.getName());
        tvAuthor.setText(book.getAlias());
        tvPublisher.setText(book.getDatebirth());
        tvPageCount.setText(book.getNacionalidad());
        tvlink.setText(book.getLink());

        copylink = book.getLink();
        name = book.getName();
        alias = book.getAlias();
        date = book.getDatebirth();
        nacio = book.getNacionalidad();
        image = book.getLargeCoverUrl();

    }

    public void link(View v) {

        String link = copylink;
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);

    }




    public void save(View v){

        String category = "piloto";
        String summary = name;
        String description = date;
        String number = alias;
        String imagelocal = "nd";
        String imagedir = image;
        String moreinfo = copylink;
        String locate = "nd";

        if (mRowId == null) {
            long id = mDbHelper.createTodo(category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
            if (id > 0) {
                mRowId = id;
            }
            Toast.makeText(getApplicationContext(), "Piloto Guardado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "El piloto ya esta guardado", Toast.LENGTH_SHORT).show();
            mDbHelper.updateTodo(mRowId, category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
        }

    }
    /*
    public void save(View v){

        //Cambiar
        SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
        String username = prefs.getString("username", null);

        String itemText = name;

        if (!itemText.isEmpty()) {
            ToDoItem toDoItem = new ToDoItem(itemText.trim(), username);
            //databaseReference.push().setValue(toDoItem);
            databaseReference.child(itemText.trim()).setValue(toDoItem,new DatabaseReference.CompletionListener(){
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error == null)
                        Toast.makeText(getApplicationContext(), "Piloto Guardado", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.returnlist) {
            Intent intent = new Intent(this, BookListActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}