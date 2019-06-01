package com.example.alumno.parcial;

import android.content.DialogInterface;
import android.util.Log;

public class ListenerAlert implements DialogInterface.OnClickListener
{


    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("dialog","click")     ;
    }
}
