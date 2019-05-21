package com.javaxian.postexercise.utils;

import android.util.Log;

public class DebugUtils {

    public static final boolean DEBUG = true;
    public static final boolean LOG_TO_FILE = false;

    public static void logInfo(String text) {
        if (DEBUG)
            Log.i("DebugUtils", String.valueOf(text));
    }

    public static void logInfo(String method, String text) {
        if (DEBUG)
            Log.i(String.valueOf(method), String.valueOf(text));
    }

    public static void logInfo(String className, String method, String text) {
        if (DEBUG)
            Log.i(className+" :: "+String.valueOf(method), String.valueOf(text));
    }

    public static void logError(String text) {
        if (DEBUG)
            Log.e("DebugUtils", String.valueOf(text));
    }

    public static void logError(String method, String text) {
        if (DEBUG)
            Log.e(String.valueOf(method), String.valueOf(text));
    }

    public static void logError(String className, String method, String text) {
        if (DEBUG)
            Log.e(className+" :: "+String.valueOf(method), String.valueOf(text));
    }

    public static void logDebug(String text) {
        if (DEBUG)
            Log.d("DebugUtils", String.valueOf(text));
    }

    public static void logDebug(String method, String text) {
        if (DEBUG)
            Log.d(String.valueOf(method), String.valueOf(text));
    }

    public static void logDebug(String className, String method, String text) {
        if (DEBUG)
            Log.d(className+" :: "+String.valueOf(method), String.valueOf(text));
    }

    public static void logThrowable(Throwable e) {
        if (DEBUG) {
            Log.e("LogUtil", e.toString());
            e.printStackTrace();
            if (LOG_TO_FILE) {
                logToFile(e);
            }
        }
    }

    public static void logWarning(String text) {
        if (DEBUG)
            Log.i("DebugUtils", String.valueOf(text));
    }

    public static void logWarning(String method, String text) {
        if (DEBUG)
            Log.i(String.valueOf(method), String.valueOf(text));
    }

    public static void logToFile(Throwable e) {
        try {
            StringBuffer sb = new StringBuffer(e.toString() + "\n");
            StackTraceElement[] stElements = e.getStackTrace();
            String newLine = "";

            for (StackTraceElement stElement : stElements) {
                sb.append(newLine);
                sb.append("\tat ");
                sb.append(stElement.toString());
                newLine = "\n";
            }
        } catch (Exception ee) {
            e.printStackTrace();
        }
    }
}
