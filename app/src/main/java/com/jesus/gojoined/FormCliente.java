package com.jesus.gojoined;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jesus on 13/11/2016.
 */
public class FormCliente extends AppCompatActivity implements View.OnClickListener {
    
    private ArrayList<Cliente> array_cliente;
    private EditText e_nombre,e_apellido,e_empresa,e_telefono,e_direccion,e_cp,e_ciudad;
    private TextView txt_clienteid;
    private Button btn_addcliente,btn_cancelar;
    private ServidorPHP servidor;
    private Context context;
    private boolean editmode;
    private int clientID;
    private int posicion;
    

    public boolean isEditmode() {
        return editmode;
    }

    public void setEditmode(boolean editmode) {
        this.editmode = editmode;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);
        context= this.getApplicationContext();
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
        
        e_nombre=(EditText)findViewById(R.id.e_nombre);
        e_apellido=(EditText)findViewById(R.id.e_apellidos);
        e_empresa =(EditText)findViewById(R.id.e_empresa);
        e_telefono=(EditText)findViewById(R.id.e_telefono);
        e_direccion=(EditText)findViewById(R.id.e_direccion);
        e_cp=(EditText)findViewById(R.id.e_cp);
        e_ciudad=(EditText)findViewById(R.id.e_ciudad);
        txt_clienteid=(TextView)findViewById(R.id.txtIDCliente);
        
        btn_addcliente=(Button)findViewById(R.id.btn_guardar);
        btn_cancelar=(Button)findViewById(R.id.btn_cancelar);

        btn_addcliente.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

        try {
            Bundle b = getIntent().getExtras();
            setEditmode(b.getBoolean("EDITMODE"));
            setClientID(b.getInt("CLIENTID"));
            setPosicion(b.getInt("POSICION"));
        } catch (NullPointerException e){
            setEditmode(false);
        }

        if(isEditmode()){
            try {
                //servidor=new ServidorPHP();
                ArrayList<Cliente> array_cliente=servidor.obtenerClienteDatos(String.valueOf(getClientID()));
                txt_clienteid.setText(String.valueOf(getClientID()));
                e_nombre.setText(array_cliente.get(0).getNombre());
                e_apellido.setText(array_cliente.get(0).getApellido());
                e_empresa.setText(array_cliente.get(0).getEmpresa());
                e_telefono.setText(array_cliente.get(0).getTelefono());
                e_direccion.setText(array_cliente.get(0).getDireccion());
                e_cp.setText(array_cliente.get(0).getCp());
                e_ciudad.setText(array_cliente.get(0).getCiudad());
/*              e_longitud.setText(array_cliente.get(0).getLongitud());
                e_latitud.setText(array_cliente.get(0).getLatitud());*/


            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar:

                if(e_direccion.getText().toString().length()<1 || e_cp.getText().toString().length()<1 || e_ciudad.getText().toString().length()<1)
                    Toast.makeText(this,"Introduzca la dirección completa (calle, cp, ciudad)",Toast.LENGTH_LONG).show();
                try {
                    servidor = new ServidorPHP();
                    if(isEditmode()) {
                        if(servidor.modificarCliente(txt_clienteid.getText().toString(), e_nombre.getText().toString(),e_apellido.getText().toString(), e_empresa.getText().toString(),e_telefono.getText().toString(),e_direccion.getText().toString(),e_cp.getText().toString(),e_ciudad.getText().toString(),"0","0").compareTo("OK")==0){
                            Toast.makeText(this,"Cliente guardado correctamente",Toast.LENGTH_LONG).show();
                            finish();
                           /* ArrayList clientes=null;
                            try {
                                clientes=servidor.obtenerCliente();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AdaptadorListaClientes.cambiosClientes(clientes);*/
                        }else
                            Toast.makeText(this,"Error al agregar el cliente",Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(servidor.insertarCliente(e_nombre.getText().toString(),e_apellido.getText().toString(), e_empresa.getText().toString(),e_telefono.getText().toString(),e_direccion.getText().toString(),e_cp.getText().toString(),e_ciudad.getText().toString(),"0","0").compareTo("OK")==0){
                            Toast.makeText(this,"Cliente agregado correctamente",Toast.LENGTH_LONG).show();
                            finish();
                           /* ArrayList clientes=null;
                            try {
                                clientes=servidor.obtenerCliente();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AdaptadorListaClientes.cambiosClientes(clientes);*/
                        }else
                            Toast.makeText(this,"Error al agregar el cliente",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_cancelar:
                finish();
                break;
        }
    }
}
