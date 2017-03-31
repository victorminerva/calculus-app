package com.minervavi.app.workcalcapp.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.adapter.ItemSettingsAdapter;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    ArrayList<String> dataModels;
    protected ListView lvSettings;
    private static ItemSettingsAdapter adapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
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
}
