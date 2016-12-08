package usuario.app.temaula.bd;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class DatasProvasDAO {

    private static DatasProvasDAO instance;
    private SQLiteDatabase db;

    private DatasProvasDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static DatasProvasDAO getInstance(Context context){
        if(instance==null){
            instance = new DatasProvasDAO(context.getApplicationContext());
        }
        return instance;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<DataProva> listDatasProvas(){

        String[] columns = {DataProvaContract.Columns._ID,
                DataProvaContract.Columns.DATA_AVALIACAO,
                DataProvaContract.Columns.DISCIPLINA_AVALIACAO,
                DataProvaContract.Columns.TIPO_AVALIACAO,
                DataProvaContract.Columns.DIAS_RESTANTES
        };

        List<DataProva> listdatasprovas = new ArrayList<>();

        try(Cursor c = db.query(DataProvaContract.TABLE_NAME,columns,null,null,null,null,null,null)) {
            if (c.moveToFirst()) {
                Log.i("Cursor",c.getString(0)+":"+c.getString(1)+":"+c.getString(2)+":"+c.getString(3));
                do {
                    DataProva dp = DatasProvasDAO.fromCursor(c);
                    listdatasprovas.add(dp);
                } while (c.moveToNext());
            }
            return listdatasprovas;
        }
    }

    public void save(DataProva dataprova){
        ContentValues values = new ContentValues();
        values.put(DataProvaContract.Columns.DATA_AVALIACAO,dataprova.getData_avaliacao());
        values.put(DataProvaContract.Columns.DISCIPLINA_AVALIACAO, dataprova.getDisciplina_avaliacao());
        values.put(DataProvaContract.Columns.TIPO_AVALIACAO,dataprova.getTipo_avaliacao());
        values.put(DataProvaContract.Columns.DIAS_RESTANTES,dataprova.getContagem_dias());

        long id = db.insert(DataProvaContract.TABLE_NAME, null, values);
        dataprova.setId((int) id);
    }

    private static DataProva fromCursor(Cursor c){

        int id = c.getInt(c.getColumnIndex(DataProvaContract.Columns._ID));
        String data_avaliacao = c.getString(c.getColumnIndex(DataProvaContract.Columns.DATA_AVALIACAO));
        String disciplina = c.getString(c.getColumnIndex(DataProvaContract.Columns.DISCIPLINA_AVALIACAO));
        String tipo_avaliacao = c.getString(c.getColumnIndex(DataProvaContract.Columns.TIPO_AVALIACAO));
        int cont = c.getInt(c.getColumnIndex(DataProvaContract.Columns.DIAS_RESTANTES));

        return new DataProva(id,data_avaliacao,disciplina,tipo_avaliacao,cont);
    }



}
