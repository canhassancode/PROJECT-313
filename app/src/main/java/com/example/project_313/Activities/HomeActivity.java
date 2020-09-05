package com.example.project_313.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_313.NohayModel;
import com.example.project_313.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.io.LineReader;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;

    private BottomNavigationView btm_nav;
    private NavController btm_nav_controller;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        // nav bar
        BottomNavigationView btm_nav = findViewById(R.id.navigation_home);
        NavController btm_nav_controller = Navigation.findNavController(this, R.id.fragment);

        NavigationUI.setupWithNavController(btm_nav, btm_nav_controller);

        // TODO: Add animation to nav bar


//        // Database list
//        firebaseFirestore = FirebaseFirestore.getInstance();
//        mFirestoreList = findViewById(R.id.noha_list);
//
//        //Query INVESTIGATE .OPTIONS AFTER
//        Query query = firebaseFirestore.collection("nohay");
//
//        //RecyclerOptions
//        FirestoreRecyclerOptions<NohayModel> options = new FirestoreRecyclerOptions.Builder<NohayModel>()
//                .setQuery(query, NohayModel.class)
//                .build();
//
//        adapter = new FirestoreRecyclerAdapter<NohayModel, NohayViewHolder>(options) {
//            @NonNull
//            @Override
//            public NohayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//                // Noha layout fullscreen options
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noha_fullscreen, parent, false);
//
//                // return to noha class
//
//                return new NohayViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull NohayViewHolder holder, int position, @NonNull NohayModel model) {
//                holder.txtTitle.setText(model.getTitle());
//                holder.txtNoha.setText(model.getNoha());
//            }
//        };
//        mFirestoreList.setHasFixedSize(true);
//        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
//        mFirestoreList.setAdapter(adapter);

    }

//    private class NohayViewHolder extends RecyclerView.ViewHolder {
//
//        // TextView from layout
//        private TextView txtTitle;
//        private TextView txtNoha;
//
//
//        public NohayViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txtTitle = itemView.findViewById(R.id.txtTitle);
//            txtNoha = itemView.findViewById(R.id.txtNoha);
//        }
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
}