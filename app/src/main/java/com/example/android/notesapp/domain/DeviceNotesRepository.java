package com.example.android.notesapp.domain;

import com.example.android.notesapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.note_number_1, R.string.descrription_1, new Date()));
        notes.add(new Note(R.string.note_number_2, R.string.descrription_2, new Date()));
        notes.add(new Note(R.string.note_number_3, R.string.descrription_3, new Date()));
        notes.add(new Note(R.string.note_number_4, R.string.descrription_4, new Date()));

        return notes;
    }
}
