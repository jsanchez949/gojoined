package com.jesus.gojoined;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static int tipousuario;
    private Button btn_compartir;

    public static int getTipousuario() {
        return tipousuario;
    }

    public static void setTipousuario(int tipousuario) {
        NavigationDrawer.tipousuario = tipousuario;
      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Llamar al administrador de la empresa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                new SimpleDialog().show(getSupportFragmentManager(), "SimpleDialog");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
       // MenuItem nav_addusers = menu.findItem(R.id.nav_addusuario);
        //MenuItem nav_addclientes = menu.findItem(R.id.nav_addcliente);

        NavigationView naviView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_subtitle = (TextView)hView.findViewById(R.id.textView);

        Bundle b = getIntent().getExtras();
        setTipousuario(b.getInt("tipoUsuario"));

        //if (b.getInt("tipoUsuario")!=0) {
        //    nav_subtitle.setText("Administrador");
        //}
        //else{
        //    nav_subtitle.setText("Usuario");
            //nav_addusers.setVisible(false);
            //nav_addclientes.setVisible(false);
        //}

        System.out.println(b.getString("nombreUsuario"));

        nav_subtitle.setText(b.getString("nombreUsuario")+ " " +b.getString("apellidoUsuario"));
        System.out.println(b.getString("nombreUsuario"));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        btn_compartir=(Button)findViewById(R.id.btn_compartir);
        btn_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Prueben la aplicación GoJoined, la herramienta que usted necesita para gestionar su empresa https://www.facebook.com/profile.php?id=100014510767696&fref=ts");
                startActivity(Intent.createChooser(intent, "Compartir con:"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista_usuarios) {
            Intent i = new Intent(NavigationDrawer.this, ListaUsuarios.class);
            startActivity(i);
        } else if (id == R.id.nav_lista_clientes) {
            Intent i = new Intent(NavigationDrawer.this, ListaClientes.class);
            startActivity(i);
        } else if (id == R.id.nav_lista_visitas) {
            Intent i = new Intent(NavigationDrawer.this, ListaCitas.class);
            startActivity(i);
        } else if (id == R.id.nav_mapas) {
            Intent i = new Intent(NavigationDrawer.this,MapsActivityTest.class);
            startActivity(i);
        } else if (id == R.id.nav_informacion) {
            Intent i = new Intent(NavigationDrawer.this,Informacion.class);
            startActivity(i);
        } else if (id == R.id.nav_salir) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("NavigationDrawer Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
