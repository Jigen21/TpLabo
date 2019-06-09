package com.example.alumno.parcial;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Noticia implements Comparable<Noticia>
{

    private String titulo;
    private String descripcion;
    private String linkimagen;
    private String linkpagina;
    private Date fecha;
    private boolean procesar;
    private String fechaString;



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLinkpagina() {
        return linkpagina;
    }

    public void setLinkpagina(String linkpagina) {
        this.linkpagina = linkpagina;
    }

    private byte[] datosimagen;

    public byte[] getDatosimagen() {
        return datosimagen;
    }

    public void setDatosimagen(byte[] datosimagen) {
        this.datosimagen = datosimagen;
    }



    public Noticia() {
        this.procesar = false;

        this.fecha = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String fechaString = formatter.format(fecha);
    }

    public boolean getProcesar() {
        return procesar;
    }

    public void setProcesar(boolean procesar) {
        this.procesar = procesar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinkimagen() {
        return linkimagen;
    }

    public void setLinkimagen(String linkimagen) {
        this.linkimagen = linkimagen;
    }

    public String getFechaString()
    {
        return fechaString;
    }

    public void setFechaString(String fechaString)
    {
        this.fechaString = fechaString;
    }

    @Override
    public int compareTo(@NonNull Noticia o)
    {
        return getFecha().compareTo(o.getFecha());
        //return getTitulo().compareTo(o.getTitulo());
    }
}
