package com.minervavi.app.workcalcapp.mvp.app;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.mancj.slideup.SlideUp;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.fragment.DadosDecimoFragment;
import com.minervavi.app.workcalcapp.fragment.DadosFeriasFragment;
import com.minervavi.app.workcalcapp.fragment.DadosHoraExtraFragment;
import com.minervavi.app.workcalcapp.fragment.DadosRetroativoFragment;
import com.minervavi.app.workcalcapp.fragment.DadosSalarioLiqFragment;

/**
 * Created by victo on 13/03/2017.
 */

public class AppPresenter implements IApp.IAppPresenter {

    private IApp.IAppView appView;
    private IApp.IAppModel appModel;

    private FragmentManager fragmentManager;
    private Fragment        fragment;

    public AppPresenter() {
        appModel = new AppModel(this);
    }

    @Override
    public Context getContext() {
        return (Context) appView;
    }

    @Override
    public void onCategoriaClick(View v) {
        v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey800));
        switchFragmentPressed(v);
    }

    private void switchFragmentPressed(View v) {
        switch (v.getId()){
            case R.id.fl_general_salario:
                fragment = DadosSalarioLiqFragment.newInstance();
                break;
            case R.id.fl_general_ferias:
                fragment = DadosFeriasFragment.newInstance();
                break;
            case R.id.fl_general_hora_extra:
                fragment = DadosHoraExtraFragment.newInstance();
                break;
            case R.id.fl_general_decimo:
                fragment = DadosDecimoFragment.newInstance();
                break;
            case R.id.fl_general_retroativo:
                fragment = DadosRetroativoFragment.newInstance();
                break;
        }
        newInstanceFragmentClass();
    }

    private void newInstanceFragmentClass() {
        fragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit();
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

    @Override
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void showSlideUpCurrent(SlideUp slideUp, final FloatingActionButton fab) {
        slideUp.show();
        slideUp.addSlideListener(new SlideUp.Listener() {
            @Override
            public void onSlide(float percent) {
                fab.hide();
            }
            @Override
            public void onVisibilityChanged(int visibility) {
                fab.show();
            }
        });
    }

    @Override
    public void removeDadosSalvos() {
        appModel.removeDadosSalvos();
    }
}
