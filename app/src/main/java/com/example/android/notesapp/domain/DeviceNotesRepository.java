package com.example.android.notesapp.domain;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DeviceNotesRepository implements NotesRepository {

    private final ArrayList<Note> res = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());

    public DeviceNotesRepository() {
        res.add(new Note("id1", "Title 1", "https://images.freeimages.com/images/large-previews/241/night-fog-1521028.jpg", new Date()));
        res.add(new Note("id2", "Title 2", "https://images.freeimages.com/images/large-previews/10f/autumn-1-1382513.jpg", new Date()));
        res.add(new Note("id3", "Title 3", "https://images.freeimages.com/images/large-previews/bfd/clouds-1371838.jpg", new Date()));
        res.add(new Note("id4", "Title 4", "https://images.freeimages.com/images/large-previews/476/chicago-night-traffic-1447010.jpg", new Date()));
        res.add(new Note("id5", "Title 5", "https://images.freeimages.com/images/large-previews/89a/one-tree-hill-1360813.jpg", new Date()));
    }

    @Override
    public void getNotes(CallBack<List<Note>> callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(res);
                    }
                });
            }
        }).start();
    }

    @Override
    public void addNote(String title, String image, Date date, CallBack<Note> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Note result = new Note(UUID.randomUUID().toString(), title, image, new Date());
                res.add(result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(result);
                    }
                });
            }
        }).start();
    }

    @Override
    public void removeNote(Note note, CallBack<Void> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                res.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(null);
                    }
                });
            }
        }).start();
    }
}
