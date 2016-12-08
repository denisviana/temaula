package usuario.app.temaula.bd;

/**
 * Created by Denis on 14/01/2016.
 */
public class Horarios  {

    public Horarios(){}

    private int id;
    private String dia_semana;
    private String disciplina;
    private String horario;
    private String professor;
    private String sala;
    private int iconID;
    private int colorID;
    private int id_disciplina;
    private int id_dia_semana;

    public Horarios(int id_disciplina, String horario, int iconID, int colorId) {
        this.id_disciplina = id_disciplina;
        this.horario = horario;
        this.colorID = colorId;
        this.iconID = iconID;
    }

    public Horarios(int id_dia_semana, int id_disciplina, String horario, int iconID, int colorId) {

        this.id_dia_semana = id_dia_semana;
        this.id_disciplina = id_disciplina;
        this.horario = horario;
        this.colorID = colorId;
        this.iconID = iconID;
    }


    public Horarios(int disciplina_horario, String horario_aula, int idIcon, int idColor,int id) {
        this.id=id;
        this.id_disciplina=disciplina_horario;
        this.horario=horario_aula;
        this.iconID = idIcon;
        this.colorID = idColor;
    }


    public Horarios(int id, int id_dia_semana,String disciplina_horario, String professor_horario, String horario_aula, int idIcon, int idColor) {
        this.id = id;
        this.id_dia_semana = id_dia_semana;
        this.disciplina = disciplina_horario;
        this.professor = professor_horario;
        this.horario = horario_aula;
        this.colorID = idColor;
        this.iconID = idIcon;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    private String diaSemana;

    public Horarios(int id, String disciplina, String horario, String professor, String sala, int iconID) {
        this.id = id;
        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.sala = sala;
        this.iconID = iconID;
    }

    public Horarios(int id, String disciplina, String horario, String professor, String sala, int iconID, int colorID, int id_disciplina) {
        this.id = id;
        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.sala = sala;
        this.iconID = iconID;
        this.colorID = colorID;
        this.id_disciplina = id_disciplina;
    }

    public Horarios(int id, String disciplina, String horario, String professor, String sala, int iconID, int colorID) {
        this.id = id;
        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.sala = sala;
        this.iconID = iconID;
        this.colorID = colorID;
    }

    public Horarios(String horario, int iconID) {
        this.horario = horario;
        this.iconID = iconID;
    }

    public Horarios(String disciplina, String horario, String professor, String sala, int iconID) {

        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.sala = sala;
        this.iconID = iconID;
    }

    public Horarios(String disciplina, String horario, String professor, String sala, int iconID, int colorID) {

        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.sala = sala;
        this.iconID = iconID;
        this.colorID = colorID;
    }

    public Horarios(String disciplina, String horario, String professor, int iconID) {
        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
        this.iconID = iconID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public int getId_dia_semana() {
        return id_dia_semana;
    }

    public void setId_dia_semana(int id_dia_semana) {
        this.id_dia_semana = id_dia_semana;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}
