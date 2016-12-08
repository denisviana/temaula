package usuario.app.temaula.bd;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class AvisosDAO {

    private static AvisosDAO instance;
    private SQLiteDatabase db;

    private AvisosDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static AvisosDAO getInstance(Context context){
        if(instance==null){
            instance = new AvisosDAO(context.getApplicationContext());
        }
        return instance;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Aviso> listAvisos(){

        String[] columns = {AvisoContract.Columns._ID,
                AvisoContract.Columns.TITULO,
                AvisoContract.Columns.PRIORIDADE
        };

        List<Aviso> listavisos = new ArrayList<>();

        try(Cursor c = db.query(AvisoContract.TABLE_NAME,columns,null,null,null,null,null,null)) {
            if (c.moveToFirst()) {
                do {
                    Aviso a = AvisosDAO.fromCursor(c);
                    listavisos.add(a);
                } while (c.moveToNext());
            }
            return listavisos;
        }
    }

    public void save(Aviso aviso){
        ContentValues values = new ContentValues();
        values.put(AvisoContract.Columns.PRIORIDADE,aviso.getIc_prioridade());
        values.put(AvisoContract.Columns.TITULO, aviso.getTitulo());

        long id = db.insert(AvisoContract.TABLE_NAME, null, values);
        aviso.setId((int) id);
    }

    private static Aviso fromCursor(Cursor c){

        int id = c.getInt(c.getColumnIndex(AvisoContract.Columns._ID));
        int prioridade = c.getInt(c.getColumnIndex(AvisoContract.Columns.PRIORIDADE));
        String titulo = c.getString(c.getColumnIndex(AvisoContract.Columns.TITULO));

        return new Aviso(id,prioridade,titulo);
    }



}
