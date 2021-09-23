package com.example.android.notesapp.ui.list;

import com.example.android.notesapp.domain.CallBack;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.domain.NotesRepository;

import java.util.Date;
import java.util.List;

public class NotesListPresenter {

    private NotesListView view;

    private NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showProgress();

        repository.getNotes(new CallBack<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                view.showNotes(data);

                view.hideProgress();
            }
        });
    }

    public void addNote(String title, String imageUrl, Date date) {
        view.showProgress();
        repository.addNote(title, imageUrl, date, new CallBack<Note>() {
            @Override
            public void onSuccess(Note data) {
                view.hideProgress();
                view.onNoteAdded(data);
            }
        });
    }

    public void removeNote(Note selectedNote) {
        view.showProgress();
        repository.removeNote(selectedNote, new CallBack<Void>() {
            @Override
            public void onSuccess(Void data) {
                view.hideProgress();
                view.onNoteRemoved(selectedNote);
            }
        });
    }
}
