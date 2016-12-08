package usuario.app.temaula.services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import usuario.app.temaula.activitys.MainActivity;
import usuario.app.temaula.R;
import usuario.app.temaula.adapter.AvisosAdapter;
import usuario.app.temaula.bd.Aviso;
import usuario.app.temaula.bd.AvisosDAO;

/**
 * Created by Dennis Viana on 14/07/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int ID_NOTIFICACAO = 1000;


            @Override
            public void onMessageReceived(RemoteMessage remoteMessage){
                super.onMessageReceived(remoteMessage);

                String details = remoteMessage.getData().get("details");
                int prioridade = Integer.parseInt(remoteMessage.getData().get("prioridade"));

                Log.d(TAG, "prioridade: " + prioridade);
                Log.d(TAG, "details: " + details);
                Log.d(TAG, "From: " + remoteMessage.getFrom());
                Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

                gravaNoBanco(prioridade,details);

                sendNotification(remoteMessage.getNotification().getBody());

            }

    private void gravaNoBanco(int prioridade, String details){
            Aviso aviso = new Aviso(details,prioridade);
            AvisosDAO avisosDAO = AvisosDAO.getInstance(this);
            AvisosAdapter avisosadapter = new AvisosAdapter(this);
            avisosDAO.save(aviso);
            List<Aviso> avisos = avisosDAO.listAvisos();
            avisosadapter.setItems(avisos);
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.book32px);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_horarios)
                .setLargeIcon(bmp)
                .setColor(0xff2ca5e0)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
