package com.demo.com.demo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkTest {

    public static void printInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networks = manager.getAllNetworks();
        if (networks != null) {
            for (Network network : networks) {
                handleSingleNetwork(context, network);
            }
        }
    }

    private static void handleSingleNetwork(Context context, Network network) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (network != null) {
            NetworkInfo networkInfo = manager.getNetworkInfo(network);
            if (network != null) {
//            Log.d("xwj", "networkInfo.getSubtypeName = " + networkInfo.getSubtypeName());
//            Log.d("xwj", "networkInfo.isConnected = " + networkInfo.isConnected());
//            Log.d("xwj", "networkInfo.isAvailable = " + networkInfo.isAvailable());
                Log.d("xwj", "networkInfo = " + networkInfo.toString());
            }

            NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
            if (capabilities != null) {
                Log.d("xwj", "capabilities NET_CAPABILITY_VALIDATED = " + capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED));
                Log.d("xwj", "capabilities NET_CAPABILITY_WIFI_P2P = " + capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P));
                Log.d("xwj", "capabilities NET_CAPABILITY_NOT_METERED = " + capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED));
                Log.d("xwj", "capabilities NET_CAPABILITY_INTERNET = " + capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET));
                Log.d("xwj", "transport TRANSPORT_CELLULAR = " + capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
                Log.d("xwj", "transport TRANSPORT_WIFI = " + capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                Log.d("xwj", "transport TRANSPORT_WIFI_AWARE = " + capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE));
            }
        }
    }
}
