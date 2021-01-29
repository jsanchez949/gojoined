package com.jesus.gojoined;

import android.os.AsyncTask;
import android.support.v4.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jesus on 13/05/2016.
 */
public class Json {

    private class HttpPostJSONArrayClass extends AsyncTask<String, String, JSONArray>{
        private String url;
        private ArrayList<Pair<String,String>> parametros;

        public HttpPostJSONArrayClass(String url,ArrayList<Pair<String,String>>parametros){
            this.url=url;
            this.parametros=parametros;
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            StringBuilder responseOutput=new StringBuilder();
            JSONArray jsonArray=null;
            try{
                //Obtengo los parametros para la URL en caso de que haya
                String parametrosfinal="";
                if(parametros!=null){
                    for(int i=0;i<parametros.size();i++)
                    {
                        parametrosfinal+=(parametros.get(i).first+"="+parametros.get(i).second);
                        //El simbolo & sirve para poner mas de un parametro
                        if(i<parametros.size()-1){
                            parametrosfinal+="&";
                        }
                    }
                }
                //Conecto via HTTP POST
                URL url2=new URL(this.url);
                HttpURLConnection connection=(HttpURLConnection)url2.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);
                DataOutputStream dStream= new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(parametrosfinal);
                dStream.flush();
                dStream.close();
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                while ((line=br.readLine())!=null)
                {
                    responseOutput.append(line);
                }
                br.close();
                //Recogemos los datos devueltos por el post y convertidos a string y los devolvemos como un objeto JSONArray
                jsonArray = new JSONArray(responseOutput.toString());
            }catch(IOException|JSONException e){
                System.out.println("Error "+e.toString());
            }


            return jsonArray;
        }
    }

    public JSONArray getJSONArrayFromUrl(String url, ArrayList<Pair<String,String>>parametros) throws ExecutionException, InterruptedException {
        return new HttpPostJSONArrayClass(url,parametros).execute().get();
    }
}