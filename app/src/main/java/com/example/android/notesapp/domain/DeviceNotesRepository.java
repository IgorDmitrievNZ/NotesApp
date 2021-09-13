package com.example.android.notesapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note("id1", "Title 1", "https://images.freeimages.com/images/large-previews/241/night-fog-1521028.jpg", new Date()));
        notes.add(new Note("id2", "Title 2", "https://images.freeimages.com/images/large-previews/10f/autumn-1-1382513.jpg", new Date()));
        notes.add(new Note("id3", "Title 3", "https://images.freeimages.com/images/large-previews/bfd/clouds-1371838.jpg", new Date()));
        notes.add(new Note("id4", "Title 4", "https://images.freeimages.com/images/large-previews/476/chicago-night-traffic-1447010.jpg", new Date()));
        notes.add(new Note("id5", "Title 5", "https://images.freeimages.com/images/large-previews/89a/one-tree-hill-1360813.jpg", new Date()));

        for (int i = 0; i < 100000; i++) {
            notes.add(new Note("id2", "Title 2", "https://images.freeimages.com/images/large-previews/10f/autumn-1-1382513.jpg", new Date()));
            notes.add(new Note("id3", "Title 3", "https://images.freeimages.com/images/large-previews/bfd/clouds-1371838.jpg", new Date()));
            notes.add(new Note("id4", "Title 4", "https://images.freeimages.com/images/large-previews/476/chicago-night-traffic-1447010.jpg", new Date()));
            notes.add(new Note("id5", "Title 5", "https://images.freeimages.com/images/large-previews/89a/one-tree-hill-1360813.jpg", new Date()));
        }

        return notes;
    }
}
