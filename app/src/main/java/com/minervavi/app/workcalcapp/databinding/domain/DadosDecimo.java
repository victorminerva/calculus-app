package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */

public class DadosDecimo implements Parcelable {

    private Integer     numMesesTrabalhados;
    private ParcelaEnum parcela;

    public DadosDecimo(Integer numMesesTrabalhados, ParcelaEnum parcela) {
        this.numMesesTrabalhados    = numMesesTrabalhados;
        this.parcela                = parcela;
    }

    public Integer getNumMesesTrabalhados() {
        return numMesesTrabalhados;
    }

    public void setNumMesesTrabalhados(Integer numMesesTrabalhados) {
        this.numMesesTrabalhados = numMesesTrabalhados;
    }

    public ParcelaEnum getParcela() {
        return parcela;
    }

    public void setParcela(ParcelaEnum parcela) {
        this.parcela = parcela;
    }

    @Override
    public String toString() {
        return "DadosDecimo{" +
                "numMesesTrabalhados=" + numMesesTrabalhados +
                ", parcela=" + parcela +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.numMesesTrabalhados);
        dest.writeInt(this.parcela == null ? -1 : this.parcela.ordinal());
    }

    public DadosDecimo() {
    }

    protected DadosDecimo(Parcel in) {
        this.numMesesTrabalhados = (Integer) in.readValue(Integer.class.getClassLoader());
        int tmpParcela = in.readInt();
        this.parcela = tmpParcela == -1 ? null : ParcelaEnum.values()[tmpParcela];
    }

    public static final Parcelable.Creator<DadosDecimo> CREATOR = new Parcelable.Creator<DadosDecimo>() {
        @Override
        public DadosDecimo createFromParcel(Parcel source) {
            return new DadosDecimo(source);
        }

        @Override
        public DadosDecimo[] newArray(int size) {
            return new DadosDecimo[size];
        }
    };
}
