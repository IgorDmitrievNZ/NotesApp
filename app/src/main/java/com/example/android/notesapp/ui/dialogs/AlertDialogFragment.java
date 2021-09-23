package com.example.android.notesapp.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.list.NotesListPresenter;

public class AlertDialogFragment extends DialogFragment {

    private NotesListPresenter presenter;
    private Note selectedNote;
    public AlertDialogFragment(NotesListPresenter presenter, Note selectedNote) {
        this.presenter = presenter;
        this.selectedNote = selectedNote;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_message)
                .setPositiveButton(R.string.ok_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.removeNote(selectedNote);
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(requireContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }
}
