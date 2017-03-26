package com.minervavi.app.workcalcapp.mvp.calcular;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;

/**
 * Created by victo on 26/03/2017.
 */

public interface ICalcularDecimo extends ICalcular.ICalcularPresenter {

    Boolean validaDadosObrigatorios(View view, EditText etNumMeses, RadioGroup radioParcela);

    DadosInput populaDadosDecimo(View view, EditText etNumMeses, RadioGroup radioParcela);

    Double calcularValorParcelaBruto(DadosInput dadosInput);

    Double calcularDecimoLiq(DadosInput dadosInput, Double inss, Double irrf);

    DadosResult populaDadosResult(DadosInput dadosInput, Double inss, Double percInss, Double irrf, Double percIrrf, Double salarioDecLiq);
}
