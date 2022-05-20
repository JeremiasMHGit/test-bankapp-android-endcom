package com.example.testbank.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbank.R;
import com.example.testbank.adapters.ListaSaldosAdapter;
import com.example.testbank.models.Saldo;
import com.example.testbank.models.SaldosRespuesta;
import com.example.testbank.services.bankService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaldoFragment extends Fragment {

    private static final String TAG = "SALDO";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaSaldosAdapter listaSaldosAdapter;

    public static SaldoFragment newInstance(String param1, String param2) {
        SaldoFragment fragment = new SaldoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.ac_recyclerview, container, false);

        recyclerView = vista.findViewById(R.id.recyclerView);

        listaSaldosAdapter = new ListaSaldosAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(listaSaldosAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosSaldo();

        return vista;
    }

    private void obtenerDatosSaldo() {
        bankService service = retrofit.create(bankService.class);
        Call<SaldosRespuesta> saldoRespuestaCall = service.obtenerListaSaldos();

        saldoRespuestaCall.enqueue(new Callback<SaldosRespuesta>() {
            @Override
            public void onResponse(Call<SaldosRespuesta> call, Response<SaldosRespuesta> response) {
                try {
                    if (response.isSuccessful()) {
                        SaldosRespuesta saldosRespuesta = response.body();
                        ArrayList<Saldo> listaSaldo = saldosRespuesta.getSaldos();
                        listaSaldosAdapter.adicionarListaSaldo(listaSaldo);
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SaldosRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
