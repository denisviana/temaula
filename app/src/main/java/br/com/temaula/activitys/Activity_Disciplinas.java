package usuario.app.temaula.activitys;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import usuario.app.temaula.R;
import usuario.app.temaula.adapter.DisciplinasAdapter;
import usuario.app.temaula.bd.DisciplinaDAO;
import usuario.app.temaula.bd.Disciplinas;
import usuario.app.temaula.dialogs.DialogAdicionarDisciplina;

public class Activity_Disciplinas extends AppCompatActivity implements DialogAdicionarDisciplina.AdicionaDisciplinas {

    public String disciplina, professor;
    private ListView listar_disciplinas;
    private DisciplinaDAO disciplinaDAO;
    private DisciplinasAdapter adapter;
    private DrawerLayout drawer;
    private Realm realm;
    private RealmResults<Disciplinas> realmResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Metodo onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplinas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Disciplinas");
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();
        realmResults = realm.where(Disciplinas.class).findAll();
        realm.close();

        listar_disciplinas = (ListView) findViewById(R.id.listView);
        TextView emptyView = (TextView) findViewById(R.id.empty);
        listar_disciplinas.setEmptyView(emptyView);

        adapter = new DisciplinasAdapter(this);
        listar_disciplinas.setAdapter(adapter); //Popula a ListView com os dados do banco de dados através da instância de DisciplinasAdapter
        disciplinaDAO = DisciplinaDAO.getInstance(this);
        updateList(); //Chama o método que atualiza o adapter da ListView;

        registerForContextMenu(listar_disciplinas);
        final Toast toast = Toast.makeText(this, "Pressione e segure para abrir o menu de contexto", Toast.LENGTH_SHORT);
        listar_disciplinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast.show();
            }
        });
        listar_disciplinas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

        //Botão Flutuante para adicionar disciplinas
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DialogAdicionarDisciplina.newInstace(null,null,null,null,-1,0).show(fm, null);
            }
        });
    }

    //Método para atualizar os dados do adapter da ListView;
    private void updateList(){
        List<Disciplinas> disciplinas = disciplinaDAO.list();
        adapter.setItems(disciplinas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_disciplinas,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.altera_disciplina){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Disciplinas disciplinas = (Disciplinas) adapter.getItem(info.position);
            long idDisciplina = disciplinas.getId();
            String disciplina = disciplinas.getNome_disciplina();
            String prof = disciplinas.getNome_professor();
            int cor = disciplinas.getColor_id();
            String tipo_disciplina = disciplinas.getTipo_disciplina();
            String sala = disciplinas.getSala_disciplina();

            FragmentManager fm = getFragmentManager();

            DialogAdicionarDisciplina.newInstace(disciplina,prof,tipo_disciplina,sala,idDisciplina,cor).show(fm,null);

        } else if(item.getItemId()==R.id.remove_disciplina){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            Disciplinas disciplina = (Disciplinas) adapter.getItem(info.position);
            disciplinaDAO.delete(disciplina);
            updateList();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }

    //Interface que faz a comunicação da Activity com o Dialog que adiciona as disciplinas.
    @Override
    public void btDialogAdicionarDisciplina() {
        updateList();
    }

    @Override
    public void btDialogCancelar() {

    }


}
