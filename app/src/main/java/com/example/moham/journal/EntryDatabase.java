package com.example.moham.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * When creating this, I looked at several online tutorials such as The New Boston
 * https://www.youtube.com/watch?v=Jcmp09LkU-I
 *
 */


// inheret from SQliteOpenHelper
public class EntryDatabase extends SQLiteOpenHelper {

    // set up constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "journal_db.db";

    public static final String TABLE_NAME = "journal";

    // column based on our class
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // method for creating the table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," //Auto Increment
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_CONTENT + " TEXT,"
                    + COLUMN_MOOD + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    private static EntryDatabase instance;

    /**
     * Default constructor
      * @param context
     */
    private EntryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // execute querty to create table
        db.execSQL(CREATE_TABLE);

        // Put in a test query to start
        db.execSQL("INSERT into "+ TABLE_NAME +" ("+COLUMN_TITLE+", " + COLUMN_CONTENT + ", " + COLUMN_MOOD + ")" + " values('Awesome ','I had the best meal ever today','smiley')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // upgrade database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // insert items into the journal database
    public long insert(JournalEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, entry.getTitle());
        values.put(COLUMN_CONTENT, entry.getContent());
        values.put(COLUMN_MOOD, entry.getMood());



        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // singlton code
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            EntryDatabase db = new EntryDatabase(context);
            instance = db;
        }
        return instance;
    }

    // A methond to select all in the database
    public Cursor selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);
        return cursor;
    }

    // Method to call a query that removes the entry with id
    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id)});
        db.close();
    }
}