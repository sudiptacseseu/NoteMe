package com.sudiptacseseu.noteme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.activity.EditNoteActivity;
import com.sudiptacseseu.noteme.model.Note;
import com.sudiptacseseu.noteme.utils.OnItemClickListener;
import com.sudiptacseseu.noteme.viewmodel.ToDoViewModel;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater toDoInflater;
    private List<Note> noteList;
    private Context context; // Cached copy of note items
    private Note currentNote; // Cached copy of note items
    private ToDoViewModel toDoViewModel;
    OnItemClickListener onItemClickListener;

    public NoteListAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        toDoInflater = LayoutInflater.from(context);
        toDoViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ToDoViewModel.class);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = toDoInflater.inflate(R.layout.recyclerview_item, viewGroup, false);

        return new NoteViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {

        if (noteList != null && noteList.size()!= 0) {
            currentNote = noteList.get(position);
            noteViewHolder.noteNameTextView.setText(currentNote.getNoteName());
            noteViewHolder.noteCreatedDateTextView.setText(currentNote.getNoteCreated());
            noteViewHolder.noteDeadlineDateTextView.setText(currentNote.getNoteDeadline());
        }
    }

    public void setToDos(List<Note> notes) {
        noteList = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (noteList != null)
            return noteList.size();
        else return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView noteNameTextView;
        public TextView noteCreatedDateTextView;
        public TextView noteDeadlineDateTextView;
        CardView cardView;
        public Button editButton;
        public Button deleteButton;

        public NoteViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            noteNameTextView = itemView.findViewById(R.id.noteNameTextViewId);
            noteCreatedDateTextView = itemView.findViewById(R.id.noteCreatedDateTextViewId);
            noteDeadlineDateTextView = itemView.findViewById(R.id.noteDeadlineDateTextViewId);
            editButton = itemView.findViewById(R.id.editButtonId);
            deleteButton = itemView.findViewById(R.id.deleteButtonId);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnClickListener(this);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditNoteActivity.class);
                    intent.putExtra("currentNote", noteList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toDoViewModel.deleteANote(noteList.get(getBindingAdapterPosition()));
                    noteList.remove(getBindingAdapterPosition());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Task Deleted Successfully", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(noteList.get(getBindingAdapterPosition()));
        }
    }
}
