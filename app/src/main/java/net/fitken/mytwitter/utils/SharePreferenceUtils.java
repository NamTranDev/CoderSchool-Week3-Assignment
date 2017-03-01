package net.fitken.mytwitter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ken on 2/3/2017.
 */

public class SharePreferenceUtils {

    private static String mPrefName = "mytwitter.pref";

    /**
     * Save specific string to SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName Desired preferences file. If a preferences file by this name does not exist, it will be created when you retrieve an editor (SharedPreferences.edit()) and then commit changes (Editor.commit())
     * @param key       The name of the preference to modify.
     * @param value     The new value for the preference.
     */
    public static void saveStringPref(Context context, String key,
                                      String value) {
        if (context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Save specific int to SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param value     The new value for the preference.
     */
    public static void saveIntPref(Context context, String key,
                                   int value) {
        if (context == null) return;

        SharedPreferences pref = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Save specific long to SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param value     The new value for the preference.
     */
    public static void saveLongPref(Context context, String key,
                                    long value) {
        if (context == null) return;

        SharedPreferences pref = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Save specific bool to SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName Desired preferences file. If a preferences file by this name does not exist, it will be created when you retrieve an editor (SharedPreferences.edit()) and then commit changes (Editor.commit())
     * @param key       The name of the preference to modify.
     * @param value     The new value for the preference.
     */
    public static void saveBoolPref(Context context, String key,
                                    boolean value) {
        if (context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * get specific bool from SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param defValue  The default value if reference not exist
     * @return the bool pref
     */
    public static boolean getBoolPref(Context context, String key, boolean defValue) {
        if (context == null) return defValue;

        SharedPreferences settings = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defValue);
    }

    /**
     * get specific int from SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param defValue  The default value if reference not exist
     * @return the int pref
     */
    public static int getIntPref(Context context, String key, int defValue) {
        if (context == null) return defValue;

        SharedPreferences settings = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        return settings.getInt(key, defValue);
    }

    /**
     * get specific string from SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param defValue  The default value if reference not exist
     * @return the string pref
     */
    public static String getStringPref(Context context, String key, String defValue) {
        if (context == null) return defValue;
        String result = null;
        SharedPreferences settings = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        result = settings.getString(key, defValue);
        return result;
    }

    /**
     * get specific long from SharedPreferences.
     *
     * @param context   The context of the preferences whose values are wanted.
     * @param mPrefName the pref name
     * @param key       The name of the preference to modify.
     * @param defValue  The default value if reference not exist
     * @return the long pref
     */
    public static long getLongPref(Context context, String key, long defValue) {
        if (context == null) return defValue;

        SharedPreferences settings = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        return settings.getLong(key, defValue);

    }
}
