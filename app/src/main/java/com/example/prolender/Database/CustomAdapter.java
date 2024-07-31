package com.example.prolender.Database;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prolender.FragmentsActivities.ClientesFragments.DetallesClienteFragment;
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
                         ArrayList image) {
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_text.setText(String.valueOf(id.get(position)));
        String nombreCompleto = nombre.get(position) + " " + apat.get(position) + " " + amat.get(position);
        holder.nombre_text.setText(nombreCompleto);
        holder.email_text.setText(String.valueOf(email.get(position)));
        holder.tel_text.setText(String.valueOf(tel.get(position)));

        holder.detalles_text.setOnClickListener(v -> {
            // Obtener el ID del cliente
            String clienteId = String.valueOf(id.get(position));

            // Crear el fragmento con el ID del cliente
            DetallesClienteFragment detallesFragment = DetallesClienteFragment.newInstance(clienteId);

            // Reemplazar el fragmento actual con el nuevo fragmento
            ((FragmentActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, detallesFragment) // Cambia el contenedor si es necesario
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_text, nombre_text, email_text, tel_text, detalles_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_text = itemView.findViewById(R.id.txtID);
            nombre_text = itemView.findViewById(R.id.cliente_titulo);
            email_text = itemView.findViewById(R.id.txtCorreo);
            tel_text = itemView.findViewById(R.id.txtTelefono);
            detalles_text = itemView.findViewById(R.id.txtDetalles); // Añadir txtDetalles
        }
    }
}
