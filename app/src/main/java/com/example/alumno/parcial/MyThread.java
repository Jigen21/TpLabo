package com.example.alumno.parcial;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by alumno on 09/05/2019.
 */

class MyThread extends Thread
{

    String url;
    Handler h;
    int imagenText;
    int position;

    public MyThread(String url,Handler h, int imagenText,int position)
    {
        this.url = url;
        this.h=h;
        this.imagenText = imagenText;
        this.position =position;
    }

    public void run()
    {

        Message m = new Message();
        m.arg1 = imagenText;
        m.arg2 = position;

        try {

            if(imagenText == MainActivity.TEXTO)
            {
                String s = MiConnec.traerNoticia(url);
                m.obj = XmlParser.traerNoticias(s);

            }
            else{
                byte [] img = MiConnec.traerImagen(url);
                m.obj = img;
            }

           // Log.d("ESTO",s);
            //Log.d("OTRO",m.obj.toString());



        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.h.sendMessage(m);

    }
}
