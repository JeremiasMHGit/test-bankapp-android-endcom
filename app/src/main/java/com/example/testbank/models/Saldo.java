package com.example.testbank.models;

public class Saldo {
    public long cuenta;
    public int saldoGeneral;
    public int ingresos;
    public int gastos;
    public int id;

    public long getCuenta() {
        return cuenta;
    }

    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
    }

    public int getSaldoGeneral() {
        return saldoGeneral;
    }

    public void setSaldoGeneral(int saldoGeneral) {
        this.saldoGeneral = saldoGeneral;
    }

    public int getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    public int getGastos() {
        return gastos;
    }

    public void setGastos(int gastos) {
        this.gastos = gastos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
