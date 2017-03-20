package com.minervavi.app.workcalcapp.mvp.calcular;

import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.util.AppConstants;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by victo on 19/03/2017.
 */
public class CalcularPresenter implements ICalcular.ICalcularPresenter {

    NumberFormat currencyFormat;
    NumberFormat percentFormat ;

    public CalcularPresenter() {
        currencyFormat = NumberFormat.getCurrencyInstance();
        percentFormat  = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(1);
    }

    @Override
    public Double calcularINSS(DadosInput dadosInput) {
        Double inss = 0.0;
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;

        if (salarioBruto <= AppConstants.INSS_8_PC_VALOR) {
            inss = salarioBruto * AppConstants.INSS_8_PC;
        } else if (salarioBruto > AppConstants.INSS_8_PC_VALOR && salarioBruto < AppConstants.INSS_9_PC_VALOR) {
            inss = salarioBruto * AppConstants.INSS_9_PC;
        } else {
            inss = salarioBruto * AppConstants.INSS_11_PC;
        }
        return inss;
    }

    @Override
    public Double percentualINSS(DadosInput dadosInput) {
        Double inss = 0.0;
        Double salarioBruto = dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100;

        if (salarioBruto <= AppConstants.INSS_8_PC_VALOR) {
            inss = AppConstants.INSS_8_PC;
        } else if (salarioBruto > AppConstants.INSS_8_PC_VALOR && salarioBruto < AppConstants.INSS_9_PC_VALOR) {
            inss = AppConstants.INSS_9_PC;
        } else {
            inss = AppConstants.INSS_11_PC;
        }
        return inss;
    }

    @Override
    public Double calcularIRRF(DadosInput dadosInput, Double inss) {
        Double irrf = 0.0;

        Integer numDependentes = dadosInput.getDadosSalarioLiq().getNumDependentes();
        if ( numDependentes > 0){
            inss += numDependentes * AppConstants.DESCONTO_POR_DEPENDENTE;
        }

        Double salarioBase = (dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100 ) - inss;

        if (AppConstants.IRRF_7_5_PC_VALOR <= salarioBase && AppConstants.IRRF_15_PC_VALOR > salarioBase) {
            irrf = (salarioBase * AppConstants.IRRF_7_5_PC) - AppConstants.IRRF_7_5_PC_DEDUCAO;
        } else if (AppConstants.IRRF_15_PC_VALOR <= salarioBase && AppConstants.IRRF_22_5_PC_VALOR > salarioBase) {
            irrf = (salarioBase * AppConstants.IRRF_15_PC) - AppConstants.IRRF_15_PC_DEDUCAO;
        } else if (AppConstants.IRRF_22_5_PC_VALOR <= salarioBase && AppConstants.IRRF_27_5_PC_VALOR > salarioBase) {
            irrf = (salarioBase * AppConstants.IRRF_22_5_PC) - AppConstants.IRRF_22_5_PC_DEDUCAO;
        } else if (AppConstants.IRRF_27_5_PC_VALOR <= salarioBase) {
            irrf = (salarioBase * AppConstants.IRRF_27_5_PC) - AppConstants.IRRF_27_5_PC_DEDUCAO;
        }
        return irrf;
    }

    @Override
    public Double percentualIRRF(DadosInput dadosInput, Double inss) {
        Double irrf = 0.0;
        Double salarioBase = (dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100 ) - inss;

        if (AppConstants.IRRF_7_5_PC_VALOR <= salarioBase && AppConstants.IRRF_15_PC_VALOR > salarioBase) {
            irrf = AppConstants.IRRF_7_5_PC;
        } else if (AppConstants.IRRF_15_PC_VALOR <= salarioBase && AppConstants.IRRF_22_5_PC_VALOR > salarioBase) {
            irrf = AppConstants.IRRF_15_PC;
        } else if (AppConstants.IRRF_22_5_PC_VALOR <= salarioBase && AppConstants.IRRF_27_5_PC_VALOR > salarioBase) {
            irrf = AppConstants.IRRF_22_5_PC;
        } else if (AppConstants.IRRF_27_5_PC_VALOR <= salarioBase) {
            irrf = AppConstants.IRRF_27_5_PC;
        }
        return irrf;
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


}
