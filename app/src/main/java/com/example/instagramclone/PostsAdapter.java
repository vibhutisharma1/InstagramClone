package com.example.instagramclone;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }
    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private ImageView profileImg;
        private ImageView likeBtn;
        private TextView captionUser;
        private TextView likeCount;
        private TextView timeAgo;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            profileImg = itemView.findViewById(R.id.profilePic);
            likeBtn = itemView.findViewById(R.id.heartLike);
            captionUser = itemView.findViewById(R.id.captionUser);
            likeCount = itemView.findViewById(R.id.numLikes);
            timeAgo = itemView.findViewById(R.id.timeAgo);

            itemView.setOnClickListener(this);

            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Post current = posts.get(getAdapterPosition());
                    current.setLike(current.getKeyLike() + 1);
                    likeBtn.setBackgroundColor(Color.rgb(255,0,0));
                    current.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            likeCount.setText("" + current.getKeyLike());
                        }
                    });

                }
            });


        }

        public void bind(Post post) {
            String username = post.getUser().getUsername();

            ParseFile profile_img =  post.getUser().getParseFile("profile");
            if (profile_img != null) {
                Glide.with(context).load(profile_img.getUrl()).circleCrop().into(profileImg);
            }

            tvUsername.setText(username);
            captionUser.setText(username);
            likeCount.setText("" +post.getKeyLike());

            Date createdAt = post.getCreatedAt();
            String time = Post.calculateTimeAgo(createdAt);
            timeAgo.setText(time);

            tvDescription.setText(post.getCaption());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }



        }



        @Override
        public void onClick(View v) {
            Post post = posts.get(getAdapterPosition());
            Intent i = new Intent(context, PostDetails.class);
            i.putExtra("post", Parcels.wrap(post));
            context.startActivity(i);

        }
    }
}