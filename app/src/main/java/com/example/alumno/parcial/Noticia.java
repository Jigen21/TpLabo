package com.example.alumno.parcial;

import android.support.annotation.NonNull;

import java.util.Date;

public class Noticia implements Comparable<Noticia>
{

    private String titulo;
    private String descripcion;
    private String linkimagen;
    private String linkpagina;
    private Date fecha;
    private boolean procesar;



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



    public Noticia()
    {
        this.procesar = false;
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


    @Override
    public int compareTo(@NonNull Noticia o)
    {
        return getFecha().compareTo(o.getFecha());
    }
}
