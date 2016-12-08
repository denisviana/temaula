package usuario.app.temaula.activitys;


import android.app.ProgressDialog;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usuario.app.temaula.R;
import usuario.app.temaula.WBInterface;
import usuario.app.temaula.adapter.DatasProvasAdapter;
import usuario.app.temaula.bd.DataProva;
import usuario.app.temaula.bd.DatasProvasDAO;

/**
 * Created by Dennis Viana on 29/07/2016.
 */
public class ActivityDatasProvas extends AppCompatActivity {

    private Button botao;
    private ListView lv_datasProvas;
    private DatasProvasAdapter dataprovaAdapter;
    private DatasProvasDAO dataprovaDAO;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datas_provas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_avaliacoes);
        toolbar.setTitle("Avaliações");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv_datasProvas = (ListView) findViewById(R.id.list_datas_provas);
        //botao = (Button) findViewById(R.id.botaoteste);
        //botao.setOnClickListener(btnteste);
        dataprovaDAO = DatasProvasDAO.getInstance(this);
        dataprovaAdapter = new DatasProvasAdapter(this);
        lv_datasProvas.setDivider(null);
        lv_datasProvas.setAdapter(dataprovaAdapter);
        mProgressDialog = new ProgressDialog(this);

        updateList();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_data_prova,menu);

        return true;
    }

    private OnClickListener btnteste = new OnClickListener(){

        @Override
        public void onClick(View view) {
            Toast.makeText(ActivityDatasProvas.this, "TESTE DE BOTÃO", Toast.LENGTH_SHORT).show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WBInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WBInterface wbinterface = retrofit.create(WBInterface.class);
            Call<List<DataProva>> requestDataProva = wbinterface.list();

            requestDataProva.enqueue(new Callback<List<DataProva>>() {

                List<DataProva> datasprovas = new ArrayList<>();
                @Override
                public void onResponse(Call<List<DataProva>> call, Response<List<DataProva>> response) {
                    if(response.code()!=200){
                        Toast.makeText(ActivityDatasProvas.this, "ERRO WEBSERVICE", Toast.LENGTH_SHORT).show();
                    } else {
                        DataProva dataprova;
                        List<DataProva> dataprovalist = response.body();
                        for(int i=0; i<dataprovalist.size();i++){
                            dataprova = dataprovalist.get(i);
                            dataprovaDAO.save(dataprova);
                            //datasprovas.add(dataprova);
                        }
                        List<DataProva> datasprovas = dataprovaDAO.listDatasProvas();
                        dataprovaAdapter.setItems(datasprovas);
                        lv_datasProvas.setAdapter(dataprovaAdapter);
                    }
                }
                @Override
                public void onFailure(Call<List<DataProva>> call, Throwable t) {
                    Log.e("erroWB","Erro: "+t.getMessage());
                }
            });

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList(){
        List<DataProva> datasprovas = dataprovaDAO.listDatasProvas();
        dataprovaAdapter.setItems(datasprovas);

    }

    public void showDialog() {

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle("Aguarde");
        mProgressDialog.setMessage("Baixando Dados...");


        if(!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {

        if(mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        showDialog();

        if(item.getItemId() == R.id.add_data_provas){



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WBInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WBInterface wbinterface = retrofit.create(WBInterface.class);
            Call<List<DataProva>> requestDataProva = wbinterface.list();

            requestDataProva.enqueue(new Callback<List<DataProva>>() {
                List<DataProva> datasprovas = new ArrayList<>();
                @Override
                public void onResponse(Call<List<DataProva>> call, Response<List<DataProva>> response) {
                    if(response.code()!=200){
                        Toast.makeText(ActivityDatasProvas.this, "ERRO WEBSERVICE", Toast.LENGTH_SHORT).show();
                    } else {
                        DataProva dataprova;
                        List<DataProva> dataprovalist = response.body();
                        for(int i=0; i<dataprovalist.size();i++){
                            dataprova = dataprovalist.get(i);
                            dataprovaDAO.save(dataprova);

                        }
                        datasprovas = dataprovaDAO.listDatasProvas();
                        dataprovaAdapter.setItems(datasprovas);
                        lv_datasProvas.setAdapter(dataprovaAdapter);
                    }

                    hideDialog();
                }

                @Override
                public void onFailure(Call<List<DataProva>> call, Throwable t) {
                    Log.e("erroWB","Erro: "+t.getMessage());
                }
            });

            /*
            FragmentAddDatasProvas fragment_add_datas_provas = new FragmentAddDatasProvas();
            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.layout_add_datas_provas, fragment_add_datas_provas,"fragment");
            */
            return true;
        }

        return false;
    }
}
