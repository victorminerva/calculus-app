package com.minervavi.app.workcalcapp.mvp.fragment;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by victo on 14/03/2017.
 */

public interface IFragment {

    interface IFragmentModel {

    }

    interface IFragmentPresenter {
        Context getContext();
        void setView(Context context);
        void onAddDescontoClick(final EditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto);

    }
    interface IFragmentView {
        void init(View view);
    }
}
