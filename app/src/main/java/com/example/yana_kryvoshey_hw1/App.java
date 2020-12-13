package com.example.yana_kryvoshey_hw1;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySp.init(this);

    }

}
