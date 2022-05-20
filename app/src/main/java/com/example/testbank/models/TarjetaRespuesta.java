package com.example.testbank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TarjetaRespuesta {
    @SerializedName("tarjetas")
    @Expose
    private ArrayList<Tarjeta> tarjetas;

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

}
