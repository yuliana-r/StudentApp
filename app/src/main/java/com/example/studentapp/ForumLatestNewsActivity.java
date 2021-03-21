package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumLatestNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button newPostButton;
    ArrayList<ForumPost> forumPosts = new ArrayList<>();
    ValueEventListener listener;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_latest_news);

        recyclerView = findViewById(R.id.recyclerViewLatestNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(ForumLatestNewsActivity.this));
        newPostButton = findViewById(R.id.newLatestNewPost);

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumLatestNewsActivity.this, ForumCreateNewPostActivity.class);
                i.putExtra("Category", "Latest News");
                startActivity(i);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("forum_latest_news");
        populateRecyclerView();
        databaseReference.addListenerForSingleValueEvent(listener);
    }

    public void populateRecyclerView() {

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    forumPosts.add(dataSnapshot.getValue(ForumPost.class));
                }
                TopicAdaptor topicAdaptor = new TopicAdaptor(forumPosts);
                recyclerView.setAdapter(topicAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }
}