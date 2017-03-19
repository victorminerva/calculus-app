package com.minervavi.app.workcalcapp.databinding.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victo on 19/03/2017.
 */

public class DadosSalarioLiq implements Parcelable {

    private Double      salarioBruto;
    private Integer     numDependentes;
    private Double      descontos;

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Integer getNumDependentes() {
        return numDependentes;
    }

    public void setNumDependentes(Integer numDependentes) {
        this.numDependentes = numDependentes;
    }

    public Double getDescontos() {
        return descontos;
    }

    public void setDescontos(Double descontos) {
        this.descontos = descontos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.salarioBruto);
        dest.writeValue(this.numDependentes);
        dest.writeValue(this.descontos);
    }

    public DadosSalarioLiq() {
    }

    protected DadosSalarioLiq(Parcel in) {
        this.salarioBruto = (Double) in.readValue(Double.class.getClassLoader());
        this.numDependentes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descontos = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<DadosSalarioLiq> CREATOR = new Parcelable.Creator<DadosSalarioLiq>() {
        @Override
        public DadosSalarioLiq createFromParcel(Parcel source) {
            return new DadosSalarioLiq(source);
        }

        @Override
        public DadosSalarioLiq[] newArray(int size) {
            return new DadosSalarioLiq[size];
        }
    };
}
