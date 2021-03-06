package com.minervavi.app.workcalcapp.util;

/**
 * Created by victo on 19/03/2017.
 */

public interface AppConstants {

    String APP_SALARIO   = "APP_SALARIO";

    String CALCULUS_LITE    = "calculus_no_ads";
    String CALCULUS_PRO     = "calculus_pro";
    String SUBSCRIPTIONS_IDS[]   = new String[]{CALCULUS_PRO, CALCULUS_LITE};
    String BASE64_KEY       = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4gpPKrk1R2TzoZ5GTd3Ee7MEA1CLF70OqMTKNdRK4iYihIfcuF+kpsAbKcxCCaKRaGL5A/mtq3RPzqF6iFdXOjhc5u7802pkRwyAuuwoFPnTRGAikRdxM8XWyqT7fPmIB6EI2BbyRXAlChB39h5aYYkATrTROkCuCuXnSAohT4LpjWKzA/304J1uoLJAoLhQ3q3J7lBp+ZVPblHKqzSBw2utsSqSTBgEjjj186DB2Lp1HvkZDn/TurMPYY5tMq0VpuLakhF8+rW9kkHNn3ZgCPsqI7cLeffheRoJIViy8Q16wT6dY4mWefC3CWIsxISbFL6SbijKj89ruIz/ggJ2QIDAQAB";
    String TOKEN            = "Token-";

    int    RC_REQUEST       = 10001;
    /**
     * Valores do INSS conforme tabela INSS 2017.
     *
     *      Descrição	              Percentual
     *
     *  Até 1.659,38	                8,00
     *  De 1.659,39 Até 2.765,66	    9,00
     *  De 2.765,67 Até 5.531,31	    11,00
     */
    Double INSS_8_PC_VALOR   = 1659.38;
    Double INSS_9_PC_VALOR   = 2765.67;
    Double INSS_11_PC_VALOR  = 5531.31;
    /**
     * Percentuais do INSS
     */
    Double INSS_8_PC        = 0.08;
    Double INSS_9_PC        = 0.09;
    Double INSS_11_PC       = 0.11;

    /**
     * Valores do IRRF conforme tabela IRRF 2015.
     *
     *          Descrição	        Percentual	Dedução
     *
     *  Até 1.903,98	                -	       -
     *  De 1.903,99 Até 2.826,65	   7,50	    142,80
     *  De 2.826,66 Até 3.751,05	   15,00	354,80
     *  De 3.751,06 Até 4.664,68	   22,50	636,13
     *  Acima De 4.664,68	           27,50	869,36
     */
    Double IRRF_7_5_PC_VALOR    = 1903.98;
    Double IRRF_15_PC_VALOR     = 2826.65;
    Double IRRF_22_5_PC_VALOR   = 3751.05;
    Double IRRF_27_5_PC_VALOR   = 4664.68;
    /**
     * Percentuais do IRRF
     */
    Double IRRF_7_5_PC      = 0.075;
    Double IRRF_15_PC       = 0.15;
    Double IRRF_22_5_PC     = 0.225;
    Double IRRF_27_5_PC     = 0.275;
    /**
     * Dedução do IRRF
     */
    Double IRRF_7_5_PC_DEDUCAO      = 142.80;
    Double IRRF_15_PC_DEDUCAO       = 354.80;
    Double IRRF_22_5_PC_DEDUCAO     = 636.13;
    Double IRRF_27_5_PC_DEDUCAO     = 869.36;

    Double DESCONTO_POR_DEPENDENTE  = 189.59;

    Double SALARIO_MINIMO           = 937.00;

    String MSG_CALC_ABONO_PECUNIARIO    = "Para cálculos de férias com abono pecuniário (venda de 1/3) o valor máximo de dias de férias (dias gozados) é de 20 dias!";
    String MSG_QTDE_DIAS_MAX_MIN        = "O número de dias deve estar entre o mínimo de 10 e o máximo de 30 dias.";
    String MSG_INFORME_SALARIO_BRUTO    = "Informe o Salário bruto, na aba Dados Salário Liquído";
    String MSG_INFORME_NUM_DEPENDENTES  = "Informe o Número de Dependentes, na aba Dados Salário Liquído";
}
