package com.example.project_313;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NohaAdapter extends FirestoreRecyclerAdapter<NohayModel, NohaAdapter.NohaViewHolder> {

    public NohaAdapter(@NonNull FirestoreRecyclerOptions<NohayModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NohaAdapter.NohaViewHolder holder, int position, @NonNull NohayModel model) {
        holder.txtTitle.setText(model.getTitle());
        holder.txtNoha.setText(model.getNoha());
    }


    @NonNull
    @Override
    public NohaAdapter.NohaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Noha layout fullscreen options
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noha_fullscreen, parent, false);

        // return to noha class

        return new NohaViewHolder(view);
    }

    public class NohaViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtNoha;

        public NohaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtNoha = itemView.findViewById(R.id.txtNoha);
        }
    }
}
