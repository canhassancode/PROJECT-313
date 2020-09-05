package com.example.project_313;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NohaAdapter extends FirestoreRecyclerAdapter<NohayModel, NohaAdapter.NohaViewHolder> {

    public Context newParent;

    public NohaAdapter(@NonNull FirestoreRecyclerOptions<NohayModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull NohaAdapter.NohaViewHolder holder, int position, @NonNull NohayModel model) {
        // animation
        holder.container.setAnimation(AnimationUtils.loadAnimation(newParent, R.anim.fade_scale_animation));

        // data
        holder.txtTitle.setText(model.getTitle());
        holder.txtNoha.setText(model.getNoha());
    }


    @NonNull
    @Override
    public NohaAdapter.NohaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Noha layout fullscreen options
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noha_view, parent, false);

        newParent = parent.getContext();

        // return to noha class
        return new NohaViewHolder(view);
    }

    public class NohaViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtNoha;
        RelativeLayout container;


        public NohaViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container_home);
//            txtTitle = itemView.findViewById(R.id.txtTitle);
//            txtNoha = itemView.findViewById(R.id.txtNoha);
            txtTitle = itemView.findViewById(R.id.main_title);
            txtNoha = itemView.findViewById(R.id.main_noha);
        }
    }

    // SOURCE: https://stackoverflow.com/questions/54458874/showing-recyclerview-on-fragment-with-data-from-firebase
}
