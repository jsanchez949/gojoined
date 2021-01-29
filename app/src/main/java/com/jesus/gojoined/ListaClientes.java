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
public class ListaClientes extends AppCompatActivity {
    private ArrayList<Cliente> clientes;
    private RecyclerView rv;
    private AdaptadorListaClientes alv;
    private LinearLayoutManager llm;
    private ServidorPHP servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

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

        Button btnaddclient = (Button) toolbar.findViewById(R.id.btn_addclient);

        btnaddclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaClientes.this, FormCliente.class);
                startActivity(i);
            }
        });
        if(NavigationDrawer.getTipousuario()!=0){
            btnaddclient.setActivated(false);
            btnaddclient.setVisibility(View.INVISIBLE);
        }

        clientes =new ArrayList<>();
        rv=(RecyclerView)findViewById(R.id.listaclientes);
        registerForContextMenu(rv);
        rv.setHasFixedSize(true);

        llm=new LinearLayoutManager(this);

        rv.setLayoutManager(llm);

        servidor=new ServidorPHP();

        try {
            clientes=servidor.obtenerCliente();
        } catch (Exception e) {
            e.printStackTrace();
        }

        alv=new AdaptadorListaClientes(clientes,this);
        rv.setAdapter(alv);
        alv.refrescar();
    }


    public void onResume(){
        super.onResume();

        try {
            clientes=servidor.obtenerCliente();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error obteniendo usuarios: "+e.toString());
        }
        alv.cambiosClientes(clientes);
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
