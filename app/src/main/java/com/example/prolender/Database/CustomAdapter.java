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
    private Activity activity;
    private ArrayList id, nombre, apat, amat, fecha, email, tel, rfc, image;

    CustomAdapter(Activity activity, Context context, ArrayList di, ArrayList nombre, ArrayList apat, ArrayList amat, ArrayList fecha, ArrayList email, ArrayList tel, ArrayList rfc,
                  ArrayList image){
        this.activity = activity;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cliente, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nombre_txt.setText(String.valueOf(nombre.get(position)));
        holder.apat_txt.setText(String.valueOf(apat.get(position)));
        holder.amat_txt.setText(String.valueOf(amat.get(position)));
        holder.fecha_txt.setText(String.valueOf(fecha.get(position)));
        holder.email_txt.setText(String.valueOf(email.get(position)));
        holder.tel_txt.setText(String.valueOf(tel.get(position)));
        holder.rfc_txt.setText(String.valueOf(rfc.get(position)));
        holder.image_txt.setText(String.valueOf(image.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.prolender.FragmentsActivities.ClientesFragments.DetallesClienteFragment .class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nombre", String.valueOf(nombre.get(position)));
                intent.putExtra("apat", String.valueOf(apat.get(position)));
                intent.putExtra("amat", String.valueOf(amat.get(position)));
                intent.putExtra("fecha", String.valueOf(fecha.get(position)));
                intent.putExtra("email", String.valueOf(email.get(position)));
                intent.putExtra("tel", String.valueOf(tel.get(position)));
                intent.putExtra("rfc", String.valueOf(rfc.get(position)));
                intent.putExtra("image", String.valueOf(image.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_txt, apat_txt, amat_txt, fecha_txt, email_txt, tel_txt, rfc_txt, image_txt;;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_txt = itemView.findViewById(R.id.campoNombre);
            apat_txt = itemView.findViewById(R.id.campoAppat);
            amat_txt = itemView.findViewById(R.id.campoAmat);
            fecha_txt = itemView.findViewById(R.id.campoFecha);
            email_txt = itemView.findViewById(R.id.campoCorreo);
            tel_txt = itemView.findViewById(R.id.campoNumero);
            rfc_txt = itemView.findViewById(R.id.campoRFC);
            image_txt = itemView.findViewById(R.id.imageViewFoto);

        }

    }

}
