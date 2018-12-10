package com.example.aluno.carrieon.Telas;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Fragments.MaisFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class LembreteActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener{

    Button btn_lemb_ativ;
    Button btn_lemb_canc;
    ImageView img_lemb_close;
    TextView txt_lemb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembrete);

        btn_lemb_ativ = (Button) findViewById(R.id.btn_lemb_ativ);
        btn_lemb_canc = (Button) findViewById(R.id.btn_lemb_canc);
        img_lemb_close = (ImageView) findViewById(R.id.img_lemb_close);
        txt_lemb = (TextView) findViewById(R.id.txt_lemb);

        btn_lemb_ativ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        btn_lemb_canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

        img_lemb_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(LembreteActivity.this,MaisFragment.class));
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Lembrete definido para: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        txt_lemb.setText(timeText);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.cancel(pendingIntent);
        txt_lemb.setText("Lembrete cancelado!");
    }
}
