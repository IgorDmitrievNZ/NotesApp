package com.example.android.notesapp.domain;

import java.util.List;

public interface NotesRepository {
    List<Note> getNotes();
}
