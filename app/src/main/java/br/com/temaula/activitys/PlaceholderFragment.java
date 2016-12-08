package usuario.app.temaula.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.adapter.HorariosAdapter;
import usuario.app.temaula.bd.Horarios;
import usuario.app.temaula.bd.HorariosDAO;

/**
 * Created by Denis on 14/01/2016.
 */
public class PlaceholderFragment extends Fragment implements AdapterView.OnItemLongClickListener, OnItemClickListener, ActionMode.Callback {

    private final int DOMINGO=1, SEGUNDA=2, TERCA=3,
            QUARTA=4, QUINTA=5, SEXTA=6, SABADO=7;
    public static boolean isInActionMode = false;
    private ListView list_Horarios;
    private HorariosDAO horariosDAO;
    private HorariosAdapter adapter;
    private TextView emptyView;
    private TextView diaDaSemanaTitle;
    private Horarios horarioselecionado = new Horarios();
    private int Position;
    private String titulo;
    int i=0;
    public static ActionMode actionMode;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt("Position",sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().supportInvalidateOptionsMenu();

        AppCompatActivity activity = (AppCompatActivity) getActivity();


        if(savedInstanceState != null){
            Position = savedInstanceState.getInt("fragmentpos");
        }


        horariosDAO = HorariosDAO.getInstance(getActivity().getBaseContext());
        adapter = new HorariosAdapter(getActivity().getBaseContext());
    }

    public static void disableActionMode(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_horarios, container, false);

        emptyView = (TextView) rootView.findViewById(R.id.horarios_empty);
        list_Horarios = (ListView) rootView.findViewById(R.id.listViewHorarios);
        list_Horarios.setEmptyView(emptyView);
        diaDaSemanaTitle = (TextView) rootView.findViewById(R.id.diaDaSemanaTitle);


        Position = getArguments().getInt("Position");

        switch (Position){
            case 0:
                diaDaSemanaTitle.setText("Segunda-Feira");
                break;
            case 1:
                diaDaSemanaTitle.setText("Terça-Feira");
                break;
            case 2:
                diaDaSemanaTitle.setText("Quarta-Feira");
                break;
            case 3:
                diaDaSemanaTitle.setText("Quinta-Feira");
                break;
            case 4:
                diaDaSemanaTitle.setText("Sexta-Feira");
                break;
            case 5:
                diaDaSemanaTitle.setText("Sábado");
                break;
        }



        update(Position);
        list_Horarios.setAdapter(adapter);
        //list_Horarios.setSelector(R.color.pressed_color);
        list_Horarios.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        /*list_Horarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("selected","item"+i++);
                view.setBackgroundResource(R.color.pressed_color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        //registerForContextMenu(list_Horarios);
        list_Horarios.setOnItemLongClickListener(this);
        list_Horarios.setOnItemClickListener(this);
       /* list_Horarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(false);

            }
        }); */

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_horarios,menu);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (actionMode == null) {
            // Faça algo ao clicar no item normalmente
        } else {
            int checkedCount =
                    atualizarItensMarcados(list_Horarios, position);

            if (checkedCount == 0) {
                actionMode.finish();
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        boolean consumed = (actionMode == null);

        if (consumed) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            actionMode = activity.startSupportActionMode(this);
            list_Horarios.setChoiceMode(
                    ListView.CHOICE_MODE_MULTIPLE);

            list_Horarios.setItemChecked(position, true);
            view.setSelected(true);
            atualizarItensMarcados(list_Horarios, position);
        }
        return consumed;
    }




    private int atualizarItensMarcados(ListView l, int position) {

        SparseBooleanArray checked =
                l.getCheckedItemPositions();

        l.setItemChecked(position,
                l.isItemChecked(position));

        int checkedCount = 0;
        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i)) {
                checkedCount++;
            }
        }

        actionMode.setTitle(
                checkedCount + " selecionados");

        return checkedCount;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getMenuInflater().inflate(R.menu.menuexcluir,menu);
        isInActionMode = true;

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        if(item.getItemId()==R.id.id_delete){
            SparseBooleanArray checked =
                    list_Horarios.getCheckedItemPositions();
            for(int i=checked.size()-1;i>=0;i--){
                if(checked.valueAt(i)){
                    Horarios horario = (Horarios) adapter.getItem(checked.keyAt(i));
                    Toast.makeText(getContext(),"Ítem "+horario.getDisciplina(),Toast.LENGTH_SHORT).show();
                    horariosDAO.delete(horario);
                    update(Position);
                }
            }
            actionMode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

        actionMode = null;
        list_Horarios.clearChoices();
        ((BaseAdapter) list_Horarios.getAdapter())
                .notifyDataSetChanged();
        isInActionMode = false;
        list_Horarios.setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    public void finishActionMode() {
        if(actionMode!=null)
        onDestroyActionMode(actionMode);
    }


    class TrataEventoItemList implements OnItemClickListener{

        int item_list;

        public TrataEventoItemList(int item_list){
            this.item_list = item_list;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Horarios horario = (Horarios) adapter.getItem(position);
            horariosDAO.delete(horario);
            update(item_list);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("ActionMode", isInActionMode);
        outState.putInt("fragmenpos",Position);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (actionMode!=null) {
            actionMode.finish();
            onDestroyActionMode(actionMode);
        }

    }

    @Override
    public void onResume() {


        super.onResume();
        int pos = getArguments().getInt("Position");
        switch (pos){
            case 0:
                update(pos);
                break;
            case 1:
                update(pos);
                break;
            case 2:
                update(pos);
                break;
            case 3:
                update(pos);
                break;
            case 4:
                update(pos);
                break;
            case 5:
                update(pos);
                break;
        }
    }

    public void update(int position){

        List<Horarios> horarios;

        switch(position){
            case 0:
                horarios = horariosDAO.listHorarios(SEGUNDA);
                adapter.setItens(horarios);
                break;
            case 1:
                horarios = horariosDAO.listHorarios(TERCA);
                adapter.setItens(horarios);
                break;
            case 2:
                horarios = horariosDAO.listHorarios(QUARTA);
                adapter.setItens(horarios);
                break;
            case 3:
                horarios = horariosDAO.listHorarios(QUINTA);
                adapter.setItens(horarios);
                break;
            case 4:
                horarios = horariosDAO.listHorarios(SEXTA);
                adapter.setItens(horarios);
                break;
            case 5:
                horarios = horariosDAO.listHorarios(SABADO);
                adapter.setItens(horarios);
                break;
        }
}

}