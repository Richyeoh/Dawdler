package com.richyeoh.android.dawdler;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class AppExecutors {
    private static ExecutorService sNetworkIO = Executors.newFixedThreadPool(3);
    private static ExecutorService sDiskIO = Executors.newSingleThreadExecutor();
    private static HandlerExecutor sMainThread = new HandlerExecutor();

    private AppExecutors() {
    }

    public static ExecutorService getNetworkIO() {
        return sNetworkIO;
    }

    public static ExecutorService getDiskIO() {
        return sDiskIO;
    }

    public static HandlerExecutor getMainThread() {
        return sMainThread;
    }

    public static class HandlerExecutor implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }
}
