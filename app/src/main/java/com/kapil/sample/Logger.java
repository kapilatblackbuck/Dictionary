package com.kapil.sample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kapilsharma on 11/07/17.
 */
public class Logger {

    private static final String TAG = Logger.class.getSimpleName();

    public static void log(String tag, String text) {
        Log.d(tag, text);
    }

    public static void log(String text) {
        log(TAG, text);
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
