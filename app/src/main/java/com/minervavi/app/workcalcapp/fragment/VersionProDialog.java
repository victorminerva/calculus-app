package com.minervavi.app.workcalcapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.application.CalculusApplication;
import com.minervavi.app.workcalcapp.util.AppConstants;
import com.minervavi.app.workcalcapp.util.IabHelper;
import com.minervavi.app.workcalcapp.util.IabResult;
import com.minervavi.app.workcalcapp.util.Purchase;

import static com.minervavi.app.workcalcapp.activity.MainActivity.randInt;

/**
 * Created by victo on 28/03/2017.
 */

public class VersionProDialog extends DialogFragment {

    private TextView        tvTexto;
    private Button          btnBuy;

    private IabHelper       mHelper;

    private Boolean         mIsPro;

    public VersionProDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(AppConstants.APP_SALARIO, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(AppConstants.APP_SALARIO, "onCreateView: ");
        View view = inflater.inflate(R.layout.alert_version_pro_dialog, container);
        init(view);

        mHelper = ((CalculusApplication) getActivity().getApplication()).getmHelper();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Log.i(AppConstants.APP_SALARIO, "buy(): ");

                if (mHelper == null) {
                    Log.d(AppConstants.APP_SALARIO, "onClick: HELPER IS NULL");
                    return;
                }

                try {
                    if (mHelper.subscriptionsSupported()) {
                        mHelper.launchSubscriptionPurchaseFlow(getActivity(), AppConstants.SUBSCRIPTIONS_IDS[0], AppConstants.RC_REQUEST, mIabPurchaseFinishedListener, AppConstants.TOKEN + randInt(1000, 9999));
                    }
                } catch (IabHelper.IabAsyncInProgressException e) {
                    Log.e(AppConstants.APP_SALARIO, "buy: " + e.getMessage(), e);
                }
            }
        });
        return view;
    }

    private void init(View view) {
        this.btnBuy         = (Button) view.findViewById(R.id.btn_buy);
        this.tvTexto        = (TextView) view.findViewById(R.id.tv_texto);
    }

    private IabHelper.OnIabPurchaseFinishedListener mIabPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            Log.i(AppConstants.APP_SALARIO, "onIabPurchaseFinished: ");

            if (result.isFailure()) {
                Log.i(AppConstants.APP_SALARIO, "onIabPurchaseFinished: FAIL : " + result);
                return;
            } else if (info.getSku().equalsIgnoreCase(AppConstants.SUBSCRIPTIONS_IDS[0])){
                mIsPro = Boolean.TRUE;
                Log.i(AppConstants.APP_SALARIO, info.getSku().toUpperCase());
                Log.i(AppConstants.APP_SALARIO, "ORDER ID: " + info.getOrderId());
                Log.i(AppConstants.APP_SALARIO, "DeveloperPayload: " + info.getDeveloperPayload());
            }
        }
    };
}
