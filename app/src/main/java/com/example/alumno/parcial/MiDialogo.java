package com.example.alumno.parcial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MiDialogo extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
       /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alerta!");
        builder.setMessage("Mensaje de alerta");
        ListenerAlert l = new ListenerAlert();
        builder.setPositiveButton("Aceptar",l);
        AlertDialog ad = builder.create();
        return ad;*/

        LayoutInflater li = LayoutInflater.from(getActivity());
        View viewAlert = li.inflate(R.layout.layout_dialogo,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Prueba!");
        builder.setView(viewAlert);

        ListenerAlert l = new ListenerAlert();
        builder.setPositiveButton("Agregar Pagina",l);
        builder.setNegativeButton("Cancelar",l);

        AlertDialog ad = builder.create();
        return ad;




    }




}
