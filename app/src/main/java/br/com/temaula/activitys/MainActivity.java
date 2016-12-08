package usuario.app.temaula.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import usuario.app.temaula.R;
import usuario.app.temaula.adapter.AvisosAdapter;
import usuario.app.temaula.bd.Aviso;
import usuario.app.temaula.bd.AvisosDAO;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listavisos;
    private AvisosDAO avisosDAO;
    private AvisosAdapter avisosadapter;
    private DrawerLayout drawer;
    private TextView textoinicial;
    private ListView listDrawer;
    private TextView textListDrawer;
    private List<Map<String,Object>> itensMenu;
    private ImageView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo);
        setSupportActionBar(toolbar);
        avisosadapter = new AvisosAdapter(this);
        avisosDAO = AvisosDAO.getInstance(this);

        intentData(getIntent());
        emptyView = (ImageView) findViewById(R.id.emptyAviso);
        //listDrawer = (ListView) findViewById(R.id.listDrawer);
        listavisos = (ListView) findViewById(R.id.listViewAvisos);
        listavisos.setEmptyView(emptyView);

        String[] de = {"imagem","opcao"};
        int[] para = {R.id.imageListDrawer,R.id.textListDrawer};

        updateList();

        listavisos.setAdapter(avisosadapter);

        //SimpleAdapter adapter = new SimpleAdapter(this,listaOpcoesMenu(),R.layout.menu_principal,de,para);

        /**
        listDrawer.setAdapter(adapter);
        listDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        startActivity(new Intent(getApplicationContext(),Activity_Disciplinas.class));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),Activity_Horarios.class));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        }); */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setFocusable(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void intentData(Intent intent){

        String titulo;
        String prioridade;
        if(intent == null || intent.getExtras()==null){
            return;
        }
            titulo = intent.getStringExtra("details");
            prioridade = intent.getStringExtra("prioridade");

        if(titulo == null || prioridade == null){
            return;
        }
            Aviso aviso = new Aviso(titulo,Integer.parseInt(prioridade));
            avisosDAO.save(aviso);
    }


    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bt_disciplinas) {
            startActivity(new Intent(getApplicationContext(),Activity_Disciplinas.class));
        } else if (id == R.id.bt_horarios) {
            startActivity(new Intent(getApplicationContext(),Activity_Horarios.class));
        } else if (id == R.id.datas_provas) {
            startActivity(new Intent(getApplicationContext(),ActivityDatasProvas.class));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateList(){
        List<Aviso> avisos = avisosDAO.listAvisos();
        avisosadapter.setItems(avisos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }


}
