package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */
public class DadosInput implements Parcelable {

    private DadosSalarioLiq dadosSalarioLiq;
    private DadosFerias     dadosFerias;
    private DadosHoraExtra  dadosHoraExtra;
    private DadosDecimo     dadosDecimo;
    private DadosRetroativo dadosRetroativo;

    public DadosInput(Double salarioBruto, Integer numDependentes, Double descontos) {
        this.dadosSalarioLiq = new DadosSalarioLiq(salarioBruto, numDependentes, descontos);
    }

    public DadosInput(Double salarioBruto, Integer numDependentes, Double valorMedioHE, Integer qtdDias, Boolean abonoPec, Boolean adiantarDecimo) {
        this.dadosSalarioLiq    = new DadosSalarioLiq(salarioBruto, numDependentes, 0D);
        this.dadosFerias        = new DadosFerias(valorMedioHE, qtdDias, abonoPec, adiantarDecimo);
    }

    public DadosInput(Double salarioBruto ,Double jornadaMensal, Double adicionalHoraExtra, Double numHoraExtra) {
        this.dadosSalarioLiq    = new DadosSalarioLiq(salarioBruto, 0, 0D);
        this.dadosHoraExtra     = new DadosHoraExtra(jornadaMensal, adicionalHoraExtra, numHoraExtra);
    }

    public DadosSalarioLiq getDadosSalarioLiq() {
        return dadosSalarioLiq;
    }

    public void setDadosSalarioLiq(DadosSalarioLiq dadosSalarioLiq) {
        this.dadosSalarioLiq = dadosSalarioLiq;
    }

    public DadosFerias getDadosFerias() {
        return dadosFerias;
    }

    public void setDadosFerias(DadosFerias dadosFerias) {
        this.dadosFerias = dadosFerias;
    }

    public DadosHoraExtra getDadosHoraExtra() {
        return dadosHoraExtra;
    }

    public void setDadosHoraExtra(DadosHoraExtra dadosHoraExtra) {
        this.dadosHoraExtra = dadosHoraExtra;
    }

    public DadosDecimo getDadosDecimo() {
        return dadosDecimo;
    }

    public void setDadosDecimo(DadosDecimo dadosDecimo) {
        this.dadosDecimo = dadosDecimo;
    }

    public DadosRetroativo getDadosRetroativo() {
        return dadosRetroativo;
    }

    public void setDadosRetroativo(DadosRetroativo dadosRetroativo) {
        this.dadosRetroativo = dadosRetroativo;
    }

    @Override
    public String toString() {
        return "DadosInput{" +
                "dadosSalarioLiq=" + dadosSalarioLiq +
                ", dadosFerias=" + dadosFerias +
                ", dadosHoraExtra=" + dadosHoraExtra +
                ", dadosDecimo=" + dadosDecimo +
                ", dadosRetroativo=" + dadosRetroativo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dadosSalarioLiq, flags);
        dest.writeParcelable(this.dadosFerias, flags);
        dest.writeParcelable(this.dadosHoraExtra, flags);
        dest.writeParcelable(this.dadosDecimo, flags);
        dest.writeParcelable(this.dadosRetroativo, flags);
    }

    public DadosInput() {
    }

    protected DadosInput(Parcel in) {
        this.dadosSalarioLiq = in.readParcelable(DadosSalarioLiq.class.getClassLoader());
        this.dadosFerias = in.readParcelable(DadosFerias.class.getClassLoader());
        this.dadosHoraExtra = in.readParcelable(DadosHoraExtra.class.getClassLoader());
        this.dadosDecimo = in.readParcelable(DadosDecimo.class.getClassLoader());
        this.dadosRetroativo = in.readParcelable(DadosRetroativo.class.getClassLoader());
    }

    public static final Parcelable.Creator<DadosInput> CREATOR = new Parcelable.Creator<DadosInput>() {
        @Override
        public DadosInput createFromParcel(Parcel source) {
            return new DadosInput(source);
        }

        @Override
        public DadosInput[] newArray(int size) {
            return new DadosInput[size];
        }
    };
}
