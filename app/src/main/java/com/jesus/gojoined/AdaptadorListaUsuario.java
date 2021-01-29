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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jesus on 03/06/2016.
 */
public class AdaptadorListaUsuario extends RecyclerView.Adapter<AdaptadorListaUsuario.ListaUsuariosHolder> {

    private static ArrayList<Usuario> array_usuarios;
    private static Context contexto;
    private String categoriausuario;
    private static ServidorPHP servidor;
    private ListaUsuarios lista;
    private static int activeposicion;

    public AdaptadorListaUsuario(ArrayList<Usuario> array_usuarios, Context contexto, String categoriausuario){
        servidor=new ServidorPHP();
        this.array_usuarios=array_usuarios;
        this.contexto=contexto;
        this.categoriausuario=categoriausuario;
    }

    @Override
    public ListaUsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuariolista,parent,false);
        ListaUsuariosHolder holder=new ListaUsuariosHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListaUsuariosHolder holder, int position) {
        holder.nombre.setText(array_usuarios.get(position).getNombre());
        holder.apellido.setText(array_usuarios.get(position).getApellido());
        holder.telefono.setText(array_usuarios.get(position).getTelefono());
        holder.tipousuario.setText(array_usuarios.get(position).getTiposUsers(array_usuarios.get(position).getTipousuario()));
        holder.login.setText(array_usuarios.get(position).getLogin());

        System.out.println("this is login: " + array_usuarios.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return array_usuarios.size();
    }

    public class ListaUsuariosHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView login, nombre, apellido, telefono, tipousuario;

        ListaUsuariosHolder(final View item) {
            super(item);

            nombre = (TextView) item.findViewById(R.id.txtNombre);
            apellido = (TextView) item.findViewById(R.id.txtApellido);
            telefono = (TextView) item.findViewById(R.id.txtTelefono);
            tipousuario = (TextView) item.findViewById(R.id.txtTipoUsuario);
            login = (TextView) item.findViewById(R.id.txtLogin);

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
                AdaptadorListaUsuario.activeposicion=getAdapterPosition();
                switch (item.getItemId()) {
                    case 1:
                        System.out.println("" + item.getMenuInfo());
                        System.out.println("" + item.getIntent());
                        System.out.println("llamar a ");

                        Toast.makeText(contexto,"Vas a llamar a "+nombre.getText().toString() +" "+apellido.getText().toString(),Toast.LENGTH_LONG).show();
                        Intent i_llamar = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",telefono.getText().toString(),null));
                        contexto.startActivity(i_llamar);

                        break;
                    case 2:
                        Intent i = new Intent(contexto, FormUsuario.class);
                        i.putExtra("EDITMODE",true);
                        i.putExtra("LOGIN",login.getText());
                        i.putExtra("POSICION",getAdapterPosition());
                        contexto.startActivity(i);
                        break;
                    case 3:
                        try {
                            servidor.eliminarUser(login.getText().toString());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(itemView.getContext(), "Has eliminado correctamente al usuario"+nombre.getText().toString(), Toast.LENGTH_SHORT).show();

                        ArrayList temparray_usuarios=null;

                        try {
                            temparray_usuarios=servidor.obtenerUsuarios();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        AdaptadorListaUsuario.array_usuarios.clear();
                        AdaptadorListaUsuario.array_usuarios.addAll(temparray_usuarios);
                        AdaptadorListaUsuario.this.notifyDataSetChanged();

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

    public void cambiosUsuarios(ArrayList<Usuario> array_usuarios){
        this.array_usuarios.clear();
        this.array_usuarios.addAll(array_usuarios);
        this.notifyDataSetChanged();
//        this.notifyItemChanged(AdaptadorListaUsuario.activeposicion);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
