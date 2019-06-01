package com.example.alumno.parcial;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alumno on 09/05/2019.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView titulo;
    TextView descripcion;
    ImageView imagen;
    int position;
    onClickNoticia listener;
    TextView fecha;
    TextView linkpagina;



    public MyViewHolder(@NonNull View itemView, onClickNoticia listener)
    {
        super(itemView);
        titulo=(TextView)itemView.findViewById(R.id.titulo);
        descripcion=(TextView)itemView.findViewById(R.id.descripcion);
        imagen=(ImageView)itemView.findViewById(R.id.imagen);
        fecha=(TextView)itemView.findViewById(R.id.fecha);
        linkpagina=(TextView)itemView.findViewById(R.id.linkpagina);
        this.listener=listener;
        itemView.setOnClickListener(this);



    }

    public void setPosition(int position)
    {
        this.position=position;
    }

    @Override
    public void onClick(View v)
    {
        this.listener.onClick(position);
    }
}
