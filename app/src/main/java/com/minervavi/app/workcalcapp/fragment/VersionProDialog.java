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
import android.widget.Toast;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.util.AppConstants;

/**
 * Created by victo on 28/03/2017.
 */

public class VersionProDialog extends DialogFragment {

    private TextView        tvTexto;
    private Button          btnBuy;

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

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Adiquirindo..", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    private void init(View view) {
        this.btnBuy         = (Button) view.findViewById(R.id.btn_buy);
        this.tvTexto        = (TextView) view.findViewById(R.id.tv_texto);
    }

}
