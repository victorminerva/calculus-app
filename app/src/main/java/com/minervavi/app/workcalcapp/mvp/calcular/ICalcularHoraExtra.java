package com.minervavi.app.workcalcapp.mvp.calcular;

import android.view.View;

import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.vicmikhailau.maskededittext.MaskedEditText;

/**
 * Created by victo on 25/03/2017.
 */

public interface ICalcularHoraExtra extends ICalcular {

    Boolean validaDadosObrigatorios(View view, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra);

    DadosInput populaDadosHoraExtra(View view, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra);

    Double calcularValorHoraExtra(DadosInput dadosInput);

    Double calcularTotalHoraExtra(DadosInput dadosInput, Double valorUnitarioHE);

    DadosResult populaDadosResult(DadosInput dadosInput, Double valorUnitarioHE, Double valorTotalHE);
}
