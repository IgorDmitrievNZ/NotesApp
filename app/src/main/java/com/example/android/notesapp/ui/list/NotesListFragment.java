package com.example.android.notesapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.MockDeviceNotesRepository;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.Router;
import com.example.android.notesapp.ui.RouterHolder;
import com.example.android.notesapp.ui.dialogs.AlertDialogFragment;
import com.example.android.notesapp.ui.edit.EditNoteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";
    public static final String ARG_NOTE = "ARG_NOTE";
    private NotesListPresenter presenter;
    private NotesAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView notesList;
    private Note selectedNote;
    private Router router;
    private boolean wasNotesRequested;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RouterHolder) {
            router = ((RouterHolder) context).getRouter();
        } else if (getActivity() instanceof RouterHolder) {
            router = ((RouterHolder) getActivity()).getRouter();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, MockDeviceNotesRepository.NOTES_REPOSITORY);

        adapter = new NotesAdapter(this);

        adapter.setListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClicked(Note note) {

                if (router != null) {
                    router.showNoteDetails(note);
                }
            }
        });

        adapter.setLongListener(new NotesAdapter.OnNoteLongClickListener() {
            @Override
            public void onNoteLongClicked(Note note) {
                selectedNote = note;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener(EditNoteFragment.KEY_NOTE_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Note note = result.getParcelable(EditNoteFragment.KEY_NOTE_RESULT);
                int index = adapter.updateNote(note);
                adapter.notifyItemChanged(index);
            }
        });

        progressBar = view.findViewById(R.id.progress);

        notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        notesList.setAdapter(adapter);

        // Visual divider. Optional
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_separator));
        notesList.addItemDecoration(itemDecoration);

        // Animation correction.
        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setRemoveDuration(4000L);
        notesList.setItemAnimator(animator);

        if (!wasNotesRequested) {
            presenter.requestNotes();
            wasNotesRequested = true;
        }


        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    adapter.setNotes(Collections.emptyList());
                    adapter.notifyDataSetChanged();
                    return true;
                }
                if (item.getItemId() == R.id.action_add) {
                    presenter.addNote(UUID.randomUUID().toString(), "https://images.freeimages.com/images/large-previews/241/night-fog-1521028.jpg", new Date());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onNoteAdded(Note note) {
        adapter.addNote(note);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
        notesList.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onNoteRemoved(Note selectedNote) {

        int index = adapter.removeNote(selectedNote);
        adapter.notifyItemRemoved(index);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.notes_list_context, menu);
    }

    private void showAlertDialogFragment() {
        new AlertDialogFragment(presenter, selectedNote).show(getChildFragmentManager(), "AlertDialogFragment");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete_one) {

            showAlertDialogFragment();
            return true;
        }
        if (item.getItemId() == R.id.action_update) {

            if (router != null) {
                router.showEditNote(selectedNote);
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }

}
