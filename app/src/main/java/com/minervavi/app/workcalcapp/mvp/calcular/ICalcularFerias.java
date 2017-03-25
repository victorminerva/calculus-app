package com.minervavi.app.workcalcapp.mvp.calcular;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;

/**
 * Created by victo on 23/03/2017.
 */

public interface ICalcularFerias extends ICalcular.ICalcularPresenter {

    /**
     * Valor base = (Salário bruto + Média de hora extra) / 30 x Dias de férias usufruídas
     *
     * @param dadosInput
     * @return
     */
    Double calcularValorBase(DadosInput dadosInput);

    /**
     * Total de proventos = 700 + (700 / 3) = 700 + 233,33 = 933,33
     *
     * @param valorBase
     * @return
     */
    Double calcularTercoFerias(Double valorBase);

    /**
     * @param dadosInput
     * @return
     */
    Double calularSalarioFerias(DadosInput dadosInput, Double inss, Double irrf, Double abono, Double adianta);

    /**
     * Calculo:
     * <p>
     * Dividir o salário mensal do funcionário por 30 com o objetivo de encontrar o valor que ele recebe por dia de trabalho.
     * Após isso, multiplicamos o valor diário pela quantidade de dias que ele vai tirar de férias.
     * </p>
     *
     * @param dadosInput
     * @return
     */
    Double calcularValorBasePorQtdeDias(DadosInput dadosInput);

    DadosInput populaDadosFerias(View view, CurrencyEditText etValorMedioHE, EditText etDiasFerias,
                                 RadioGroup radioAbonar, RadioGroup radioadiantar);

    DadosResult populaDadosResult(DadosInput dadosInput, Double valorTerco, Double valorAbono, Double valorAdianta, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioLiq);

    Boolean validaDadosObrigatorios(View view, CurrencyEditText etValorMedioHE, EditText etDiasFerias,
                                    RadioGroup radioAbonar, RadioGroup radioadiantar);
}
