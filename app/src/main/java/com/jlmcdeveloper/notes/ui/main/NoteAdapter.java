package com.jlmcdeveloper.notes.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.data.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHole> {

    private List<Note> notes;
    private MainMvpPresenter<MainMvpView> presenter;

    NoteAdapter(@NonNull MainMvpPresenter<MainMvpView> presenter, List<Note> notes){
        this.presenter = presenter;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHole onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHole(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_note_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHole holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    //----------------- ViewHolder ---------------------
    class NoteHole extends RecyclerView.ViewHolder {
        private int position;

        @BindView(R.id.text_note_title)
        TextView textTitle;

        @BindView(R.id.text_note_descri)
        TextView textDiscri;


        @OnClick(R.id.liner_layout_cardView)
        void cardView(){
            presenter.editNote(notes.get(position));
        }


        NoteHole(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void onBind(int position) {
            this.position = position;

            textTitle.setText(notes.get(position).getTitle());
            textDiscri.setText(notes.get(position).getDescription());
        }
    }
}
