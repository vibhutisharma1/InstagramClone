package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nEcWHcUUTmyfbLDJ1R4HQQsNaJmZ8BVGEERjST8L")
                .clientKey("hjQK2EpIZ14JaFFEOeZEhHuPN9ORWqfQffxE0Fm2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
