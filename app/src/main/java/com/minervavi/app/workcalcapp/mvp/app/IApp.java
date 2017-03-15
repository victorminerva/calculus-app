package com.minervavi.app.workcalcapp.mvp.app;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by victo on 13/03/2017.
 */

public interface IApp {

    interface IAppModel {
    }

    interface IAppPresenter {
        Context getContext();

        void onCategoriaClick(View v);

        void frameLayoutUnpressed(FrameLayout flGeneralSalario, FrameLayout flGeneralFerias,
                                  FrameLayout flGeneralHoraExtra, FrameLayout flGeneralDecimo,
                                  FrameLayout flGeneralRetroativo);

        void setView(IAppView view);
    }

    interface IAppView {
        //void onCategoriaClick();

    }
}
