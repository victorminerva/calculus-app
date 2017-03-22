package com.minervavi.app.workcalcapp.mvp.app;

import android.app.Activity;
import android.content.SharedPreferences;

import com.minervavi.app.workcalcapp.R;

/**
 * Created by victo on 13/03/2017.
 */

public class AppModel implements IApp.IAppModel {

    private IApp.IAppPresenter appPresenter;
    private SharedPreferences preferences;

    public AppModel(IApp.IAppPresenter appPresenter) {
        this.appPresenter = appPresenter;
    }

    @Override
    public void removeDadosSalvos() {
        preferences = appPresenter.getContext().getSharedPreferences(appPresenter.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(appPresenter.getContext().getString(R.string.preference_file_key));
        editor.clear();
        editor.commit();
    }

}
