package com.example.instagramclone;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Comments")
public class Comment extends ParseObject {

    public static final String KEY_COMMENT = "words";
    public static final String KEY_POST = "post";
    public static final String KEY_USER = "user";


    public String getKeyComment() { return getString(KEY_COMMENT); }
    public void setComment(String description) {
        put(KEY_COMMENT, description);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseObject getPost(){ return getParseObject(KEY_POST);}

    public void setPost(ParseObject post){ put(KEY_POST, post);}


}
