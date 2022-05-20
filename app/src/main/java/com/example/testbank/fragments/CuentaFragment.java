package com.example.testbank.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testbank.R;
import com.example.testbank.adapters.ListaCuentaAdapter;
import com.example.testbank.models.Cuenta;
import com.example.testbank.models.CuentaRespuesta;
import com.example.testbank.services.bankService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CuentaFragment extends Fragment {

    private static final String TAG = "CUENTA";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaCuentaAdapter listaCuentaAdapter;

    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.ac_recyclerview, container, false);

        recyclerView = vista.findViewById(R.id.recyclerView);

        listaCuentaAdapter = new ListaCuentaAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(listaCuentaAdapter);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosCuenta();

        return vista;
    }


    private void obtenerDatosCuenta() {
        bankService service = retrofit.create(bankService.class);
        Call<CuentaRespuesta> cuentaRespuestaCall = service.obtenerListaCuenta();

        cuentaRespuestaCall.enqueue(new Callback<CuentaRespuesta>() {
            @Override
            public void onResponse(Call<CuentaRespuesta> call, Response<CuentaRespuesta> response) {
                try {
                    if (response.isSuccessful()) {
                        CuentaRespuesta cuentaRespuesta = response.body();
                        ArrayList<Cuenta> listaCuenta = cuentaRespuesta.getCuenta();
                        listaCuentaAdapter.adicionarListaCuenta(listaCuenta);
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CuentaRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
