package com.example.android.notesapp.ui;

import androidx.fragment.app.FragmentManager;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.details.NoteDetailsFragment;
import com.example.android.notesapp.ui.edit.EditNoteFragment;
import com.example.android.notesapp.ui.list.NotesListFragment;
import com.example.android.notesapp.ui.settings.InfoFragment;
import com.example.android.notesapp.ui.settings.SettingsFragment;

public class Router {

    private final FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotesList() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, new NotesListFragment())
                .addToBackStack(null)
                .commit();
    }

    public void showInfo() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, new InfoFragment())
                .addToBackStack(null)
                .commit();
    }

    public void showNoteDetails(Note note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, NoteDetailsFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

    public void showEditNote(Note note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, EditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

    public void showSettings() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

}
