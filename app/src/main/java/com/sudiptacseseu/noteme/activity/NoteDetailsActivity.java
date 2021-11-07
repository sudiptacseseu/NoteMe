package com.sudiptacseseu.noteme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.databinding.ActivityNoteDetailsBinding;
import com.sudiptacseseu.noteme.model.Note;
import com.sudiptacseseu.noteme.utils.Util;
import com.sudiptacseseu.noteme.viewmodel.ToDoViewModel;

import java.util.Objects;

public class NoteDetailsActivity extends AppCompatActivity {

    private ToDoViewModel toDoViewModel;
    private ActivityNoteDetailsBinding binding;
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);

        if (getIntent() != null) {
            currentNote = (Note) getIntent().getSerializableExtra("currentClickedNote");
            if (currentNote != null) {
               binding.setObj(currentNote);
            }
        }

        //Customize the ActionBar
        final ActionBar actionBar = getSupportActionBar();
        Util.customizeActionBar(Objects.requireNonNull(actionBar), this, "Task details");


       binding.editNoteFab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(NoteDetailsActivity.this, EditNoteActivity.class);
               intent.putExtra("currentNote", currentNote);
               startActivity(intent);
           }
       });

       binding.deleteButtonId.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               toDoViewModel.deleteANote(currentNote);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(NoteDetailsActivity.this, "Task Deleted Successfully", Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(NoteDetailsActivity.this, HomeActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(intent);
                   }
               }, 1000);
           }
       });
    }
}