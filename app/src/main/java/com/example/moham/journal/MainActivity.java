package com.example.moham.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {


    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });

        // siglton database
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(getApplicationContext(), cursor);



        final ListView journalList = findViewById(R.id.listViewJournals);
        journalList.setAdapter(adapter);

        // Listen for when clicking on a itemList to take us to the detail activity of that item
        journalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                // get the actual item by the position on the screen
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                // pass the information about the entry to the next activity
                intent.putExtra("titleEntry", cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TITLE)));
                intent.putExtra("moodEntry", cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_MOOD)));
                intent.putExtra("contentEntry", cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_CONTENT)));
                intent.putExtra("timeStampEntry", cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TIMESTAMP)));

                // go to the detailed activity
                startActivity(intent);
            }
        });

        journalList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EntryDatabase.getInstance(getApplicationContext()).delete(id);
                EntryDatabase database = db;
                EntryAdapter entryAdapter = adapter;
                entryAdapter.swapCursor(db.selectAll());
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        EntryDatabase database = db;
        EntryAdapter entryAdapter = adapter;
        entryAdapter.swapCursor(db.selectAll());
        super.onResume();
    }
}