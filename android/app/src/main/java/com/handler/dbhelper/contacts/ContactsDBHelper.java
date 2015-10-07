package com.handler.dbhelper.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.handler._entity.ContactsEntity;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-10.
 */
public class ContactsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "imfreeContacts";

    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_CONTACTSID = "contactsId";
    private static final String KEY_DISPLAYNAME = "displayName";
    private static final String KEY_PHONENO = "phoneNo";
    private static final String KEY_PHOTOID = "photoId";
    private static final String KEY_THUMBURL = "thumbUri";
    private static final String KEY_HASHPHONE = "hashPhone";
    private static final String KEY_FAVORITE = "favorite";

    public ContactsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static ContactsDBHelper _instance = null;
    public static ContactsDBHelper getInstance(Context context)
    {
        if(_instance == null) {
            _instance = new ContactsDBHelper(context);
        }
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CONTACTSID + " INTEGER NOT NULL,"
                + KEY_DISPLAYNAME + " TEXT NOT NULL,"
                + KEY_PHONENO + " TEXT,"
                + KEY_PHOTOID + " TEXT,"
                + KEY_THUMBURL + " TEXT,"
                + KEY_HASHPHONE + " TEXT,"
                + KEY_FAVORITE + " TEXT"
                + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void dropTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

        _allContacts = null;
    }

    public void resetContact(ArrayList<ContactsEntity> contacts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

        for(ContactsEntity contact : contacts)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_CONTACTSID, contact.getContactId());
            values.put(KEY_DISPLAYNAME, contact.getDisplayName());
            values.put(KEY_PHONENO, contact.getPhoneNo());
            values.put(KEY_PHOTOID, contact.getPhotoId());
            values.put(KEY_THUMBURL, contact.getThumbUrl());
            values.put(KEY_HASHPHONE, contact.getHashPhone());
            values.put(KEY_FAVORITE, contact.getFavorite());
            db.insert(TABLE_CONTACTS, null, values);
        }
        _allContacts = null;
    }

    public void addContacts(ArrayList<ContactsEntity> contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(ContactsEntity contact : contacts)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_CONTACTSID, contact.getContactId());
            values.put(KEY_DISPLAYNAME, contact.getDisplayName());
            values.put(KEY_PHONENO, contact.getPhoneNo());
            values.put(KEY_PHOTOID, contact.getPhotoId());
            values.put(KEY_THUMBURL, contact.getThumbUrl());
            values.put(KEY_HASHPHONE, contact.getHashPhone());
            values.put(KEY_FAVORITE, contact.getFavorite());
            db.insert(TABLE_CONTACTS, null, values);
        }
        _allContacts = null;
    }

    public void addContact(ContactsEntity contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONTACTSID, contact.getContactId());
        values.put(KEY_DISPLAYNAME, contact.getDisplayName());
        values.put(KEY_PHONENO, contact.getPhoneNo());
        values.put(KEY_PHOTOID, contact.getPhotoId());
        values.put(KEY_THUMBURL, contact.getThumbUrl());
        values.put(KEY_HASHPHONE, contact.getHashPhone());
        values.put(KEY_FAVORITE, contact.getFavorite());

        db.insert(TABLE_CONTACTS, null, values);
        _allContacts = null;
    }

    // id 에 해당하는 Contact 객체 가져오기
    public ContactsEntity getContact(int contactId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                KEY_CONTACTSID,
                KEY_DISPLAYNAME,
                KEY_PHONENO,
                KEY_PHOTOID,
                KEY_THUMBURL,
                KEY_HASHPHONE,
                KEY_FAVORITE
        };

        Cursor cursor = db.query(TABLE_CONTACTS, projection, KEY_CONTACTSID + "=?", new String[]{String.valueOf(contactId)}, null, null, null, null);
        ContactsEntity contact = new ContactsEntity(0, "", "", "", "", "", "");
        if (cursor != null) {
            cursor.moveToFirst();
            contact = new ContactsEntity(
                    cursor.getLong(cursor.getColumnIndex(KEY_CONTACTSID)),
                    cursor.getString(cursor.getColumnIndex(KEY_DISPLAYNAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHONENO)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHOTOID)),
                    cursor.getString(cursor.getColumnIndex(KEY_THUMBURL)),
                    cursor.getString(cursor.getColumnIndex(KEY_HASHPHONE)),
                    cursor.getString(cursor.getColumnIndex(KEY_FAVORITE))
            );
        }

        cursor.close();

        // return contact
        return contact;
    }

    private ArrayList<ContactsEntity> _allContacts = null;
    public ArrayList<ContactsEntity> getAllContacts() {
        if ( _allContacts == null ) {
            ArrayList<ContactsEntity> contactList = new ArrayList<ContactsEntity>();
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    ContactsEntity contact = new ContactsEntity(
                            cursor.getLong(cursor.getColumnIndex(KEY_CONTACTSID)),
                            cursor.getString(cursor.getColumnIndex(KEY_DISPLAYNAME)),
                            cursor.getString(cursor.getColumnIndex(KEY_PHONENO)),
                            cursor.getString(cursor.getColumnIndex(KEY_PHOTOID)),
                            cursor.getString(cursor.getColumnIndex(KEY_THUMBURL)),
                            cursor.getString(cursor.getColumnIndex(KEY_HASHPHONE)),
                            cursor.getString(cursor.getColumnIndex(KEY_FAVORITE))
                    );
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
            cursor.close();
            _allContacts = contactList;
        }
        return _allContacts;
    }

    public int updateContact(ContactsEntity contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONTACTSID, contact.getContactId());
        values.put(KEY_DISPLAYNAME, contact.getDisplayName());
        values.put(KEY_PHONENO, contact.getPhoneNo());
        values.put(KEY_PHOTOID, contact.getPhotoId());
        values.put(KEY_THUMBURL, contact.getThumbUrl());
        values.put(KEY_HASHPHONE, contact.getHashPhone());
        values.put(KEY_FAVORITE, contact.getFavorite());

        _allContacts = null;

        int count = db.update(TABLE_CONTACTS, values, KEY_CONTACTSID + " = ?", new String[] { String.valueOf(contact.getContactId()) });
        return count;
    }

    public void deleteContact(ContactsEntity contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_CONTACTSID + " = ?",
                new String[]{String.valueOf(contact.getContactId())});

        _allContacts = null;
    }

    public void deleteContacts(ArrayList<ContactsEntity> contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(ContactsEntity contact : contacts) {
            db.delete(TABLE_CONTACTS, KEY_CONTACTSID + " = ?", new String[]{String.valueOf(contact.getContactId())});
        }
    }

    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
