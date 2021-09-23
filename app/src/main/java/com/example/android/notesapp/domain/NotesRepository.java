package com.example.android.notesapp.domain;

import java.util.Date;
import java.util.List;

public interface NotesRepository {

    void getNotes(CallBack<List<Note>> callBack);

    void addNote(String title, String image, Date date, CallBack<Note> callBack);

    void removeNote(Note note, CallBack<Void> callBack);
}
