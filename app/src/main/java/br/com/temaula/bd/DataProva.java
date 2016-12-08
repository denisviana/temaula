package usuario.app.temaula.bd;

import java.io.Serializable;

/**
 * Created by Dennis Viana on 28/07/2016.
 */
public class DataProva implements Serializable {

    private int id;
    private String data_avaliacao;
    private String tipo_avaliacao;
    private String disciplina_avaliacao;
    private int contagem_dias;

    public DataProva(int id,String data_avaliacao, String tipo_avaliacao, String disciplina_avaliacao, int contagem_dias){
        this.id = id;
        this.data_avaliacao = data_avaliacao;
        this.tipo_avaliacao = tipo_avaliacao;
        this.disciplina_avaliacao = disciplina_avaliacao;
        this.contagem_dias = contagem_dias;
    }

    public int getId(){return this.id;}

    public void setId(int id){
        this.id= id;
    }

    public String getData_avaliacao() {
        return data_avaliacao;
    }

    public void setData_avaliacao(String data) {
        this.data_avaliacao = data;
    }

    public String getTipo_avaliacao() {
        return tipo_avaliacao;
    }

    public void setTipo_avaliacao(String tipo_avaliacao) {
        this.tipo_avaliacao = tipo_avaliacao;
    }

    public String getDisciplina_avaliacao() {
        return disciplina_avaliacao;
    }

    public void setDisciplina_avaliacao(String disciplina_avaliacao) {
        this.disciplina_avaliacao = disciplina_avaliacao;
    }

    public int getContagem_dias() {
        return contagem_dias;
    }

    public void setContagem_dias(int contagem_dias) {
        this.contagem_dias = contagem_dias;
    }
}
