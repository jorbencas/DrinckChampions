package com.herprogramacion.restaurantericoparico.adapters;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.herprogramacion.restaurantericoparico.ui.DataBaseHelper;

public class DBAdapter {
	//Campos de la BD
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_SUMMARY = "summary";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_IMAGELOCAL = "imagelocal";
	public static final String KEY_IMAGEDIR = "imagedir";
	public static final String KEY_MOREINFO = "moreinfo";
	public static final String KEY_LOCATE = "locate";
	private static final String DATABASE_TABLE = "todo";
	private Context context;
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;

	public DBAdapter(Context context) {
		this.context = context;
	}

	public DBAdapter open() throws SQLException {
		dbHelper = new DataBaseHelper(context);
		database = dbHelper.getWritableDatabase();
		//fill_db();
		return this;
	}

	public void close() {
		database.execSQL("delete from todo");
		dbHelper.close();
	}

	public void fill_db() {
		ContentValues initialValues = null;
		for (int i=0;i<10;i++) {
			initialValues = createContentValues("category_" + i, "summary_" + i,"description" + i, "number" + i,"imagelocal" + i, "imagedir" + i, "moreinfo" + i, "locate" + i);
			database.insert(DATABASE_TABLE, null, initialValues);
		}
	}

	/**
	 * Crea una nueva tarea, si esto va bien retorna la
	 * rowId de la tarea, de lo contrario retorna -1
	 */
	/*
	public long createTodo(String category, String summary, String description) {
		ContentValues initialValues = createContentValues(category, summary, description);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}
	*/
	public long createTodo(String category, String summary, String description, String number, String imagelocal, String imagedir, String moreinfo, String locate) {
		ContentValues initialValues = createContentValues(  category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	//Actualiza la tarea
	public boolean updateTodo(long rowId, String category, String summary, String description, String number, String imagelocal, String imagedir, String moreinfo, String locate) {
		ContentValues updateValues = createContentValues(category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	//Borra la tarea
	public boolean deleteTodo(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	//Returna un Cursor que contiene todos los items
	public Cursor fetchAllTodos() {
		return database.query(DATABASE_TABLE , new String[] { KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION, KEY_NUMBER, KEY_IMAGELOCAL, KEY_IMAGEDIR , KEY_MOREINFO, KEY_LOCATE }, null, null, null,
				null, null);
		//return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, null, null, null,
		//		null, null);
	}
	
	//Returna un Cursor que contiene la info del item
	public Cursor fetchTodo(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION, KEY_NUMBER, KEY_IMAGELOCAL, KEY_IMAGEDIR , KEY_MOREINFO, KEY_LOCATE },
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		//Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
		//		KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION},KEY_ROWID  + "=" + rowId, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(String category, String summary, String description, String number, String imagelocal, String imagedir, String moreinfo, String locate) {
		ContentValues values = new ContentValues();
		values.put(KEY_CATEGORY, category);
		values.put(KEY_SUMMARY, summary);
		values.put(KEY_DESCRIPTION, description);
		values.put(KEY_NUMBER, number);
		values.put(KEY_IMAGELOCAL, imagelocal);
		values.put(KEY_IMAGEDIR, imagedir);
		values.put(KEY_MOREINFO, moreinfo);
		values.put(KEY_LOCATE, locate);
		return values;
	}
}