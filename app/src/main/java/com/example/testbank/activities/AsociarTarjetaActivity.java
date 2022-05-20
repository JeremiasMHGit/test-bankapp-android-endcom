package com.example.testbank.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testbank.R;

public class AsociarTarjetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_asociar_tarjeta);

        //configurar toolbar personalizado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_bank);

        Button btnCancelar, btnAgregar;

        btnCancelar = findViewById(R.id.button2);
        btnAgregar = findViewById(R.id.button3);

        btnCancelar.setBackgroundColor(Color.parseColor("#d8d8d8"));
        btnAgregar.setBackgroundColor(Color.parseColor("#3ecca9"));

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
