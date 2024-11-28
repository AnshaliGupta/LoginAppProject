package com.anshali.demologinapp.helper;
import android.content.Context;
import android.content.SharedPreferences;
public class PreferenceHelper {
    private static PreferenceHelper demopreferenceHelper = null;
    private SharedPreferences demoPreferences;
    private Context democontext;
    public static final String EMAIL = "email";
    public static final String PWD = "password";
    public static final String NAME = "name";
    public static final String IS_USER_LOGGED_IN = "is_user_logged_in";

    public PreferenceHelper(Context democontext1) {
        this.democontext = democontext1;
        demoPreferences=democontext1.getSharedPreferences("demoLoginpref", Context.MODE_PRIVATE);
    }

    public static synchronized PreferenceHelper getInstance(Context democontext) {
        if(demopreferenceHelper==null) {
            demopreferenceHelper = new PreferenceHelper(democontext);
        }
        return demopreferenceHelper;
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor demoEditor = demoPreferences.edit();
        demoEditor.putString(key, value);
        demoEditor.apply();
    }

    public String getString(String key) {
        return demoPreferences.getString(key,"");
    }

    public void saveLogin(String email, String pwd) {
        SharedPreferences.Editor demoeditor = demoPreferences.edit();
        /*demoeditor.putString(EMAIL, email);
        demoeditor.putString(PWD, pwd);*/
        demoeditor.putBoolean(IS_USER_LOGGED_IN, true);
        demoeditor.apply();
    }

    public void saveSignUp(String name, String email, String pwd) {
        SharedPreferences.Editor demoeditor = demoPreferences.edit();
        demoeditor.putString(NAME, name);
        demoeditor.putString(EMAIL, email);
        demoeditor.putString(PWD, pwd);
        demoeditor.putBoolean(IS_USER_LOGGED_IN, true);
        demoeditor.apply();
    }

    public boolean IsUserLoggedIn() {
        return demoPreferences.getBoolean(IS_USER_LOGGED_IN,false);
    }

    public void logout() {
        SharedPreferences.Editor demoeditor = demoPreferences.edit();
        demoeditor.putBoolean(IS_USER_LOGGED_IN, false);
        demoeditor.apply();
    }
}
