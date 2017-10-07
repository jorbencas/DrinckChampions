package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.herprogramacion.restaurantericoparico.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.herprogramacion.restaurantericoparico.modelo.Comida.panoID;


public class Details extends Activity {
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;
	private DBAdapter mDbHelper;
	private Spinner mCategory;
	private ImageView ivBookCover;
	private TextView tvTitle;
	private TextView tvAuthor;
	private TextView tvPublisher;
	private TextView tvPageCount;
	private TextView tvlink;
	private Button btnsave;
	private Button street;
	private String locate;
	private String copylink;
	private String imdir;
	private String imlocal;
	private DatabaseReference databaseReference;
	private DBAdapter dbHelper;


	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		mDbHelper = new DBAdapter(this);
		mDbHelper.open();
		setContentView(R.layout.activity_book_detail);

		ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvAuthor = (TextView) findViewById(R.id.tvAuthor);
		tvPublisher = (TextView) findViewById(R.id.tvPublisher);
		tvPageCount = (TextView) findViewById(R.id.tvPageCount);
		tvlink = (TextView) findViewById(R.id.tvlink);

		btnsave = (Button) findViewById(R.id.btnsave);
		street = (Button) findViewById(R.id.fab_street_view);


		mCategory = (Spinner) findViewById(R.id.category);


		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
		mRowId = null;
		Bundle extras = getIntent().getExtras();
		mRowId = (bundle == null) ? null : (Long) bundle.getSerializable(DBAdapter.KEY_ROWID);
		if (extras != null) {
			mRowId = extras.getLong(DBAdapter.KEY_ROWID);
		}
		populateFields();





	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void populateFields() {
		if (mRowId != null) {
			Cursor todo = mDbHelper.fetchTodo(mRowId);
			startManagingCursor(todo);
			String category = todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_CATEGORY));
			//for (int i=0; i<mCategory.getCount();i++){
				//String s = (String) mCategory.getItemAtPosition(i);
				//Log.e(null, s +" " + category);
				//if (s.equalsIgnoreCase(category)){
					//mCategory.setSelection(i);
				//}
			//}


			tvTitle.setText(todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_SUMMARY)));
			tvPublisher.setText(todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_DESCRIPTION)));
			tvAuthor.setText(todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_NUMBER)));
			tvlink.setText(todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_MOREINFO)));
			locate=todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_LOCATE));
			imdir=todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_IMAGEDIR));
			imlocal=todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_IMAGELOCAL));
			copylink=todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_MOREINFO));

			if (Objects.equals(imdir, "nd")) {
				int imdire=  Integer.parseInt(todo.getString(todo.getColumnIndexOrThrow(DBAdapter.KEY_IMAGELOCAL)));
				Glide.with(this).load(imdire).into(ivBookCover);
			} else {
				Picasso.with(this).load(Uri.parse(imdir)).error(R.drawable.ic_nocover).into(ivBookCover);
			}

			if (Objects.equals(locate, "nd")) {
				btnsave.setVisibility(View.INVISIBLE);
				btnsave.setText("More info");
			} else {
				btnsave.setVisibility(View.VISIBLE);
				btnsave.setText("Geolocalizar");
			}



		}
	}

	public void link(View v){

		String link = copylink;
		Intent intent = null;
		intent = new Intent(intent.ACTION_VIEW,Uri.parse(link));
		startActivity(intent);

	}


	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(DBAdapter.KEY_ROWID, mRowId);
	}

	public void save(View v){

		Uri uri = Uri.parse(locate);
		if (URLUtil.isValidUrl(uri.toString())) {
			startActivity( new Intent(Intent.ACTION_VIEW, uri));

		}
	}


	private void saveState() {
		String category = (String) mCategory.getSelectedItem();
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();
		String number = mBodyText.getText().toString();
		String imagelocal = mBodyText.getText().toString();
		String imagedir = mBodyText.getText().toString();
		String moreinfo = mBodyText.getText().toString();
		String locate = mBodyText.getText().toString();
		if (mRowId == null) {
			long id = mDbHelper.createTodo(category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
			if (id > 0) {
				mRowId = id;
			}
			Toast.makeText(getApplicationContext(), "Cerveza guardada", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "La cerveza ya esta guardada", Toast.LENGTH_SHORT).show();
			mDbHelper.updateTodo(mRowId, category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
		}
	}

	@Override
	protected void onDestroy() {
		Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}
