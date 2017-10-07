package com.herprogramacion.restaurantericoparico.activities;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.adapters.ToDoItemsRecyclerAdapter;
import com.herprogramacion.restaurantericoparico.modelo.ToDoItem;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.recycler_view_items)
  RecyclerView recyclerView;
  @BindView(R.id.editTextItem) EditText editTextItem;

  private DatabaseReference databaseReference;
  private FirebaseRecyclerAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main1
    );
    ButterKnife.bind(this);

    SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
    String username = prefs.getString("username", null);
    setTitle(username);

    databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
    adapter = new ToDoItemsRecyclerAdapter(R.layout.row1, databaseReference);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_logout) {
      AuthUI.getInstance()
          .signOut(this)
          .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
              prefs.edit().clear().commit();

              Intent i = new Intent(MainActivity.this, LoginActivity.class);
              i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                  | Intent.FLAG_ACTIVITY_NEW_TASK
                  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(i);
              finish();
            }
          });
    }
    return super.onOptionsItemSelected(item);
  }
  @OnClick(R.id.fab)
  public void addToDoItem() { //databaseReference.push().setValue(toDoItem);
    SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
    String username = prefs.getString("username", null);

    String itemText = editTextItem.getText().toString();
    editTextItem.setText("");
    String description = "Heyy";
    float precio = 5.0f;
    int image = 1;
    String imagedir="Direccion web";
    float rating = 5.0f;
    int position = 1;
    String url = "eo";
    String geo = "eo";
    String panoid = "Direccion";

    InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    if (!itemText.isEmpty()) {
      ToDoItem toDoItem = new ToDoItem(itemText.trim(),username,description.trim(),precio,image,imagedir.trim(),rating,position,url.trim(),geo.trim(),panoid.trim());

      //databaseReference.push().setValue(toDoItem);
      databaseReference.child(itemText.trim()).setValue(toDoItem,new DatabaseReference.CompletionListener(){
        public void onComplete(DatabaseError error, DatabaseReference ref) {
          if(error == null)
            Toast.makeText(getApplicationContext(), "Alta OK", Toast.LENGTH_SHORT).show();
          else
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
}
