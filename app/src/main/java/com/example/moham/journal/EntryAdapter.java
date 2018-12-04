package com.example.moham.journal;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }


    // Get the entry information and assign the information to the views
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textTitle = view.findViewById(R.id.textViewTitle);
        TextView textContent = view.findViewById(R.id.textViewContent);
        TextView textTimestamp = view.findViewById(R.id.textViewTimeStamp);

        String mood = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_MOOD));
        int resId = context.getResources().getIdentifier(mood, "drawable", context.getPackageName());
        ImageView imgMood = view.findViewById(R.id.imageViewMood);


        String title = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TITLE));
        textTitle.setText(title);

        String content = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_CONTENT));
        textContent.setText(content);

        imgMood.setImageResource(resId);

        String timestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TIMESTAMP));
        textTimestamp.setText(timestamp);
    }
}