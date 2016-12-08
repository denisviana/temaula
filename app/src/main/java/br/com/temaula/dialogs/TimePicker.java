package usuario.app.temaula.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Denis on 15/01/2016.
 */
public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog timePicker;
    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
    private int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);

    public static TimePicker newInstance(int btHoraInicial, int btHoraFinal, int btId){
        TimePicker dialog = new TimePicker();
        Bundle args = new Bundle();
        args.putInt("btHoraInicial",btHoraInicial);
        args.putInt("btHoraFinal", btHoraFinal);
        args.putInt("btID", btId);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        timePicker = new TimePickerDialog(getActivity(),this, hourOfDay,minute,true);


        return timePicker;
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            String hora = String.format("%02d:%02d",hourOfDay,minute);

        int btId = getArguments().getInt("btID");
        int btHoraInicial = getArguments().getInt("btHoraInicial");
        int btHoraFinal = getArguments().getInt("btHoraFinal");

        if(btId == btHoraInicial){
            selecionar_horario.timePickerHorarioInicial(hora);
        } else if (btId == btHoraFinal){
            selecionar_horario.timePickerHorarioFinal(hora);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        selecionar_horario = (selecionaHorario) activity;
    }

    private selecionaHorario selecionar_horario;

    public interface selecionaHorario{
        public void timePickerHorarioInicial(String hora);
        public void timePickerHorarioFinal(String hora);
    }
}
