package usuario.app.temaula.bd;

import usuario.app.temaula.R;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class Aviso {

    private int id;
    private String titulo;
    private int ic_prioridade;

    public Aviso(){}

    public Aviso(int id, int prioridade, String titulo) {
        this.id=id;
        this.ic_prioridade = prioridade;
        this.titulo = titulo;
    }

    public Aviso(String titulo, int prioridade) {
        this.titulo = titulo;
        switch (prioridade){
            case 1:
                this.ic_prioridade = R.drawable.alertred;
                break;
            case 2:
                this.ic_prioridade = R.drawable.alertyellow;
                break;
            case 3:
                this.ic_prioridade = R.drawable.alertgreen;
                break;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIc_prioridade() {

        return ic_prioridade;
    }

    public void setIc_prioridade(int ic_prioridade) {

        switch (ic_prioridade){
            case 1:
                this.ic_prioridade = R.drawable.alertred;
                break;
            case 2:
                this.ic_prioridade = R.drawable.alertyellow;
                break;
            case 3:
                this.ic_prioridade = R.drawable.alertgreen;
                break;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
