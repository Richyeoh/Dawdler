package com.richyeoh.android.dawdler;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import java.util.List;

final class Utils {
    static void init() {
        ActivityStack.init(AppOps.getApplication());
    }

    static Context getTopActivityOrApp() {
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
        return AppOps.getApplicationContext();
    }

    static Object getSystemService(String name) {
        return AppOps.getApplication().getSystemService(name);
    }
}
