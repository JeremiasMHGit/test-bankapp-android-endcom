package com.example.testbank.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testbank.R;
import com.example.testbank.models.Movimiento;

import java.util.ArrayList;

public class ListaMovimientosAdapter extends RecyclerView.Adapter<ListaMovimientosAdapter.ViewHolder> {

    private ArrayList<Movimiento> dataset;
    private Context context;

    public ListaMovimientosAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movimientos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String tipoAbono = "abono";
        String tipoCargo = "cargo";

        Movimiento p = dataset.get(position);
        holder.tipoMov.setText(p.getTipo());
        holder.fechaMov.setText(p.getFecha());
        //holder.montoMov.setText("$" + p.getMonto());

        if(p.getTipo().equals(tipoAbono)){
            holder.montoMov.setTextColor(Color.parseColor("#3ecca9"));
            holder.montoMov.setText("+$" + p.getMonto());
        }else if(p.getTipo().equals(tipoCargo)){
            holder.montoMov.setTextColor(Color.RED);
            holder.montoMov.setText("-$" + p.getMonto());
        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
/*
        if(dataset.size()>3){
            return 3;
        }
        else {
            return dataset.size();
        }*/
    }

    public void adicionarListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
        dataset.addAll(listaMovimientos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tipoMov, fechaMov, montoMov;

        public ViewHolder(View itemView) {
            super(itemView);
            //handleShowView(itemView);

            tipoMov = itemView.findViewById(R.id.txt_desc_mov);
            fechaMov = itemView.findViewById(R.id.txt_fecha_mov);
            montoMov = itemView.findViewById(R.id.txt_monto_mov);

        }
    }
}
