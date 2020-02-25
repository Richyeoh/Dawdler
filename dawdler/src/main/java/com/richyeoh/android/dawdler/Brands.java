package com.richyeoh.android.dawdler;

import android.os.Build;

public final class Brands {
    private Brands() {
    }

    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    public static boolean isOppo() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    public static boolean isVivo() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    public static boolean isHuawei() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("huawei")
                || Build.BRAND != null && Build.BRAND.toLowerCase().equals("honor");
    }

    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    public static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }
}
