package com.sudiptacseseu.noteme.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.databinding.ActivityHomeBinding;
import com.sudiptacseseu.noteme.fragment.DoneFragment;
import com.sudiptacseseu.noteme.fragment.InProgressFragment;
import com.sudiptacseseu.noteme.fragment.OpenFragment;
import com.sudiptacseseu.noteme.fragment.TestFragment;
import com.sudiptacseseu.noteme.viewmodel.ToDoViewModel;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ToDoViewModel toDoViewModel;
    private BottomNavigationView bottomNavigationView;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        View view = binding.getRoot();
        setContentView(view);

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.bottomNavigationView.setOnItemSelectedListener(navListener);
        binding.bottomNavigationView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new OpenFragment())
                .commit();

        binding.addNoteFab.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectFragment = new OpenFragment();
        switch (item.getItemId()){
            case R.id.menuOpen:
                selectFragment = new OpenFragment();
                break;
            case R.id.menuInProgress:
                selectFragment = new InProgressFragment();
                break;
            case R.id.menuTest:
                selectFragment = new TestFragment();
                break;
            case R.id.menuDone:
                selectFragment = new DoneFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment)
                .addToBackStack(null).commit();

        return true;
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.addNoteFab) {
            Intent intent = new Intent(HomeActivity.this, AddNoteActivity.class);
            startActivity(intent);
        }
    }
}
