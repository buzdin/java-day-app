package lv.jug.javaday.androidapp.application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import lv.jug.javaday.androidapp.infrastructure.common.ClassLogger;

import javax.inject.Inject;

public class NetworkService {

    ClassLogger logger = new ClassLogger(NetworkService.class);

    @Inject
    Context context;

    public boolean internetAvailable()
    {
        NetworkInfo info = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            logger.d("no internet connection");
            return false;
        } else {
            if(info.isConnected()) {
                logger.d(" internet connection available...");
                return true;
            } else {
                logger.d(" internet connection");
                return true;
            }
        }
    }
}
