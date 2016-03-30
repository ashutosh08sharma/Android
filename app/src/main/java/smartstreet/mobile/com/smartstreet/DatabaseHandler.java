package smartstreet.mobile.com.smartstreet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Ashutosh on 3/15/2016.
 * all the database operation and crud operation are performed in this class
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SmartStreet.db";
    private static final int DATABASE_VERSION = 5;
    public static final String PERSON_TABLE_NAME = "User";
    public static final String PERSON_COLUMN_ID = "_id";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_PASSWORD = "password";
    public static final String PERSON_COLUMN_EMAILID = "email_id";
    public static final String PERSON_COLUMN_AGE = "age";
    public static final String PERSON_COLUMN_PHONENO = "phoneNo";
    public static final String PERSON_COLUMN_COMMENTS = "comments";
    public static final String TABLE_COMMENTS = "userComments";
    public static final String USER_COMMENT = "comment";
    public static final String USER_ID="_id";
    public static final String USER_Rating = "userRating";


    private static final String TABLE_CONTACTS = "users";
    private static final String LOG = "databasehandler";
    private String name;
    private String password;
    private String email_id;
    private String age;
    private String phoneNo;
    private String comment;
    private String rating;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_DATABASE = "CREATE TABLE " + PERSON_TABLE_NAME + "(" +
            PERSON_COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " + PERSON_COLUMN_NAME + " TEXT, " + PERSON_COLUMN_PASSWORD + " TEXT, " + PERSON_COLUMN_EMAILID + " TEXT, " +
            PERSON_COLUMN_AGE + " INTEGER, " + PERSON_COLUMN_COMMENTS + " TEXT, " + PERSON_COLUMN_PHONENO + " INTEGER" + ")";

    private static final String CREATE_COMMENT_TABLE = "CREATE TABLE " + TABLE_COMMENTS + "(" + USER_ID  + " INTEGER," + USER_COMMENT + " TEXT, " + USER_Rating + " TEXT" + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DATABASE); // creation of table
        db.execSQL(CREATE_COMMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);

        // Create tables
        onCreate(db);
    }
    // user registration
    public boolean insertUser(String name, String password, String email_id, String age, String phoneNo) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_PASSWORD, password);
        contentValues.put(PERSON_COLUMN_EMAILID, email_id);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_PHONENO, phoneNo);
        db.insert(PERSON_TABLE_NAME, null, contentValues);//insert query
        return true;
    }
   // updating user profile
    public boolean updateUser(Integer id, String name, String password, String email_id, int age, int phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //assigning values to column
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_PASSWORD, password);
        contentValues.put(PERSON_COLUMN_EMAILID, email_id);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_PHONENO, phoneNo);
        db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    // login method to check if user is valid or not
    public String getPassword(String userName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("User", null, "email_id=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // username Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;
    }

    // comment method
    public boolean addUserComment(String comment, String rating) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COMMENT, comment);
        contentValues.put(USER_Rating, rating);
        db.insert(TABLE_COMMENTS, null, contentValues);
        return true;
    }
    // dispalying all user comments in listview
    public Cursor getAllComments() {

       // getting all the rows form table_comments
        String selectQuery = " SELECT * FROM " + TABLE_COMMENTS + " ORDER BY " + USER_ID + " DESC";


        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (c != null) {
            c.moveToFirst();

        }

        return c;
    }
}

