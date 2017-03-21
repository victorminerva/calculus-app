package com.minervavi.app.workcalcapp.databinding.domain;

/**
 * Created by victo on 19/03/2017.
 */

public class DadosResult {

    private String      valorBruto;
    private String      valorLiquido;
    private String      valorTercoFerias;
    private String      outrosDescontos;
    private Tributos    tributos;

    public DadosResult() {}

    public DadosResult(String valorInss, String percentualInss, String valorIrrf, String percentualIrrf) {
        this.tributos = new Tributos(valorInss, percentualInss, valorIrrf, percentualIrrf);
    }

    public String getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(String valorBruto) {
        this.valorBruto = valorBruto;
    }

    public String getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(String valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getValorTercoFerias() {
        return valorTercoFerias;
    }

    public void setValorTercoFerias(String valorTercoFerias) {
        this.valorTercoFerias = valorTercoFerias;
    }

    public String getOutrosDescontos() {
        return outrosDescontos;
    }

    public void setOutrosDescontos(String outrosDescontos) {
        this.outrosDescontos = outrosDescontos;
    }

    public Tributos getTributos() {
        return tributos;
    }

    public void setTributos(Tributos tributos) {
        this.tributos = tributos;
    }
}