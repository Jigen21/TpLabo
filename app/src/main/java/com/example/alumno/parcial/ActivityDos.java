package com.example.alumno.parcial;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class ActivityDos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina);

        final String s = getIntent().getStringExtra("pagina");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView web = (WebView)findViewById(R.id.web);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //web.getSettings().setJavaScriptEnabled(true);
        //setContentView(web);
        Log.d("Pagina",s);


        if(s.contains("www"))
        {
            web.loadUrl(s);
        }
        else
        {
            web.loadUrl("https://www.telam.com.ar"+s);
        }

        //web.loadUrl("https://www.telam.com.ar"+s);

        FloatingActionButton compartir = (FloatingActionButton) findViewById(R.id.compartirr);
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                myIntent.putExtra(Intent.EXTRA_TEXT,s);
                String shareBody ="body";
                String shareSub = "sub";
                //  startActivity(Intent.createChooser(myIntent),"Compartir Usando");
                startActivity(myIntent);
            }
        });






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}
