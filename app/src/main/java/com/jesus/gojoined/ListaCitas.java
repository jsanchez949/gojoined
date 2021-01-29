package com.jesus.gojoined;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by jesus on 13/11/2016.
 */
public class ListaCitas extends AppCompatActivity {
    private ArrayList<Cita> citas;
    private RecyclerView rv;
    private AdaptadorListaCita alv;
    private LinearLayoutManager llm;
    private ServidorPHP servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setLogo(R.drawable.logogojoined3);
        toolbar.setNavigationIcon(R.drawable.arrowleft);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnaddcita = (Button) toolbar.findViewById(R.id.btn_addcita);

        btnaddcita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaCitas.this, FormCita.class);
                startActivity(i);
            }
        });
        if(NavigationDrawer.getTipousuario()!=9){
            btnaddcita.setActivated(false);
            btnaddcita.setVisibility(View.INVISIBLE);
        }

        citas =new ArrayList<>();

        rv=(RecyclerView)findViewById(R.id.listacitas);
        rv.setHasFixedSize(true);

        llm=new LinearLayoutManager(this);

        rv.setLayoutManager(llm);

        servidor=new ServidorPHP();

        try {
            citas =servidor.obtenerCita();
        } catch (Exception e) {
            e.printStackTrace();
        }

        alv=new AdaptadorListaCita(citas,this);
        rv.setAdapter(alv);
        //alv.refrescar();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }*/
}
