package com.server.suggest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 종열 on 2015-06-15.
 */
public class DBMySuggestHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "imfreeSuggest";

    private static final String TABLE_MYSUGGEST = "mySuggest";
    private static final String TABLE_MYSUGGESTACCEPTFRIEND = "mySuggestAcceptFriend";
    private static final String TABLE_ACCEPTSUGGEST = "acceptSuggest";

    private static final String KEY_ID = "id";
    private static final String KEY_SUGGESTSN = "suggestSN";
    private static final String KEY_HASHPHONE = "hashPhone";
    private static final String KEY_CATEGORYSN = "categorySN";
    private static final String KEY_CATEGORYITEMSN = "itemSN";
    private static final String KEY_ACCPETUSERCOUNT = "acceptUserCount";

    private static DBMySuggestHelper _instance;
    public static DBMySuggestHelper getInstance(Context context)
    {
        if (_instance == null )
            _instance = new DBMySuggestHelper(context);

        return _instance;
    }

    public DBMySuggestHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MYSUGGEST_TABLE = "CREATE TABLE " + TABLE_MYSUGGEST + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUGGESTSN + " TEXT,"
                + KEY_CATEGORYSN + " TEXT,"
                + KEY_CATEGORYITEMSN + " TEXT,"
                + KEY_ACCPETUSERCOUNT + " TEXT "
                + ")";

        String CREATE_MYSUGGEST_ACCEPTFRIEND_TABLE = "CREATE TABLE " + TABLE_MYSUGGESTACCEPTFRIEND + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUGGESTSN + " TEXT,"
                + KEY_HASHPHONE + " TEXT"
                + ")";

        String CREATE_ACCEPTSUGGEST_TABLE = "CREATE TABLE " + TABLE_ACCEPTSUGGEST + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUGGESTSN + " TEXT"
                + ")";
        db.execSQL(CREATE_MYSUGGEST_TABLE);
        db.execSQL(CREATE_ACCEPTSUGGEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MYSUGGEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MYSUGGESTACCEPTFRIEND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCEPTSUGGEST);
        onCreate(db);
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MYSUGGEST, null, null);
        db.delete(TABLE_ACCEPTSUGGEST, null, null);
    }

    public void resetSuggest(ArrayList<SuggestEntity> suggestList, HashSet<String> acceptSuggestList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MYSUGGEST, null, null);
        db.delete(TABLE_ACCEPTSUGGEST, null, null);

        for(SuggestEntity suggestEntity : suggestList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_SUGGESTSN , suggestEntity.getSuggestSN());
            values.put(KEY_CATEGORYSN , suggestEntity.getCategorySN());
            values.put(KEY_CATEGORYITEMSN , suggestEntity.getItemSN());
            values.put(KEY_ACCPETUSERCOUNT, suggestEntity.getAcceptedUserCount());
            db.insert(TABLE_MYSUGGEST , null, values);
        }

        for(String suggestSN : acceptSuggestList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_SUGGESTSN, suggestSN);
            db.insert(TABLE_ACCEPTSUGGEST, null, values);
        }
    }

    private HashSet<String> _acceptSuggestList = null;
    public HashSet<String> getAcceptSuggestList()
    {
        if ( _acceptSuggestList == null )
        {
            _acceptSuggestList = new HashSet<String>();
            String selectQuery = "SELECT * FROM " + TABLE_ACCEPTSUGGEST;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String suggestSN = cursor.getString(cursor.getColumnIndex(KEY_SUGGESTSN));
                    if ( _acceptSuggestList.contains(suggestSN) == false)
                        _acceptSuggestList.add(suggestSN);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return _acceptSuggestList;
    }

    public void removeAcceptSuggest(String suggestSN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCEPTSUGGEST, KEY_SUGGESTSN + " = ?", new String[]{suggestSN});
        _acceptSuggestList = null;
    }

    public void addAcceptSuggest(String suggestSN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUGGESTSN, suggestSN);
        db.insert(TABLE_ACCEPTSUGGEST, null, values);
    }

    private ArrayList<SuggestEntity> _suggestList = null;
    public ArrayList<SuggestEntity> getMySuggestAllList()
    {
        if ( _suggestList == null ) {
            ArrayList<SuggestEntity> suggestList = new ArrayList<SuggestEntity>();
            String selectQuery = "SELECT  * FROM " + TABLE_MYSUGGEST;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    SuggestEntity suggestEntity = new SuggestEntity(
                            cursor.getString(cursor.getColumnIndex(KEY_SUGGESTSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_CATEGORYSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_CATEGORYITEMSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_ACCPETUSERCOUNT))
                    );
                    suggestList.add(suggestEntity);
                } while (cursor.moveToNext());
            }
            cursor.close();
            _suggestList = suggestList;
        }
        return _suggestList;
    }

    public void addMySuggest(SuggestEntity suggestEntity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUGGESTSN , suggestEntity.getSuggestSN());
        values.put(KEY_CATEGORYSN , suggestEntity.getCategorySN());
        values.put(KEY_CATEGORYITEMSN , suggestEntity.getItemSN());
        values.put(KEY_ACCPETUSERCOUNT, suggestEntity.getAcceptedUserCount());
        db.insert(TABLE_MYSUGGEST , null, values);
    }

    public void removeMySuggest(String suggestSN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MYSUGGEST, KEY_SUGGESTSN + " = ?", new String[]{suggestSN});
        _acceptSuggestList = null;
    }
}
