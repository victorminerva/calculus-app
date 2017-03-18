package com.minervavi.app.workcalcapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mancj.slideup.SlideUp;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.fragment.DadosSalarioLiqFragment;
import com.minervavi.app.workcalcapp.mvp.app.AppPresenter;
import com.minervavi.app.workcalcapp.mvp.app.IApp;

public class MainActivity extends AppCompatActivity implements IApp.IAppView, FrameLayout.OnClickListener {

    private AppPresenter appPresenter;

    /**
     * Screen's Components
     */
    private FloatingActionButton fab;
    private FrameLayout flGeneralSalario;
    private FrameLayout flGeneralFerias;
    private FrameLayout flGeneralHoraExtra;
    private FrameLayout flGeneralDecimo;
    private FrameLayout flGeneralRetroativo;
    private FrameLayout flContainer;

    private RelativeLayout slideView;
    private FrameLayout dim;
    /**
     * Slide
     */
    protected SlideUp slideUp;


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

        slideUp = new SlideUp.Builder(slideView)
                .withListeners(new SlideUp.Listener() {
                    @Override
                    public void onSlide(float percent) {
                        dim.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        fab.show();
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.show();
                fab.hide();
            }
        });
    }

    @Override
    public void init() {
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.flGeneralSalario = (FrameLayout) findViewById(R.id.fl_general_salario);
        this.flGeneralFerias = (FrameLayout) findViewById(R.id.fl_general_ferias);
        this.flGeneralHoraExtra = (FrameLayout) findViewById(R.id.fl_general_hora_extra);
        this.flGeneralDecimo = (FrameLayout) findViewById(R.id.fl_general_decimo);
        this.flGeneralRetroativo = (FrameLayout) findViewById(R.id.fl_general_retroativo);
        this.flContainer = (FrameLayout) findViewById(R.id.fl_container);
        this.slideView = (RelativeLayout) findViewById(R.id.slideView);
        this.dim = (FrameLayout) findViewById(R.id.dim);

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
