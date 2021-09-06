package com.example.android.notesapp.ui.details;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.list.NotesListFragment;

public class NoteDetailsFragment extends Fragment implements Parcelable {

    public static final Creator<NoteDetailsFragment> CREATOR = new Creator<NoteDetailsFragment>() {
        @Override
        public NoteDetailsFragment createFromParcel(Parcel in) {
            return new NoteDetailsFragment(in);
        }

        @Override
        public NoteDetailsFragment[] newArray(int size) {
            return new NoteDetailsFragment[size];
        }
    };
    private static final String ARG_NOTE = "ARG_NOTE";
    private TextView noteName;
    private TextView noteDate;
    private TextView noteDescription;

    protected NoteDetailsFragment(Parcel in) {
    }

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);

        arguments.putParcelable(ARG_NOTE, note);

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteName = view.findViewById(R.id.note_name);

        noteDate = view.findViewById(R.id.note_date);

        noteDescription = view.findViewById(R.id.note_description);

        getParentFragmentManager().setFragmentResultListener(NotesListFragment.KEY_SELECTED_NOTE, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Note note = result.getParcelable(NotesListFragment.ARG_NOTE);

                displayNote(note);
            }
        });

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {

            Note note = getArguments().getParcelable(ARG_NOTE);

            if (note != null) {
                displayNote(note);
            }
        }
    }

    private void displayNote(Note note) {
        noteName.setText(note.getName());
        noteDate.setText(note.getDate().toString());
        noteDescription.setText(note.getDescription());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
