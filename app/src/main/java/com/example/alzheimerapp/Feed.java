package com.example.alzheimerapp;

public class Feed {
    private int id;
    private String subject;
    private String thoughts;
    private byte[] image;

    public Feed(int id, String subject, String thoughts, byte[] image) {
        this.id = id;
        this.subject = subject;
        this.thoughts = thoughts;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
