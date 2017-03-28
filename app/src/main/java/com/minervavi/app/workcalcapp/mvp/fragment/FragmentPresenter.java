package com.minervavi.app.workcalcapp.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.mvp.app.IApp;
import com.minervavi.app.workcalcapp.util.AppConstants;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 14/03/2017.
 */

public class FragmentPresenter implements IFragment.IFragmentPresenter {

    private IApp.IAppView               appView;

    /** Context */
    private Context                     context;
    /** Lists */
    private List<String>                listOfDescontos;
    /** Preferences*/
    private SharedPreferences           preferences;
    private SharedPreferences.Editor    editor;

    private Gson                        gson;

    public FragmentPresenter() {
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
    public List<String> onAddDescontoClick(final CurrencyEditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto) {
        if ("".equals(etDesconto.getText().toString())) {
            etDesconto.setError("Preencha este campo!");
        } else {
            listOfDescontos.add(String.valueOf(etDesconto.getRawValue()));

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowDesconto = layoutInflater.inflate(R.layout.row_desconto, null);

            final TextView tvDesconto = (TextView) rowDesconto.findViewById(R.id.tv_desconto);
            tvDesconto.setText(etDesconto.getText().toString());

            ImageButton btnRemove = (ImageButton) rowDesconto.findViewById(R.id.remove);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRemoveDescontoClick(rowDesconto, etDesconto, tvDesconto, btnAdd);
                }
            });

            llDesconto.addView(rowDesconto, 1);

            etDesconto.setText("");

