package com.fadzthor.akademik;

import android.content.Context;
import android.content.SharedPreferences;

public class SahredPrefManager {
    private static SahredPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "sharedpref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USERMAIL = "email";
    private static final String KEY_USERID = "userid";
    private static final String KEY_NPM = "npm";
    private static final String KEY_NAMAMHS = "namaMhs";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_NAMAPRODI = "namaprodi";
    private static final String KEY_JENJANG = "jenjang";

    private SahredPrefManager(Context context){mCtx = context;}
    public static synchronized SahredPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SahredPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String username, String email){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USERID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USERMAIL, email);

        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERNAME, null) !=null){
            return true;
        }
        return false;
    }
    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }
    public String getUserMail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERMAIL, null);
    }

    public boolean profileMhs(String namaMhs, String semester, String namaprodi, String jenjang, String npm){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAMAMHS, namaMhs);
        editor.putString(KEY_NPM, npm);
        editor.putString(KEY_SEMESTER, semester);
        editor.putString(KEY_NAMAPRODI, namaprodi);
        editor.putString(KEY_JENJANG, jenjang);

        editor.apply();
        return true;
    }

}
