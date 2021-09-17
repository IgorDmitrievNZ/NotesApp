package com.example.android.notesapp.ui.list;

import com.example.android.notesapp.domain.Note;

import java.util.List;

public interface NotesListView {

    void showNotes(List<Note> notes);

    void showProgress();

    void hideProgress();

    void onNoteAdded(Note note);

    void onNoteRemoved(Note selectedNote);
}
