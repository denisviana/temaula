package usuario.app.temaula.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.Disciplinas;

/**
 * Created by Denis on 15/01/2016.
 */
public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<Disciplinas> disciplina;

    public SpinnerAdapter (Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return disciplina.size();
    }

    @Override
    public Object getItem(int position) {

        return disciplina.get(position);
    }

    @Override
    public long getItemId(int position) {
        Disciplinas disciplinas = this.disciplina.get(position);
        return disciplinas.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if(holder==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_spiner,parent,false);
            holder = new ViewHolder();

            holder.txtdisciplina_spiner = (TextView) view.findViewById(R.id.spiner_disciplina);
            holder.color = (LinearLayout) view.findViewById(R.id.spinner_color);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Disciplinas disciplina = this.disciplina.get(position);

        holder.txtdisciplina_spiner.setText(disciplina.getNome_disciplina());
        holder.color.setBackgroundColor(disciplina.getColor_id());

        return view;
    }

    public void setItems(List<Disciplinas> disciplinas) {
        this.disciplina = disciplinas;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txtdisciplina_spiner;
        public LinearLayout color;
    }
}
