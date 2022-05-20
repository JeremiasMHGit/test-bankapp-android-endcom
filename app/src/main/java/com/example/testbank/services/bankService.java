package com.example.testbank.services;

import com.example.testbank.models.CuentaRespuesta;
import com.example.testbank.models.MovimientosRespuesta;
import com.example.testbank.models.SaldosRespuesta;
import com.example.testbank.models.TarjetaRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;


public interface bankService {
    @GET("tarjetas")
    Call<TarjetaRespuesta> obtenerListaTarjetas();

    @GET("movimientos")
    Call<MovimientosRespuesta> obtenerListaMovimientos();

    @GET("cuenta")
    Call<CuentaRespuesta> obtenerListaCuenta();

    @GET("saldos")
    Call<SaldosRespuesta> obtenerListaSaldos();
}
