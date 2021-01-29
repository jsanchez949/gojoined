package com.jesus.gojoined;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jesus on 13/11/2016.
 */
public class AdaptadorListaClientes extends RecyclerView.Adapter<AdaptadorListaClientes.ListaClientesHolder> {
    private static ServidorPHP servidor;
    private static ArrayList<Cliente> array_clientes;
    private static Context contexto;
    private static int activeposicion;
//    private MapsActivity mapa;
    public AdaptadorListaClientes(ArrayList<Cliente> array_clientes, Context contexto){
        servidor=new ServidorPHP();
        this.contexto=contexto;
        this.array_clientes=array_clientes;
    }

    public Context getContexto() {
        return this.contexto;
    }

    @Override
    public ListaClientesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.clientelista,parent,false);
        ListaClientesHolder holder=new ListaClientesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListaClientesHolder holder, int position) {
        holder.nombre.setText(array_clientes.get(position).getNombre());
        holder.apellido.setText(array_clientes.get(position).getApellido());
        holder.empresa.setText(array_clientes.get(position).getEmpresa());

        holder.telefono.setText(array_clientes.get(position).getTelefono());

        if(array_clientes.get(position).getNombre().length()==0)
            holder.nombre.setVisibility(View.GONE);

        if(array_clientes.get(position).getApellido().length()==0)
            holder.apellido.setVisibility(View.GONE);

        if(array_clientes.get(position).getEmpresa().length()==0)
            holder.empresa.setVisibility(View.GONE);

        String tempdireccion;
        tempdireccion=array_clientes.get(position).getDireccion();
        if(array_clientes.get(position).getCp().toString().length()>0){
            tempdireccion=tempdireccion+", "+array_clientes.get(position).getCp().toString();
        }
        if(array_clientes.get(position).getCiudad().toString().length()>0){
            tempdireccion=tempdireccion+", "+array_clientes.get(position).getCiudad().toString();
        }

        holder.direccion.setText(tempdireccion);
        holder.idcliente.setText(String.valueOf(array_clientes.get(position).getId()));

        /*        holder.direccion.setText(array_clientes.get(position).getCp());
        holder.direccion.setText(array_clientes.get(position).getCiudad());
        holder.direccion.setText(String.valueOf(array_clientes.get(position).getLongitud()));
        holder.direccion.setText(String.valueOf(array_clientes.get(position).getLatitud()));*/

        final int idcliente=array_clientes.get(position).getId();
        final String nombre=array_clientes.get(position).getNombre();
        final String apellido=array_clientes.get(position).getNombre();
        final String telefono=array_clientes.get(position).getTelefono();
        final String direccion=array_clientes.get(position).getDireccion();

/*        final String cp=array_clientes.get(position).getCp();
        final String ciudad=array_clientes.get(position).getCiudad();
        final String longitud=String.valueOf(array_clientes.get(position).getLongitud());
        final String latitud=String.valueOf(array_clientes.get(position).getLatitud());*/



        /*holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Has eliminado la visita "+idcliente);
                Toast.makeText(contexto,"Has eliminado la visita del usuario "+nombre,Toast.LENGTH_LONG).show();
                try {
                    servidor.eliminarCliente(idcliente);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return array_clientes.size();
    }

    public class ListaClientesHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView idcliente, nombre, apellido, empresa, telefono,direccion;
//        ImageButton modificar,eliminar,maps;

        public ListaClientesHolder(View item){
            super(item);
            nombre=(TextView) item.findViewById(R.id.txt_nomCliente);
            apellido=(TextView) item.findViewById(R.id.txt_appelidoCliente);
            empresa=(TextView) item.findViewById(R.id.txt_empresa);
            telefono=(TextView)item.findViewById(R.id.txt_telefono);
            direccion=(TextView)item.findViewById(R.id.txt_direccion);
            idcliente=(TextView)item.findViewById(R.id.txt_idcliente);

            item.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("-Opciones-");

            MenuItem Call = contextMenu.add(contextMenu.NONE, 1, 0, "Llamar");

            if(NavigationDrawer.getTipousuario()==0) {
                MenuItem Edit = contextMenu.add(contextMenu.NONE, 2, 0, "Editar");
                MenuItem Delete = contextMenu.add(contextMenu.NONE, 3, 0, "Borrar");
                Edit.setOnMenuItemClickListener(onContextMenu);
                Delete.setOnMenuItemClickListener(onContextMenu);
            }
            MenuItem Cancel = contextMenu.add(contextMenu.NONE, 4, 0, "Cancelar");

            Call.setOnMenuItemClickListener(onContextMenu);
            Cancel.setOnMenuItemClickListener(onContextMenu);
        }

        private final MenuItem.OnMenuItemClickListener onContextMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdaptadorListaClientes.activeposicion=getAdapterPosition();
                switch (item.getItemId()) {
                    case 1:
                        System.out.println("" + item.getMenuInfo());
                        System.out.println("" + item.getIntent());
                        System.out.println("llamar a ");

                        //Toast.makeText(contexto,"Vas a llamar a "+nombre.getText().toString() +" "+apellido.getText().toString(),Toast.LENGTH_LONG).show();
                        Intent i_llamar = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",telefono.getText().toString(),null));
                        contexto.startActivity(i_llamar);

                        break;
                    case 2:
                        Intent i = new Intent(contexto, FormCliente.class);
                        i.putExtra("EDITMODE",true);
                        i.putExtra("CLIENTID",Integer.valueOf(String.valueOf(idcliente.getText())));
                        i.putExtra("POSICION",getAdapterPosition());
                        contexto.startActivity(i);
                        break;
                    case 3:
                        try {
                            servidor.eliminarCliente(idcliente.getText().toString());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(itemView.getContext(), "Has eliminado correctamente al cliente", Toast.LENGTH_SHORT).show();

                        ArrayList temparray_clientes=null;

                        try {
                            temparray_clientes=servidor.obtenerCliente();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        AdaptadorListaClientes.array_clientes.clear();
                        AdaptadorListaClientes.array_clientes.addAll(temparray_clientes);
                        AdaptadorListaClientes.this.notifyDataSetChanged();

                        System.out.println("borrar usuario");
                        break;
                    /*
             holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lista.onBackPressed();
                System.out.println("Has eliminado al usuario "+login);
                Toast.makeText(contexto,"Has eliminado al usuario "+nombre,Toast.LENGTH_LONG).show();
                try {
                    servidor.eliminarUser(login);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
                   */
                    case 4:
                        break;
                    default:
                        System.out.println("nada hecho");
                        return true;
                }
                return true;
            }
        };
    }

    public void AgregarClientes(ArrayList<Cliente> array_clientes){
        this.array_clientes.clear();
        this.array_clientes.addAll(array_clientes);
    }

    public void cambiosClientes(ArrayList<Cliente> array_clientes){
        this.array_clientes.clear();
        this.array_clientes.addAll(array_clientes);
        this.notifyDataSetChanged();
//        this.notifyItemChanged(AdaptadorListaUsuario.activeposicion);
    }

    public void refrescar(){notifyDataSetChanged();}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
