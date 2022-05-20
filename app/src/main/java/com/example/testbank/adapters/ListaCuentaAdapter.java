package com.example.testbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbank.R;
import com.example.testbank.models.Cuenta;

import java.util.ArrayList;


public class ListaCuentaAdapter extends RecyclerView.Adapter<ListaCuentaAdapter.ViewHolder> {

    private ArrayList<Cuenta> dataset;
    private Context context;

    public ListaCuentaAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cuenta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cuenta c = dataset.get(position);
        holder.cuentaNombre.setText(c.getNombre());
        holder.cuentaUltSesion.setText("Último inicio " + c.getUltimaSesion());


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaCuenta(ArrayList<Cuenta> listaCuenta) {
        //Con addAll() permitimos que el ArrayList que llega lo junte con el ArrayList de este Adaptador
        dataset.addAll(listaCuenta);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cuentaNombre, cuentaUltSesion;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuentaNombre = itemView.findViewById(R.id.txt_cuentaNombre);
            cuentaUltSesion = itemView.findViewById(R.id.txt_cuentaSesion);
        }
    }


}
