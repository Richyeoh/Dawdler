package com.richyeoh.android.dawdler;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.util.List;

public class Utils {
    private static void init() {
        ActivityStack.init(App.getApplication());
    }

    public static Context getTopActivityOrApp() {
        if (ActivityStack.getStackSize() != 0) {
            List<Activity> activityList = ActivityStack.getActivityList();
            for (int i = activityList.size() - 1; i >= 0; i--) {
                Activity activity = activityList.get(i);
                if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
                    continue;
                }
                return activity;
            }
        }
        return App.getApplicationContext();
    }

    public static final class DawdlerFileProvider extends FileProvider {
        @Override
        public boolean onCreate() {
            init();
            return true;
        }
    }
}
