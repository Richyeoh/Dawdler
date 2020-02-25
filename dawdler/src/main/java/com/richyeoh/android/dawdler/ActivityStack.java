package com.richyeoh.android.dawdler;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class ActivityStack implements Application.ActivityLifecycleCallbacks {
    private static final ActivityStack INSTANCE = new ActivityStack();
    private static ActivityLifecycleCallbacks sLifecycleCallbacks = null;
    private static ArrayList<Activity> sActivityList = new ArrayList<>();

    private ActivityStack() {
    }

    public static void init(Application app) {
        app.registerActivityLifecycleCallbacks(INSTANCE);
    }

    public static List<Activity> getActivityList() {
        return sActivityList;
    }

    public static int getStackSize() {
        return sActivityList.size();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
        }
        sActivityList.add(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityStarted(activity);
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityResumed(activity);
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityPaused(activity);
        }
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityStopped(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);
        }
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        if (sLifecycleCallbacks != null) {
            sLifecycleCallbacks.onActivityDestroyed(activity);
        }
        sActivityList.remove(activity);
    }

    public interface ActivityLifecycleCallbacks {

        void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState);

        void onActivityStarted(@NonNull Activity activity);

        void onActivityResumed(@NonNull Activity activity);

        void onActivityPaused(@NonNull Activity activity);

        void onActivityStopped(@NonNull Activity activity);

        void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState);

        void onActivityDestroyed(@NonNull Activity activity);
    }

    public static class SimpleActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
        }
    }
}
