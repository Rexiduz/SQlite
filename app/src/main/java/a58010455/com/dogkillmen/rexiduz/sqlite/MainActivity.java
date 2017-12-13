package a58010455.com.dogkillmen.rexiduz.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;

import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.CONTENT;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.TABLE_NAME;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant.TIME;
import static a58010455.com.dogkillmen.rexiduz.sqlite.Constant._ID;

public class MainActivity extends AppCompatActivity {
    private EditText add_box;
    private Button add_butt;
    private TextView add_view;
    private NotesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        try {
            Cursor cursor = getAllNotes();
            showNotes(cursor);
        } finally {
            helper.close();
        }
        add_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addNote(add_box.getText().toString());
                    Cursor cursor = getAllNotes();
                    showNotes(cursor);
                    add_box.setText(null);
                } finally {
                    helper.close();
                }
            }
        });
    }

    private void addNote(String str) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(CONTENT, str);
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    private static String[] COLUMNS = {_ID, TIME, CONTENT};
    private static String ORDER_BY = TIME + " DESC";

    private Cursor getAllNotes() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null,
                ORDER_BY);
        return cursor;
    }

    private void showNotes(Cursor cursor) {
        StringBuilder builder = new StringBuilder("ข้อความที่บันทึกไว้:\n\n");
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            long time = cursor.getLong(1);
            String content = cursor.getString(2);
            builder.append("ล าดับ ").append(id).append(": ");
            String strDate = (String) DateFormat.format("yyyy-MM-dd hh:mm:ss",
                    new Date(time));
            builder.append(strDate).append("\n");
            builder.append("\t").append(content).append("\n\n");
        }
        add_view.setText(builder);
    }

    private void init() {
        add_box = (EditText) findViewById(R.id.add_box);
        add_butt = (Button) findViewById(R.id.add_butt);
        add_view = (TextView) findViewById(R.id.add_view);
        helper = new NotesHelper(this);
    }

}
