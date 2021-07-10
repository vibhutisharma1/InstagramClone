package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ParseException;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.instagramclone.databinding.ActivityMainBinding;
import com.example.instagramclone.databinding.ActivityPostDetailsBinding;
import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDetails extends AppCompatActivity {

    Post post;
    ActivityPostDetailsBinding binding;
    List<Comment> comments;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        post  = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));

        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, comments);
        binding.rvComments.setAdapter(commentAdapter);
        binding.rvComments.setLayoutManager(new LinearLayoutManager(this));

        //caption
        binding.tvDescription.setText(post.getCaption());
        //username
        binding.tvUsername.setText(post.getUser().getUsername());
        //post image
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(binding.ivImage);
        }

        //profile pic
        ParseFile profile_img =  post.getUser().getParseFile("profile");
        if (profile_img != null) {
            Glide.with(this).load(profile_img.getUrl()).into(binding.profilePic);
        }

        //caption username
        String username = post.getUser().getUsername();
        binding.captionUser.setText(username);
        //likes
        binding.numLikes.setText("" +post.getKeyLike());

        //time created
        Date createdAt = post.getCreatedAt();
        String time = Post.calculateTimeAgo(createdAt);
        binding.timeAgo.setText(time);

        getComments();


    }
    private void getComments() {
        // Define the class we would like to query
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        // limit query to latest 20 items
        query.setLimit(20);
        query.whereEqualTo("post", post);
        // order posts by creation date
        query.addDescendingOrder("createdAt");
        // Execute the find asynchronously
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> objects, com.parse.ParseException e) {
                if (e == null) {
                    commentAdapter.clear();
                    comments.addAll(objects);
                    Log.d("comment objects ", "cannot load the comments " + comments.size());
                    commentAdapter.notifyDataSetChanged();
                } else {
                    Log.d("item", "cannot load the comments " + e.getMessage());
                }

            }

        });
    }

}