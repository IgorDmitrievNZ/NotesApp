package com.example.android.notesapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.android.notesapp.R;
import com.example.android.notesapp.domain.Note;
import com.example.android.notesapp.ui.details.NoteDetailsFragment;
import com.example.android.notesapp.ui.list.NotesListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private static final String ARG_NOTE = "ARG_NOTE";
    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.user_name) {
                Toast.makeText(MainActivity.this, "User Name", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.user_info) {
                Toast.makeText(MainActivity.this, "User info", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.themes) {
                Toast.makeText(MainActivity.this, "Select theme", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.help) {
                Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.settings) {
                Toast.makeText(MainActivity.this, "Settings!!!!!", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.about_app) {
                Toast.makeText(MainActivity.this, "This is an awesome app", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onNoteClicked(Note note) {

        selectedNote = note;

        if (getResources().getBoolean(R.bool.isLandscape)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_landscape, NoteDetailsFragment.newInstance(note), "NoteDetailsFragment")
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoteDetailsFragment.newInstance(note), "NoteDetailsFragment")
                    .addToBackStack(null)
                    .commit();
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
