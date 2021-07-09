package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.instagramclone.databinding.ActivityMainBinding;
import com.example.instagramclone.databinding.ActivityPostDetailsBinding;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetails extends AppCompatActivity {

    Post post;
    ActivityPostDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        post  = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));

        binding.tvDescription.setText(post.getCaption());
        binding.tvUsername.setText(post.getUser().getUsername());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(binding.ivImage);
        }


    }

}