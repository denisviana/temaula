package usuario.app.temaula.dialogs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;

import usuario.app.temaula.R;
import usuario.app.temaula.bd.DBHelper;
import usuario.app.temaula.bd.DisciplinaDAO;
import usuario.app.temaula.bd.Disciplinas;
import usuario.app.temaula.bd.DisciplinasContract;
import usuario.app.temaula.bd.HorariosDAO;

/**
 * Created by Denis on 06/01/2016.
 */
public class DialogAdicionarDisciplina extends DialogFragment {

    private AutoCompleteTextView add_disciplinas;
    private EditText add_professor, sala_disciplina;
    private Button bt_adicionar, bt_cancelar;
    private RadioGroup rg_tipo_disciplina;
    private RadioButton tipo_presencial,tipo_ead;
    private Disciplinas disciplinaObject;
    private HorariosDAO horariosDAO;
    private DisciplinaDAO disciplinaDAO;
    private String tipo_disciplina, sala;
    private boolean AlteracaoDados = false;
    Random random = new Random();
    private  int color, id;
    private String[] siglas;
    private String codSigla;


    public static DialogAdicionarDisciplina newInstace(String disciplina, String professor, String tipo, String sala, long id, int cor){
       DialogAdicionarDisciplina dialog = new DialogAdicionarDisciplina();
        Bundle bundle = new Bundle();
        bundle.putString("disciplina", disciplina);
        bundle.putString("professor", professor);
        bundle.putString("tipo_disciplina", tipo);
        bundle.putString("sala", sala);
        bundle.putInt("color", cor);
        bundle.putInt("id", id);
        dialog.setArguments(bundle);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Adicionar Disciplina");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_adiciona_disciplina, null);
        dialog.setView(v);

        add_disciplinas =
                (AutoCompleteTextView) v.findViewById(R.id.adicionar_disciplina);
        add_professor =
                (EditText) v.findViewById(R.id.edt_professor);
        sala_disciplina =
                (EditText) v.findViewById(R.id.disciplina_sala);
        bt_adicionar = (Button) v.findViewById(R.id.bt_adicionar);
        bt_cancelar = (Button) v.findViewById(R.id.bt_cancelar);
        rg_tipo_disciplina = (RadioGroup) v.findViewById(R.id.rg_tipo_disciplina);
        tipo_presencial = (RadioButton) v.findViewById(R.id.disciplina_presencial);
        tipo_ead = (RadioButton) v.findViewById(R.id.disciplina_ead);

        String disciplina = getArguments().getString("disciplina"); //Recebe os dados passados pela activity via Bundle para verificar se é uma alteração de dados
        String professor = getArguments().getString("professor"); //Recebe os dados passados pela activity via Bundle para verificar se é uma alteração de dados

        AlteracaoDados = alteracaoDeDados(disciplina, professor); //Chama o método que verifica se o usuário solicitou uma alteração de dados.

        disciplinaDAO = DisciplinaDAO.getInstance(getActivity());
        horariosDAO = HorariosDAO.getInstance(getActivity());

        if(tipo_presencial.isChecked()){
            tipo_disciplina="Presencial";
        }

        rg_tipo_disciplina.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              if(checkedId==R.id.disciplina_ead){
                  add_professor.setText("Tutor");
                  add_professor.setEnabled(false);
                  tipo_disciplina = "EAD";
               } else {
                  add_professor.setText(null);
                  add_professor.setEnabled(true);
                  tipo_disciplina = "Presencial";
              }
            }
        });
        siglas = getResources().getStringArray(R.array.siglas);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.disciplinas, android.R.layout.simple_list_item_1);

        add_disciplinas.setAdapter(adapter);

        bt_adicionar.setOnClickListener(new TrataEventoBtAdicionar());
        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return dialog.create();
    }

        //Classe responsável pro tratar os eventos dos botões do Dialog
    class TrataEventoBtAdicionar implements View.OnClickListener{

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {

            String cores;
            Color cor;
            String[] columns = {DisciplinasContract.Columns.NOME_DISCIPLINA};

            SQLiteDatabase db;
            DBHelper dbHelper = DBHelper.getInstance(getActivity());
            db = dbHelper.getWritableDatabase();


            String disciplina = add_disciplinas.getText().toString();
            String professor = add_professor.getText().toString();
            sala = sala_disciplina.getText().toString();
            String msg = "Disciplina "+disciplina+" adicionada com sucesso";
            boolean disciplinaExiste = false;

            //Aqui ele verifica se é uma alteração de dados ou não. Se não for, Verifica se a disciplina ja existe no Banco de Dados
            //se existir, ele retorna TRUE para a variável "disciplinaExiste"
            if(AlteracaoDados==false){
                try(Cursor c = db.query(DisciplinasContract.TABLE_NAME,columns,null,null,null,null,null)){
                    while(c.moveToNext()){
                        //Armazem a disciplina solicitada em um variável
                        String disciplinaBD = c.getString(c.getColumnIndex(DisciplinasContract.Columns.NOME_DISCIPLINA));
                        if(disciplinaBD.equals(disciplina)){  //Compara se a disciplina já existe no banco de dados
                            disciplinaExiste = true;
                            break;
                        }
                    }
                }
            }
                //Remove aviso de erro do campo Disciplina
                add_disciplinas.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        add_disciplinas.setError(null);
                    }
                });
                //Remove aviso de erro do campo Professor
                add_professor.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void afterTextChanged(Editable s) {
                        add_professor.setError(null);
                    }
                });

                //Se o usuário solicitou uma alteração de dados, grava os dados alterados numa instância da classe Disciplinas
                //Em seguida chama o método UPDATE da instância de DisciplinasDAO para fazer a alteraçao no banco de dados
                if(AlteracaoDados) {
                    Disciplinas disciplinas = new Disciplinas();

                    disciplinas.setId(id);
                    disciplinas.setNome_disciplina(disciplina);
                    disciplinas.setNome_professor(professor);
                    disciplinas.setTipo_disciplina(tipo_disciplina);
                    disciplinas.setSala_disciplina(sala);
                    disciplinas.setColor_id(color);
                    disciplinaDAO.update(disciplinas);
                    Toast.makeText(getActivity(),"Disciplina alterada com sucesso",Toast.LENGTH_LONG).show();
                    addDisciplinas.btDialogAdicionarDisciplina();
                    getDialog().dismiss();
                } else if(disciplinaExiste){ //Se "disciplinaExiste" for TRUE, informa o usuário que a disciplina já está cadastrada
                    Toast.makeText(getActivity(),"Disciplina já cadastrada. Insira uma nova disciplina",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(disciplina)) { //Se o campo disciplina estiver vazio, informa uma mensagem de erro.
                    add_disciplinas.setError("Entre com a Disciplina.");
                    add_disciplinas.requestFocus();
                } else if (TextUtils.isEmpty(professor)) { //Se o campo professor estiver vazio, informa uma mensagem de erro.
                    add_professor.setError("Entre com o nome do Professor.");
                    add_professor.requestFocus();
                }else{
                    //Caso AlteracaoDados seja "FALSE", realiza a inserção dos dados no banco de dados.
                    cores = String.format("#%06X",random.nextInt(0xFFFFFF+1));
                    cor = new Color();
                    color = cor.parseColor(cores);

                    disciplinaObject = new Disciplinas(disciplina, professor, tipo_disciplina,color);
                    disciplinaDAO.save(disciplinaObject);

                    add_disciplinas.setText(null);
                    add_disciplinas.requestFocus();
                    add_professor.setText(null);
                    tipo_presencial.setChecked(true);

                    Toast toast = Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG);
                    toast.show();
                    addDisciplinas.btDialogAdicionarDisciplina();
                }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Ajusta o Dialog para que os botões não fiquem atrás do teclado
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //Faz avançar para o próximo EditText através da tecla enter do teclado do Android
        add_disciplinas.setNextFocusDownId(R.id.adicionar_disciplina);

        add_disciplinas.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    add_disciplinas.clearFocus();
                    add_professor.requestFocus();
                    return true;
                }
                return false;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    //Verifica se o usuário solicitou alteração de disciplinas

    private boolean alteracaoDeDados(String disciplina, String professor){
        //Verifica se as variáveis passadas pelo bundle estão vazias,
        //se não estiverem, preenche os campos vazios com os dados da disciplina que será alterada,
        //além de alterar o texto do botão e no final retorna TRUE;
        add_disciplinas.setText(disciplina);
        if(disciplina!=null && professor!=null){
            add_professor.setText(professor);
            String tipo = getArguments().getString("tipo_disciplina");
            if(tipo.equals("Presencial")){
                tipo_presencial.setChecked(true);
            } else if (tipo.equals("EAD")){
                tipo_ead.setChecked(true);
                add_professor.setText("Tutor");
                add_professor.setEnabled(false);
                tipo_disciplina = "EAD";
            }
            sala = getArguments().getString("sala");
            color = getArguments().getInt("color");
            id = getArguments().getInt("id");
            bt_adicionar.setText("Alterar");

            return true;

        } else return false;
    }

   public interface AdicionaDisciplinas {
       public void btDialogAdicionarDisciplina();
        public void btDialogCancelar();
    }

    private  AdicionaDisciplinas addDisciplinas;

   @Override
   public void onAttach(Activity activity) {
       super.onAttach(activity);
       addDisciplinas = (AdicionaDisciplinas) activity;
   }
}
