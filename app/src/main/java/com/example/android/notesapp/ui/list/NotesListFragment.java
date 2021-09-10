package com.example.android.notesapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.DeviceNotesRepository;
import com.example.android.notesapp.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";
    public static final String ARG_NOTE = "ARG_NOTE";
    private OnNoteClicked onNoteClicked;
    private NotesListPresenter presenter;
    private LinearLayout container;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.base);

        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {

        for (final Note note : notes) {

            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);

            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteClicked(note);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, note);

                    getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTE, bundle);
                }
            });

            TextView noteName = noteItem.findViewById(R.id.note_name);

            noteName.setText(note.getName());

            container.addView(noteItem);
        }
    }

    public interface OnNoteClicked {
        void onNoteClicked(Note note);
    }
}
