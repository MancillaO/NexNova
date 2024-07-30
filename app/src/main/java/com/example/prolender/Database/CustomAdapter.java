package com.example.prolender.Database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prolender.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, nombre, apat, amat, fecha, email, tel, rfc, image;

    public CustomAdapter(Context context,
                         ArrayList id,
                         ArrayList nombre,
                         ArrayList apat,
                         ArrayList amat,
                         ArrayList fecha,
                         ArrayList email,
                         ArrayList tel,
                         ArrayList rfc,
                         ArrayList image){
        this.context = context;
        this.id = id;
        this.nombre = nombre;
        this.apat = apat;
        this.amat = amat;
        this.fecha = fecha;
        this.email = email;
        this.tel = tel;
        this.rfc = rfc;
        this.image = image;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cliente, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.id_text.setText(String.valueOf(id.get(position)));
        holder.nombre_text.setText(String.valueOf(nombre.get(position)));
        holder.email_text.setText(String.valueOf(email.get(position)));
        holder.tel_text.setText(String.valueOf(tel.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_text, nombre_text, apat_text, amat_text, fecha_text, email_text, tel_text, rfc_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_text = itemView.findViewById(R.id.txtID);
            nombre_text = itemView.findViewById(R.id.cliente_titulo);
            email_text = itemView.findViewById(R.id.txtCorreo);
            tel_text = itemView.findViewById(R.id.txtTelefono);

        }
    }
}
