package com.example.nguyenducnhat.media_player;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle,txtTimeSong,txtTimeTotal;
    private SeekBar skSong;
    private Button btnPre,btnPlay,btnPause,btnNext;
    ArrayList<Song> arrySong;
    private  int postision = 0;

    private double startTime = 0;
    private  double finalTime = 0;
    private Handler myhandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private MediaPlayer mediaPlayer;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddEvent();
        AddSong();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Playing Sound",Toast.LENGTH_SHORT).show();
               mediaPlayer = MediaPlayer.create(MainActivity.this,arrySong.get(postision).getFile());
               mediaPlayer.start();
               finalTime = mediaPlayer.getDuration();
               startTime = mediaPlayer.getCurrentPosition();
               if(oneTimeOnly == 0)
               {
                   skSong.setMax((int) finalTime);
                   oneTimeOnly = 1;
               }
                txtTimeTotal.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );
                txtTimeSong.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                txtTitle.setText(arrySong.get(postision).getTitle());
                skSong.setProgress((int)startTime);
                myhandler.postDelayed(UpdateSongTime,100);
                btnPause.setEnabled(true);
                btnPlay.setEnabled(false);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                btnPlay.setEnabled(true);
                btnPause.setEnabled(false);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    txtTitle.setText(arrySong.get(postision).getTitle());
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    txtTitle.setText(arrySong.get(postision).getTitle());
                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            txtTimeSong.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            skSong.setProgress((int)startTime);
            myhandler.postDelayed(this, 100);
        }
    };
    private void AddSong()
    {
        arrySong = new ArrayList<>();
        arrySong.add(new Song("I wanna be a cat",R.raw.iwannabeacat));
        arrySong.add(new Song("Kill this love",R.raw.killthislove));

    }

    private void AddEvent()
    {
        btnNext = (Button)findViewById(R.id.btn_next);
        btnPause =(Button)findViewById(R.id.btn_pause);
        btnPlay =(Button)findViewById(R.id.btn_play);
        btnPre =(Button)findViewById(R.id.btn_previous);
        skSong = (SeekBar)findViewById(R.id.skSong);
        txtTimeSong = (TextView)findViewById(R.id.tv_TimeSong);
        txtTimeTotal = (TextView)findViewById(R.id.totalTime);
        txtTitle = (TextView)findViewById(R.id.tv_SongName);

        txtTitle.setText("Song.mp3");
        btnPause.setEnabled(false);
        skSong.setClickable(false);
    }

}
