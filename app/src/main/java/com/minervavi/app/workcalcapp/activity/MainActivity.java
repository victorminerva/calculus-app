package com.minervavi.app.workcalcapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mancj.slideup.SlideUp;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.fragment.DadosSalarioLiqFragment;
import com.minervavi.app.workcalcapp.mvp.app.AppPresenter;
import com.minervavi.app.workcalcapp.mvp.app.IApp;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;

public class MainActivity extends AppCompatActivity implements IApp.IAppView, FrameLayout.OnClickListener {

    private AppPresenter        appPresenter;
    private FragmentPresenter   fragmentPresenter;

    /**
     * Screen's Components
     */
    private FrameLayout flGeneralSalario;
    private FrameLayout flGeneralFerias;
    private FrameLayout flGeneralHoraExtra;
    private FrameLayout flGeneralDecimo;
    private FrameLayout flGeneralRetroativo;
    private FrameLayout flGeneralSettings;

    private RelativeLayout  slideView;
    private FrameLayout     dim;
    /**
     * Slide
     */
    protected SlideUp slideUp;

    private String base64EncodedPublicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPresenter = new AppPresenter();
        appPresenter.setView(this);
        appPresenter.setFragmentManager(getSupportFragmentManager());
        fragmentPresenter = new FragmentPresenter();
        fragmentPresenter.setViewApp(this);
        init();

        slideUp = new SlideUp.Builder(slideView)
                .withListeners(new SlideUp.Listener() {
                    @Override
                    public void onSlide(float percent) {
                        dim.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {}
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();

    }

    @Override
    public void init() {
        base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmgsvdMHh1FSSMGzh6Mt8yFxMONamxa6IYR3J32zKAZXw9aQBaNh1J5s6+a/O9188waswqyqvjnXss780o579rqpCCZ0CwVta5qJWZ1lJoOMD3fxj5jM8Z3izerjF2UgI/Mv/gjqqEQvq1Pn4EcYa0BNNEkym/E7lEkw80hz95nZYWZKj5gnfb0VckQFL3N3FmKI0GExJvpAa1c7RoHtz7C4G7x24t+VcUDEutKGpY1M6ze3u+Wk52wTaUbf+tdKh8oGf4xsVRx/u2Sixl0jHAiInx5NbOyVL0c3/DFM18J+quCP4KDppIHoYP0jaLSxHe4Rp0YA3VRpPTpdJ9sR7xQIDAQAB";

        this.flGeneralSalario       = (FrameLayout) findViewById(R.id.fl_general_salario);
        this.flGeneralFerias        = (FrameLayout) findViewById(R.id.fl_general_ferias);
        this.flGeneralHoraExtra     = (FrameLayout) findViewById(R.id.fl_general_hora_extra);
        this.flGeneralDecimo        = (FrameLayout) findViewById(R.id.fl_general_decimo);
        this.flGeneralRetroativo    = (FrameLayout) findViewById(R.id.fl_general_retroativo);
        this.flGeneralSettings      = (FrameLayout) findViewById(R.id.fl_general_settings);
        this.slideView              = (RelativeLayout) findViewById(R.id.slideView);
        this.dim                    = (FrameLayout) findViewById(R.id.dim);

        flGeneralSalario.setOnClickListener(this);
        flGeneralFerias.setOnClickListener(this);
        flGeneralHoraExtra.setOnClickListener(this);
        flGeneralDecimo.setOnClickListener(this);
        flGeneralSettings.setOnClickListener(this);

        flGeneralRetroativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (BuildConfig.FLAVOR.equals("free")) {
                    VersionProDialog dialog = new VersionProDialog();
                    dialog.show(getSupportFragmentManager(), "VerionProDialog");
                } else {*/
                    appPresenter.frameLayoutUnpressed(flGeneralSalario, flGeneralFerias, flGeneralHoraExtra,
                            flGeneralDecimo, flGeneralRetroativo, flGeneralSettings);
                    appPresenter.onCategoriaClick(v);
                /*}*/
            }
        });

        flGeneralSalario.setBackgroundColor(ContextCompat.getColor(this, R.color.grey800));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.right_in, R.anim.right_out)
                .replace(R.id.fl_container, DadosSalarioLiqFragment.newInstance())
                .commit();

        flGeneralSalario.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralSalario.getRootView().getId() > -1) ? R.anim.up_from_bottom : R.anim.down_from_top));
        flGeneralFerias.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralFerias.getRootView().getId() > -1) ? R.anim.up_from_bottom : R.anim.down_from_top));
        flGeneralHoraExtra.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralHoraExtra.getRootView().getId() > -1) ? R.anim.up_from_bottom : R.anim.down_from_top));
        flGeneralDecimo.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralDecimo.getRootView().getId() > -1) ? R.anim.down_from_top : R.anim.up_from_bottom));
        flGeneralRetroativo.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralRetroativo.getRootView().getId() > -1) ? R.anim.down_from_top : R.anim.up_from_bottom));
        flGeneralSettings.startAnimation(AnimationUtils.loadAnimation(this, (flGeneralSettings.getRootView().getId() > -1) ? R.anim.down_from_top : R.anim.up_from_bottom));

    }

    @Override
    public void showSlideUpCurrent(final FloatingActionButton fab) {
        appPresenter.showSlideUpCurrent(slideUp, fab);
    }



    @Override
    public void onClick(View v) {
        appPresenter.frameLayoutUnpressed(flGeneralSalario, flGeneralFerias, flGeneralHoraExtra,
                flGeneralDecimo, flGeneralRetroativo, flGeneralSettings);
        appPresenter.onCategoriaClick(v);
    }

    @Override
    public void onBackPressed() {
        if (slideUp.isVisible()) {
            slideUp.hide();
        } else {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appPresenter.removeDadosSalvos();
    }

}
