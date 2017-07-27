package com.cl.languagetranslation.base;

import android.app.Application;
import android.content.Context;

import com.cl.languagetranslation.util.LanguageHelper;

/**
 * Created by Vinay on 29/5/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // override the base context of application to update default locale for the application
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, LanguageHelper.getLanguage(base)));
    }
}
