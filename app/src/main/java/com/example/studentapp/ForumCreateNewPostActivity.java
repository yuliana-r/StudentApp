package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;

public class ForumCreateNewPostActivity extends AppCompatActivity implements TopicAdaptor.TopicsAdaptor.TopicHolderClick {
    TopicAdaptor topicsAdaptor;
    EditText postTitle, postAuthor, postContent;
    Button addPost;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    String category, threadID;
    TextView categoryDisplay;
    RecyclerView recyclerView;
    ArrayList<ForumPost> forumPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_create_new_post);

        postTitle = findViewById(R.id.topicTitle);
        postAuthor = findViewById(R.id.topicAuthor);
        postContent = findViewById(R.id.postContent);
        categoryDisplay = findViewById(R.id.postCategoryTv);
        addPost = findViewById(R.id.addPostButton);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            category = extras.getString("Category");
        }

        if (category.equals("Latest News")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("forum_latest_news");
            categoryDisplay.setText("Latest News");
        }

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageReference = FirebaseStorage.getInstance().getReference("forum_latest_news");
                Calendar calendar = Calendar.getInstance();
                String date;
                final String threadId = databaseReference.push().getKey();

                date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get((Calendar.MONTH + 1)) + "/" +
                        calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

                databaseReference.child(threadId).setValue(new ForumPost(postTitle.getText().toString(), postAuthor.getText().toString(),
                        date, postContent.getText().toString(), threadId)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ForumCreateNewPostActivity.this, "Your post has been added successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ForumCreateNewPostActivity.this, ForumActivity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForumCreateNewPostActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onTopicClick(int position) {
        Intent i = new Intent (ForumCreateNewPostActivity.this, ForumRepliesActivity.class);
        i.putExtra("topic", (CharSequence) forumPosts.get(position));
        startActivity(i);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture){}
}