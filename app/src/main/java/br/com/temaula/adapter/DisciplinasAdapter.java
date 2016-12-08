package usuario.app.temaula.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.Disciplinas;

/**
 * Created by Denis on 09/01/2016.
 */
public class DisciplinasAdapter extends BaseAdapter {

    private Context context;
    private List<Disciplinas> disciplinas = new ArrayList<>();

    public DisciplinasAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return disciplinas.size();
    }

    @Override
    public Object getItem(int position) {
        return disciplinas.get(position);
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
            view = inflater.inflate(R.layout.lista_disciplinas,parent,false);
            holder = new ViewHolder();
            holder.txtdisciplina = (TextView) view.findViewById(R.id.txt_disciplina);
            holder.txtprofessor = (TextView) view.findViewById(R.id.txt_professor);
            holder.txttipodisciplina = (TextView) view.findViewById(R.id.txt_tipo_discplina);
            holder.cor_disciplina_lista = (LinearLayout) view.findViewById(R.id.barra_lateral_lista);
            holder.imageDisciplina = (ImageView) view.findViewById(R.id.imageDisciplina);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Disciplinas disciplina = disciplinas.get(position);
        holder.txtdisciplina.setText(disciplina.getNome_disciplina());
        holder.txtprofessor.setText("Professor(a): "+disciplina.getNome_professor());
        holder.txttipodisciplina.setText("Tipo Disciplina: "+disciplina.getTipo_disciplina());
        holder.cor_disciplina_lista.setBackgroundColor(disciplina.getColor_id());
        holder.imageDisciplina.setImageResource(R.drawable.book32px);

        return view;
    }

    public void setItems(List<Disciplinas> disciplinas) {
        this.disciplinas = disciplinas;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txtdisciplina;
        public TextView txtprofessor;
        public TextView txttipodisciplina;
        public LinearLayout cor_disciplina_lista;
        public ImageView imageDisciplina;
    }
}
