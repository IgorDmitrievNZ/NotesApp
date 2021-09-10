package com.example.android.notesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.details.NoteDetailsActivity;
import com.example.android.notesapp.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private static final String ARG_NOTE = "ARG_NOTE";
    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteClicked(Note note) {

        selectedNote = note;

        if (getResources().getBoolean(R.bool.isLandscape)) {

        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }
}
