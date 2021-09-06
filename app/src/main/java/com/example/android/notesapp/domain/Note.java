package com.example.android.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

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
    @StringRes
    private final int name;
    @StringRes
    private final int description;
    private final Date date;

    public Note(int name, int description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected Note(Parcel in) {
        name = in.readInt();
        description = in.readInt();
        date = (Date) in.readSerializable();
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
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
        parcel.writeInt(name);
        parcel.writeInt(description);
        parcel.writeSerializable(date);
    }
}
