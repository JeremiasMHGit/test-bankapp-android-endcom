package com.example.testbank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.testbank.R;


public class AgregarTarjetaFragment extends Fragment {

    private TextView txtAdd;

    public static AgregarTarjetaFragment newInstance(String param1, String param2) {
        AgregarTarjetaFragment fragment = new AgregarTarjetaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_agregar_tarjeta, container, false);
        return  vista;
    }
}
