package com.handler.dbhelper.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;

import com.handler._entity.UserEntity;
import com.libs.encrypt.Encrypt;

import java.security.NoSuchAlgorithmException;

/**
 * Created by 종열 on 2015-06-21.
 */
public class UserDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "imfreeUser";

    private static final String TABLE_USER = "imfreeuser";

    private static final String KEY_ID = "id";
    private static final String KEY_USERSN = "usersn";
    private static final String KEY_GUID = "guid";
    private static final String KEY_PHONENO = "phoneno";
    private static final String KEY_ACCESSTOKEN = "accesstoken";
    private static final String KEY_HASHPHONE = "hashphone";
    private static final String KEY_DEVICEID = "deviceid";
    private static final String KEY_PUSHKEY = "pushkey";

    private Context _context;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    private static UserDBHelper _instance = null;
    public static UserDBHelper getInstance(Context context)
    {
        if(_instance == null) {
            _instance = new UserDBHelper(context);
        }
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERSN + " TEXT,"
                + KEY_GUID + " TEXT,"
                + KEY_PHONENO + " TEXT,"
                + KEY_ACCESSTOKEN + " TEXT,"
                + KEY_HASHPHONE + " TEXT,"
                + KEY_PUSHKEY + " TEXT,"
                + KEY_DEVICEID + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        blankInsert(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    private void blankInsert(SQLiteDatabase db)
    {
        TelephonyManager systemService = (TelephonyManager)_context.getSystemService(_context.TELEPHONY_SERVICE);
        String PhoneNumber = systemService.getLine1Number();
        PhoneNumber = PhoneNumber.substring(PhoneNumber.length()-10,PhoneNumber.length());
        PhoneNumber="0"+PhoneNumber;
        PhoneNumber = PhoneNumberUtils.formatNumber(PhoneNumber);

        ContentValues values = new ContentValues();
        values.put(KEY_USERSN, "");
        values.put(KEY_GUID, "");
        values.put(KEY_PHONENO, PhoneNumber);
        values.put(KEY_ACCESSTOKEN, "");
        values.put(KEY_HASHPHONE, new Encrypt().SHA256(PhoneNumber));
        values.put(KEY_PUSHKEY, "");
        values.put(KEY_DEVICEID, systemService.getDeviceId());

        db.insert(TABLE_USER, null, values);
    }

    public void update(UserEntity user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERSN, user.getUserSN());
        values.put(KEY_GUID, user.getGuid());
        values.put(KEY_PHONENO, user.getPhoneNo());
        values.put(KEY_ACCESSTOKEN, user.getAccessToken());
        values.put(KEY_HASHPHONE, user.getHashPhone());
        values.put(KEY_PUSHKEY, user.getPushKey());
        values.put(KEY_DEVICEID, user.getDeviceId());
        db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public UserEntity get()
    {
        UserEntity result = new UserEntity();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY " + KEY_ID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                result.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                result.setUserSN(cursor.getString(cursor.getColumnIndex(KEY_USERSN)));
                result.setGuid(cursor.getString(cursor.getColumnIndex(KEY_GUID)));
                result.setPhoneNo(cursor.getString(cursor.getColumnIndex(KEY_PHONENO)));
                result.setAccessToken(cursor.getString(cursor.getColumnIndex(KEY_ACCESSTOKEN)));
                result.setHashPhone(cursor.getString(cursor.getColumnIndex(KEY_HASHPHONE)));
                result.setDeviceId(cursor.getString(cursor.getColumnIndex(KEY_DEVICEID)));
                result.setPushKey(cursor.getString(cursor.getColumnIndex(KEY_PUSHKEY)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        else
        {
            cursor.close();
            blankInsert(db);
            result = get();
        }
        return result;
    }
}
