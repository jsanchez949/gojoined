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
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jesus on 13/11/2016.
 */
public class FormCita extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner_usuario;
    private Spinner spinner_cliente;
    //private ArrayList<String> array_categorias;
    private ArrayList<Cita> array_nombres;
    private EditText e_nombre,e_telefono,e_fecha,e_tarea,e_cobro,e_direccion;
    private Button btn_guardar;
    private Button btn_cancelar;
    private ServidorPHP servidor;
    private Context context;
    private String string_snombre="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cita);
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
        e_fecha=(EditText)findViewById(R.id.e_fecha);
        e_tarea =(EditText)findViewById(R.id.e_tarea);
        e_cobro=(EditText)findViewById(R.id.e_cobro);
        btn_guardar=(Button)findViewById(R.id.btn_guardar);
        btn_cancelar=(Button)findViewById(R.id.btn_cancelar);

        spinner_usuario=(Spinner)findViewById(R.id.spinner_usuario);
        spinner_cliente=(Spinner)findViewById(R.id.spinner_cliente);
        servidor=new ServidorPHP();

        ArrayList array_usuarios=new ArrayList<Usuario>();
        ArrayList array_clientes=new ArrayList<Cliente>();

        try {
            array_usuarios=servidor.obtenerUsuarios();
            array_usuarios=servidor.obtenerCliente();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rellenarSpinner(spinner_usuario,array_usuarios,context);
        rellenarSpinner(spinner_cliente,array_clientes,context);

        btn_guardar.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

        spinner_usuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void rellenarSpinner(Spinner spinner, ArrayList<Cita> array, Context contexto){
        ArrayList<String> vacio = new ArrayList<>();

        ArrayAdapter spinner_adapter=new ArrayAdapter(contexto,android.R.layout.simple_spinner_item,vacio);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);

        ArrayAdapter spinner_lleno=new ArrayAdapter(contexto,android.R.layout.simple_spinner_item,array);

        spinner_lleno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_lleno);

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
                System.out.println(spinner_usuario.getSelectedItem().toString());
                System.out.println(spinner_usuario.getSelectedItemPosition());
               /* try {

                    if(servidor.insertarCita(spinner_usuario.getSelectedItem().toString(),
                            e_telefono.getText().toString(),e_fecha.getText().toString(),e_tarea.getText().toString(),
                            e_cobro.getText().toString()).compareTo("OK")==0){
                        Toast.makeText(this,"Registrado correctamente",Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(this,"Error al insertar la visita",Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                break;
            case R.id.btn_cancelar:
                finish();
                break;
        }
    }
}
