package usuario.app.temaula.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.Aviso;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class AvisosAdapter extends BaseAdapter {

    Context context;
    List<Aviso> listavisos = new ArrayList<>();

    public AvisosAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return listavisos.size();
    }

    @Override
    public Object getItem(int position) {
        return listavisos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_aviso,parent,false);
            holder = new ViewHolder();
            holder.txttitulo = (TextView) view.findViewById(R.id.titulo_aviso);
            holder.prioridade = (ImageView) view.findViewById(R.id.tipo_prioridade);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Aviso aviso = listavisos.get(position);
        holder.txttitulo.setText(aviso.getTitulo());
        holder.prioridade.setImageResource(aviso.getIc_prioridade());

        return view;
    }

    public void setItems(List<Aviso> listavisos) {
        this.listavisos = listavisos;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txttitulo;
        public ImageView prioridade;
    }
}
