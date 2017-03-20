package com.minervavi.app.workcalcapp.databinding.domain;

/**
 * Created by victo on 19/03/2017.
 */

public class Tributos {

    private String valorInss;
    private String percentualInss;
    private String valorIrrf;
    private String percentualIrrf;

    public Tributos() {}

    public Tributos(String valorInss, String percentualInss, String valorIrrf, String percentualIrrf) {
        this.valorInss      = valorInss;
        this.percentualInss = percentualInss;
        this.valorIrrf      = valorIrrf;
        this.percentualIrrf = percentualIrrf;
    }

    public String getValorInss() {
        return valorInss;
    }

    public void setValorInss(String valorInss) {
        this.valorInss = valorInss;
    }

    public String getPercentualInss() {
        return percentualInss;
    }

    public void setPercentualInss(String percentualInss) {
        this.percentualInss = percentualInss;
    }

    public String getValorIrrf() {
        return valorIrrf;
    }

    public void setValorIrrf(String valorIrrf) {
        this.valorIrrf = valorIrrf;
    }

    public String getPercentualIrrf() {
        return percentualIrrf;
    }

    public void setPercentualIrrf(String percentualIrrf) {
        this.percentualIrrf = percentualIrrf;
    }
}
