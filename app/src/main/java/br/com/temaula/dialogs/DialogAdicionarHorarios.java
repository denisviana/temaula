package usuario.app.temaula.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import usuario.app.temaula.R;
import usuario.app.temaula.activitys.PlaceholderFragment;
import usuario.app.temaula.adapter.SpinnerAdapter;
import usuario.app.temaula.bd.DisciplinaDAO;
import usuario.app.temaula.bd.Disciplinas;
import usuario.app.temaula.bd.Horarios;
import usuario.app.temaula.bd.HorariosDAO;

/**
 * Created by Denis on 06/01/2016.
 */
public class DialogAdicionarHorarios extends Activity implements TimePicker.selecionaHorario {

    private final int DOMINGO=1, SEGUNDA=2, TERCA=3,
            QUARTA=4, QUINTA=5, SEXTA=6, SABADO=7;
    private long position, color, itemId;
    private Spinner spiner_horario;
    private Button btHorario_adicionar,
            btHorario_cancelar,
            btHora_inicio,
            btHora_final;
    private CheckBox cb_aula1, cb_aula2,
            cb_aula3, cb_aula4;
    private EditText sala_horario;
    private String hora = "Selecionar";
    private String spinner_professor;
    private long spinner_disciplina;
    private Horarios horariosObject;
    private HorariosDAO horarioDAO;
    private DisciplinaDAO disciplinaDAO;
    private SpinnerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        this.position = bundle.getInt("position");
        setContentView(R.layout.dialog_adiciona_horario);
        setTitle("Adicionar Horário");

        cb_aula1 = (CheckBox) findViewById(R.id.cb_aula1);
        cb_aula2 = (CheckBox) findViewById(R.id.cb_aula2);
        cb_aula3 = (CheckBox) findViewById(R.id.cb_aula3);
        cb_aula4 = (CheckBox) findViewById(R.id.cb_aula4);
        spiner_horario = (Spinner) findViewById(R.id.spiner_horarios);
        //btHora_inicio = (Button) findViewById(R.id.btHora_inicio);
        //btHora_final = (Button) findViewById(R.id.btHora_final);
        btHorario_adicionar = (Button) findViewById(R.id.btHorario_adicionar);
        btHorario_cancelar = (Button) findViewById(R.id.btHorario_cancelar);
        //btHora_inicio.setText(hora);
       // btHora_final.setText(hora);
        //sala_horario = (EditText) findViewById(R.id.edt_sala);
//        sala_horario.clearFocus();

        horarioDAO = HorariosDAO.getInstance(this);
        disciplinaDAO = DisciplinaDAO.getInstance(this);
        adapter = new SpinnerAdapter(this);

        List<Disciplinas> disciplinas = disciplinaDAO.listDisciplinaIndividual();
        adapter.setItems(disciplinas);

        spiner_horario.setAdapter(adapter);

        spiner_horario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Disciplinas disciplinas = (Disciplinas) adapter.getItem(position);
                spinner_disciplina = disciplinas.getId();
                spinner_professor = disciplinas.getNome_professor();
                color = disciplinas.getColor_id();
                itemId = disciplinas.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void bt_DialogHorario(View view){

        String  edt_sala = null,
                horaInicio,
                horario=null,
                horafim;

        if(view.getId()==btHorario_adicionar.getId()){

            if(cb_aula1.isChecked() &&
                    cb_aula2.isChecked() &&
                    cb_aula3.isChecked() &&
                    cb_aula4.isChecked()){
                horario = String.format("19:00 - 22:30");
            } else if(cb_aula1.isChecked() &&
                    cb_aula2.isChecked() &&
                    cb_aula3.isChecked() &&
                    !cb_aula4.isChecked()){
                horario = String.format("19:00 - 21:40");
            }else if(cb_aula1.isChecked() &&
                    cb_aula2.isChecked() &&
                    !cb_aula3.isChecked() &&
                    !cb_aula4.isChecked()){
                horario = String.format("19:00 - 20:40");
            } else if(cb_aula1.isChecked() &&
                    !cb_aula2.isChecked() &&
                    !cb_aula3.isChecked() &&
                    !cb_aula4.isChecked()){
                horario = String.format("19:00 - 19:50");
            } else if(!cb_aula1.isChecked() &&
                    !cb_aula2.isChecked() &&
                    cb_aula3.isChecked() &&
                    cb_aula4.isChecked()) {
                horario = String.format("21:00 - 22:30");
            } else {
                Toast.makeText(this,"Selecione os horários em ordem, ou adicione separadamente.", Toast.LENGTH_LONG).show();
                return;
            }

            if(!cb_aula1.isChecked() &&
                    !cb_aula2.isChecked() &&
                        !cb_aula3.isChecked() &&
                            !cb_aula4.isChecked()){
                Toast.makeText(this,"Por favor selecione o(s) horário(s) da aula.", Toast.LENGTH_LONG).show();
            } else {
//                edt_sala = sala_horario.getText().toString();
               // horaInicio = btHora_inicio.getText().toString();
               // horafim = btHora_final.getText().toString();

                //horario = String.format("%s - %s", horaInicio, horafim);

                //Disciplinas disciplinas = new Disciplinas();

                //disciplinas.setId(itemId);
                //disciplinas.setSala_disciplina(edt_sala);

                if(this.position==0){
                    horariosObject = new Horarios(SEGUNDA,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveSegunda(horariosObject);
                    PlaceholderFragment.newInstance(0);
                } else if(this.position==1){
                    horariosObject = new Horarios(TERCA,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveTerca(horariosObject);
                    PlaceholderFragment.newInstance(1);
                } else if(this.position==2) {
                    horariosObject = new Horarios(QUARTA,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveQuarta(horariosObject);
                    PlaceholderFragment.newInstance(2);
                } else if(this.position==3) {
                    horariosObject = new Horarios(QUINTA,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveQuinta(horariosObject);
                    PlaceholderFragment.newInstance(3);
                } else if(this.position==4) {
                    horariosObject = new Horarios(SEXTA,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveSexta(horariosObject);
                    PlaceholderFragment.newInstance(4);
                } else if(this.position==5) {
                    horariosObject = new Horarios(SABADO,spinner_disciplina, horario, R.drawable.clock128px,color);
                    //horarioDAO.saveSabado(horariosObject);
                    PlaceholderFragment.newInstance(5);
                }

                horarioDAO.saveHorarios(horariosObject);

                finish();
            }
        } else if(view.getId()==btHorario_cancelar.getId()){
            finish();
        }
    }

    public void timePicker(View view){
        int btHoraInicial = btHora_inicio.getId();
        int btHoraFinal = btHora_final.getId();

        TimePicker timepicker = TimePicker.newInstance(btHoraInicial,btHoraFinal,view.getId());
        timepicker.show(getFragmentManager(), "timePicker");
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void timePickerHorarioInicial(String hora) {
        btHora_inicio.setText(hora);
    }

    @Override
    public void timePickerHorarioFinal(String hora) {
        btHora_final.setText(hora);
    }
}
