package com.sudiptacseseu.noteme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.activity.NoteDetailsActivity;
import com.sudiptacseseu.noteme.adapter.NoteListAdapter;
import com.sudiptacseseu.noteme.databinding.FragmentOpenBinding;
import com.sudiptacseseu.noteme.model.Note;
import com.sudiptacseseu.noteme.utils.OnItemClickListener;
import com.sudiptacseseu.noteme.viewmodel.ToDoViewModel;

import java.util.List;

public class OpenFragment extends Fragment implements OnItemClickListener {

    public OpenFragment() {
        // Required empty public constructor
    }

    ToDoViewModel toDoViewModel;
    private NoteListAdapter noteListAdapter;
    FragmentOpenBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_open, container, false);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        View view = binding.getRoot();

        noteListAdapter = new NoteListAdapter(getContext(), this);
        binding.recyclerview.setAdapter(noteListAdapter);
        noteListAdapter.notifyDataSetChanged();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        toDoViewModel.getAllToDos().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {

                // Update the cached copy of notes in the adapter
                if (notes != null && notes.size()!= 0) {
                    noteListAdapter.setToDos(notes);
                    binding.noTaskTextViewId.setVisibility(View.INVISIBLE);
                } else {
                    binding.noTaskTextViewId.setText(R.string.no_note);
                }
            }
        });

        return view;
    }
    @Override
    public void onItemClick(Note note) {
        Intent intent = new Intent(getContext(), NoteDetailsActivity.class);
        intent.putExtra("currentClickedNote", note);
        startActivity(intent);

    }
}

