package com.minervavi.app.workcalcapp.mvp.app;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;

import com.mancj.slideup.SlideUp;

/**
 * Created by victo on 13/03/2017.
 */

public interface IApp {

    interface IAppModel {
        void removeDadosSalvos();
    }

    interface IAppPresenter {
        Context getContext();
        void onCategoriaClick(View v);
        void frameLayoutUnpressed(FrameLayout flGeneralSalario, FrameLayout flGeneralFerias,
                                  FrameLayout flGeneralHoraExtra, FrameLayout flGeneralDecimo,
                                  FrameLayout flGeneralRetroativo, FrameLayout flGeneralSettings);
        void setView(IAppView view);
        void setFragmentManager(FragmentManager fragmentManager);
        void showSlideUpCurrent(SlideUp slideUp, FloatingActionButton fab);

        void removeDadosSalvos();
    }

    interface IAppView {
        void init();
        void showSlideUpCurrent(FloatingActionButton fab);
    }
}
