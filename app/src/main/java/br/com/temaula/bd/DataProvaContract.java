package usuario.app.temaula.bd;

/**
 * Created by Dennis Viana on 29/07/2016.
 */
public class DataProvaContract {

    public static final String TABLE_NAME = "tbdatas_provas";

    public static final class Columns {
        public static final String _ID = "id_datasprovas";
        public static final String DATA_AVALIACAO = "data_avaliacao";
        public static final String TIPO_AVALIACAO  = "tipo_avaliacao";
        public static final String DISCIPLINA_AVALIACAO = "fk_disciplina_avaliacao";
        public static final String DIAS_RESTANTES = "dias_restantes";
    }

}
