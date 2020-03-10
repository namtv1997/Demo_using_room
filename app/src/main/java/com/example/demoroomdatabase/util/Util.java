package com.example.demoroomdatabase.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDateString(Date date) {

        try {
            SimpleDateFormat spf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            Log.d("hh4",spf.format(date));
            return spf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
