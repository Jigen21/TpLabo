package com.example.alumno.parcial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MiDialogoVerPaginas extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elija una pagina!");
        final String[] items = new String[]{"https://www.lapoliticaonline.com/files/rss/ultimasnoticias.xml","https://www.telam.com.ar/rss2/sociedad.xml","https://www.telam.com.ar/rss2/deportes.xml","https://www.telam.com.ar/rss2/ultimasnoticias.xml"};





        ListenerAlert l = new ListenerAlert();
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), items [which], Toast.LENGTH_SHORT).show();
            }
        });

        //builder.setMessage("Mensaje de alerta");


        //ListenerAlert l = new ListenerAlert();
        builder.setPositiveButton("Elegir", l);
        builder.setNegativeButton("Cancelar", l);

        AlertDialog ad = builder.create();
        return ad;

    }

}
