package com.minervavi.app.workcalcapp.mvp.calcular;

import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;

/**
 * Created by victo on 13/03/2017.
 */

public interface ICalcular {

    interface ICalcularPresenter {
        /**
         * Responsável por calcular o INSS através do Salário Bruto.
         *
         * @param dadosInput
         * @return Valor do INSS.
         */
        Double calcularINSS(DadosInput dadosInput);

        /**
         * Responsável por verificar o Percentual do INSS através do Salário Bruto.
         *
         * @param dadosInput
         * @return Percentual do INSS.
         */
        Double percentualINSS(DadosInput dadosInput);

        /**
         * Responsável por calcular o IRRF através da diferença do Salário Bruto e INSS.
         *
         * @param dadosInput
         * @param inss
         * @return Valor do IRRF.
         */
        Double calcularIRRF(DadosInput dadosInput, Double inss);

        /**
         * Responsável por verificar o Percentual do IRRF através da diferença do Salário Bruto e INSS.
         *
         * @param dadosInput
         * @param inss
         * @return
         */
        Double percentualIRRF(DadosInput dadosInput, Double inss);

    }

    interface ICalcularView {
        void calcular();
    }

}
