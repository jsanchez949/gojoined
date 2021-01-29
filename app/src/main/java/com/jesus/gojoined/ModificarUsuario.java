package com.jesus.gojoined;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class ModificarUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_login,edit_password,edit_usuario,edit_apellidos,edit_telefono,edit_categoria;
    private Button guardar,fab;
    private ServidorPHP servidor;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setLogo(R.drawable.medico2);
        toolbar.setNavigationIcon(R.drawable.arrowleft);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        servidor=new ServidorPHP();

        edit_login=(EditText)findViewById(R.id.e_login);
        edit_usuario=(EditText)findViewById(R.id.e_nombre);
        edit_apellidos=(EditText)findViewById(R.id.e_apellidos);
        edit_password=(EditText)findViewById(R.id.e_passwd);
        edit_telefono=(EditText)findViewById(R.id.e_telefono);
        edit_categoria=(EditText)findViewById(R.id.e_categoria);

        guardar=(Button)findViewById(R.id.btn_guardar);

        guardar.setOnClickListener(this);

        String login=this.getIntent().getExtras().getString("Login");
        String nombre=this.getIntent().getExtras().getString("Nombre");
        String apellido=this.getIntent().getExtras().getString("Apellidos");
        String passwd=this.getIntent().getExtras().getString("Password");
        String telefono=this.getIntent().getExtras().getString("Telefono");
        String categoria=this.getIntent().getExtras().getString("Categoria");

        edit_login.setText(login);
        edit_usuario.setText(nombre);
        edit_apellidos.setText(apellido);
        edit_password.setText(passwd);
        edit_telefono.setText(telefono);
        edit_categoria.setText(categoria);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar:
                try {

                    if( servidor.modificarUsuario(edit_login.getText().toString(),edit_usuario.getText().toString()
                            ,edit_apellidos.getText().toString(),edit_password.getText().toString(),edit_telefono.getText().toString(),edit_categoria.getText().toString()).compareTo("OK")==0){
                        Toast.makeText(this,"Has modifcado al usuario "+edit_usuario.getText().toString(),Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(this,"Error al modificar el usuario "+edit_usuario.getText().toString(),Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.home:
                finish();
        }
    }
}
