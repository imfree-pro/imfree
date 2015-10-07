package com.server.suggest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-15.
 */
public class DBFriendSuggestHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "imfreeFriendSuggest";

    private static final String TABLE_FRIENDSUGGEST = "friendSuggest";

    private static final String KEY_ID = "id";
    private static final String KEY_SUGGESTSN = "suggestSN";
    private static final String KEY_USERSN = "userSN";
    private static final String KEY_CATEGORYSN = "categorySN";
    private static final String KEY_CATEGORYITEMSN = "itemSN";
    private static final String KEY_ACCPETUSERCOUNT = "acceptUserCount";
    private static final String KEY_DISPLAYNAME = "displayName";

    private static DBFriendSuggestHelper _instance;
    public static DBFriendSuggestHelper getInstance(Context context)
    {
        if (_instance == null )
            _instance = new DBFriendSuggestHelper(context);

        return _instance;
    }

    public DBFriendSuggestHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIENDSUGGEST_TABLE = "CREATE TABLE " + TABLE_FRIENDSUGGEST + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUGGESTSN + " TEXT NOT NULL,"
                + KEY_USERSN + " TEXT,"
                + KEY_CATEGORYSN + " TEXT,"
                + KEY_CATEGORYITEMSN + " TEXT,"
                + KEY_ACCPETUSERCOUNT + " TEXT,"
                + KEY_DISPLAYNAME + " TEXT"
                + ")";

        db.execSQL(CREATE_FRIENDSUGGEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDSUGGEST);
        onCreate(db);
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDSUGGEST, null, null);

        _suggestList = null;
    }

    public void resetSuggest(ArrayList<SuggestEntity> suggestList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDSUGGEST, null, null);

        for(SuggestEntity suggestEntity : suggestList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_SUGGESTSN , suggestEntity.getSuggestSN());
            values.put(KEY_USERSN , suggestEntity.getUserSN());
            values.put(KEY_CATEGORYSN , suggestEntity.getCategorySN());
            values.put(KEY_CATEGORYITEMSN , suggestEntity.getItemSN());
            values.put(KEY_ACCPETUSERCOUNT, suggestEntity.getAcceptedUserCount());
            values.put(KEY_DISPLAYNAME, suggestEntity.getDisplayName());
            db.insert(TABLE_FRIENDSUGGEST , null, values);
        }
        _suggestList = null;
    }

    public int updateSuggest(SuggestEntity suggestEntity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDSUGGEST, null, null);

        ContentValues values = new ContentValues();
        values.put(KEY_USERSN , suggestEntity.getUserSN());
        values.put(KEY_CATEGORYSN , suggestEntity.getCategorySN());
        values.put(KEY_CATEGORYITEMSN , suggestEntity.getItemSN());
        values.put(KEY_ACCPETUSERCOUNT, suggestEntity.getAcceptedUserCount());
        values.put(KEY_DISPLAYNAME, suggestEntity.getDisplayName());
        int count = db.update(TABLE_FRIENDSUGGEST, values, KEY_SUGGESTSN + " = ?",
                new String[] { String.valueOf(suggestEntity.getSuggestSN()) });

        _suggestList = null;
        return count;
    }

    private ArrayList<SuggestEntity> _suggestList = null;
    public ArrayList<SuggestEntity> getSuggestAllList()
    {
        if ( _suggestList == null ) {
            ArrayList<SuggestEntity> suggestList = new ArrayList<SuggestEntity>();
            String selectQuery = "SELECT  * FROM " + TABLE_FRIENDSUGGEST;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    SuggestEntity suggestEntity = new SuggestEntity(
                            cursor.getString(cursor.getColumnIndex(KEY_SUGGESTSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_USERSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_CATEGORYSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_CATEGORYITEMSN)),
                            cursor.getString(cursor.getColumnIndex(KEY_ACCPETUSERCOUNT)),
                            cursor.getString(cursor.getColumnIndex(KEY_DISPLAYNAME))
                    );
                    suggestList.add(suggestEntity);
                } while (cursor.moveToNext());
            }
            cursor.close();
            _suggestList = suggestList;
        }
        return _suggestList;
    }

    public SuggestEntity getSuggestInfo(String suggestSN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_FRIENDSUGGEST, null, KEY_SUGGESTSN + "=?", new String[]{String.valueOf(suggestSN)}, null, null, null, null);
        if ( cursor == null ) {
            return new SuggestEntity(
                    suggestSN,
                    "",
                    "",
                    "",
                    "",
                    ""
            );
        }
        cursor.moveToFirst();
        SuggestEntity suggestEntity = new SuggestEntity(
                cursor.getString(cursor.getColumnIndex(KEY_SUGGESTSN)),
                cursor.getString(cursor.getColumnIndex(KEY_USERSN)),
                cursor.getString(cursor.getColumnIndex(KEY_CATEGORYSN)),
                cursor.getString(cursor.getColumnIndex(KEY_CATEGORYITEMSN)),
                cursor.getString(cursor.getColumnIndex(KEY_ACCPETUSERCOUNT)),
                cursor.getString(cursor.getColumnIndex(KEY_DISPLAYNAME))
        );
        cursor.close();
        return suggestEntity;
    }
}
