package com.sparrowpaul.journalapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PrivateMode = 0;

    // shared pref file name
    private static final String PrefName = "Journal App";

    private static final String FirstTimeLaunch = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PrefName, PrivateMode);
        editor = pref.edit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(FirstTimeLaunch, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(FirstTimeLaunch, true);
    }


}
