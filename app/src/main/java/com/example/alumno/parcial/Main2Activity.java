package com.example.alumno.parcial;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements Handler.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView mail = (TextView)findViewById(R.id.recu1);
        final TextView clave = (TextView)findViewById(R.id.recu2);
        Button boton = (Button)findViewById(R.id.recuboton);
        final Handler h2 = new Handler(this);
       // final String user = mail.getText().toString();
       // final String pass = mail.getText().toString();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;
                final String user = mail.getText().toString();
                final String pass = clave.getText().toString();

                if((user.equals("") || pass.equals("")))
                {
                    flag++;
                    if(flag==1)
                    {
                        Toast.makeText(getBaseContext(), "Complete todos los campos",
                                Toast.LENGTH_SHORT).show();

                    }


                }


                if(user.equals("admin") && pass.equals("1234"))
                {
                    flag++;
                    if(flag==1)
                    {
                        Toast.makeText(getBaseContext(), "Bienvenido/a admin" ,
                                Toast.LENGTH_SHORT).show();

                    }

                }

                if(user.equals("usuario") && pass.equals("1234"))
                {
                    flag++;
                    if(flag==1)
                    {
                        Toast.makeText(getBaseContext(), "Bienvenido/a usuario/a" ,
                                Toast.LENGTH_SHORT).show();

                    }

                }




                Log.d("Usuario",user);
                Log.d("password",pass);
               // MyOtherThread mt = new MyOtherThread("http://192.168.2.177:3000/login/admin/1234",h2);
                MyOtherThread mt = new MyOtherThread("http://192.168.2.177:3000/login/"+user+"/"+pass,h2);
                mt.start();
                Log.d("llegue","asd");

            }
        });


       // MyOtherThread mt = new MyOtherThread("http://192.168.2.177:3000/login/admin/1234",h2);
      //  mt.start();
       // Log.d("llegue","asd");


    }

    @Override
    public boolean handleMessage(Message msg)
    {
        Log.d("llegue",msg.obj.toString());
       // b = new Productos();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(msg.obj.toString());
            String id = jsonObject.getString("type");
            Log.d("bueno",id.toString());

            if(id.equals("Admin"))
            {
                Log.d("Soy admin","esa");
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("valor","admin");
                startActivity(i);
                finish();
            }

            if(id.equals("User"))
            {
                Log.d("Soy User","esa");
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("valor","user");
                startActivity(i);
                finish();
            }

            if(id.equals("error"))
            {
                Log.d("Soy Error","esa");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


       // b.setId(id);



        return true;
      //  return false;
    }
}
