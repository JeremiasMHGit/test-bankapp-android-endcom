package com.example.testbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testbank.R;
import com.example.testbank.models.Saldo;

import java.util.ArrayList;


public class ListaSaldosAdapter extends RecyclerView.Adapter<ListaSaldosAdapter.ViewHolder> {

    private ArrayList<Saldo> dataset;
    private Context context;

    public ListaSaldosAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_saldos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Saldo p = dataset.get(position);

        holder.saldoGeneral.setText("$" + String.valueOf(p.getSaldoGeneral()));
        holder.totalIngresos.setText("$" + String.valueOf(p.getIngresos()));
        holder.totalGastos.setText("$" + String.valueOf(p.getGastos()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaSaldo(ArrayList<Saldo> listaSaldo) {
        dataset.addAll(listaSaldo);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView saldoGeneral, totalIngresos, totalGastos;

        public ViewHolder(View itemView) {
            super(itemView);

            saldoGeneral = itemView.findViewById(R.id.txt_saldoGeneral);
            totalIngresos = itemView.findViewById(R.id.txt_totalIngresos);
            totalGastos = itemView.findViewById(R.id.txt_totalGastos);
        }
    }
}
