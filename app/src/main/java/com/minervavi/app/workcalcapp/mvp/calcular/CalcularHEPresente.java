package com.minervavi.app.workcalcapp.mvp.calcular;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import com.github.pierry.simpletoast.SimpleToast;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.util.AppConstants;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.text.NumberFormat;

/**
 * Created by victo on 25/03/2017.
 */

public class CalcularHEPresente implements ICalcularHoraExtra {

    private NumberFormat currencyFormat;
    private NumberFormat percentFormat ;
    private CalcularPresenter calcularPresenter;

    private SharedPreferences preferences;

    public CalcularHEPresente() {
        this.calcularPresenter  = new CalcularPresenter();
        this.currencyFormat     = NumberFormat.getCurrencyInstance();
        this.percentFormat      = NumberFormat.getPercentInstance();
        this.percentFormat.setMinimumFractionDigits(1);
    }

    @Override
    public Boolean validaDadosObrigatorios(View view, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra) {
        Boolean isValid = Boolean.TRUE;
        preferences = view.getContext().getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        if ("".equals(preferences.getString(view.getContext().getString(R.string.key_salario_bruto), ""))) {
            SimpleToast.muted(view.getContext(), AppConstants.MSG_INFORME_SALARIO_BRUTO);
            isValid = Boolean.FALSE;
        }
        if ("".equals(etJornadaMensal.getText().toString())){
            etJornadaMensal.setError("Informe a Jornada Mensal.");
            isValid = Boolean.FALSE;
        }
        if ("".equals(etAdicionalHE.getText().toString())){
            etAdicionalHE.setError("Informe o percentual do Adicional Hora extra (ex.: 50%).");
            isValid = Boolean.FALSE;
        }
        if ("".equals(etNumHoraExtra.getText().toString())){
            etNumHoraExtra.setError("Informe o Número de Horas extras.");
            isValid = Boolean.FALSE;
        } else if(etNumHoraExtra.getUnMaskedText().length() <= 3) {
            etNumHoraExtra.setError("Formato inválido (ex.: 10:30).");
            isValid = Boolean.FALSE;
        }

        return isValid;
    }

    @Override
    public DadosInput populaDadosHoraExtra(View view, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra) {
        preferences = view.getContext().getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        Double  salarioBruto    = Double.parseDouble(preferences.getString(view.getContext().getString(R.string.key_salario_bruto), "").replaceAll("[R$,.]", ""));
        Double  jornadaMensal   = Double.parseDouble(etJornadaMensal.getText().toString());
        Double  adicionalHE     = Double.parseDouble(etAdicionalHE.getText().toString());
        Double  numHoraExtras   = convertTimeInDecimal(etNumHoraExtra.getText().toString());

        return new DadosInput(salarioBruto, jornadaMensal, adicionalHE, numHoraExtras);
    }

    private Double convertTimeInDecimal(String numHoraExtra){
        String[] values = numHoraExtra.split(":");
        return Double.parseDouble(values[0]) + (Double.parseDouble(values[1]) / 60);
    }

    @Override
    public Double calcularValorHoraExtra(DadosInput dadosInput) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        Double jornadaMensal = dadosInput.getDadosHoraExtra().getJornadaMensal();
        Double adicionalHoraExtra = dadosInput.getDadosHoraExtra().getAdicionalHoraExtra() / 100;
        Double valorHora = salarioBruto / jornadaMensal;
        return valorHora + (valorHora * adicionalHoraExtra);
    }

    @Override
    public Double calcularTotalHoraExtra(DadosInput dadosInput, Double valorUnitarioHE) {
        Double numHoraExtra = dadosInput.getDadosHoraExtra().getNumHoraExtra();
        return valorUnitarioHE * numHoraExtra;
    }

    @Override
    public DadosResult populaDadosResult(DadosInput dadosInput, Double valorUnitarioHE, Double valorTotalHE) {
        DadosResult result;
        result = new DadosResult();
        result.setValorUnitHoraExtra(currencyFormat.format(valorUnitarioHE));
        result.setValorLiquido(currencyFormat.format(valorTotalHE));
        return result;
    }
}
