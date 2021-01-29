package com.jesus.gojoined;

/**
 * Created by Jesus on 26/05/2016.
 */

import android.provider.Settings;
import android.support.v4.util.Pair;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
/**
 * Created by Jesus on 13/05/2016.
 */
public class ServidorPHP {
    private static String servidor="http://www.homehealth.esy.es/";
    private static String urlLogin=servidor+"login.php";
    private static String urlobtenerusuarios=servidor+"obtenerUsuarios.php";
    private static String urlobtenerusuariodatos=servidor+"obtenerUsuariosDatos.php";
    private static String urlobtenercliente=servidor+"obtenerCliente.php";
    private static String urlobtenerclientedatos=servidor+"obtenerClienteDatos.php";
    private static String urlobtenercita=servidor+"obtenerCita.php";
    private static String urleliminarusuario=servidor+"eliminarUsuario.php";
    private static String urleliminarcliente=servidor+"eliminarCliente.php";
    private static String urleliminarcita=servidor+"eliminarCita.php";
    private static String urlcomprobarUsuario=servidor+"comprobarTipoUsuario.php";
    private static String urlmodificarUsuario=servidor+"modificarUsuario.php";
    private static String urlmodificarCliente=servidor+"modificarCliente.php";
    private static String urlmodificarCita=servidor+"modificarCita.php";
    private static String urlinsertarusuario=servidor+"insertarUsuario.php";
    private static String urlinsertarcliente=servidor+"insertarCliente.php";
    private static String urlinsertarCita=servidor+"insertarCliente.php";
    private static String urlobtenerusuariocategoria=servidor+"obtenerUsuarioCategoria.php";

    private static final String TAG = ServidorPHP.class.getSimpleName();

    public ServidorPHP(){

    }

    public boolean Login(String login, String password) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        boolean resultado=true;

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",login));
        parametros.add(new Pair<>("password",password));
        datos=j.getJSONArrayFromUrl(urlLogin,parametros);

        String valor=datos.getJSONObject(0).getString("resultadoLogin");
        Log.d(TAG, "testeo");
        Log.d(TAG, valor);

        if(valor.equals("0")){
            resultado=false;
        }

