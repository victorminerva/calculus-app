package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */

public class DadosRetroativo implements Parcelable {

    private Double      salarioBaseReaj;
    private Double      percentualAumento;
    private Integer     getNumMesesTrabalhadosReaj;

    public Double getSalarioBaseReaj() {
        return salarioBaseReaj;
    }

    public void setSalarioBaseReaj(Double salarioBaseReaj) {
        this.salarioBaseReaj = salarioBaseReaj;
    }

    public Double getPercentualAumento() {
        return percentualAumento;
    }

    public void setPercentualAumento(Double percentualAumento) {
        this.percentualAumento = percentualAumento;
    }

    public Integer getGetNumMesesTrabalhadosReaj() {
        return getNumMesesTrabalhadosReaj;
    }

    public void setGetNumMesesTrabalhadosReaj(Integer getNumMesesTrabalhadosReaj) {
        this.getNumMesesTrabalhadosReaj = getNumMesesTrabalhadosReaj;
    }

    @Override
    public String toString() {
        return "DadosRetroativo{" +
                "salarioBaseReaj=" + salarioBaseReaj +
                ", percentualAumento=" + percentualAumento +
                ", getNumMesesTrabalhadosReaj=" + getNumMesesTrabalhadosReaj +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.salarioBaseReaj);
        dest.writeValue(this.percentualAumento);
        dest.writeValue(this.getNumMesesTrabalhadosReaj);
    }

    public DadosRetroativo() {
    }

    protected DadosRetroativo(Parcel in) {
        this.salarioBaseReaj = (Double) in.readValue(Double.class.getClassLoader());
        this.percentualAumento = (Double) in.readValue(Double.class.getClassLoader());
        this.getNumMesesTrabalhadosReaj = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<DadosRetroativo> CREATOR = new Parcelable.Creator<DadosRetroativo>() {
        @Override
        public DadosRetroativo createFromParcel(Parcel source) {
            return new DadosRetroativo(source);
        }

        @Override
        public DadosRetroativo[] newArray(int size) {
            return new DadosRetroativo[size];
        }
    };
}
