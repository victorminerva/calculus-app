package com.minervavi.app.workcalcapp.mvp.calcular;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.github.pierry.simpletoast.SimpleToast;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.util.AppConstants;

import java.text.NumberFormat;

/**
 * Created by victo on 23/03/2017.
 */

public class CalcularFeriasPresenter implements ICalcularFerias {

    private NumberFormat currencyFormat;
    private NumberFormat percentFormat ;
    private CalcularPresenter calcularPresenter;

    private SharedPreferences preferences;

    public CalcularFeriasPresenter() {
        calcularPresenter = new CalcularPresenter();
        currencyFormat = NumberFormat.getCurrencyInstance();
        percentFormat  = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(1);
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

    @Override
    public Double calcularValorBase(DadosInput dadosInput) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        Double mediaHorasExtrasAno = dadosInput.getDadosFerias().getMediaHorasExtrasAno() / 100;
        Integer qtdeDiasFerias = dadosInput.getDadosFerias().getQtdeDiasFerias();
        return (salarioBruto + mediaHorasExtrasAno) / 30 * qtdeDiasFerias;
    }

    @Override
    public Double calcularTercoFerias(Double valorBase) {
        return valorBase/3;
    }

    @Override
    public Double calularSalarioFerias(DadosInput dadosInput, Double inss, Double irrf, Double abono, Double adianta) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        Double valorAbono   = abono == null ? 0 : abono;
        Double valorAdianta = adianta == null ? 0 : adianta;
        return salarioBruto - inss - irrf + valorAbono + (valorAbono/3) + valorAdianta;
    }

    @Override
    public Double calcularValorBasePorQtdeDias(DadosInput dadosInput) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto();
        Double valorDia = salarioBruto / 30;
        return valorDia * dadosInput.getDadosFerias().getQtdeDiasFerias();
    }

    @Override
    public DadosInput populaDadosFerias(View view, CurrencyEditText etValorMedioHE, EditText etDiasFerias, RadioGroup radioAbonar, RadioGroup radioadiantar) {
        preferences = view.getContext().getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Activity.MODE_PRIVATE);
        RadioButton rbAbonar;
        RadioButton rbAdiantar;
        Long valorMedioHE       = etValorMedioHE.getRawValue();
        Integer qtdDias         = Integer.parseInt(etDiasFerias.getText().toString());
        Boolean abonoPec        = Boolean.FALSE;
        Boolean adiantarDec     = Boolean.FALSE;

        int abonoPecuniarioID   = radioAbonar.getCheckedRadioButtonId();
        int adiantarDecimaID    = radioadiantar.getCheckedRadioButtonId();

        if (abonoPecuniarioID != -1) {
            rbAbonar = (RadioButton) view.findViewById(abonoPecuniarioID);
            if ("Sim".equals(rbAbonar.getText().toString())){
                abonoPec = Boolean.TRUE;
            }
        }
        if (adiantarDecimaID != -1) {
            rbAdiantar = (RadioButton) view.findViewById(adiantarDecimaID);
            if ("Sim".equals(rbAdiantar.getText().toString())){
                adiantarDec = Boolean.TRUE;
            }
        }

        Double  salarioBruto = Double.parseDouble(preferences.getString(view.getContext().getString(R.string.key_salario_bruto), "").replaceAll("[R$,.]", ""));
        Integer numDeps = Integer.parseInt(preferences.getString(view.getContext().getString(R.string.key_num_dependentes), "0"));

        return new DadosInput(salarioBruto, numDeps, valorMedioHE.doubleValue(), qtdDias, abonoPec, adiantarDec);
    }

    @Override
    public DadosResult populaDadosResult(DadosInput dadosInput, Double valorTerco, Double valorAbono, Double valorAdianta, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioLiq) {
        DadosResult result;
        result = new DadosResult(currencyFormat.format(inss), percentFormat.format(percInss), currencyFormat.format(irrf), percentFormat.format(percIrrf));
        result.setValorBruto(currencyFormat.format(dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100));
        result.setValorLiquido(currencyFormat.format(salarioLiq));
        result.setValorTercoFerias(currencyFormat.format(valorTerco/100));
        result.setValorAbonoPecuniario(null);
        result.setValorTercoAbono(null);
        result.setValorAdiantamento(null);

        if(valorAbono != null) {
            result.setValorAbonoPecuniario(currencyFormat.format(valorAbono));
            result.setValorTercoAbono(currencyFormat.format(valorAbono/3));
        }
        if (valorAdianta != null) {
            result.setValorAdiantamento(currencyFormat.format(valorAdianta));
        }
        return result;
    }

    @Override
    public Boolean validaDadosObrigatorios(View view, CurrencyEditText etValorMedioHE, EditText etDiasFerias, RadioGroup radioAbonar, RadioGroup radioadiantar) {
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
            if ("".equals(etValorMedioHE.getRawValue())) {
                etValorMedioHE.setText("0");
                isValid = Boolean.FALSE;
            }
            if ("".equals(etDiasFerias.getText().toString().trim())) {
                etDiasFerias.setText("0");
                etDiasFerias.setError("Número de dias inválido.");
                isValid = Boolean.FALSE;
            } else {
                Integer qtdDias = Integer.parseInt(etDiasFerias.getText().toString());
                if (qtdDias > 30 || qtdDias < 10) {
                    SimpleToast.error(view.getContext(), AppConstants.MSG_QTDE_DIAS_MAX_MIN);
                    isValid = Boolean.FALSE;
                }
                int abonoPecuniarioID = radioAbonar.getCheckedRadioButtonId();
                if (abonoPecuniarioID != -1) {
                    RadioButton rbAbono = (RadioButton) view.findViewById(abonoPecuniarioID);
                    if ("Sim".equals(rbAbono.getText().toString()) && qtdDias > 20) {
                        SimpleToast.error(view.getContext(), AppConstants.MSG_CALC_ABONO_PECUNIARIO);
                        isValid = Boolean.FALSE;
                    }
                }
            }
        }
        return isValid;
    }
}
