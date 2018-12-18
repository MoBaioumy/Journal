package com.example.moham.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {


    private int id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    /**
     * Default constructor with timeStamp
     * @param id
     * @param title
     * @param content
     * @param mood
     * @param timeStamp
     */

    public JournalEntry(int id, String title, String content, String mood, String timeStamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timeStamp;
    }

    /**
     * Constructor without timeStamp
     * @param title
     * @param content
     * @param mood
     */

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }


    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}