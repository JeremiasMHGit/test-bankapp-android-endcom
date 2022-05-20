package com.example.testbank.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import android.os.Bundle;

import com.example.testbank.R;

public class MainActivity extends AppCompatActivity {

    private TextView txtMisCuentas, txtEnviarDinero, txtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configurar toolbar personalizado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_bank);

        txtMisCuentas = findViewById(R.id.txt_mis_cuentas);
        txtEnviarDinero = findViewById(R.id.txt_enviar_dinero);
        txtAdd = findViewById(R.id.txt_add_tarjeta);

        txtAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AsociarTarjetaActivity.class);
                startActivity(intent);
            }
        });

        txtMisCuentas.setPaintFlags(txtAdd.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtEnviarDinero.setPaintFlags(txtAdd.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtAdd.setPaintFlags(txtAdd.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }
}