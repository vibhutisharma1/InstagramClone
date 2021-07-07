package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nEcWHcUUTmyfbLDJ1R4HQQsNaJmZ8BVGEERjST8L")
                .clientKey("hjQK2EpIZ14JaFFEOeZEhHuPN9ORWqfQffxE0Fm2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
