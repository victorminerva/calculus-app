package com.minervavi.app.workcalcapp.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.mvp.app.IApp;

/**
 * Created by victo on 14/03/2017.
 */

public interface IFragment {

    interface IFragmentModel {

    }

    interface IFragmentPresenter {
        Context getContext();
        void setView(Context context);
        void setViewApp(IApp.IAppView appView);
        void setActivity(Activity activity);
        void onAddDescontoClick(final CurrencyEditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto);
        void showSlideUpFragCurrent(FloatingActionButton fab);
    }

    interface IFragmentView {
        void init(View view);
    }
}
