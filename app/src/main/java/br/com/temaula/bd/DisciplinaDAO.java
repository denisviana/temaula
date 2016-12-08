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
 * Created by Denis on 09/01/2016.
 */
public class DisciplinaDAO {

    private static DisciplinaDAO instance;
    private SQLiteDatabase db;

    public static DisciplinaDAO getInstance(Context context){
        if(instance==null){
           instance = new DisciplinaDAO(context.getApplicationContext());
        }
        return instance;
    }

    private DisciplinaDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Disciplinas> list() {

        String[] columns = {
                DisciplinasContract.Columns._ID,
                DisciplinasContract.Columns.NOME_DISCIPLINA,
                DisciplinasContract.Columns.NOME_PROFESSOR,
                DisciplinasContract.Columns.TIPO_DISCIPLINA,
                //DisciplinasContract.Columns.SALA_DISCIPLINA,
                DisciplinasContract.Columns.ID_COLOR
        };

        List<Disciplinas> disciplinas = new ArrayList<>();
        try (Cursor c = db.query(DisciplinasContract.TABLE_NAME, columns, null, null, null, null, DisciplinasContract.Columns.NOME_DISCIPLINA))
        {
            if (c.moveToFirst()) {
                do{
                    Disciplinas d = DisciplinaDAO.fromCursor(c);
                    disciplinas.add(d);
                } while(c.moveToNext());
            }
            return disciplinas;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Disciplinas> listDisciplinaIndividual(){

        String[] columns = {
                DisciplinasContract.Columns.NOME_DISCIPLINA,
                DisciplinasContract.Columns.NOME_PROFESSOR,
                DisciplinasContract.Columns.ID_COLOR,
                DisciplinasContract.Columns._ID
        };

        List<Disciplinas> disciplinas = new ArrayList<>();
        try (Cursor c = db.query(DisciplinasContract.TABLE_NAME, columns, null, null, null, null, DisciplinasContract.Columns.NOME_DISCIPLINA))
        {

            if (c.moveToFirst()) {
                do{
                    Disciplinas d = DisciplinaDAO.listaDisciplinaProfessor(c);
                    disciplinas.add(d);
                } while(c.moveToNext());
            }
            return disciplinas;
        }
    }

    private static Disciplinas fromCursor(Cursor c){
        int id = c.getInt(c.getColumnIndex(DisciplinasContract.Columns._ID));
        String disciplina = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_DISCIPLINA));
        String professor = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_PROFESSOR));
        String tipo_disciplina = c.getString(c.getColumnIndex(DisciplinasContract.Columns.TIPO_DISCIPLINA));
        //String sala_disciplina = c.getString(c.getColumnIndex(DisciplinasContract.Columns.SALA_DISCIPLINA));
        int id_color = c.getInt(c.getColumnIndex(DisciplinasContract.Columns.ID_COLOR));

        return new Disciplinas(id,disciplina,professor,tipo_disciplina,id_color);
    }

    private static Disciplinas listaDisciplinaProfessor(Cursor c){
        String disciplina = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_DISCIPLINA));
        String professor = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_PROFESSOR));
        int id_color = c.getInt(c.getColumnIndex(DisciplinasContract.Columns.ID_COLOR));
        int id = c.getInt(c.getColumnIndex(DisciplinasContract.Columns._ID));

        return new Disciplinas(disciplina,professor,id_color,id);
    }

    public void save(Disciplinas disciplina){
        ContentValues values = new ContentValues();
        values.put(DisciplinasContract.Columns.NOME_DISCIPLINA, disciplina.getNome_disciplina());
        values.put(DisciplinasContract.Columns.NOME_PROFESSOR, disciplina.getNome_professor());
        values.put(DisciplinasContract.Columns.TIPO_DISCIPLINA,disciplina.getTipo_disciplina());
        //values.put(DisciplinasContract.Columns.SALA_DISCIPLINA,disciplina.getSala_disciplina());
        values.put(DisciplinasContract.Columns.ID_COLOR, disciplina.getColor_id());

       long id = db.insert(DisciplinasContract.TABLE_NAME, null, values);
        disciplina.setId((int) id);
    }

    public void update(Disciplinas disciplina){
        ContentValues values = new ContentValues();
        values.put(DisciplinasContract.Columns.NOME_DISCIPLINA, disciplina.getNome_disciplina());
        values.put(DisciplinasContract.Columns.NOME_PROFESSOR, disciplina.getNome_professor());
        values.put(DisciplinasContract.Columns.TIPO_DISCIPLINA, disciplina.getTipo_disciplina());
        //values.put(DisciplinasContract.Columns.SALA_DISCIPLINA,disciplina.getSala_disciplina());
        values.put(DisciplinasContract.Columns.ID_COLOR, disciplina.getColor_id());

        String[] where = new String[]{String.valueOf(disciplina.getId())};

        db.update(DisciplinasContract.TABLE_NAME, values, DisciplinasContract.Columns._ID + " = ?", where);

    }

    public void updateHorarios(Disciplinas disciplina){
        ContentValues values = new ContentValues();
        values.put(DisciplinasContract.Columns.SEG, disciplina.isSeg());
        values.put(DisciplinasContract.Columns.TER, disciplina.isTer());
        values.put(DisciplinasContract.Columns.QUA, disciplina.isQua());
        values.put(DisciplinasContract.Columns.QUI, disciplina.isQui());
        values.put(DisciplinasContract.Columns.SEX, disciplina.isSex());
        values.put(DisciplinasContract.Columns.SAB, disciplina.isSab());

        String[] where = new String[]{String.valueOf(disciplina.getId())};

        db.update(DisciplinasContract.TABLE_NAME, values, DisciplinasContract.Columns._ID + " = ?", where);

    }

    public void delete(Disciplinas disciplina){
        db.delete(DisciplinasContract.TABLE_NAME, DisciplinasContract.Columns._ID +
                " = ?", new String[]{String.valueOf(disciplina.getId())});
    }
}
