package com.example.project_313;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_313.Activities.HomeActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class home_fragment extends Fragment {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    View view;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home_fragment, container, false);

        mFirestoreList = view.findViewById(R.id.noha_list);
        setUpRecyclerView();

        // Database list

        return view;
    }

    public void setUpRecyclerView() {
        //Query INVESTIGATE .OPTIONS AFTER
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("nohay");

        //RecyclerOptions
        FirestoreRecyclerOptions<NohayModel> options = new FirestoreRecyclerOptions.Builder<NohayModel>()
                .setQuery(query, NohayModel.class)
                .build();


        this.adapter = new FirestoreRecyclerAdapter<NohayModel, NohayViewHolder>(options) {
            @NonNull
            @Override
            public NohayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Noha layout fullscreen options
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noha_fullscreen, parent, false);

                // return to noha class

                return new home_fragment.NohayViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull NohayViewHolder holder, int position, @NonNull NohayModel model) {
                holder.txtTitle.setText(model.getTitle());
                holder.txtNoha.setText(model.getNoha());
            }
        };
//        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFirestoreList.setAdapter(adapter);

    }

    public class NohayViewHolder extends RecyclerView.ViewHolder  {
        // TextView from layout
        private TextView txtTitle;
        private TextView txtNoha;


        public NohayViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtNoha = itemView.findViewById(R.id.txtNoha);
        }
    }
}