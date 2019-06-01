package com.example.alumno.parcial;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alumno on 09/05/2019.
 */

public class XmlParser
{

    public static List<Noticia> traerNoticias(String s) throws XmlPullParserException, IOException, ParseException, ParseException {
        List<Noticia> noticias = new ArrayList<Noticia>();
        XmlPullParser parser = Xml.newPullParser();

        int event=parser.getEventType();
        parser.setInput(new StringReader(s));
        Noticia n = new Noticia();


        while(event!=XmlPullParser.END_DOCUMENT)
        {
            switch (event)
            {

                case XmlPullParser.START_TAG:


                    if("title".equals(parser.getName()))
                    {
                        n = new Noticia();

                        n.setTitulo(parser.nextText());
                        Log.d("llegue",n.getTitulo());
                    }

                    if("link".equals(parser.getName()))
                    {
                        n.setLinkpagina(parser.nextText());

                    }


                    if("description".equals(parser.getName()))
                    {
                        n.setDescripcion(parser.nextText());
                        //noticias.add(n);


                    }

                    if("pubDate".equals(parser.getName()))
                    {

                        // n.setFecha(parser.nextText());
                        //DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
                        //Date date = formatter.parse("Sat, 24 Apr 2010 14:01:00 GMT";
                        // Log.d("FECHA",date.toString());

                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                        //Date date = parser.nextText();
                        // DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z",Locale.ENGLISH);
                        // String pubDateText = formatter.format(parser.nextText());

                        //DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",Locale.ENGLISH);
                        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZZ", Locale.ENGLISH);
                        //Date date = formatter.parse("Sat, 24 Apr 2010 14:01:00 GMT");
                        Date date = formatter.parse(parser.nextText());
                        Log.d("fecha",date.toString());
                        n.setFecha(date);

                    }

                       /* if("image".equals(parser.getName()))
                        {
                            n.setLinkimagen(parser.nextText());
                            Log.d("rrr",n.getLinkimagen());
                            //n.setDatosimagen(MiConnec.traerImagen(n.getLinkimagen()));
                        }*/

                    if("enclosure".equals(parser.getName()))
                    {
                        n.setLinkimagen(parser.getAttributeValue(null,"url"));
                        Log.d("rrr",n.getLinkimagen());
                        //n.setDatosimagen(MiConnec.traerImagen(n.getLinkimagen()));
                        noticias.add(n);

                    }





            }

            event=parser.next();



        }

        Collections.sort(noticias);
        //Collections.reverse(noticias);

        return noticias;

    }


}