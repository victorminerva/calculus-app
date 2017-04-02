package com.minervavi.app.workcalcapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mancj.slideup.SlideUp;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.application.CalculusApplication;
import com.minervavi.app.workcalcapp.fragment.DadosSalarioLiqFragment;
import com.minervavi.app.workcalcapp.fragment.VersionProDialog;
import com.minervavi.app.workcalcapp.mvp.app.AppPresenter;
import com.minervavi.app.workcalcapp.mvp.app.IApp;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.util.AppConstants;
import com.minervavi.app.workcalcapp.util.IabHelper;
import com.minervavi.app.workcalcapp.util.IabResult;
import com.minervavi.app.workcalcapp.util.Inventory;
import com.minervavi.app.workcalcapp.util.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    /**
     * Booleans
     */
    private Boolean mIsLite;
    private Boolean mIsPro;
    /**
     * Variaveis Faturamento
     */
    private String      base64EncodedPublicKey;
    private IabHelper   mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPresenter = new AppPresenter();
        appPresenter.setView(this);
        appPresenter.setmIsPro(Boolean.FALSE);
        appPresenter.setmIsLite(Boolean.FALSE);
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
        base64EncodedPublicKey = AppConstants.BASE64_KEY;
        mHelper = ((CalculusApplication) getApplication()).getmHelper();

        mIsPro  = Boolean.FALSE;
        mIsLite = Boolean.FALSE;

        if (mHelper == null){
            mHelper = new IabHelper(MainActivity.this, base64EncodedPublicKey);
            mHelper.enableDebugLogging(false);

            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                @Override
                public void onIabSetupFinished(IabResult result) {
                    Log.i(AppConstants.APP_SALARIO, "onIabSetupFinished: ");

                    if (result.isFailure()) {
                        Log.i(AppConstants.APP_SALARIO, "onIabSetupFinished: FAIL :" + result);
                    } else {
                        Log.i(AppConstants.APP_SALARIO, "onIabSetupFinished: SUCCESS");
                        List<String> productsIds = new ArrayList<String>();
                        for (int i = 0; i < AppConstants.SUBSCRIPTIONS_IDS.length ; i++){
                            productsIds.add(AppConstants.SUBSCRIPTIONS_IDS[i]);
                        }

                        try {
                            mHelper.queryInventoryAsync(true, productsIds, mQueryInventoryFinishedListener);
                        } catch (IabHelper.IabAsyncInProgressException e) {
                            Log.e(AppConstants.APP_SALARIO, "onIabSetupFinished: " + e.getMessage(), e);
                        }
                    }
                }
            });

            ((CalculusApplication) getApplication()).setmHelper(mHelper);
        }

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
                if (!mIsPro) {
                    VersionProDialog dialog = new VersionProDialog();
                    dialog.show(getSupportFragmentManager(), "VerionProDialog");
                } else {
                    appPresenter.frameLayoutUnpressed(flGeneralSalario, flGeneralFerias, flGeneralHoraExtra,
                            flGeneralDecimo, flGeneralRetroativo, flGeneralSettings);
                    appPresenter.onCategoriaClick(v);
                }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper != null && mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

        if (mHelper != null){
            mHelper.disposeWhenFinished();
        }
        mHelper = null;
        ((CalculusApplication) getApplication()).setmHelper(null);
    }

    public static int randInt(int min, int max){
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * LISTENERS
     */
    private IabHelper.QueryInventoryFinishedListener mQueryInventoryFinishedListener
            = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
            Log.i(AppConstants.APP_SALARIO, "onQueryInventoryFinished: ");

            if (result.isFailure()) {
                Log.i(AppConstants.APP_SALARIO, "onQueryInventoryFinished: FAIL : " + result);
            } else if (inv != null){
                Purchase calculusPro = inv.getPurchase(AppConstants.SUBSCRIPTIONS_IDS[0]);
                Purchase calculusNoADS = inv.getPurchase(AppConstants.SUBSCRIPTIONS_IDS[1]);

                if(calculusPro != null) {
                    mIsPro = Boolean.TRUE;
                    appPresenter.setmIsPro(Boolean.TRUE);
                }
                if (calculusNoADS != null) {
                    mIsLite = Boolean.TRUE;
                    appPresenter.setmIsLite(Boolean.TRUE);
                }
                for (int i = 0; i < AppConstants.SUBSCRIPTIONS_IDS.length; i++) {
                    if (inv.hasDetails(AppConstants.SUBSCRIPTIONS_IDS[i])) {
                        Log.i("Script", inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getSku().toUpperCase());
                        Log.i("Script", "Sku: " + inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getSku().toUpperCase());
                        Log.i("Script", "Title: " + inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getTitle());
                        Log.i("Script", "Type: " + inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getType());
                        Log.i("Script", "Price: " + inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getPrice());
                        Log.i("Script", "Description: " + inv.getSkuDetails(AppConstants.SUBSCRIPTIONS_IDS[i]).getDescription());
                        Log.i("Script", "Status purchase: " + (inv.hasPurchase(AppConstants.SUBSCRIPTIONS_IDS[i]) ? "COMPRADO" : "NÃO COMPRADO"));
                        Log.i("Script", "----------------------------------------------------");
                    }
                }
            }
        }
    };

}
