package com.minervavi.app.workcalcapp.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.adapter.ItemSettingsAdapter;
import com.minervavi.app.workcalcapp.application.CalculusApplication;
import com.minervavi.app.workcalcapp.util.AppConstants;
import com.minervavi.app.workcalcapp.util.IabHelper;
import com.minervavi.app.workcalcapp.util.IabResult;
import com.minervavi.app.workcalcapp.util.Purchase;

import java.util.ArrayList;

import static com.minervavi.app.workcalcapp.activity.MainActivity.randInt;

public class SettingsFragment extends Fragment {

    ArrayList<String> dataModels;
    protected ListView lvSettings;
    private static ItemSettingsAdapter adapter;


    private IabHelper       mHelper;

    private Boolean         mIsPro;
    private Boolean         mIsLite;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(Boolean mIsPro, Boolean mIsLite) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putBoolean(AppConstants.CALCULUS_PRO, mIsPro);
        args.putBoolean(AppConstants.CALCULUS_LITE, mIsLite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataModels = new ArrayList<>();
        dataModels.add("Sobre o App");
        dataModels.add("Termos de uso");
        dataModels.add("Politica de Privacidade");
        dataModels.add("Remover Anúncios");
        dataModels.add("Adquirir Versão PRO");

        this.mIsPro     = getArguments().getBoolean(AppConstants.CALCULUS_PRO);
        this.mIsLite    = getArguments().getBoolean(AppConstants.CALCULUS_LITE);

        mHelper = ((CalculusApplication) getActivity().getApplication()).getmHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        lvSettings = (ListView) view.findViewById(R.id.lv_settings);

        adapter = new ItemSettingsAdapter(dataModels, getContext().getApplicationContext());

        lvSettings.setAdapter(adapter);

        lvSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showSobreDialog();
                        break;
                    case 1:
                        showTermosDeUsoDialog();
                        break;
                    case 2:
                        showPrivacyPolicyDialog();
                        break;
                    case 3:
                        purchaseSubscription(AppConstants.SUBSCRIPTIONS_IDS[1]);
                        break;
                    case 4:
                        purchaseSubscription(AppConstants.SUBSCRIPTIONS_IDS[0]);
                        break;
                }

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showSobreDialog() {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.sobre_dialog);

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.ll_dialog);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showPrivacyPolicyDialog() {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.privacy_policy_dialog);

        TextView tvPrivacyPolicy = (TextView) dialog.findViewById(R.id.tv_privacy_policy);
        tvPrivacyPolicy.setText(Html.fromHtml(getString(R.string.privacy_policy_html)));

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.ll_dialog);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showTermosDeUsoDialog() {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.termos_uso_dialog);

        TextView tvPrivacyPolicy = (TextView) dialog.findViewById(R.id.tv_termos_uso);
        tvPrivacyPolicy.setText(Html.fromHtml(getString(R.string.termos_de_uso)));

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.ll_termos_dialog);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void purchaseSubscription(String subscription){
        if (mHelper == null) {
            Log.d(AppConstants.APP_SALARIO, "onClick: HELPER IS NULL");
            return;
        }

        try {
            if (mHelper.subscriptionsSupported()) {
                mHelper.launchSubscriptionPurchaseFlow(getActivity(),subscription, AppConstants.RC_REQUEST, mIabPurchaseFinishedListener, AppConstants.TOKEN + randInt(1000, 9999));
            }
        } catch (IabHelper.IabAsyncInProgressException e) {
            Log.e(AppConstants.APP_SALARIO, "buy: " + e.getMessage(), e);
        }
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
            } else if (info.getSku().equalsIgnoreCase(AppConstants.SUBSCRIPTIONS_IDS[1])){
                mIsLite = Boolean.TRUE;
                Log.i(AppConstants.APP_SALARIO, info.getSku().toUpperCase());
                Log.i(AppConstants.APP_SALARIO, "ORDER ID: " + info.getOrderId());
                Log.i(AppConstants.APP_SALARIO, "DeveloperPayload: " + info.getDeveloperPayload());
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper != null && mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);

            mHelper.disposeWhenFinished();
        }
    }

}
