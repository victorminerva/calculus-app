package com.minervavi.app.workcalcapp.mvp.calcular;

import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;

import java.util.List;

/**
 * Created by victo on 21/03/2017.
 */

public interface ICalcularSalarioLiq extends ICalcular.ICalcularPresenter {

    /**
     * Responsável por verificar o Percentual do IRRF através da diferença do Salário Bruto e INSS.
     *
     * @param dadosInput
     * @param inss
     * @return
     */
    Double percentualIRRF(DadosInput dadosInput, Double inss);

    /**
     * Responsável por calcular o Salário Liquído através da diferença do Salário Bruto, INSS, IRRF e Descontos.
     * <p>
     * A composição do Salário Liquido é feito através dos passos:
     * <br/>
     * - Desconto do INSS que é desconto de uma porcentagem de acordo com o valor do seu salário, tabela 1.
     * <br/>
     * - Caso você tenha dependentes se desconta do INSS o valor de R$ 189,59 por dependente para obter o valor base para calculo do IR.
     * <br/>
     * - Desconto do imposto de renda que é subtraido de acordo com sua faixa salarial uma porcentagem e somado um valor que se deduzir de acordo com a tabela 2.
     * </p>
     *
     * @param dadosInput
     * @param inss
     * @param irrf
     * @param descontoTotal
     * @return
     */
    Double calcularSalarioLiq(DadosInput dadosInput, Double inss, Double irrf, Double descontoTotal);

    /**
     * Responsável por somar os descontos adicionados.
     *
     * @param list
     * @return
     */
    Double calcularDescontos(List<String> list);

    Double calculaDescontoPorDependente(Double inss, DadosInput dadosInput);

    DadosInput populaDadosSalarioLiq(CurrencyEditText etSalario, EditText etNumDep, Double descontoTotal);

    DadosResult populaDadosResult(DadosInput dadosInput, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioLiq, Double descontoTotal);

    Boolean validaDadosObrigatorios(CurrencyEditText etSalario, EditText etNumDep);
}
