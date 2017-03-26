package com.minervavi.app.workcalcapp.mvp.calcular;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.pierry.simpletoast.SimpleToast;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.databinding.domain.ParcelaEnum;
import com.minervavi.app.workcalcapp.util.AppConstants;

import java.text.NumberFormat;

/**
 * Created by victo on 26/03/2017.
 */

public class CalcularDecimoPresenter implements ICalcularDecimo {

    private NumberFormat currencyFormat;
    private NumberFormat percentFormat ;
    private CalcularPresenter calcularPresenter;

    private SharedPreferences preferences;

    public CalcularDecimoPresenter() {
        calcularPresenter   = new CalcularPresenter();
        currencyFormat      = NumberFormat.getCurrencyInstance();
        percentFormat       = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(1);
    }

    @Override
    public Boolean validaDadosObrigatorios(View view, EditText etNumMeses, RadioGroup radioParcela) {
        Boolean isValid = Boolean.TRUE;
        preferences = view.getContext().getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        if ("".equals(preferences.getString(view.getContext().getString(R.string.key_salario_bruto), ""))) {
            SimpleToast.muted(view.getContext(), AppConstants.MSG_INFORME_SALARIO_BRUTO);
            isValid = Boolean.FALSE;
        }
        if ("".equals(preferences.getString(view.getContext().getString(R.string.key_num_dependentes), ""))){
            SimpleToast.muted(view.getContext(), AppConstants.MSG_INFORME_NUM_DEPENDENTES);
            isValid = Boolean.FALSE;
        }
        if (isValid) {
            if ("".equals(etNumMeses.getText().toString())){
                etNumMeses.setError("Número de meses trabalhados deve estar entre 1 e 12.");
                isValid = Boolean.FALSE;
            }
            int parcelaID = radioParcela.getCheckedRadioButtonId();
            if (parcelaID == -1){
                SimpleToast.error(view.getContext(), "Informe a parcela.");
                isValid = Boolean.FALSE;
            }
        }
        return isValid;
    }

    @Override
    public DadosInput populaDadosDecimo(View view, EditText etNumMeses, RadioGroup radioParcela) {
        RadioButton rbParcela;
        ParcelaEnum parcela = null;
        preferences = view.getContext().getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        Integer numeroMeses     = Integer.parseInt(etNumMeses.getText().toString());
        int     parcelaID       = radioParcela.getCheckedRadioButtonId();

        if (parcelaID != -1) {
            rbParcela = (RadioButton) view.findViewById(parcelaID);
            switch (rbParcela.getText().toString().toUpperCase()){
                case "ÚNICA":
                    parcela = ParcelaEnum.UNICA;
                    break;
                case "PRIMEIRA":
                    parcela = ParcelaEnum.PRIMEIRA;
                    break;
                case "SEGUNDA":
                    parcela = ParcelaEnum.SEGUNDA;
                    break;
            }
        }

        Double  salarioBruto    = Double.parseDouble(preferences.getString(view.getContext().getString(R.string.key_salario_bruto), "").replaceAll("[R$,.]", ""));
        Integer numDeps         = Integer.parseInt(preferences.getString(view.getContext().getString(R.string.key_num_dependentes), "0"));

        return new DadosInput(salarioBruto, numDeps, numeroMeses, parcela);
    }

    @Override
    public Double calcularValorParcelaBruto(DadosInput dadosInput) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        Integer numMesesTrabalhados = dadosInput.getDadosDecimo().getNumMesesTrabalhados();
        return salarioBruto / 12 * numMesesTrabalhados;
    }

    @Override
    public Double calcularDecimoLiq(DadosInput dadosInput, Double inss, Double irrf) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        if (ParcelaEnum.PRIMEIRA.equals(dadosInput.getDadosDecimo().getParcela())){
            salarioBruto = salarioBruto / 2;
            inss = 0.0;
            irrf = 0.0;
        }
        if (ParcelaEnum.SEGUNDA.equals(dadosInput.getDadosDecimo().getParcela())){
            salarioBruto = salarioBruto / 2;
        }
        return salarioBruto - inss - irrf;
    }

    @Override
    public DadosResult populaDadosResult(DadosInput dadosInput, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioDecLiq) {
        DadosResult result;
        Double parcelaBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        if (ParcelaEnum.UNICA.equals(dadosInput.getDadosDecimo().getParcela())){
            result = new DadosResult(currencyFormat.format(inss), percentFormat.format(percInss), currencyFormat.format(irrf), percentFormat.format(percIrrf));
        } else if (ParcelaEnum.SEGUNDA.equals(dadosInput.getDadosDecimo().getParcela())) {
            parcelaBruto = parcelaBruto / 2;
            result = new DadosResult(currencyFormat.format(inss), percentFormat.format(percInss), currencyFormat.format(irrf), percentFormat.format(percIrrf));
        } else {
            parcelaBruto = parcelaBruto / 2;
            result = new DadosResult();
        }
        result.setValorBruto(currencyFormat.format(parcelaBruto));
        result.setValorLiquido(currencyFormat.format(salarioDecLiq));
        result.setParcela(dadosInput.getDadosDecimo().getParcela());

        return result;
    }

    @Override
    public Double calcularINSS(DadosInput dadosInput) {
        return calcularPresenter.calcularINSS(dadosInput);
    }

    @Override
    public Double percentualINSS(DadosInput dadosInput) {
        return calcularPresenter.percentualINSS(dadosInput);
    }

    @Override
    public Double calcularIRRF(DadosInput dadosInput, Double inss) {
        return calcularPresenter.calcularIRRF(dadosInput, inss);
    }

    @Override
    public Double percentualIRRF(DadosInput dadosInput, Double inss) {
        return calcularPresenter.percentualIRRF(dadosInput, inss);
    }
}
