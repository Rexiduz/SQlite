package a58010455.com.dogkillmen.rexiduz.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.CONTENT;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.TABLE_NAME;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.TIME;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant._ID;

/**
 * Created by Blazter on 6/11/2560.
 */

public class NotesHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simple_sqlite.db";
    private static final int DATABASE_VERSION = 1;

    public NotesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + "INTEGER, " + CONTENT + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
