package com.example.testbank.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.testbank.R;
import com.example.testbank.adapters.ListaMovimientosAdapter;
import com.example.testbank.models.Movimiento;
import com.example.testbank.models.MovimientosRespuesta;
import com.example.testbank.services.bankService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovimientosFragment extends Fragment {

    private static final String TAG = "MOVIMIENTOS";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaMovimientosAdapter listaMovimientosAdapter;

    //Expand
    public TextView btnExpand;
    public RelativeLayout expandableLayout;
    public LinearLayout linearLayoutt;

    public static MovimientosFragment newInstance(String param1, String param2) {
        MovimientosFragment fragment = new MovimientosFragment();
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
        View vista = inflater.inflate(R.layout.ac_recyclerview_movimientos, container, false);

        btnExpand = vista.findViewById(R.id.txt_expand_mov_recientes);
        expandableLayout = vista.findViewById(R.id.expandable_layout);
        linearLayoutt = vista.findViewById(R.id.linearLayoutt);

        recyclerView = vista.findViewById(R.id.rv_movimientos_rec);

        listaMovimientosAdapter = new ListaMovimientosAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(listaMovimientosAdapter);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        btnExpand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(expandableLayout.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(linearLayoutt, new AutoTransition());
                    expandableLayout.setVisibility(View.GONE);
                }else{
                    TransitionManager.beginDelayedTransition(linearLayoutt, new AutoTransition());
                    expandableLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosMovimientos();

        return vista;
    }


    private void obtenerDatosMovimientos() {
        bankService service = retrofit.create(bankService.class);
        Call<MovimientosRespuesta> movimientoRespuestaCall = service.obtenerListaMovimientos();

        movimientoRespuestaCall.enqueue(new Callback<MovimientosRespuesta>() {
            @Override
            public void onResponse(Call<MovimientosRespuesta> call, Response<MovimientosRespuesta> response) {
                try {
                    if (response.isSuccessful()) {
                        MovimientosRespuesta movimientosRespuesta = response.body();
                        ArrayList<Movimiento> listaMovimientos = movimientosRespuesta.getMovimientos();
                        listaMovimientosAdapter.adicionarListaMovimientos(listaMovimientos);
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MovimientosRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
