package com.example.alumno.parcial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback , onClickNoticia , SearchView.OnQueryTextListener
{

    MyAdapter adapter;
    final List<Noticia> noticias = new ArrayList<Noticia>();
    Handler h;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.action_search).getActionView();
        sv.setOnQueryTextListener(this);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //el cambio
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Noticia n = new Noticia();

        //noticias.add(n);
        //noticias.add(n);

        this.h =new Handler(this);


        RecyclerView rv = (RecyclerView)findViewById(R.id.noticias);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(noticias,this,h);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        //Handler h =new Handler(this);
        //this.h =new Handler(this);
        //MyThread mt = new MyThread("https://www.lapoliticaonline.com/files/rss/ultimasnoticias.xml",h);
      /*  MyThread mt = new MyThread("https://www.telam.com.ar/rss2/sociedad.xml",h,TEXTO,0);
        mt.start();

        //agregue esto
        mt = new MyThread("https://www.clarin.com/rss/lo-ultimo/",h,TEXTO,0);
        mt.start(); */

        //ESTO YA LO AGREGUE



        //ESTO YA LO AGREGUE,borro para probar
        String[] items = new String[]{"https://www.iprofesional.com/rss/legales","https://www.telam.com.ar/rss2/sociedad.xml","https://www.telam.com.ar/rss2/deportes.xml","https://www.clarin.com/rss/lo-ultimo/"};
        SharedPreferences prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]).append(",");
        }
        editor.putString("paginas", sb.toString());
        editor.commit();
       /* SharedPreferences prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);
        String datoStr=prefs.getString("paginas","asd");
        String[] paginas = datoStr.split(",");
        Log.d("Guardado",paginas[0]);*/


        SharedPreferences prefsdos = getSharedPreferences("miConfig", Context.MODE_PRIVATE);


        String datoStr=prefsdos.getString("paginas","asd");

        final String[] paginas = datoStr.split(",");

        SharedPreferences prefsBooleanos = getSharedPreferences("memory", Context.MODE_PRIVATE);

        boolean[] checkState = {false, false, false, false};

        if (prefsBooleanos.contains("memory"))
        {

            String mem = prefsBooleanos.getString("memory", "");
            // mEditPrefs.putString("memory", "");
            String[] array = mem.split(","); //see below for the getArray() method
            checkState = new boolean[array.length];
            for(int nd = 0; nd < array.length; nd++){
                if(array[nd].equals("true"))
                {
                    checkState[nd] = true;
                } else {
                    checkState[nd] = false;
                }
            }


            for(int a = 0;a<paginas.length;a++)
            {
                if(checkState[a] == true)
                {
                    //Log.d("en true",paginas[a]);
                    MyThread mt = new MyThread(paginas[a],h,TEXTO,0);
                    mt.start();


                }

            }





        }








    }

    @Override
    public boolean handleMessage(Message msg)
    {

        if(msg.arg1 == TEXTO)
        {

            //this.noticias = (List<Noticia>) msg.obj;
            this.noticias.addAll((List<Noticia>) msg.obj);
            Collections.sort(noticias);
            adapter.setNoticias(this.noticias);
            adapter.notifyDataSetChanged();

        }
        else{
            this.adapter.SetImagenPer((byte[])msg.obj,msg.arg2);
            adapter.notifyItemChanged(msg.arg2);
        }
        return false;

        // MiDialogo dialog = new MiDialogo();
        //dialog.show(getSupportFragmentManager(),"dialogo");



    }

    @Override
    public void onClick(int position)
    {
        Log.d("Click","JEJE");
        Log.d("CLIC",this.noticias.get(position).getTitulo());
        Intent a = new Intent(getBaseContext(),ActivityDos.class);
        a.putExtra("pagina",this.noticias.get(position).getLinkpagina());
        startActivity(a);



    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("TextSubmit", s);
        this.adapter.filter(s);
        this.adapter.notifyDataSetChanged();
        /*this.noticias = this.myAdapter.getNoticias();
        List<Noticia> noticiasFiltro = new ArrayList<>();
        for (Noticia n : noticias) {
            if(n.getTitulo().equalsIgnoreCase(query)) {
                noticiasFiltro.add(n);
            }
        }
        this.myAdapter.setNoticias(noticiasFiltro);
        this.myAdapter.notifyDataSetChanged();*/
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        this.adapter.filter(newText);
        //Se puede usar un pattern en Java o un indexOf para buscar coincidencias. Lo que se busca en el search, debe coincidir con el titulo de la noticia.
        Log.d("TextChange", newText);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.itemConfig:
               /* MiDialogoVerPaginas uno = new MiDialogoVerPaginas();
                uno.show(getSupportFragmentManager(), "dialogo");*/


                // final String[] items = new String[]{"https://www.lapoliticaonline.com/files/rss/ultimasnoticias.xml","https://www.telam.com.ar/rss2/sociedad.xml","https://www.telam.com.ar/rss2/deportes.xml","https://www.clarin.com/rss/lo-ultimo/"};




                SharedPreferences prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);


                String datoStr=prefs.getString("paginas","asd");

                final String[] paginas = datoStr.split(",");
                Log.d("Guardado",paginas[0]);



                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Elija una pagina");
                //  final String[] items = new String[]{"https://www.lapoliticaonline.com/files/rss/ultimasnoticias.xml","https://www.telam.com.ar/rss2/sociedad.xml","https://www.telam.com.ar/rss2/deportes.xml","https://www.clarin.com/rss/lo-ultimo/"};

               /* builder.setItems(paginas, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //  Log.d("asdads","asd");
                        //MyThread mt = new MyThread(items[item],h);
                        noticias.clear();
                        MyThread mt = new MyThread(paginas[item],h,TEXTO,0);
                        mt.start();




                    }
                });*/
                boolean[] checkState = {false, false, false, false};


                SharedPreferences prefsBooleanos = getSharedPreferences("memory", Context.MODE_PRIVATE);

                if (prefsBooleanos.contains("memory"))
                {

                    String mem = prefsBooleanos.getString("memory", "");
                    // mEditPrefs.putString("memory", "");
                    String[] array = mem.split(","); //see below for the getArray() method
                    checkState = new boolean[array.length];
                    for(int n = 0; n < array.length; n++){
                        if(array[n].equals("true"))
                        {
                            checkState[n] = true;
                        } else {
                            checkState[n] = false;
                        }
                    }
                    final boolean[] checked = checkState;

                }

                //final boolean[] checked = new boolean[] {false, false, false, false};


                final boolean[] checked = checkState;



                builder.setMultiChoiceItems(paginas, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checked[i] = b;

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        // user clicked OK

                        noticias.clear();

                        for(int a = 0;a<paginas.length;a++)
                        {
                            if(checked[a] == true)
                            {
                                //Log.d("en true",paginas[a]);

                                MyThread mt = new MyThread(paginas[a],h,TEXTO,0);
                                mt.start();


                            }

                        }

                        SharedPreferences prefs = getSharedPreferences("memory", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();


                        String save = "";
                        for (int n = 0; n < checked.length; n++) {

                                if(checked[n])
                                {
                                    save = save + "true" + ",";
                                } else {
                                    save = save + "false" + ",";
                                }

                        }
                        editor.putString("memory", save);
                        editor.commit();




                    }
                });
                builder.setNegativeButton("Cancel", null);


                AlertDialog alert = builder.create();
                alert.show();

                return true;
            case R.id.itemConfig2:


                // MiDialogo dos = new MiDialogo();
                //dos.show(getSupportFragmentManager(), "dialogo");

                AlertDialog.Builder builderdos = new AlertDialog.Builder(
                        MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View mView = inflater.inflate(R.layout.layout_dialogo, null);
                final EditText etSearch = (EditText)mView.findViewById(R.id.pro);
                builderdos.setView(mView)
                        .setPositiveButton("Agregar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        String guardar = etSearch.getText().toString();
                                        Log.d("lexa",guardar);

                                        if(URLUtil.isValidUrl(guardar))
                                        {

                                            SharedPreferences prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);


                                            String datoStr=prefs.getString("paginas","asd");

                                            String[] paginas = datoStr.split(",");

                                            //List<String> wordList = Arrays.asList(paginas);
                                            List<String> wordList = new ArrayList<String>(Arrays.asList(paginas));
                                            wordList.add(guardar);

                                            Log.d("lexados",wordList.toString());

                                            SharedPreferences.Editor editor = prefs.edit();


                                            StringBuilder sb = new StringBuilder();
                                            for (int i = 0; i < wordList.size(); i++) {
                                                sb.append(wordList.get(i)).append(",");
                                            }
                                            editor.putString("paginas", sb.toString());
                                            editor.commit();


                                        }

                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"INGRESE UNA URL VALIDA",Toast.LENGTH_LONG).show();
                                        }






                                    }
                                })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builderdos.create();
                alertDialog.show();
                return true;






            //your code

            default:
                return super.onOptionsItemSelected(item);

        }
    }




}