package com.minervavi.app.workcalcapp.mvp.app;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.minervavi.app.workcalcapp.R;

/**
 * Created by victo on 13/03/2017.
 */

public class AppPresenter implements IApp.IAppPresenter {

    private IApp.IAppView appView;
    private IApp.IAppModel appModel;

    public AppPresenter() {
        appModel = new AppModel(this);
    }

    @Override
    public Context getContext() {
        return (Context) appView;
    }

    @Override
    public void onCategoriaClick(View v) {
//        Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
        v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey800));
    }

    @Override
    public void frameLayoutUnpressed(FrameLayout flGeneralSalario, FrameLayout flGeneralFerias,
                                     FrameLayout flGeneralHoraExtra, FrameLayout flGeneralDecimo,
                                     FrameLayout flGeneralRetroativo) {
        flGeneralSalario.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey700));
        flGeneralFerias.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey700));
        flGeneralHoraExtra.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey700));
        flGeneralDecimo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey700));
        flGeneralRetroativo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey700));
    }

    @Override
    public void setView(IApp.IAppView appView) {
        this.appView = appView;
    }

}
