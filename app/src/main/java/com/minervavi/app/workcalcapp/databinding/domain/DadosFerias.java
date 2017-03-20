package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */
public class DadosFerias implements Parcelable {

    private Double      mediaHorasExtrasAno;
    private Integer     qtdeDiasFerias;
    private Boolean     abonoPecuniario;
    private Boolean     adiantarPrimeiraDecimo;

    public Double getMediaHorasExtrasAno() {
        return mediaHorasExtrasAno;
    }

    public void setMediaHorasExtrasAno(Double mediaHorasExtrasAno) {
        this.mediaHorasExtrasAno = mediaHorasExtrasAno;
    }

    public Integer getQtdeDiasFerias() {
        return qtdeDiasFerias;
    }

    public void setQtdeDiasFerias(Integer qtdeDiasFerias) {
        this.qtdeDiasFerias = qtdeDiasFerias;
    }

    public Boolean getAbonoPecuniario() {
        return abonoPecuniario;
    }

    public void setAbonoPecuniario(Boolean abonoPecuniario) {
        this.abonoPecuniario = abonoPecuniario;
    }

    public Boolean getAdiantarPrimeiraDecimo() {
        return adiantarPrimeiraDecimo;
    }

    public void setAdiantarPrimeiraDecimo(Boolean adiantarPrimeiraDecimo) {
        this.adiantarPrimeiraDecimo = adiantarPrimeiraDecimo;
    }

    @Override
    public String toString() {
        return "DadosFerias{" +
                "mediaHorasExtrasAno=" + mediaHorasExtrasAno +
                ", qtdeDiasFerias=" + qtdeDiasFerias +
                ", abonoPecuniario=" + abonoPecuniario +
                ", adiantarPrimeiraDecimo=" + adiantarPrimeiraDecimo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mediaHorasExtrasAno);
        dest.writeValue(this.qtdeDiasFerias);
        dest.writeValue(this.abonoPecuniario);
        dest.writeValue(this.adiantarPrimeiraDecimo);
    }

    public DadosFerias() {
    }

    protected DadosFerias(Parcel in) {
        this.mediaHorasExtrasAno = (Double) in.readValue(Double.class.getClassLoader());
        this.qtdeDiasFerias = (Integer) in.readValue(Integer.class.getClassLoader());
        this.abonoPecuniario = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.adiantarPrimeiraDecimo = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DadosFerias> CREATOR = new Parcelable.Creator<DadosFerias>() {
        @Override
        public DadosFerias createFromParcel(Parcel source) {
            return new DadosFerias(source);
        }

        @Override
        public DadosFerias[] newArray(int size) {
            return new DadosFerias[size];
        }
    };
}
