package usuario.app.temaula.bd;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Denis on 09/01/2016.
 */
public class Disciplinas extends RealmObject implements Serializable{

    @Index
    @Required
    @PrimaryKey
    private long id;
    private String nome_disciplina;
    private String nome_professor;
    private String tipo_disciplina;
    private int color_id;
    private String sala_disciplina;
    private boolean seg=false, ter=false, qua=false, qui=false, sex=false, sab=false;

    public Disciplinas(){}

    public Disciplinas(int id, String nome_disciplina, String nome_professor, String tipo_disciplina, int color_id) {
        this.id = id;
        this.nome_disciplina = nome_disciplina;
        this.nome_professor = nome_professor;
        this.tipo_disciplina = tipo_disciplina;
        //this.sala_disciplina = sala_disciplina;
        this.color_id = color_id;
    }

    public Disciplinas(int id,String nome_disciplina,String nome_professor, String tipo_disciplina,String sala_disciplina,int color_id) {
        this.id = id;
        this.tipo_disciplina = tipo_disciplina;
        this.nome_professor = nome_professor;
        this.nome_disciplina = nome_disciplina;
        this.sala_disciplina = sala_disciplina;
        this.color_id = color_id;
    }

    public Disciplinas(String nome_disciplina, String nome_professor, String tipo_disciplina) {
        this.id = id;
        this.nome_disciplina = nome_disciplina;
        this.nome_professor = nome_professor;
        this.tipo_disciplina = tipo_disciplina;
    }

    public Disciplinas(int id,String nome_disciplina, String nome_professor, String tipo_disciplina) {
        this.id = id;
        this.nome_disciplina = nome_disciplina;
        this.nome_professor = nome_professor;
        this.tipo_disciplina = tipo_disciplina;
    }

    public Disciplinas(int id, String nome_disciplina, String nome_professor){
        this.id = id;
        this.nome_disciplina = nome_disciplina;
        this.nome_professor = nome_professor;
    }

    public Disciplinas(String nome_disciplina, String nome_professor,int color_id){
        this.nome_disciplina = nome_disciplina;
        this.nome_professor = nome_professor;
        this.color_id = color_id;
    }

    public Disciplinas(String disciplina) {
        this.nome_disciplina = disciplina;
    }

    public Disciplinas(String disciplina, String professor, int id_color, int id) {
        this.id = id;
        this.nome_disciplina = disciplina;
        this.nome_professor = professor;
        this.color_id = id_color;
    }

    public Disciplinas(String disciplina, String professor, String tipo_disciplina, int color) {
        this.nome_disciplina = disciplina;
        this.nome_professor = professor;
        this.tipo_disciplina = tipo_disciplina;
        this.color_id = color;
    }


    public String getSala_disciplina() {
        return sala_disciplina;
    }

    public void setSala_disciplina(String sala_disciplina) {
        this.sala_disciplina = sala_disciplina;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getTipo_disciplina() {
        return tipo_disciplina;
    }

    public void setTipo_disciplina(String tipo_disciplina) {
        this.tipo_disciplina = tipo_disciplina;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public boolean isSeg() {
        return seg;
    }

    public void setSeg(boolean seg) {
        this.seg = seg;
    }

    public boolean isTer() {
        return ter;
    }

    public void setTer(boolean ter) {
        this.ter = ter;
    }

    public boolean isQua() {
        return qua;
    }

    public void setQua(boolean qua) {
        this.qua = qua;
    }

    public boolean isQui() {
        return qui;
    }

    public void setQui(boolean qui) {
        this.qui = qui;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isSab() {
        return sab;
    }

    public void setSab(boolean sab) {
        this.sab = sab;
    }
}
