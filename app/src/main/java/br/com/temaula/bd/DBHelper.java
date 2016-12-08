package usuario.app.temaula.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Denis on 09/01/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "appfaculdadeDB";
    private static final int DB_VERSION = 2;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + DisciplinasContract.TABLE_NAME;
    private static final String SQL_CREATE = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s VARCHAR(45) NOT NULL,\n" +
                    "  %s VARCHAR(45) NOT NULL,\n" +
                    "  %s VARCHAR(20) NOT NULL,\n" +
                    "  %s INTEGER NULL\n" +
                    ");",
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID,
            DisciplinasContract.Columns.NOME_DISCIPLINA,
            DisciplinasContract.Columns.NOME_PROFESSOR,
            DisciplinasContract.Columns.TIPO_DISCIPLINA,
            DisciplinasContract.Columns.ID_COLOR,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIOS = String.format(
            "CREATE TABLE IF NOT EXISTS %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DIA_SEMANA,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_AVISOS = String.format(
            "CREATE TABLE IF NOT EXISTS %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s VARCHAR(100) NOT NULL);",
            AvisoContract.TABLE_NAME,
            AvisoContract.Columns._ID,
            AvisoContract.Columns.PRIORIDADE,
            AvisoContract.Columns.TITULO);

    private static final String SQL_CREATE_DATASPROVAS =  String.format(
            "CREATE TABLE IF NOT EXISTS %s (" +
                    " %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s VARCHAR (100) NOT NULL," +
                    " %s VARCHAR (100) NOT NULL," +
                    " %s VARCHAR (100) NOT NULL);",
            DataProvaContract.TABLE_NAME,
            DataProvaContract.Columns._ID,
            DataProvaContract.Columns.DATA_AVALIACAO,
            DataProvaContract.Columns.TIPO_AVALIACAO,
            DataProvaContract.Columns.DISCIPLINA_AVALIACAO,
            DataProvaContract.Columns.DIAS_RESTANTES

    );

    private static final String view_datas_provas = "CREATE VIEW IF NOT EXISTS view_datas_provas AS " +
            "SELECT tbdatas_provas.id_datasprovas, tbdatas_provas.data_avaliacao, tbdatas_provas.tipo_avaliacao, " +
            "tbdisciplinas.nome_disciplina, tbdatas_provas.dias_restantes " +
            "FROM tbdatas_provas " +
            "INNER JOIN tbdisciplinas ON tbdatas_provas.fk_disciplina_avaliacao = tbdisciplinas.idtbdisciplinas;";

    private static final String view_horarios = "CREATE VIEW IF NOT EXISTS view_horarios AS\n" +
            "SELECT tbhorarios.idtbhorario, tbhorarios.dia_semana, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorarios.id_icone, tbhorarios.id_color, tbhorarios.horario\n" +
            "FROM tbhorarios\n" +
            "INNER JOIN tbdisciplinas ON tbhorarios.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    /*
    private static final String SQL_CREATE_HORARIO_SEGUNDA = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIO_SEGUNDA,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIO_TERCA = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS_TERCA,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIO_QUARTA = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS_QUARTA,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIO_QUINTA = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS_QUINTA,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIO_SEXTA = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS_SEXTA,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID);

    private static final String SQL_CREATE_HORARIO_SABADO = String.format(
            "CREATE TABLE %s (\n" +
                    "  %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  %s INTEGER NOT NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  %s VARCHAR(45) NULL,\n" +
                    "  %s INTEGER NULL,\n" +
                    "  FOREIGN KEY(%s)\n" +
                    "    REFERENCES %s(%s)\n" +
                    "      ON DELETE RESTRICT\n" +
                    "      ON UPDATE CASCADE);",
            HorariosContract.TABLE_HORARIOS_SABADO,
            HorariosContract.Columns._ID,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.ICON_ID,
            HorariosContract.Columns.HORARIO_AULA,
            HorariosContract.Columns.ID_COLOR,
            HorariosContract.Columns.DISC_HORARIO,
            HorariosContract.Columns.DISC_HORARIO,
            DisciplinasContract.TABLE_NAME,
            DisciplinasContract.Columns._ID); */

/*
    private static final String view_segunda = "CREATE VIEW view_horariosegunda AS\n" +
            "SELECT tbhorario_segunda.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_segunda.id_icone, tbhorario_segunda.id_color, tbhorario_segunda.horario\n" +
            "FROM tbhorario_segunda\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_segunda.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    private static final String view_terca = "CREATE VIEW view_horarioterca AS\n" +
            "SELECT tbhorario_terca.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_terca.id_icone, tbhorario_terca.id_color, tbhorario_terca.horario\n" +
            "FROM tbhorario_terca\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_terca.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    private static final String view_quarta = "CREATE VIEW view_horarioquarta AS\n" +
            "SELECT tbhorario_quarta.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_quarta.id_icone, tbhorario_quarta.id_color, tbhorario_quarta.horario\n" +
            "FROM tbhorario_quarta\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_quarta.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    private static final String view_quinta = "CREATE VIEW view_horarioquinta AS\n" +
            "SELECT tbhorario_quinta.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_quinta.id_icone, tbhorario_quinta.id_color, tbhorario_quinta.horario\n" +
            "FROM tbhorario_quinta\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_quinta.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    private static final String view_sexta = "CREATE VIEW view_horariosexta AS\n" +
            "SELECT tbhorario_sexta.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_sexta.id_icone, tbhorario_sexta.id_color, tbhorario_sexta.horario\n" +
            "FROM tbhorario_sexta\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_sexta.fk_disciplina=tbdisciplinas.idtbdisciplinas;";

    private static final String view_sabado = "CREATE VIEW view_horariosabado AS\n" +
            "SELECT tbhorario_sabado.idtbhorario, tbdisciplinas.nome_disciplina, tbdisciplinas.professor,\n" +
            "tbhorario_sabado.id_icone, tbhorario_sabado.id_color, tbhorario_sabado.horario\n" +
            "FROM tbhorario_sabado\n" +
            "INNER JOIN tbdisciplinas ON tbhorario_sabado.fk_disciplina=tbdisciplinas.idtbdisciplinas;"; */

    private static DBHelper instance;

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        //Cria Tabelas
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_CREATE_HORARIOS);
        db.execSQL(SQL_CREATE_AVISOS);
        db.execSQL(SQL_CREATE_DATASPROVAS);

        //Cria Views
        db.execSQL(view_horarios);
        db.execSQL(view_datas_provas);
       /* db.execSQL(view_segunda);
        db.execSQL(view_terca);
        db.execSQL(view_quarta);
        db.execSQL(view_quinta);
        db.execSQL(view_sexta);
        db.execSQL(view_sabado); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
