package com.cl.languagetranslation.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.cl.languagetranslation.R;
import com.cl.languagetranslation.adapters.LanguageSelectionAdapter;
import com.cl.languagetranslation.base.BaseActivity;
import com.cl.languagetranslation.models.Language;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 29/5/17.
 */

public class SettingsActivity extends BaseActivity {

    private LanguageSelectionAdapter mAdapter;
    private ArrayList<Language> langAndLocalesList = new ArrayList<>();

    @BindView(R.id.settingsRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.changeLanguage));
        initView();
    }

    private void initView() {
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadLanguageList();
        mAdapter = new LanguageSelectionAdapter(this, langAndLocalesList);
        recyclerView.setAdapter(mAdapter);
    }

    private void loadLanguageList() {
        for (String langCode : getResources().getStringArray(R.array.ln)) {
            // based on language code get language name to display in same locale
            Locale languageLocale = new Locale(langCode);
            String languageName = languageLocale.getDisplayLanguage(languageLocale);
            // based on language code get language name to display in english locale
            Locale englishLocale = new Locale(getString(R.string.en));
            String languageNameInEnglishLocale = languageLocale.getDisplayLanguage(englishLocale);
            Language langAndLocale = new Language(langCode, languageName, languageNameInEnglishLocale);
            langAndLocalesList.add(langAndLocale);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
