package com.example.instagramclone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.instagramclone.Post;
import com.example.instagramclone.R;
import com.example.instagramclone.databinding.FragmentCommentBinding;
import com.example.instagramclone.databinding.FragmentPostsBinding;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class CommentFragment extends Fragment {

    FragmentCommentBinding binding;
    private Post post;
    public static final String TAG = "ComposeFragment";

    public CommentFragment() {
        // Required empty public constructor
    }

    public static CommentFragment newInstance() {
        CommentFragment commentFragment = new CommentFragment();
        Bundle args = new Bundle();
        commentFragment.setArguments(args);
        return commentFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCommentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        post = (Post) getArguments().getSerializable("post");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update backend with comment details
                ParseObject newComment = ParseObject.create("Comments");
                newComment.put("text", binding.commentInfo.getText().toString());
                newComment.put("post", post);
                newComment.put("user", ParseUser.getCurrentUser());

                newComment.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e != null){
                            Log.e(TAG, "Error while saving comment", e);
                            Toast.makeText(getContext(),"Error while saving comment!",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Comment was saved :)",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }


}