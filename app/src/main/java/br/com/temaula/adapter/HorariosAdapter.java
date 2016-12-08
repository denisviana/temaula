package usuario.app.temaula.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.Horarios;

/**
 * Created by Denis on 12/01/2016.
 */
public class HorariosAdapter extends BaseAdapter{

    private Context context;
    private List<Horarios> horarios = new ArrayList<>();


    public HorariosAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return horarios.size();
    }

    @Override
    public Object getItem(int position) {
       return horarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
        ViewHolder holder = null;
        ListView listView = (ListView) parent;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.horario,parent,false);
            holder = new ViewHolder();
            holder.iconID = (ImageView) view.findViewById(R.id.relogio_horario);
            holder.horario = (TextView) view.findViewById(R.id.horario_aula);
            holder.disciplina_horario = (TextView) view.findViewById(R.id.disciplina_horario);
            holder.professor_horario = (TextView) view.findViewById(R.id.prof_horario);
            holder.color = (LinearLayout) view.findViewById(R.id.cor_horario);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Horarios horario = horarios.get(position);

        holder.iconID.setImageResource(R.drawable.horario_icon);
        holder.horario.setText(horario.getHorario());
        holder.disciplina_horario.setText(horario.getDisciplina());
        holder.professor_horario.setText("Professor: "+horario.getProfessor());
        holder.color.setBackgroundColor(horario.getColorID());


        int color = listView.isItemChecked(position) ?
                Color.parseColor("#E0E0E0") :
                Color.TRANSPARENT;

        view.setBackgroundColor(color);

        return view;
    }

    public void setItens (List<Horarios> horarios){
        this.horarios = horarios;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public ImageView iconID;
        public TextView horario;
        public TextView disciplina_horario;
        public TextView professor_horario;
        public LinearLayout color;
    }
}