            if (listOfDescontos.size() == 8) {
                btnAdd.setVisibility(View.GONE);
                etDesconto.setVisibility(View.GONE);
            }
        }

        return listOfDescontos;
    }

    @Override
    public List<String> onRemoveDescontoClick(View rowDesconto, CurrencyEditText etDesconto, TextView tvDesconto, ImageButton btnAdd) {
        ((LinearLayout) rowDesconto.getParent()).removeView(rowDesconto);

        String desc = tvDesconto.getText().toString().replaceAll("[R$,.]", "");

        if (listOfDescontos.contains(desc)) {
            listOfDescontos.remove(desc);
        }
        btnAdd.setVisibility(View.VISIBLE);
        etDesconto.setVisibility(View.VISIBLE);
        return listOfDescontos;
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

    @Override
    public void manterDadosSalarioLiq(SharedPreferences preferences, EditText etSalario, EditText etNumDep, List<String> listDescontos) {
        gson = new Gson();
        gson.toJson(listDescontos);
        editor = preferences.edit();

        editor.putString(getContext().getString(R.string.key_salario_bruto), etSalario.getText().toString());
        editor.putString(getContext().getString(R.string.key_num_dependentes), etNumDep.getText().toString());
        editor.putString(getContext().getString(R.string.key_descontos), gson.toJson(listDescontos));
        editor.commit();
    }

    @Override
    public List<String> retrieveListOfDesconts() {
        preferences     = getContext().getSharedPreferences(getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);
        editor          = preferences.edit();
        gson = new Gson();
        String descontos = preferences.getString(getContext().getString(R.string.key_descontos), null);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        return gson.fromJson(descontos, List.class);
    }

    @Override
    public void manterDadosFerias(SharedPreferences preferences, CurrencyEditText etvalormediohe, EditText etdiasferias, RadioGroup radioabonar, RadioGroup radioadiantar) {
        editor = preferences.edit();

        editor.putString(getContext().getString(R.string.key_valor_medio_hora_extra), etvalormediohe.getText().toString());
        editor.putString(getContext().getString(R.string.key_dias_de_ferias), etdiasferias.getText().toString());
        editor.putInt(getContext().getString(R.string.key_abono_pecuniario), radioabonar.getCheckedRadioButtonId());
        editor.putInt(getContext().getString(R.string.key_adiantar_decima), radioadiantar.getCheckedRadioButtonId());
        editor.commit();
    }

    @Override
    public void recuperaDadosFerias(View view, SharedPreferences preferences, CurrencyEditText etvalormediohe, EditText etdiasferias, RadioGroup radioabonar, RadioGroup radioadiantar) {
        etvalormediohe.setText(preferences.getString(getContext().getString(R.string.key_valor_medio_hora_extra), ""));
        etdiasferias.setText(preferences.getString(getContext().getString(R.string.key_dias_de_ferias), ""));
        int abonoPecuniarioID   = preferences.getInt(getContext().getString(R.string.key_abono_pecuniario), -1);
        int adiantarDecima      = preferences.getInt(getContext().getString(R.string.key_adiantar_decima), -1);

        if(abonoPecuniarioID != -1) {
            RadioButton rbAbono = (RadioButton) view.findViewById(abonoPecuniarioID);
            rbAbono.setChecked(true);
        }
        if(adiantarDecima != -1) {
            RadioButton rbAdiantar = (RadioButton) view.findViewById(adiantarDecima);
            rbAdiantar.setChecked(true);
        }
    }

    @Override
    public void manterDadosHoraExtra(SharedPreferences preferences, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra) {
        editor = preferences.edit();

        editor.putString(getContext().getString(R.string.key_jornada_mensal), etJornadaMensal.getText().toString());
        editor.putString(getContext().getString(R.string.key_adicional_hora_extra), etAdicionalHE.getText().toString());
        editor.putString(getContext().getString(R.string.key_num_hora_extra), etNumHoraExtra.getText().toString());
        editor.commit();
    }

    @Override
    public void recuperaDadosHoraExtra(SharedPreferences preferences, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra) {
        etJornadaMensal.setText(preferences.getString(getContext().getString(R.string.key_jornada_mensal), ""));
        etAdicionalHE.setText(preferences.getString(getContext().getString(R.string.key_adicional_hora_extra), ""));
        etNumHoraExtra.setText(preferences.getString(getContext().getString(R.string.key_num_hora_extra), ""));
    }

    @Override
    public void manterDadosDecimo(SharedPreferences preferences, EditText etNumMeses, RadioGroup radioParcela) {
        editor = preferences.edit();

        editor.putString(getContext().getString(R.string.key_num_meses_trabalhados_reaj), etNumMeses.getText().toString());
        editor.putInt(getContext().getString(R.string.key_parcela), radioParcela.getCheckedRadioButtonId());
        editor.commit();
    }


    @Override
    public void recuperaDadosDecimo(View view, SharedPreferences preferences, EditText etNumMeses, RadioGroup radioParcela) {
        etNumMeses.setText(preferences.getString(getContext().getString(R.string.key_num_meses_trabalhados_reaj), ""));
        int parcelaID = preferences.getInt(getContext().getString(R.string.key_parcela), -1);
        Log.d(AppConstants.APP_SALARIO, "recuperaDadosDecimo: " + parcelaID);
        if(parcelaID != -1) {
            RadioButton rbParcela = (RadioButton) view.findViewById(parcelaID);
            rbParcela.setChecked(true);
        }
    }

    @Override
    public void manterDadosRetroativo(SharedPreferences preferences, CurrencyEditText etSalarioAnterior, EditText etPercentual, EditText etQtdMeses) {
        editor = preferences.edit();
        editor.putString(getContext().getString(R.string.key_salario_base_reaj), etSalarioAnterior.getText().toString());
        editor.putString(getContext().getString(R.string.key_percentual_aumento), etPercentual.getText().toString());
        editor.putString(getContext().getString(R.string.key_num_meses_trabalhados_reaj), etQtdMeses.getText().toString());
        editor.commit();
    }

    @Override
    public void recuperaDadosRetroativo(SharedPreferences preferences, CurrencyEditText etSalarioAnterior, EditText etPercentual, EditText etQtdMeses) {
        etSalarioAnterior.setText(preferences.getString(getContext().getString(R.string.key_salario_base_reaj), ""));
        etPercentual.setText(preferences.getString(getContext().getString(R.string.key_percentual_aumento), ""));
        etQtdMeses.setText(preferences.getString(getContext().getString(R.string.key_num_meses_trabalhados_reaj), ""));
    }

}
