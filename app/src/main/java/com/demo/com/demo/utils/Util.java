package com.demo.com.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public class Util {
    public static boolean isNetworkValidated(Context context) {
        boolean validated = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = manager.getActiveNetwork();
        if (network != null) {
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
            if (capabilities != null) {
                validated = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            }
        }
        return validated;
    }

    public static boolean isWifiValidated(Context context) {
        boolean validated = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = manager.getActiveNetwork();
        if (network != null) {
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
            if (capabilities != null) {
                validated = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            }
        }
        return validated;
    }
}
