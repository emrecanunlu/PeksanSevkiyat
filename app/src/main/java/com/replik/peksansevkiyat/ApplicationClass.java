
package com.replik.peksansevkiyat;

import android.app.Application;

import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

public class ApplicationClass extends Application {

    private static final String ONESIGNAL_APP_ID = "908471e5-afb4-4acb-8248-b9d5cb078204";

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        OneSignal.getNotifications().requestPermission(false, Continue.none());
    }
}
