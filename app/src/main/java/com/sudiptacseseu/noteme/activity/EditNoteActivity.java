package com.sudiptacseseu.noteme.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.databinding.ActivityEditNoteBinding;
import com.sudiptacseseu.noteme.model.Note;
import com.sudiptacseseu.noteme.utils.OnEditTextClickListener;
import com.sudiptacseseu.noteme.utils.Util;
import com.sudiptacseseu.noteme.viewmodel.ToDoViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener, OnEditTextClickListener {

    private ToDoViewModel toDoViewModel;
    private ActivityEditNoteBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    private String myFormat = "dd.MM.yyyy";
    private Note currentNote;
    private String email = "";
    private String phone = "";
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);

        //Customize the ActionBar
        final ActionBar actionBar = getSupportActionBar();
        Util.customizeActionBar(Objects.requireNonNull(actionBar), this, "Edit Tasks");

        if (getIntent() != null) {
            currentNote = (Note) getIntent().getSerializableExtra("currentNote");
            if (currentNote != null) {
                Log.d("TAG", "onCreate: " + currentNote.getNoteName());
                binding.setObj(currentNote);
            }
        }

        //create a list of items for the spinner.
        String[] items = new String[]{"Open", "In-progress", "Test", "Done"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        binding.statusSpinnerId.setAdapter(adapter);

        binding.emailLayoutId.setOnClickListener(this);
        binding.phoneLayoutId.setOnClickListener(this);
        binding.urlLayoutId.setOnClickListener(this);

        DatePickerDialog.OnDateSetListener date = (datePicker, i, i1, i2) -> {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            binding.deadlineDateTextViewId.setText(Util.getDeadlineDate(myFormat, myCalendar));
        };
        binding.dateImageButtonId.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditNoteActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.saveNoteButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.todoEditTextId.getText())&&TextUtils.isEmpty(binding.todoDescriptionEditTextId.getText())) {
                    Toast.makeText(EditNoteActivity.this, R.string.empty_not_saved, Toast.LENGTH_LONG)
                            .show();
                }else {

                    Note note = new Note(binding.todoEditTextId.getText().toString(),binding.todoDescriptionEditTextId.getText().toString(),
                            Util.getCurrentDate(myFormat),Util.getDeadlineDate(myFormat, myCalendar),binding.statusSpinnerId.getSelectedItem().toString(),"","","");
                    toDoViewModel.update(note);
                    Util.noteSaveDialog(EditNoteActivity.this);
                    //finish();
                }
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.emailLayoutId) {
            Util.customDialog(EditNoteActivity.this, "E-mail",getResources().getDrawable(R.drawable.ic_email,null), this);
        }
        if (id == R.id.phoneLayoutId) {
            Util.customDialog(EditNoteActivity.this,"Phone", getResources().getDrawable(R.drawable.ic_phone,null), this);
        }
        if (id == R.id.urlLayoutId) {

            Util.customDialog(EditNoteActivity.this, "Url", getResources().getDrawable(R.drawable.ic_url,null), this);

        }
    }

    @Override
    public void onEditTextClick(Map.Entry<String,String> input) {
        if(input.getKey().equals("E-mail")){
            this.email = input.getValue();
        }
        if(input.getKey().equals("Phone")){
            this.phone = input.getValue();
        }
        if(input.getKey().equals("Url")){
            this.url = input.getValue();
        }
    }
}