package com.pascalhow.htmlparsingexampleapp;

import android.app.Application;
import android.content.Context;

import com.pascalhow.htmlparsingexampleapp.classes.MyMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by pascal on 04/02/2017.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    public static MyApplication getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Timber.plant(new Timber.DebugTree());

        // Configure Realm for the application
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("ExampleAppRealm.realm")
                .schemaVersion(1) // Must be bumped when the schema changes
                .migration(new MyMigration()) // Migration to run instead of throwing an exception.build();
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static MyApplication getApp(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public static Context getContext() {
        return application;
    }
}
