package com.richyeoh.android.dawdler;

import android.os.Build;

import java.lang.reflect.Field;

public class Device {
    public static boolean isEmulator() {
        try {
            Class<Build> clazz = Build.class;
            Field isEmulator = clazz.getField("IS_EMULATOR");
            isEmulator.setAccessible(true);
            return isEmulator.getBoolean(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
