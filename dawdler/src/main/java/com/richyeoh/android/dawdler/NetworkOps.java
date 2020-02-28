package com.richyeoh.android.dawdler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresPermission;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public final class NetworkOps {
    private static ConnectivityManager sManager = (ConnectivityManager) Utils.getSystemService(Context.CONNECTIVITY_SERVICE);

    private NetworkOps() {
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean hasNetworkConnected() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] allNetworks = sManager.getAllNetworks();
            for (Network network : allNetworks) {
                NetworkInfo networkInfo = sManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } else {
            NetworkInfo[] allNetworkInfo = sManager.getAllNetworkInfo();
            for (NetworkInfo network : allNetworkInfo) {
                if (network.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isMobileConnected() {
        NetworkInfo networkInfo = getNetworkInfo();
        return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = getNetworkInfo();
        return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static int getNetworkType() {
        return getNetworkInfo().getType();
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static String getNetworkTypeName() {
        return getNetworkInfo().getTypeName();
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static NetworkInfo getNetworkInfo() {
        return sManager.getActiveNetworkInfo();
    }
}
