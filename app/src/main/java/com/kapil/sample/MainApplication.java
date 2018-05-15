package com.kapil.sample;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;


import com.kapil.sample.dagger.DaggerServiceComponent;
import com.kapil.sample.dagger.ServiceComponent;


/**
 * Created by kapilsharma on 11/07/17.
 */
public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static MainApplication instance;
    private ServiceComponent component;
    private SharedPreferences sharedPreferences;
    private Toast toast;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sharedPreferences = getSharedPreferences(TAG, 0);
        component = DaggerServiceComponent.builder().build();
    }

    public ServiceComponent component() {
        return component;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void showToast(String message) {
        cancelToast();
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
