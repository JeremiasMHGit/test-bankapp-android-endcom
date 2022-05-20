package com.example.testbank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tarjeta {
    @SerializedName("tarjeta")
    @Expose
    private String tarjeta;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("saldo")
    @Expose
    private int saldo;

    @SerializedName("estado")
    @Expose
    private String estado;

    @SerializedName("tipo")
    @Expose
    private String tipo;

    @SerializedName("id")
    @Expose
    private int id;

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
