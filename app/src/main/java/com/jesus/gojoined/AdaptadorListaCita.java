package com.jesus.gojoined;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by jesus on 13/11/2016.
 */public class AdaptadorListaCita extends RecyclerView.Adapter<AdaptadorListaCita.ListaVisitasHolder>{
    private ServidorPHP servidor;
    private ArrayList<Cita> array_visitas;
    private Context contexto;
//  private MapsActivity mapa;


    public AdaptadorListaCita(ArrayList<Cita> array_visitas, Context contexto){
        servidor=new ServidorPHP();
        this.array_visitas = array_visitas;
        this.contexto=contexto;
    }

    @Override
    public ListaVisitasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.citaslista,parent,false);
        ListaVisitasHolder holder=new ListaVisitasHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ListaVisitasHolder holder, int position) {
        holder.nombre.setText(array_visitas.get(position).getNombre());
        holder.telefono.setText(array_visitas.get(position).getTelefono());
        holder.fecha.setText(array_visitas.get(position).getFecha());
        holder.tarea.setText(array_visitas.get(position).getTarea());
        holder.cobro.setText(array_visitas.get(position).getCobro());
    }

    @Override
    public int getItemCount() {
        return array_visitas.size();
    }

    public class ListaVisitasHolder extends RecyclerView.ViewHolder{
        TextView nombre,telefono,fecha,tarea,cobro,direccion;
        ImageButton modificar,eliminar,maps;

        public ListaVisitasHolder(final View item){
            super(item);
            nombre=(TextView) item.findViewById(R.id.txt_nomCliente);
            telefono=(TextView)item.findViewById(R.id.txtTelefono);
            fecha=(TextView) item.findViewById(R.id.txt_fecha);
            tarea=(TextView) item.findViewById(R.id.txt_tarea);
            cobro=(TextView)item.findViewById(R.id.txt_cobro);
            direccion=(TextView)item.findViewById(R.id.txt_direccion);
/*            modificar=(ImageButton) item.findViewById(R.id.modificar);
            eliminar=(ImageButton)item.findViewById(R.id.eliminar);
//            maps=(ImageButton) item.findViewById(R.id.maps);*/

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i = new Intent(AdaptadorListaCita.this.contexto, CitaDetail.class);
                    i.putExtra("IDUsuario", "1245");
                    i.putExtra("nombreUsuario",  (String) nombre.getText());
                    contexto.startActivity(i);
                }
            });
        }
    }

    public void AgregarClientes(ArrayList<Cliente> array_clientes){
        this.array_visitas.clear();
        this.array_visitas.addAll(array_visitas);
    }

    public void refrescar(){notifyDataSetChanged();}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
