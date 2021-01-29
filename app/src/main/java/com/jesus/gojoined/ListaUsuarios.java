package com.jesus.gojoined;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {
    private ArrayList<Usuario> usuarios;
    private RecyclerView rv;
    private AdaptadorListaUsuario alu;
    private LinearLayoutManager llm;
    private ServidorPHP servidor;
    private NavigationDrawer drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lista_usuarios);
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

        Button btnadduser = (Button) toolbar.findViewById(R.id.btn_adduser);

        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaUsuarios.this, FormUsuario.class);
                startActivity(i);
            }
        });

        if(NavigationDrawer.getTipousuario()!=0){
            btnadduser.setActivated(false);
            btnadduser.setVisibility(View.INVISIBLE);
        }

        usuarios=new ArrayList<>();
        rv=(RecyclerView)findViewById(R.id.listauser);
        registerForContextMenu(rv);
        rv.setHasFixedSize(true);
        llm=new LinearLayoutManager(this);

        rv.setLayoutManager(llm);

        servidor=new ServidorPHP();

        try {
            usuarios=servidor.obtenerUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error obteniendo usuarios: "+e.toString());
        }
        //cambiar para que detecte el tipo de usuario

        alu=new AdaptadorListaUsuario(usuarios,this,"aaa");
        rv.setAdapter(alu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

 /*   public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){
            case 1:
                System.out.println(""+item.getMenuInfo());
                System.out.println(""+item.getIntent());
                System.out.println("llamar a ");
                break;
            case 2:
                System.out.println(""+item.getMenuInfo());
                System.out.println(""+item.getIntent());
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                System.out.println("llamar a ");


                *//*Intent i = new Intent(ListaUsuarios.this, FormUsuario.class);

                i.putExtra("EDITMODE",true);
                startActivity(i);*//*
                break;
            case 3:
                System.out.println("borrar usuario");
                break;
            case 4:
                break;
            default:
                System.out.println("nada echa");
                return true;
        }


        return true;
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    public void onResume(){
        super.onResume();

        try {
            usuarios=servidor.obtenerUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error obteniendo usuarios: "+e.toString());
        }
        alu.cambiosUsuarios(usuarios);
     }
}
