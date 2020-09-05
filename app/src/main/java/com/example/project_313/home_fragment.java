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
import com.google.api.Distribution;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class home_fragment extends Fragment {

    private RecyclerView mFirestoreList;
    private NohaAdapter adapter;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        // Recycler View
        mFirestoreList = view.findViewById(R.id.noha_list);
        setUpRecyclerView();

        // Database list
        return view;
    }

    public void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance().collection("nohay");

        FirestoreRecyclerOptions<NohayModel> options = new FirestoreRecyclerOptions.Builder
                <NohayModel>()
                .setQuery(query, NohayModel.class)
                .build();

        adapter = new NohaAdapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        mFirestoreList.setLayoutManager(linearLayoutManager);
        mFirestoreList.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }


    }
}