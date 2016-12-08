package usuario.app.temaula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import usuario.app.temaula.bd.DataProva;

/**
 * Created by Denis Viana on 07/08/2016.
 */
public interface WBInterface {

    public static final String BASE_URL = "http://www.temaula.com/WBJava/";

    @GET("ws/datas_avaliacoes")
    Call<List<DataProva>> list();

}
