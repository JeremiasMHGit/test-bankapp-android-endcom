package com.example.testbank.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testbank.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AsociarTarjetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_asociar_tarjeta);

        //configurar toolbar personalizado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_bank);

        Button btnCancelar, btnAgregar;
        EditText numTarjeta, cuentaT, issureT, nombreT, marcaT, estatusT, saldoT, tipoT;

        numTarjeta = findViewById(R.id.et_num_tarjeta);
        cuentaT = findViewById(R.id.et_cuenta);
        issureT = findViewById(R.id.et_issure);
        nombreT = findViewById(R.id.et_nom_tarj);
        marcaT = findViewById(R.id.et_marca);
        estatusT = findViewById(R.id.et_estatus);
        saldoT = findViewById(R.id.et_saldo);
        tipoT = findViewById(R.id.et_tipo_cuenta);


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

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog
                        .Builder builder = new AlertDialog.Builder(AsociarTarjetaActivity.this);
                builder.setTitle("Asociar Tarjeta");
                //llenar JSONObject
                JSONObject tarjeta = new JSONObject();
                try {
                    tarjeta.put("numTarjeta", numTarjeta.getText().toString());
                    tarjeta.put("cuenta", cuentaT.getText().toString());
                    tarjeta.put("issure", issureT.getText().toString());
                    tarjeta.put("nombre", nombreT.getText().toString());
                    tarjeta.put("marca", marcaT.getText().toString());
                    tarjeta.put("estatus", estatusT.getText().toString());
                    tarjeta.put("saldo", saldoT.getText().toString());
                    tarjeta.put("tipo", tipoT.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                builder.setMessage(tarjeta.toString());

                builder.setPositiveButton("Aceptar", null);
                //builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();

                Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                //Set positive button text color
                pbutton.setTextColor(Color.parseColor("#3ecca9"));
            }
        });

    }
}
