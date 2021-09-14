package com.example.android.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private final String id;
    private final String title;
    private final String imageUrl;
    private final Date date;

    public Note(String id, String title, String imageUrl, Date date) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        date = (Date) in.readSerializable();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeSerializable(date);
    }
}
