package com.cl.languagetranslation.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.cl.languagetranslation.util.LanguageHelper;

/**
 * Created by Vinay on 5/6/17.
 */

public class BaseActivity extends AppCompatActivity {

    // override the base context of application to update default locale for this activity
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base));
    }
}
