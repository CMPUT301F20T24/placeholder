package com.example.unlibrary.models;

import java.util.ArrayList;

public class Book {
    enum Status {
        AVAILABLE,
        REQUESTED,
        ACCEPTED,
        BORROWED
    }

    private String mIsbn;
    private String mDescription;
    private String mTitle;
    private String mAuthor;

    private ArrayList<String> mPhotos;
    private Status mStatus;

    public Book(String isbn, String title) {
        this.mIsbn = isbn;
        this.mTitle = title;
    }

    public void setIsbn(String isbn) {
        this.mIsbn = isbn;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public ArrayList<String> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(ArrayList<String> photos) {
        mPhotos = photos;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public String getIsbn() {
        return this.mIsbn;
    }

    public String getTitle() {
        return this.mTitle;
    }
}