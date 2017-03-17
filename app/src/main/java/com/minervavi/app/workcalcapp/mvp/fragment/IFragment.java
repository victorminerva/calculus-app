package com.minervavi.app.workcalcapp.mvp.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blackcat.currencyedittext.CurrencyEditText;

/**
 * Created by victo on 14/03/2017.
 */

public interface IFragment {

    interface IFragmentModel {

    }

    interface IFragmentPresenter {
        Context getContext();
        void setView(Context context);
        void onAddDescontoClick(final CurrencyEditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto);

    }
    interface IFragmentView {
        void init(View view);
    }
}
