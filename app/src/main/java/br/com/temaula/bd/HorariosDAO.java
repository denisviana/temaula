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
 * Created by Denis on 14/01/2016.
 */
public class HorariosDAO {

    private static HorariosDAO instance;
    private SQLiteDatabase db;

    public HorariosDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static HorariosDAO getInstance (Context context){
        if(instance == null){
            instance = new HorariosDAO(context.getApplicationContext());
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listHorarios(int dia_semana){
        List<Horarios> horarioslist = new ArrayList<>();
        String[] columns = {"*"};
        String where = HorariosContract.Columns.DIA_SEMANA + "=" + dia_semana;

        try (Cursor c = db.query("view_horarios",columns,where,null,null,null,HorariosContract.Columns.HORARIO_AULA)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarioslist.add(h);
                } while (c.moveToNext());
            }
            return horarioslist;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listSegunda(){

        String[] columns = {"*"};


        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horariosegunda",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listTerca(){

        String[] columns = {"*"};



        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horarioterca",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listQuarta(){

        String[] columns = {"*"};

        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horarioquarta",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listQuinta(){

        String[] columns = {"*"};

        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horarioquinta",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listSexta(){

        String[] columns = {"*"};


        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horariosexta",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Horarios> listSabado(){

        String[] columns = {"*"};

        List<Horarios> horarios = new ArrayList<>();
        try (Cursor c = db.query("view_horariosabado",columns,null,null,null,null,null)){
            if(c.moveToFirst()){
                do {
                    Horarios h = HorariosDAO.fromCursor(c);
                    horarios.add(h);
                } while (c.moveToNext());
            }
            return horarios;
        }
    }

    public void saveHorarios(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID, horarios.getIconID());
        values.put(HorariosContract.Columns.DIA_SEMANA,horarios.getId_dia_semana());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO, horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO, horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO, horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR, horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS,null,values);
        horarios.setId((int)id);
    }

    public void saveSegunda(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID, horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO, horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO, horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO, horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR, horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIO_SEGUNDA,null,values);
        horarios.setId((int)id);
    }

    public void saveTerca(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID,horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO,horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO,horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO,horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR,horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS_TERCA,null,values);
        horarios.setId((int)id);
    }

    public void saveQuarta(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID,horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO,horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO,horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO,horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR,horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS_QUARTA,null,values);
        horarios.setId((int)id);
    }

    public void saveQuinta(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID,horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO,horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO,horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO,horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR,horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS_QUINTA,null,values);
        horarios.setId((int)id);
    }

    public void saveSexta(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID,horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO,horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO,horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO,horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR,horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS_SEXTA,null,values);
        horarios.setId((int)id);
    }

    public void saveSabado(Horarios horarios){
        ContentValues values = new ContentValues();

        values.put(HorariosContract.Columns.ICON_ID,horarios.getIconID());
        values.put(HorariosContract.Columns.HORARIO_AULA,horarios.getHorario());
        values.put(HorariosContract.Columns.DISC_HORARIO,horarios.getId_disciplina());
        //values.put(HorariosContract.Columns.PROF_HORARIO,horarios.getProfessor());
        //values.put(HorariosContract.Columns.SALA_HORARIO,horarios.getSala());
        values.put(HorariosContract.Columns.ID_COLOR,horarios.getColorID());

        long id = (int) db.insert(HorariosContract.TABLE_HORARIOS_SABADO,null,values);
        horarios.setId((int)id);
    }

    private static Horarios fromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(HorariosContract.Columns._ID));
        int id_dia_semana = c.getInt(c.getColumnIndex(HorariosContract.Columns.DIA_SEMANA));
        int idColor = c.getInt(c.getColumnIndex(HorariosContract.Columns.ID_COLOR));
        int idIcon = c.getInt(c.getColumnIndex(HorariosContract.Columns.ICON_ID));
        String horario_aula = c.getString(c.getColumnIndex(HorariosContract.Columns.HORARIO_AULA));

        //int hour = Integer.parseInt(horario_aula.substring(0, 2));
        //int minute = Integer.parseInt(horario_aula.substring(3, 5));

        String disciplina_horario = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_DISCIPLINA));
        String professor_horario = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_PROFESSOR));
        //String sala = c.getString(c.getColumnIndex(HorariosContract.Columns.SALA_HORARIO));
        return new Horarios(id,id_dia_semana,disciplina_horario,professor_horario,horario_aula,idIcon,idColor);
    }

    public void delete(Horarios horario){

        db.delete(HorariosContract.TABLE_HORARIOS, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horario.getId())});


    }

    private void deleteSegunda(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIO_SEGUNDA, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }

    private void deleteTerca(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIOS_TERCA, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }

    private void deleteQuarta(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIOS_QUARTA, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }

    private void deleteQuinta(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIOS_QUINTA, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }

    private void deleteSexta(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIOS_SEXTA, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }

    private void deleteSabado(Horarios horarios){
        db.delete(HorariosContract.TABLE_HORARIOS_SABADO, HorariosContract.Columns._ID+
                "= ?", new String[]{String.valueOf(horarios.getId())});
    }
}
