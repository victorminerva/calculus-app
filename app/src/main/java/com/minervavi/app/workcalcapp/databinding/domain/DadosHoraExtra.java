package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */

public class DadosHoraExtra implements Parcelable {

    private Double      jornadaMensal;
    private Double      adicionalHoraExtra;
    private Double      numHoraExtra;

    public Double getJornadaMensal() {
        return jornadaMensal;
    }

    public void setJornadaMensal(Double jornadaMensal) {
        this.jornadaMensal = jornadaMensal;
    }

    public Double getAdicionalHoraExtra() {
        return adicionalHoraExtra;
    }

    public void setAdicionalHoraExtra(Double adicionalHoraExtra) {
        this.adicionalHoraExtra = adicionalHoraExtra;
    }

    public Double getNumHoraExtra() {
        return numHoraExtra;
    }

    public void setNumHoraExtra(Double numHoraExtra) {
        this.numHoraExtra = numHoraExtra;
    }

    @Override
    public String toString() {
        return "DadosHoraExtra{" +
                "jornadaMensal=" + jornadaMensal +
                ", adicionalHoraExtra=" + adicionalHoraExtra +
                ", numHoraExtra=" + numHoraExtra +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.jornadaMensal);
        dest.writeValue(this.adicionalHoraExtra);
        dest.writeValue(this.numHoraExtra);
    }

    public DadosHoraExtra() {
    }

    protected DadosHoraExtra(Parcel in) {
        this.jornadaMensal = (Double) in.readValue(Double.class.getClassLoader());
        this.adicionalHoraExtra = (Double) in.readValue(Double.class.getClassLoader());
        this.numHoraExtra = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<DadosHoraExtra> CREATOR = new Parcelable.Creator<DadosHoraExtra>() {
        @Override
        public DadosHoraExtra createFromParcel(Parcel source) {
            return new DadosHoraExtra(source);
        }

        @Override
        public DadosHoraExtra[] newArray(int size) {
            return new DadosHoraExtra[size];
        }
    };
}
