package com.khairulcreation.soundandvideoplayer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class SoundActivity extends Activity implements Runnable {

    private Button startButton;

    private Button stopButton;

    private Button pauseButton;

    private Button videoButton;

    private SeekBar soundSeekBar;

    private MediaPlayer soundPlayer;

    private Thread soundThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        startButton = (Button) findViewById(R.id.playButton);

        pauseButton = (Button) findViewById(R.id.pauseButton);

        stopButton = (Button) findViewById(R.id.stopButton);

        videoButton = (Button) findViewById(R.id.videoButton);

        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);

        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.pomdeter);



        setupListeners();



        soundThread = new Thread(this);

        soundThread.start();

    }

    private void setupListeners() {

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                soundPlayer.start();

            }

        });

        pauseButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                soundPlayer.pause();

            }

        });

        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View currentView) {

                soundPlayer.stop();

                soundPlayer =  MediaPlayer.create(getBaseContext(), R.raw.pomdeter);

            }

        });

        videoButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View currentView) {

                Intent intent = new Intent(currentView.getContext(), VideoActivity.class);

                startActivityForResult(intent, 0);

            }

        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser)

                {

                    soundPlayer.seekTo(progress);

                }

            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {



            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {



            }

        });

    }

    @Override

    public void run() {

        int currentPosition = 0;

        int soundTotal = soundPlayer.getDuration();

        soundSeekBar.setMax(soundTotal);



        while (soundPlayer != null && currentPosition < soundTotal)

        {

            try

            {

                Thread.sleep(300);

                currentPosition = soundPlayer.getCurrentPosition();

            } catch (InterruptedException soundException) {

                return;

            } catch (Exception otherException) {

                return;

            }

            soundSeekBar.setProgress(currentPosition);

        }

    }





    public void onPointerCaptureChanged(boolean hasCapture) {



    }

}

