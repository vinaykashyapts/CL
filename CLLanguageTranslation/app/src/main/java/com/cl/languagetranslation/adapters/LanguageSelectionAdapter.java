package com.cl.languagetranslation.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.cl.languagetranslation.R;
import com.cl.languagetranslation.activities.MainActivity;
import com.cl.languagetranslation.models.Language;
import com.cl.languagetranslation.util.LanguageHelper;

import java.util.ArrayList;

public class LanguageSelectionAdapter extends RecyclerView.Adapter<LanguageSelectionAdapter.MyViewHolder> {
    private ArrayList<Language> langAndLocales;
    private TextView languageName;
    private Activity activity;

    public LanguageSelectionAdapter(Activity activity, ArrayList<Language> langAndLocales) {
        this.activity = activity;
        this.langAndLocales = langAndLocales;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View itemView) {
            super(itemView);
            languageName = (TextView) itemView.findViewById(R.id.languageName);
            final MaterialRippleLayout ripple = (MaterialRippleLayout) itemView.findViewById(R.id.languageNameLayout);

            ripple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Language langAndLocale = langAndLocales.get(getAdapterPosition());
                    switchLanguage(activity, langAndLocale.getLanguageCode());
                }

            });
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_language_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Language langAndLocale = langAndLocales.get(position);
        languageName.setText(langAndLocale.getLanguageName() + " ( " + langAndLocale.getLanguageNameInDefaultLocale() + " ) ");
    }

    @Override
    public int getItemCount() {
        return langAndLocales.size();
    }

    public void switchLanguage(Activity activity, String languageCode) {
        LanguageHelper.setLanguage(activity, languageCode);
        relaunch(activity);
    }

    public void relaunch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        Runtime.getRuntime().exit(0);
        activity.finish();
    }
}
