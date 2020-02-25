package com.richyeoh.android.dawdler;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;

public final class ActivityOps {
    private ActivityOps() {
    }

    public static Intent newSafeIntent(Context ctx, Class<? extends Activity> cls) {
        Intent intent = new Intent(ctx, cls);
        if (!(ctx instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    public static Intent newSafeIntent(Context ctx, String pkg, String cls) {
        Intent intent = new Intent();
        if (!(ctx instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        ComponentName componentName = new ComponentName(pkg, cls);
        intent.setComponent(componentName);
        return intent;
    }

    public static void startActivity(Class<? extends Activity> cls, int enterAnim, int exitAnim) {
        Context ctx = Utils.getTopActivityOrApp();
        startActivity(ctx, cls, null, getActivityOptions(ctx, enterAnim, exitAnim));
    }

    public static void startActivity(String pkg, String cls, int enterAnim, int exitAnim) {
        Context ctx = Utils.getTopActivityOrApp();
        startActivity(ctx, pkg, cls, null, getActivityOptions(ctx, enterAnim, exitAnim));
    }

    public static void startActivity(String pkg, String cls) {
        Context ctx = Utils.getTopActivityOrApp();
        startActivity(ctx, pkg, cls);
    }

    public static void startActivity(Context ctx, String pkg, String cls) {
        startActivity(ctx, pkg, cls, null);
    }

    public static void startActivity(Context ctx, String pkg, String cls, Bundle extras) {
        startActivity(ctx, pkg, cls, extras, null);
    }

    public static void startActivity(Context ctx, String pkg, String cls, Bundle extras, Bundle options) {
        Intent intent = newSafeIntent(ctx, pkg, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(ctx, intent, options);
    }

    public static void startActivity(Class<? extends Activity> cls) {
        Context ctx = Utils.getTopActivityOrApp();
        startActivity(ctx, cls);
    }

    public static void startActivity(Context ctx, Class<? extends Activity> cls) {
        startActivity(ctx, cls, null);
    }

    public static void startActivity(Context ctx, Class<? extends Activity> cls, Bundle extras) {
        startActivity(ctx, cls, extras, null);
    }

    public static void startActivity(Context ctx, Class<? extends Activity> cls, Bundle extras, Bundle options) {
        Intent intent = newSafeIntent(ctx, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(ctx, intent, options);
    }

    public static void startActivity(Context ctx, Intent intent, Bundle options) {
        if (options != null) {
            ctx.startActivity(intent, options);
        } else {
            ctx.startActivity(intent);
        }
    }

    public static Bundle getActivityOptions(Context ctx, int enterAnim, int exitAnim) {
        return ActivityOptionsCompat.makeCustomAnimation(ctx, enterAnim, exitAnim).toBundle();
    }
}
