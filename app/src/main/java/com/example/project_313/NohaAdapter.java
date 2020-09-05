package com.example.project_313;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_313.Activities.EmailActivity;
import com.example.project_313.Activities.NohaFullScreen;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Text;

public class NohaAdapter extends FirestoreRecyclerAdapter<NohayModel, NohaAdapter.NohaViewHolder> {

    public Context newParent;
    public RelativeLayout newContainer;
    public NohayModel nohayModel;
    public String testNumber;

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

        String test1 = getSnapshots().getSnapshot(position).getId();
        testNumber = test1;
//
//        holder.txtTitleFull.se;
    }


    @NonNull
    @Override
    public NohaAdapter.NohaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Noha layout fullscreen options
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noha_view, parent, false);

        newParent = parent.getContext();
        // Click on Nohay
        final NohaViewHolder testVar = new NohaViewHolder(view);


        // Open Full screen noha
        final Intent NohaFullScreen = new Intent(newParent, NohaFullScreen.class);

//        NohaViewHolder.txtTitleFull.setText((testVar.getAdapterPosition()).getTitle);


        NohaViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(newParent, "Test Click"+testNumber+String.valueOf(testVar.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                newParent.startActivity(NohaFullScreen);
            }
        });

        // return to noha class
//        return new NohaViewHolder(view);
        return testVar;
    }

    public static class NohaViewHolder extends RecyclerView.ViewHolder {

        public static TextView txtTitle, txtNoha, txtTitleFull, txtNohaFull;
        public static RelativeLayout container;


        public NohaViewHolder(@NonNull View itemView) {
            super(itemView);


            container = itemView.findViewById(R.id.container_home);
//            txtTitle = itemView.findViewById(R.id.txtTitle);
//            txtNoha = itemView.findViewById(R.id.txtNoha);
            txtTitle = itemView.findViewById(R.id.main_title);
            txtNoha = itemView.findViewById(R.id.main_noha);

            // Fullscreen

            txtTitleFull = itemView.findViewById(R.id.txtTitleFull);
            txtNohaFull = itemView.findViewById(R.id.txtNohaFull);
        }
    }

    // SOURCE: https://stackoverflow.com/questions/54458874/showing-recyclerview-on-fragment-with-data-from-firebase
}