        return resultado;
    }

    public ArrayList<Usuario> obtenerUsuarios() throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        System.out.println(urlobtenerusuarios);
        datos=parser.getJSONArrayFromUrl(urlobtenerusuarios,null);

        for(int i=0; i<datos.length();i++){
            Usuario u = new Usuario();
            u.setLogin(datos.getJSONObject(i).getString("login"));
            u.setNombre(datos.getJSONObject(i).getString("nombre"));
            u.setApellido(datos.getJSONObject(i).getString("apellido"));
            u.setTelefono(datos.getJSONObject(i).getString("telefono"));
            u.setTipousuario(datos.getJSONObject(i).getInt("tipousuario"));
//            u.setPassword(datos.getJSONObject(i).getString("password"));

            arrayUsuarios.add(u);
        }
        return arrayUsuarios;
    }

    public ArrayList<Usuario> obtenerUsuarioDatos(String login) throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Usuario> arrayUser = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",login+""));
        datos=parser.getJSONArrayFromUrl(urlobtenerusuariodatos,parametros);

        for(int i=0; i<datos.length();i++){
            Usuario u = new Usuario();
            u.setLogin(datos.getJSONObject(i).getString("login"));
            u.setNombre(datos.getJSONObject(i).getString("nombre"));
            u.setApellido(datos.getJSONObject(i).getString("apellido"));
            u.setTelefono(datos.getJSONObject(i).getString("telefono"));
            u.setTipousuario(datos.getJSONObject(i).getInt("tipousuario"));
            u.setPassword(datos.getJSONObject(i).getString("password"));

            arrayUser.add(u);
        }
        return arrayUser;
    }

    public ArrayList<Cliente> obtenerCliente() throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Cliente> arrayClientes = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        datos=parser.getJSONArrayFromUrl(urlobtenercliente,null);

        for(int i=0; i<datos.length();i++){
            Cliente c = new Cliente();
            c.setId(datos.getJSONObject(i).getInt("id"));
            c.setNombre(datos.getJSONObject(i).getString("nombre"));
            c.setApellido(datos.getJSONObject(i).getString("apellido"));
            c.setEmpresa(datos.getJSONObject(i).getString("empresa"));
            c.setTelefono(datos.getJSONObject(i).getString("telefono"));
            c.setDireccion(datos.getJSONObject(i).getString("direccion"));
            c.setCp(datos.getJSONObject(i).getString("cp"));
            c.setCiudad(datos.getJSONObject(i).getString("ciudad"));
            c.setLongitud(Float.parseFloat(datos.getJSONObject(i).getString("latitud")));
            c.setLatitud(Float.parseFloat(datos.getJSONObject(i).getString("longitud")));
            arrayClientes.add(c);
        }
        return arrayClientes;
    }

    public ArrayList<Cliente> obtenerClienteDatos(String clienteID) throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Cliente> arrayClientes = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("id",clienteID+""));
        datos=parser.getJSONArrayFromUrl(urlobtenerclientedatos,parametros);

        for(int i=0; i<datos.length();i++){
            Cliente c = new Cliente();
            c.setId(datos.getJSONObject(i).getInt("id"));
            c.setNombre(datos.getJSONObject(i).getString("nombre"));
            c.setApellido(datos.getJSONObject(i).getString("apellido"));
            c.setEmpresa(datos.getJSONObject(i).getString("empresa"));
            c.setTelefono(datos.getJSONObject(i).getString("telefono"));
            c.setDireccion(datos.getJSONObject(i).getString("direccion"));
            c.setCp(datos.getJSONObject(i).getString("cp"));
            c.setCiudad(datos.getJSONObject(i).getString("ciudad"));
            /*c.setLongitud(Float.parseFloat(datos.getJSONObject(i).getString("latitud")));
            c.setLatitud(Float.parseFloat(datos.getJSONObject(i).getString("longitud")));*/
            arrayClientes.add(c);
        }
        return arrayClientes;
    }

    public ArrayList<Cita> obtenerCita() throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Cita> arrayVisitas = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        datos=parser.getJSONArrayFromUrl(urlobtenercita,null);

        for(int i=0; i<datos.length();i++){
            Cita v = new Cita();
            v.setId(datos.getJSONObject(i).getInt("entradaid"));
            v.setNombre(datos.getJSONObject(i).getString("idusuario"));
            v.setNombre(datos.getJSONObject(i).getString("idcliente"));
            v.setFecha(datos.getJSONObject(i).getString("fecha"));
            v.setTarea(datos.getJSONObject(i).getString("tarea"));
            v.setCobro(datos.getJSONObject(i).getString("cobro"));
            v.setCobro(datos.getJSONObject(i).getString("estado"));

            arrayVisitas.add(v);
        }
        return arrayVisitas;
    }


    public String eliminarUser(String login) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",login+""));
        datos=j.getJSONArrayFromUrl(urleliminarusuario,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }

    public String eliminarCliente(String id) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("id",id+""));
        datos=j.getJSONArrayFromUrl(urleliminarcliente,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }
    public String eliminarCita(int id) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("id",id+""));
        datos=j.getJSONArrayFromUrl(urleliminarcita,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }


    public String comprobarTipoUsuario(String nombre) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",nombre));
        datos=j.getJSONArrayFromUrl(urlcomprobarUsuario,parametros);

        resultado=datos.getJSONObject(0).getString("tipousuario");

        return resultado;
    }

    public String modificarUsuario(String login, String nombre, String apellido, String password, String telefono, String tipousuario) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",login));
        parametros.add(new Pair<>("nombre",nombre));
        parametros.add(new Pair<>("apellido",apellido));
        parametros.add(new Pair<>("password",password));
        parametros.add(new Pair<>("telefono",telefono));
        parametros.add(new Pair<>("tipousuario",tipousuario));
        datos=j.getJSONArrayFromUrl(urlmodificarUsuario,parametros);
        System.out.println("Datos:" +login+nombre+apellido+password+telefono+tipousuario);
        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }
    public String modificarCliente(String id,String nombre, String apellido, String empresa, String telefono,String direccion,String cp, String ciudad,String longitud, String latitud) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("id",id+""));
        parametros.add(new Pair<>("nombre",nombre));
        parametros.add(new Pair<>("apellido",apellido));
        parametros.add(new Pair<>("empresa",empresa));
        parametros.add(new Pair<>("telefono",telefono));
        parametros.add(new Pair<>("direccion",direccion));
        parametros.add(new Pair<>("cp",cp));
        parametros.add(new Pair<>("ciudad",ciudad));
        parametros.add(new Pair<>("longitud",longitud));
        parametros.add(new Pair<>("latitud",latitud));
        datos=j.getJSONArrayFromUrl(urlmodificarCliente,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }

    public String modificarCita(int entradaid,int idusuario, int idcliente, String fecha, String tarea,String cobro,String estado) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("id",entradaid+""));
        parametros.add(new Pair<>("id",idusuario+""));
        parametros.add(new Pair<>("id",idcliente+""));
        parametros.add(new Pair<>("fecha",fecha));
        parametros.add(new Pair<>("tarea",tarea));
        parametros.add(new Pair<>("cobro",cobro));
        parametros.add(new Pair<>("estado",estado));
        datos=j.getJSONArrayFromUrl(urlmodificarCita,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }

    public String insertarUsuario(String login,String nombre,String apellido,String telefono,String password,String tipousuario) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("login",login));
        parametros.add(new Pair<>("nombre",nombre));
        parametros.add(new Pair<>("apellido",apellido));
        parametros.add(new Pair<>("telefono",telefono));
        parametros.add(new Pair<>("password",password));
        parametros.add(new Pair<>("tipousuario",tipousuario));
        datos=j.getJSONArrayFromUrl(urlinsertarusuario,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }
    public String insertarCliente(String nombre, String apellido, String empresa, String telefono,String direccion,String cp, String ciudad, String longitud, String latitud) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";

        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("nombre",nombre));
        parametros.add(new Pair<>("apellido",apellido));
        parametros.add(new Pair<>("empresa",empresa));
        parametros.add(new Pair<>("telefono",telefono));
        parametros.add(new Pair<>("direccion",direccion));
        parametros.add(new Pair<>("cp",cp));
        parametros.add(new Pair<>("ciudad",ciudad));
        parametros.add(new Pair<>("longitud",longitud));
        parametros.add(new Pair<>("latitud",latitud));
        datos=j.getJSONArrayFromUrl(urlinsertarcliente,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }

    public String insertarCita(String idusuario, String idcliente, String fecha,  String tarea, String cobro) throws ExecutionException, InterruptedException, JSONException {
        Json j = new Json();
        String resultado="";
        JSONArray datos;
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("idusuario",String.valueOf(idusuario)));
        parametros.add(new Pair<>("idcliente",String.valueOf(idcliente)));
        parametros.add(new Pair<>("fecha",fecha));
        parametros.add(new Pair<>("tarea",tarea));
        parametros.add(new Pair<>("cobro",cobro));
        parametros.add(new Pair<>("estado","0"));
        datos=j.getJSONArrayFromUrl(urlinsertarCita,parametros);

        resultado=datos.getJSONObject(0).getString("resultado");

        return resultado;
    }

    public ArrayList<String> obtenerUsuarioCategoria(String categoria) throws ExecutionException, InterruptedException, JSONException {
        ArrayList<String> arraynombres = new ArrayList<>();
        JSONArray datos;
        Json parser = new Json();
        ArrayList<Pair<String,String>> parametros=new ArrayList<>();
        parametros.add(new Pair<>("tipousuario",categoria));
        datos=parser.getJSONArrayFromUrl(urlobtenerusuariocategoria,parametros);

        for(int i=0; i<datos.length();i++){

            arraynombres.add(datos.getJSONObject(i).getString("nombre"));
        }
        return arraynombres;
    }
}
