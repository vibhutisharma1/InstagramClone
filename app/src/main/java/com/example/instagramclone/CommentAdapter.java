package com.example.instagramclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<Comment> comments;


    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }


    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        try {
            holder.bind(comment);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void clear() {
        comments.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Comment> list){
        comments.addAll(list);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView commentTxt;
        private ImageView profile;
        private TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentTxt = itemView.findViewById(R.id.commentTxt);
            profile = itemView.findViewById(R.id.profilePic);
            username = itemView.findViewById(R.id.tvUsername);

        }
        public void bind(Comment comment) throws ParseException {
            commentTxt.setText("" + comment.getKeyComment());
            username.setText(comment.getUser().fetchIfNeeded().getUsername());

            ParseFile profile_img =  comment.getUser().getParseFile("profile");
            if (profile_img != null) {
                Glide.with(context).load(profile_img.getUrl()).into(profile);
            }
        }

        @Override
        public void onClick(View v) {

        }


    }
}
