package com.minervavi.app.workcalcapp.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.mvp.app.IApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 14/03/2017.
 */

public class FragmentPresenter implements IFragment.IFragmentPresenter {

    private IApp.IAppView appView;
    private IFragment.IFragmentView fragmentView;

    /** Context */
    private Context context;
    /** Lists */
    private List<String> listOfDescontos;

    public FragmentPresenter(){
        listOfDescontos = new ArrayList<>();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setView(Context context) {
        this.context = context;
    }

    @Override
    public void setViewApp(IApp.IAppView appView) {
        this.appView = appView;
    }

    @Override
    public void setActivity(Activity activity) {
        this.appView = (IApp.IAppView) activity;
    }

    @Override
    public void onAddDescontoClick(final CurrencyEditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto) {
        if ("".equals(etDesconto.getText().toString())) {
            etDesconto.setError("Preencha este campo!");
        } else {
            listOfDescontos.add(String.valueOf(etDesconto.getRawValue()));

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowDesconto = layoutInflater.inflate(R.layout.row_desconto, null);

            final TextView tvDesconto = (TextView) rowDesconto.findViewById(R.id.tv_desconto);
            ImageButton btnRemove = (ImageButton) rowDesconto.findViewById(R.id.remove);

            tvDesconto.setText(etDesconto.getText().toString());

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LinearLayout) rowDesconto.getParent()).removeView(rowDesconto);

                    if(listOfDescontos.contains(String.valueOf(etDesconto.getRawValue())))
                        listOfDescontos.remove(String.valueOf(etDesconto.getRawValue()));

                    btnAdd.setVisibility(View.VISIBLE);
                    etDesconto.setVisibility(View.VISIBLE);
                }
            });

            llDesconto.addView(rowDesconto, 1);

            etDesconto.setText("");

            if (listOfDescontos.size() == 8) {
                btnAdd.setVisibility(View.GONE);
                etDesconto.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showSlideUpFragCurrent(FloatingActionButton fab) {
        appView.showSlideUpCurrent(fab);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent(FragmentManager fragManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();

        fragmentTransaction.replace(R.id.container_slideup, fragment).commit();
    }
}
