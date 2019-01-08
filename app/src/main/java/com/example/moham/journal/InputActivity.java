package com.example.moham.journal;

import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Hashtable;
import java.util.Set;


public class InputActivity extends AppCompatActivity {

    private int id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    int previousImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        final ImageButton buttonHappy = findViewById(R.id.imageViewHappy);
        final ImageButton buttonSad = findViewById(R.id.imageViewSad);
        final ImageButton buttonSmiley = findViewById(R.id.imageViewSmiley);
        final ImageButton buttonTired = findViewById(R.id.imageViewTired);

        final Hashtable <String, ImageButton> imageButtonsTable = new Hashtable<>();
        imageButtonsTable.put("happy", buttonHappy);
        imageButtonsTable.put("sad", buttonSad);
        imageButtonsTable.put("smiley", buttonSmiley);
        imageButtonsTable.put("tired", buttonTired);


        View.OnClickListener imageButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imageViewHappy){
                    mood = "happy";
                    previousImageButton = R.id.imageViewHappy;
                }
                else if (v.getId() == R.id.imageViewSad){
                    mood = "sad";
                    previousImageButton = R.id.imageViewSad;
                }
                else if (v.getId() == R.id.imageViewSmiley){
                    mood = "smiley";
                    previousImageButton = R.id.imageViewSmiley;
                }
                else if (v.getId() == R.id.imageViewTired){
                    mood = "tired";
                    previousImageButton = R.id.imageViewTired;
                }
                else {
                    // Print iets van het gaat fout
                    Log.d("###Fout 3", "Geen van de 4 emojis werkt goed.");
                }

                Set <String> keys = imageButtonsTable.keySet();
                for(String key: keys){
                    ImageButton value = imageButtonsTable.get(key);
                    if (key.equals(mood)){
                        value.setBackgroundTintList(ContextCompat.getColorStateList(InputActivity.this, R.color.colorPrimary));
                    }
                    else {
                        value.setBackgroundTintList(ContextCompat.getColorStateList(InputActivity.this, R.color.LightGrey));
                    }
                    System.out.println("Value of "+key+" is: "+imageButtonsTable.get(key));
                }

            }
        };

        buttonHappy.setOnClickListener(imageButtonListener);
        buttonSad.setOnClickListener(imageButtonListener);
        buttonSmiley.setOnClickListener(imageButtonListener);
        buttonTired.setOnClickListener(imageButtonListener);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEntry();
            }
        });

        final EditText titleInput = findViewById(R.id.editContentTitle);
        final EditText contentInput = findViewById(R.id.editTextContent);

        titleInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                title = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        contentInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                content = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }

    private void addEntry() {
        if (!checkInput()) {
            return;
        }
        JournalEntry entry = new JournalEntry(title, content, mood);
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);
        InputActivity.this.finish();
    }

    private boolean checkInput() {
        if (this.title != null && this.content != null && this.mood != null) {
            return true;
        }
        Toast.makeText(this, "Please provide complete info!",
                Toast.LENGTH_LONG).show();
        return false;
    }

}