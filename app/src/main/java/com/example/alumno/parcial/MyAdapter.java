package com.example.alumno.parcial;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 09/05/2019.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{

    List<Noticia> noticias;
    List<Noticia> noticiasFull;
    onClickNoticia listener;
    private Handler myHanler;

    public MyAdapter(List<Noticia> noticias,onClickNoticia listener,Handler myHanler)
    {
        this.noticias = noticias;
        // noticiasFull = new ArrayList<>(noticias);
        // this.noticiasFull = noticias;

        this.listener=listener;
        this.myHanler = myHanler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.noticias,viewGroup,false);

        MyViewHolder myViewHolder = new MyViewHolder(v,listener);

        return myViewHolder;




    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        Noticia p = this.noticias.get(i);
        myViewHolder.titulo.setText(p.getTitulo());
       // myViewHolder.descripcion.setText(p.getDescripcion());

        if(p.getDescripcion().length()>80)
        {
            myViewHolder.descripcion.setText(p.getDescripcion().substring(0,80) + "...");
        }
        else
        {
            myViewHolder.descripcion.setText(p.getDescripcion());
        }



       // myViewHolder.imagen.setImageBitmap(BitmapFactory.decodeByteArray(p.getDatosimagen(),0,p.getDatosimagen().length));
        //myViewHolder.fecha.setText(p.getFecha().toString());
        myViewHolder.fecha.setText(p.getFechaString().toString());
        myViewHolder.linkpagina.setText(p.getLinkpagina());
        myViewHolder.setPosition(i);

        if(!p.getProcesar()){
            MyThread hilo2 = new MyThread(p.getLinkimagen(),this.myHanler,2,i);
            p.setProcesar(true);
            hilo2.start();
        }
        else{
            if(p.getDatosimagen() != null){
                myViewHolder.imagen.setImageBitmap(BitmapFactory.decodeByteArray(p.getDatosimagen(),0,p.getDatosimagen().length));
            }
        }


    }

    public void SetImagenPer(byte[] imagen,int position){
        Noticia p = this.noticias.get(position);
        p.setDatosimagen(imagen);
    }

    @Override
    public int getItemCount()
    {
        return this.noticias.size();
    }


    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
        // this.noticiasFull = noticias;
        noticiasFull = new ArrayList<>(noticias);
    }

    public void filter(String text) {

        if(noticiasFull!=null)
        {

            //this.noticias.clear();
            this.noticias.clear();
            //if(text.isEmpty())
            //if(text.equals("a"))
            if(text.length()<=3)
            {
                //ver porque lo cambie
                Log.d("tamaño lista uno",this.noticias.toString());
                Log.d("tamaño lista dos",this.noticiasFull.toString());
                this.noticias.addAll(this.noticiasFull);
                Log.d("noestavacio","asd");
            }
            else {
                text = text.toLowerCase();
                for(Noticia item: this.noticiasFull)
                {
                    Log.d("titulo",item.getTitulo());
                    if(item.getTitulo().toLowerCase().contains(text))
                    {
                        this.noticias.add(item);
                    }
                }
            }
            notifyDataSetChanged();

        }



    }


}