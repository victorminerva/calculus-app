package com.minervavi.app.workcalcapp.mvp.calcular;

import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.util.AppConstants;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by victo on 21/03/2017.
 */

public class CalcularSalarioLiqPresenter implements ICalcularSalarioLiq {

    NumberFormat currencyFormat;
    NumberFormat percentFormat ;
    CalcularPresenter calcularPresenter;

    public CalcularSalarioLiqPresenter() {
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
    public Double calcularSalarioLiq(DadosInput dadosInput, Double inss, Double irrf, Double descontoTotal) {
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;
        return salarioBruto - inss - irrf - descontoTotal;
    }

    @Override
    public Double calcularDescontos(List<String> list) {
        Double descontoTotal = 0.0;
        if(list != null ){
            for( String desconto : list ){
                descontoTotal += Double.parseDouble(desconto);
            }
        }
        return descontoTotal;
    }

    @Override
    public Double calculaDescontoPorDependente(Double inss, DadosInput dadosInput) {
        Double descontoPorDependente = dadosInput.getDadosSalarioLiq().getNumDependentes() * AppConstants.DESCONTO_POR_DEPENDENTE;
        inss -= descontoPorDependente;
        return inss;
    }

    @Override
    public DadosInput populaDadosSalarioLiq(CurrencyEditText etSalario, EditText etNumDep, Double descontoTotal) {
        Long    salario         = etSalario.getRawValue();
        Integer numDependentes  = Integer.parseInt(etNumDep.getText().toString());
        return new DadosInput(salario.doubleValue(), numDependentes, descontoTotal);
    }

    @Override
    public DadosResult populaDadosResult(DadosInput dadosInput, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioLiq, Double descontoTotal) {
        DadosResult result;
        result = new DadosResult(currencyFormat.format(inss), percentFormat.format(percInss), currencyFormat.format(irrf), percentFormat.format(percIrrf));
        result.setValorBruto(currencyFormat.format(dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100));
        result.setValorLiquido(currencyFormat.format(salarioLiq));
        result.setOutrosDescontos(currencyFormat.format(descontoTotal / 100));

        return result;
    }

    @Override
    public Boolean validaDadosObrigatorios(CurrencyEditText etSalario, EditText etNumDep) {
        Boolean isValid = Boolean.TRUE;
        if(etSalario.getText() == null || "".equals(etSalario.getText().toString().trim())){
            etSalario.setError("Preencha este campo!");
            isValid = Boolean.FALSE;
        }

        if(etSalario.getRawValue() < AppConstants.SALARIO_MINIMO){
            etSalario.setError("Salário inferior ao salário Mínimo ("+ currencyFormat.format(AppConstants.SALARIO_MINIMO) +")");
            isValid = Boolean.FALSE;
        }

        if(etNumDep.getText() == null || "".equals(etNumDep.getText().toString().trim())){
            etNumDep.setText("0");
        }
        return isValid;
    }
}
