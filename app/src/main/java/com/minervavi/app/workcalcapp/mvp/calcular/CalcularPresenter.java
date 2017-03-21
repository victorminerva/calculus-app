package com.minervavi.app.workcalcapp.mvp.calcular;

import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.util.AppConstants;

import java.text.NumberFormat;

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

}
