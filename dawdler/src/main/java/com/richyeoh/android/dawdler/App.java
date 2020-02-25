package com.richyeoh.android.dawdler;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

public class App {
    private static Application application;

    static {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Method currentApplication = activityThread.getDeclaredMethod("currentApplication");
            application = (Application) currentApplication.invoke(null); // as Application
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (application == null) {
            throw new NullPointerException("Application cannot be null");
        }
    }

    private App() {
    }

    public static Application getApplication() {
        return application;
    }

    public static Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public static Context getContext() {
        return getApplication().getBaseContext();
    }

    public static AssetManager getAssetManager() {
        return getApplication().getAssets();
    }

    public static PackageManager getPackageManager() {
        return getApplication().getPackageManager();
    }

    public static Resources getResources() {
        return getApplication().getResources();
    }

    public static ContentResolver getContentResolver() {
        return getApplication().getContentResolver();
    }
}
