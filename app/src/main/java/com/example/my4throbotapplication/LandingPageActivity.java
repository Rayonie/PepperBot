package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.touch.Touch;

import java.util.Timer;
import java.util.TimerTask;

public class LandingPageActivity extends AppCompatActivity implements RobotLifecycleCallbacks {

    private MediaPlayer mediaPlayer;

    private float volume = 1.0f; // 1.0f is the default maximum volume (full volume)
    Timer timer;

    VideoView landing_vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        landing_vv = findViewById(R.id.landingvideoView);
        QiSDK.register(this, this);

        mediaPlayer = MediaPlayer.create(this, R.raw.rocketsound);
        mediaPlayer.setVolume(volume, volume); // Set initial volume for both left and right channels

        // Set an event listener for when the audio finishes playing
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Release resources after audio finishes playing
                mediaPlayer.release();
            }
        });

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.rocketsound);

        // Set an event listener for when the audio finishes playing
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Release resources after audio finishes playing
                mediaPlayer.release();
            }
        });

        // Start playing the audio automatically
        mediaPlayer.start();


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);



        String path = "android.resource://com.example.my4throbotapplication/"+R.raw.main;
        Uri u = Uri.parse(path);
        landing_vv.setVideoURI(u);
        landing_vv.start();



        landing_vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false);
            }

        });


    }



    @Override
    protected void onPostResume() {
        landing_vv.resume();
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        landing_vv.resume();
        super.onResume();
    }


    @Override
    protected void onPause() {
        landing_vv.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        landing_vv.stopPlayback();
        super.onDestroy();
        // Release resources when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Say say1 = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Welcome aboard fellow astronauts! Let's go an adventure shall we?") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.wolf_a001)
                .build();
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        animate.async().run();
        say1.async().run();
    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}