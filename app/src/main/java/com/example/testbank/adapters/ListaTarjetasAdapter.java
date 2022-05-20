package com.example.testbank.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testbank.R;
import com.example.testbank.models.Tarjeta;

import java.util.ArrayList;

public class ListaTarjetasAdapter extends RecyclerView.Adapter<ListaTarjetasAdapter.ViewHolder> {

    private ArrayList<Tarjeta> dataset;
    private Context context;

    public ListaTarjetasAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String statusOff = "desactivada";

        Tarjeta p = dataset.get(position);
        holder.statusTarjeta.setText(p.getEstado());
        holder.numeroTarjeta.setText(p.getTarjeta());
        holder.nombreTarjeta.setText(p.getNombre());
        holder.tipoTarjeta.setText(p.getTipo());
        holder.saldoTarjeta.setText("$" + String.valueOf(p.getSaldo()));

        if(p.getEstado().equals(statusOff)){
            holder.statusTarjeta.setTextColor(Color.LTGRAY);
            holder.saldoTarjeta.setTextColor(Color.LTGRAY);
            holder.imgTarjetaOn.setImageResource(R.drawable.ic_tarjeta_off);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaTarjetas(ArrayList<Tarjeta> listaTarjetas) {
        dataset.addAll(listaTarjetas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTarjetaOn;
        private TextView statusTarjeta, numeroTarjeta, nombreTarjeta, tipoTarjeta, saldoTarjeta;

        public ViewHolder(View itemView) {
            super(itemView);

            imgTarjetaOn = itemView.findViewById(R.id.img_tarjetaOn);
            statusTarjeta = itemView.findViewById(R.id.txt_statusTarjeta);
            numeroTarjeta = itemView.findViewById(R.id.txt_numTarjeta);
            nombreTarjeta = itemView.findViewById(R.id.txt_nombreTarjeta);
            tipoTarjeta = itemView.findViewById(R.id.txt_tipo);
            saldoTarjeta = itemView.findViewById(R.id.txt_saldoTarjeta);
        }
    }
}
