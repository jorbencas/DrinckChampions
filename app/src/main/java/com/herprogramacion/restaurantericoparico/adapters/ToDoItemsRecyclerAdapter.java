package com.herprogramacion.restaurantericoparico.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.ToDoItem;
import com.herprogramacion.restaurantericoparico.ui.crudfilebase;

import butterknife.ButterKnife;
import butterknife.BindView;

public class ToDoItemsRecyclerAdapter extends FirebaseRecyclerAdapter<ToDoItem, ToDoItemsRecyclerAdapter.ToDoItemViewHolder> {

  public String item;

  public ToDoItemsRecyclerAdapter(int modelLayout, DatabaseReference ref) {
    super(ToDoItem.class, modelLayout, ToDoItemsRecyclerAdapter.ToDoItemViewHolder.class, ref);
  }

  @Override
  public ToDoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);
    return new ToDoItemViewHolder(view);
  }

  @Override
  protected void populateViewHolder(ToDoItemViewHolder holder, ToDoItem item, int position) {
    String itemDescription = item.getItem();
    String username = item.getUsername();

    holder.txtItem.setText(itemDescription);
    holder.txtUser.setText(username);
    if (item.isCompleted()) {
      holder.imgDone.setVisibility(View.VISIBLE);
    } else {
      holder.imgDone.setVisibility(View.INVISIBLE);
    }
  }

  class ToDoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    @BindView(R.id.txtItem) TextView txtItem;
    @BindView(R.id.txtUser) TextView txtUser;
    @BindView(R.id.imgDone) ImageView imgDone;

    public ToDoItemViewHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      itemView.setOnLongClickListener(this);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(final View view){ //reference.updateChildren(updates);

      int position = getAdapterPosition();



      ToDoItem currentItem = (ToDoItem)getItem(position);
      DatabaseReference reference = getRef(position);


      item = currentItem.getItem();

      Toast.makeText(view.getContext(), currentItem.getItem() , Toast.LENGTH_SHORT).show();


      crudfilebase.launch((Activity) view.getContext(),currentItem,reference);

      /*
      currentItem.setItem("Ole!");
      Map<String, Object> updates = new HashMap<String, Object>();
      updates.put("item", "Ole!");
      //reference.updateChildren(updates);
      reference.
              updateChildren(updates, new DatabaseReference.CompletionListener(){
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                  if(error == null)
                    Toast.makeText(view.getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                  else
                    Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
              });


      ToDoItem currentItem = (ToDoItem)getItem(position);
      DatabaseReference reference = getRef(position);
      boolean completed = !currentItem.isCompleted();

      currentItem.setCompleted(completed);
      Map<String, Object> updates = new HashMap<String, Object>();
      updates.put("completed", completed);
      //reference.updateChildren(updates);
      reference.
              updateChildren(updates, new DatabaseReference.CompletionListener(){
        public void onComplete(DatabaseError error, DatabaseReference ref) {
          if(error == null)
            Toast.makeText(view.getContext(), "Update OK", Toast.LENGTH_SHORT).show();
          else
            Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
      */
    }



    public ToDoItem getitems(int position) {
      ToDoItem currentItem = (ToDoItem)getItem(position);
      return currentItem;
    }

    public DatabaseReference getdatabase(int position) {
      DatabaseReference reference = getRef(position);
      return reference;
    }

    @Override
    public boolean onLongClick(final View view) { //reference.removeValue();
      int position = getAdapterPosition();
      DatabaseReference reference = getRef(position);
      //reference.removeValue();
      reference.removeValue(new DatabaseReference.CompletionListener() {
        public void onComplete(DatabaseError error, DatabaseReference ref) {
          if(error == null)
            Toast.makeText(view.getContext(), "Delete OK", Toast.LENGTH_SHORT).show();
          else
            Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
      return true;
    }
  }


}

