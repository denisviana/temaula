package usuario.app.temaula.bd;

/**
 * Created by Denis on 14/01/2016.
 */
public class HorariosContract {

    public static final String TABLE_HORARIOS = "tbhorarios";
    public static final String TABLE_HORARIO_SEGUNDA = "tbhorario_segunda";
    public static final String TABLE_HORARIOS_TERCA = "tbhorario_terca";
    public static final String TABLE_HORARIOS_QUARTA = "tbhorario_quarta";
    public static final String TABLE_HORARIOS_QUINTA = "tbhorario_quinta";
    public static final String TABLE_HORARIOS_SEXTA= "tbhorario_sexta";
    public static final String TABLE_HORARIOS_SABADO = "tbhorario_sabado";

    public static final class Columns{
        public static final String _ID = "idtbhorario";
        public static final String SIGLA = "cod";
        public static final String DIA_SEMANA = "dia_semana";
        public static final String ICON_ID = "id_icone";
        public static final String HORARIO_AULA = "horario";
        public static final String DISC_HORARIO = "fk_disciplina";
        public static final String PROF_HORARIO = "professor_horario";
        public static final String SALA_HORARIO = "sala_horario";
        public static final String ID_COLOR = "id_color";
        public static final String ID_DISCIPLINA = "id_disciplina";
    }
}
