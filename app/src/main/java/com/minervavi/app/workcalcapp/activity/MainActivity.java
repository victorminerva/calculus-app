package com.minervavi.app.workcalcapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.fragment.DadosSalarioLiqFragment;
import com.minervavi.app.workcalcapp.mvp.app.AppPresenter;
import com.minervavi.app.workcalcapp.mvp.app.IApp;

public class MainActivity extends AppCompatActivity implements IApp.IAppView, FrameLayout.OnClickListener {

    private AppPresenter appPresenter;

    /** Screen's Components */
    private FloatingActionButton fab;
    private FrameLayout flGeneralSalario;
    private FrameLayout flGeneralFerias;
    private FrameLayout flGeneralHoraExtra;
    private FrameLayout flGeneralDecimo;
    private FrameLayout flGeneralRetroativo;
    private FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPresenter = new AppPresenter();
        appPresenter.setView(this);
        appPresenter.setFragmentManager(getSupportFragmentManager());

        init();

        flGeneralSalario.setOnClickListener(this);
        flGeneralFerias.setOnClickListener(this);
        flGeneralHoraExtra.setOnClickListener(this);
        flGeneralDecimo.setOnClickListener(this);
        flGeneralRetroativo.setOnClickListener(this);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void init() {
        flGeneralSalario    = (FrameLayout) findViewById(R.id.fl_general_salario);
        flGeneralFerias     = (FrameLayout) findViewById(R.id.fl_general_ferias);
        flGeneralHoraExtra  = (FrameLayout) findViewById(R.id.fl_general_hora_extra);
        flGeneralDecimo     = (FrameLayout) findViewById(R.id.fl_general_decimo);
        flGeneralRetroativo = (FrameLayout) findViewById(R.id.fl_general_retroativo);
        flContainer         = (FrameLayout) findViewById(R.id.fl_container);

        flGeneralSalario.setBackgroundColor(ContextCompat.getColor(this, R.color.grey800));
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                DadosSalarioLiqFragment.newInstance()).commit();
    }

    @Override
    public void onClick(View v) {
        appPresenter.frameLayoutUnpressed(flGeneralSalario, flGeneralFerias, flGeneralHoraExtra,
                flGeneralDecimo, flGeneralRetroativo);
        appPresenter.onCategoriaClick(v);
    }
}
