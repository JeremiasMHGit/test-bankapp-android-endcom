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
import com.example.testbank.adapters.ListaTarjetasAdapter;
import com.example.testbank.models.Tarjeta;
import com.example.testbank.models.TarjetaRespuesta;
import com.example.testbank.services.bankService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TarjetasFragment extends Fragment {

    private static final String TAG = "TARJETAS";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaTarjetasAdapter listaTarjetasAdapter;


    public static TarjetasFragment newInstance(String param1, String param2) {
        TarjetasFragment fragment = new TarjetasFragment();
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

        listaTarjetasAdapter = new ListaTarjetasAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(listaTarjetasAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosTarjeta();

        return vista;
    }


    private void obtenerDatosTarjeta() {
        bankService service = retrofit.create(bankService.class);
        Call<TarjetaRespuesta> tarjetaRespuestaCall = service.obtenerListaTarjetas();

        tarjetaRespuestaCall.enqueue(new Callback<TarjetaRespuesta>() {
            @Override
            public void onResponse(Call<TarjetaRespuesta> call, Response<TarjetaRespuesta> response) {
                try {
                    if (response.isSuccessful()) {
                        TarjetaRespuesta tarjetaRespuesta = response.body();
                        ArrayList<Tarjeta> listaTarjetas = tarjetaRespuesta.getTarjetas();
                        listaTarjetasAdapter.adicionarListaTarjetas(listaTarjetas);
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TarjetaRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
