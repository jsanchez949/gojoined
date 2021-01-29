package com.jesus.gojoined;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FormUsuario extends AppCompatActivity implements View.OnClickListener {
    private Button btn_guardar, btn_cancelar;
    private ServidorPHP servidor;
    private EditText e_login,e_passwd,e_nombre,e_apellidos,e_telefono,e_categoria;
    private boolean editmode;
    private String login;
    private int posicion;

    public boolean isEditmode() {
        return editmode;
    }

    public void setEditmode(boolean editmode) {
        this.editmode = editmode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        setContentView(R.layout.activity_form_usuario);

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

        btn_guardar=(Button)findViewById(R.id.btn_guardar);
        btn_cancelar=(Button)findViewById(R.id.btn_cancelar);
        e_login=(EditText)findViewById(R.id.e_login);
        e_passwd=(EditText)findViewById(R.id.e_passwd);
        e_nombre=(EditText)findViewById(R.id.e_nombre);
        e_apellidos=(EditText)findViewById(R.id.e_apellidos);
        e_telefono=(EditText)findViewById(R.id.e_telefono);
        e_categoria=(EditText)findViewById(R.id.e_categoria);
        servidor=new ServidorPHP();
        btn_guardar.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

       try {
            Bundle b = getIntent().getExtras();
            setEditmode(b.getBoolean("EDITMODE"));
            setLogin(b.getString("LOGIN"));
            setPosicion(b.getInt("POSICION"));
        } catch (NullPointerException e){
            setEditmode(false);
        }

        if(isEditmode()){
            try {
                ArrayList<Usuario> array_usuario=servidor.obtenerUsuarioDatos(login);
                e_login.setText(array_usuario.get(0).getLogin());
                e_nombre.setText(array_usuario.get(0).getNombre());
                e_apellidos.setText(array_usuario.get(0).getApellido());
                e_passwd.setText(array_usuario.get(0).getPassword());
                e_telefono.setText(array_usuario.get(0).getTelefono());
                e_categoria.setText(String.valueOf(array_usuario.get(0).getTipousuario()));
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
                try {
                    servidor=new ServidorPHP();
                    if(isEditmode()) {
                        if(servidor.modificarUsuario(e_login.getText().toString(),e_nombre.getText().toString(), e_apellidos.getText().toString(),
                                e_passwd.getText().toString(),e_telefono.getText().toString(),e_categoria.getText().toString()).compareTo("OK")==0){
                            Toast.makeText(this,"Usuario guardado correctamente",Toast.LENGTH_LONG).show();

                        }else
                            Toast.makeText(this,"Error al agregar el usuario",Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(servidor.insertarUsuario(e_login.getText().toString(),e_nombre.getText().toString(), e_apellidos.getText().toString(),
                                e_passwd.getText().toString(),e_telefono.getText().toString(),e_categoria.getText().toString()).compareTo("OK")==0){
                            Toast.makeText(this,"Usuario agregado correctamente",Toast.LENGTH_LONG).show();

                        }else
                            Toast.makeText(this,"Error al agregar el usuario",Toast.LENGTH_LONG).show();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                finish();
                break;
            case R.id.btn_cancelar:
                    finish();
                    break;
        }
    }
}
