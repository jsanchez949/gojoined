package com.jesus.gojoined;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private static Button boton_entrar;
    //private String usuario,passwd;
    private EditText usuario,passwd;
    private ServidorPHP servidor;
    //private AjustesTema tema;

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //tema.preferenciasTema();
        //cargarPreferencias();

        servidor=new ServidorPHP();

        usuario=(EditText)findViewById(R.id.txtusuario);
        passwd=(EditText)findViewById(R.id.txtpassword);

        boton_entrar=(Button)findViewById(R.id.btn_entrar);
        boton_entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tipodeusuario="";
                int nivelUsuario=9;
                ArrayList<Usuario> datosusuario = null;
                String strusuario=usuario.getText().toString();
                String strpasswd=passwd.getText().toString();

                try {
                    if (servidor.Login(strusuario, strpasswd)) {
                        tipodeusuario = servidor.comprobarTipoUsuario(usuario.getText().toString());
                        datosusuario = servidor.obtenerUsuarioDatos(strusuario);

                        if (tipodeusuario.equals("0")) {
                            nivelUsuario = 0; //'Nivel 0=admin, Nivel 9=usuario normal'
                        }
                        Intent i = new Intent(LoginActivity.this, NavigationDrawer.class);
                        i.putExtra("nivelUsuario", nivelUsuario);
                        i.putExtra("nombreUsuario", datosusuario.get(0).getNombre());
                        i.putExtra("apellidoUsuario", datosusuario.get(0).getApellido());
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error al introducir usuario o contraseña", Toast.LENGTH_SHORT).show();
                    }
                }

                /*        if (tipodeusuario.compareTo("Administrador")==0) {
                            Intent i = new Intent(LoginActivity.this, NavigationDrawer.class);
                            startActivity(i);
                        }else{
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }*/


                //Guardar preferencias
//                        Toast.makeText(getApplicationContext(), "Login Test", Toast.LENGTH_SHORT).show();
                    /*}else{
                        Toast.makeText(getApplicationContext(), "Error al introducir usuario o contraseña", Toast.LENGTH_SHORT).show();
                        }*/

                catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        /*boton_entrar = (Button)findViewById(R.id.btn_entrar);
        boton_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusuario;
                String strpasswd;

                strusuario = ((EditText)findViewById(R.id.txtusuario)).getText().toString();
                strpasswd = ((EditText)findViewById(R.id.txtpassword)).getText().toString();
                if (strusuario.equals("admin") && strpasswd.equals("admin")){
                    Intent i = new Intent(LoginActivity.this, NavigationDrawer.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Error al introducir usuario o contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

}

