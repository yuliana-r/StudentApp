package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//This class is used to display information on topic posts e.g. Topic subject, author, posted date and post content
public class TopicAdaptor extends RecyclerView.Adapter<TopicAdaptor.TopicHolder> {

    ArrayList<ForumPost> forumPosts;

    public static class TopicsAdaptor extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView topicName, topicAuthor, topicDate, topicContent;
        int replies;
        TopicHolderClick listener;

        public TopicsAdaptor(@NonNull View itemView, TopicHolderClick _listener) {
            super(itemView);
            topicName = itemView.findViewById(R.id.topicTitle);
            topicAuthor = itemView.findViewById(R.id.topicAuthor);
            topicDate = itemView.findViewById(R.id.topicDate);
            topicContent = itemView.findViewById(R.id.topicContent);
        }

        public  interface TopicHolderClick {
            void onTopicClick(int position);
        }

        @Override
        public void onClick(View view) {
            listener.onTopicClick(getAdapterPosition());
        }
    }
        public TopicAdaptor(ArrayList<ForumPost> forumPosts) {
        this.forumPosts = forumPosts;
        }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_card, viewGroup, false);
        return new TopicHolder(view);
    }

    @Override
    public void  onBindViewHolder(@NonNull TopicHolder topicHolder, int i) {
        topicHolder.topicDateTv.setText(forumPosts.get(i).getPostedDate());
        topicHolder.topicAuthorId.setText(forumPosts.get(i).getUserID());
        topicHolder.topicTitleTv.setText(forumPosts.get(i).getTitle());
        topicHolder.topicContentTv.setText(forumPosts.get(i).getPostContent());

    }

    public int getItemCount() {
        return forumPosts.size();
    }

    public static class TopicHolder extends RecyclerView.ViewHolder  {
        TextView topicTitleTv, topicDateTv, topicAuthorId, topicContentTv, topicRepliesTv;

        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            topicTitleTv = itemView.findViewById(R.id.topicTitle);
            topicDateTv = itemView.findViewById(R.id.topicDate);
            topicAuthorId = itemView.findViewById(R.id.topicAuthor);
            topicContentTv = itemView.findViewById(R.id.topicContent);
        }
    }
}
