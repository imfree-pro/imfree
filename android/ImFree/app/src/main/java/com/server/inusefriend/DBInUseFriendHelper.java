package com.server.inusefriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.handler._entity.InUseFriendCountEntity;
import com.handler._entity.InUseFriendEntity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 종열 on 2015-06-15.
 */
public class DBInUseFriendHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "imfreeInUseFriend";

    private static final String TABLE_INUSEFRIEND = "inUseFriend";
    private static final String TABLE_INUSEFRIENDCOUNT = "inUseFriendCount";

    private static final String KEY_ID = "id";
    private static final String KEY_PHOTOID = "photoId";
    private static final String KEY_THUMBURL = "thumbUri";
    private static final String KEY_HASHPHONE = "hashPhone";
    private static final String KEY_USERSN = "userSN";
    private static final String KEY_DISPLAYNAME = "displayName";

    private static final String KEY_COUNT = "count";

    public DBInUseFriendHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DBInUseFriendHelper _instance = null;
    public static DBInUseFriendHelper getInstance(Context context)
    {
        if(_instance == null) {
            _instance = new DBInUseFriendHelper(context);
        }
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INUSEFIEND_TABLE = "CREATE TABLE " + TABLE_INUSEFRIEND + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERSN + " TEXT NOT NULL,"
                + KEY_DISPLAYNAME + " TEXT NOT NULL,"
                + KEY_PHOTOID + " TEXT,"
                + KEY_THUMBURL + " TEXT,"
                + KEY_HASHPHONE + " TEXT"
                + ")";

        String CREATE_INUSEFIENDCOUNT_TABLE = "CREATE TABLE " + TABLE_INUSEFRIENDCOUNT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERSN + " TEXT NOT NULL,"
                + KEY_COUNT + " TEXT NOT NULL"
                + ")";
        db.execSQL(CREATE_INUSEFIEND_TABLE);
        db.execSQL(CREATE_INUSEFIENDCOUNT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INUSEFRIEND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INUSEFRIENDCOUNT);
        onCreate(db);
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INUSEFRIEND, null, null);
        _inUseFriendCount = null;
    }

    public void reset(ArrayList<InUseFriendEntity> inUserFriendList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INUSEFRIEND, null, null);

        for(InUseFriendEntity contact : inUserFriendList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_USERSN, contact.getUserSN());
            values.put(KEY_DISPLAYNAME, contact.getDisplayName());
            values.put(KEY_PHOTOID, contact.getPhotoId());
            values.put(KEY_THUMBURL, contact.getThumbUrl());
            values.put(KEY_HASHPHONE, contact.getHashPhone());

            db.insert(TABLE_INUSEFRIEND, null, values);
        }
        _inUseFriendCount = null;
    }

    public void add(ArrayList<InUseFriendEntity> inUserFriendList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(InUseFriendEntity entity : inUserFriendList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_USERSN, entity.getUserSN());
            values.put(KEY_DISPLAYNAME, entity.getDisplayName());
            values.put(KEY_PHOTOID, entity.getPhotoId());
            values.put(KEY_THUMBURL, entity.getThumbUrl());
            values.put(KEY_HASHPHONE, entity.getHashPhone());

            db.insert(TABLE_INUSEFRIEND, null, values);
        }
        _inUseFriendCount = null;
    }

    private HashMap<String, String> _inUseFriendCount = null;
    public void addCount(ArrayList<InUseFriendCountEntity> countList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INUSEFRIENDCOUNT, null, null);
        for(InUseFriendCountEntity contact : countList)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_USERSN, contact.getUserSN());
            values.put(KEY_COUNT, contact.getCount());

            db.insert(TABLE_INUSEFRIENDCOUNT, null, values);
        }
        _inUseFriendCount = null;
    }

    public HashMap<String, String> getAllCount()
    {
        if ( _inUseFriendCount != null )
            return _inUseFriendCount;

        HashMap<String, String> inUseFriendCount = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_INUSEFRIENDCOUNT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                InUseFriendCountEntity entity = new InUseFriendCountEntity(
                        cursor.getString(cursor.getColumnIndex(KEY_USERSN)),
                        cursor.getString(cursor.getColumnIndex(KEY_COUNT))
                );

                if ( inUseFriendCount.containsKey(entity.getUserSN()) == false)
                    inUseFriendCount.put(entity.getUserSN(), entity.getCount());

            } while (cursor.moveToNext());
        }
        cursor.close();
        _inUseFriendCount = inUseFriendCount;
        return _inUseFriendCount;
    }

    public ArrayList<InUseFriendEntity> getAll() {
        ArrayList<InUseFriendEntity> inUseFriendList = new ArrayList<InUseFriendEntity>();
        String selectQuery = "SELECT * FROM " + TABLE_INUSEFRIEND;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                InUseFriendEntity entity = new InUseFriendEntity(
                        cursor.getString(cursor.getColumnIndex(KEY_USERSN)),
                        cursor.getString(cursor.getColumnIndex(KEY_DISPLAYNAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_PHOTOID)),
                        cursor.getString(cursor.getColumnIndex(KEY_THUMBURL)),
                        cursor.getString(cursor.getColumnIndex(KEY_HASHPHONE))
                );
                inUseFriendList.add(entity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return inUseFriendList;
    }
}
