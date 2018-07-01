package com.sparrowpaul.journalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class EditJournalActivity extends AppCompatActivity {

    EditText editText;
    int noteIdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        editText = findViewById(R.id.noteEditorID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent myIntent = getIntent();
        noteIdo = myIntent.getIntExtra("noteId", 0);

        if (noteIdo != 0){
            editText.setText(MainActivity.notes.get(noteIdo));
        }else {
            MainActivity.notes.add("");
            noteIdo = MainActivity.notes.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteIdo,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.sparrowpaul.journalapp", Context.MODE_PRIVATE );
                HashSet<String> hashSet = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",hashSet).apply();
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_journal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_doneID){
            Toast.makeText(EditJournalActivity.this, "Journal Saved", Toast.LENGTH_LONG).show();
            Intent i = new Intent(EditJournalActivity.this, MainActivity.class);
            startActivity(i);
        }

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
