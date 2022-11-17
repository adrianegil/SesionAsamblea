package cu.desoft.sesionasamblea.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        NetworkInfo mobileNetInfo = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((null == networkInfo) && !mobileNetInfo.isConnected()) {
           // SNBApp.BUS.post(SNBEvent.CONNECTION_LOST);
        } else if (mobileNetInfo.isConnectedOrConnecting()) {
            if (mobileNetInfo.isConnected()) {
              //  SNBApp.BUS.post(SNBEvent.CONNECTED);
            } else {
              //  SNBApp.BUS.post(SNBEvent.RECONNECTING);
            }
        } else {
          //  SNBApp.BUS.post(SNBEvent.CONNECTED);
        }
    }
}
