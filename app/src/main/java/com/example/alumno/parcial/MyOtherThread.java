package com.example.alumno.parcial;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by alumno on 23/05/2019.
 */

public class MyOtherThread extends Thread
{
    String url;
    Handler h;

    public MyOtherThread(String url, Handler h)
    {
        this.url = url;
        this.h = h;
    }

    public void run()
    {
     /*   try {
            String s = MiConnec.traer(url);
            Message m = new Message();
            m.obj=s;
            this.h.sendMessage(m);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error leyendo","asd");
        }


*/
    }



}
