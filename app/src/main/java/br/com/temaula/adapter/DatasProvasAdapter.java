package usuario.app.temaula.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.DataProva;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class DatasProvasAdapter extends BaseAdapter {

    private final int JAN=1,FEV=2,MAR=3,ABR=4,MAI=5,JUN=6,JUL=7,AGO=8,SET=9,OUT=10,NOV=11,DEZ=12;
    private int[] MESES = {JAN,FEV,MAR,ABR,MAI,JUN,JUL,AGO,SET,OUT,NOV,DEZ};
    Context context;
    List<DataProva> listdataprova = new ArrayList<>();

    public DatasProvasAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return listdataprova.size();
    }

    @Override
    public Object getItem(int position) {
        return listdataprova.get(position);
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
            view = inflater.inflate(R.layout.item_data_prova,parent,false);
            holder = new ViewHolder();
            holder.txtdata = (TextView) view.findViewById(R.id.data_avaliacao);
            holder.txttipo = (TextView) view.findViewById(R.id.tipo_avaliacao);
            holder.txtdisciplina = (TextView) view.findViewById(R.id.disciplina_avaliacao);
            holder.txtcontagem = (TextView) view.findViewById(R.id.contagem_dias_avaliacao);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        DataProva dataprova = listdataprova.get(position);
        String data = dataprova.getData_avaliacao();

        String dia = data.substring(0,2);
        String mes = data.substring(3,6);


        if(dataprova.getTipo_avaliacao().equals("Case")){
            holder.txtdata.setBackgroundResource(R.drawable.bg_case);
        }

        holder.txtdata.setText(dia+"\n"+mes);
        holder.txtdisciplina.setText(dataprova.getDisciplina_avaliacao());
        holder.txttipo.setText(dataprova.getTipo_avaliacao());
        holder.txtcontagem.setText("Restam: "+String.valueOf(dataprova.getContagem_dias())+" dias");

        return view;
    }

    public void setItems(List<DataProva> datasprovas) {
        this.listdataprova = datasprovas;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txtdata;
        public TextView txttipo;
        public TextView txtdisciplina;
        public TextView txtcontagem;
    }
}
