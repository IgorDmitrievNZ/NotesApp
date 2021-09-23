package com.example.android.notesapp.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final ArrayList<Note> data = new ArrayList<>();

    private OnNoteClickListener listener;

    private Fragment fragment;

    private OnNoteLongClickListener longListener;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setNotes(List<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View noteItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NotesViewHolder(noteItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {

        Note note = data.get(position);

        holder.getTitle().setText(note.getTitle());

        Glide.with(holder.getImage()).load(note.getImageUrl()).into(holder.getImage());

        holder.getDate().setText(note.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnNoteClickListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickListener listener) {
        this.listener = listener;
    }

    public void addNote(Note note) {
        data.add(note);
    }

    public OnNoteLongClickListener getLongListener() {
        return longListener;
    }

    public void setLongListener(OnNoteLongClickListener longListener) {
        this.longListener = longListener;
    }

    public int removeNote(Note selectedNote) {

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(selectedNote)) {
                data.remove(i);
                return i;
            }
        }
        return -1;
    }

    public int updateNote(Note note) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(note)) {
                data.set(i, note);
                return i;
            }
        }
        return -1;
    }

    interface OnNoteClickListener {
        void onNoteClicked(Note note);
    }

    interface OnNoteLongClickListener {
        void onNoteLongClicked(Note note);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private TextView date;


        public NotesViewHolder(@NonNull View itemView) {
            super((itemView));

            fragment.registerForContextMenu(itemView);

            title = itemView.findViewById(R.id.note_title);
            image = itemView.findViewById(R.id.note_image);
            date = itemView.findViewById(R.id.note_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getListener() != null) {
                        getListener().onNoteClicked(data.get(getAdapterPosition()));
                    }
                }

            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemView.showContextMenu();

                    if (getLongListener() != null) {
                        getLongListener().onNoteLongClicked(data.get(getAdapterPosition()));
                    }

                    return true;
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getDate() {
            return date;
        }
    }
}
