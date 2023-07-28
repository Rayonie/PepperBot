package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.Color;


import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.touch.Touch;
import com.aldebaran.qi.sdk.object.touch.TouchSensor;

public class CareerActivityUIDesigner extends AppCompatActivity implements RobotLifecycleCallbacks{
    VideoView career_vv1;
    ImageView career_ui, career_ux, career_develop, career_analyst;
    private Chat chat;

    TextView tvuidescripition;
    // Store the head touch sensor.
    private TouchSensor headTouchSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_uidesigner);
        QiSDK.register(this, this);

        // Find the TextView by its id
        tvuidescripition = findViewById(R.id.tvuidescription);

        career_vv1 = findViewById(R.id.careervideo1);
        career_ui = findViewById(R.id.planet3);
        career_ux = findViewById(R.id.planet4);
        career_develop = findViewById(R.id.planet1);
        career_analyst = findViewById(R.id.planet2);

        String path = "android.resource://"+getPackageName()+"/"+R.raw.careervid;
        Uri u = Uri.parse(path);
        career_vv1.setVideoURI(u);
        career_vv1.start();

        // Set the initial alpha to 0 (fully transparent) and translationY to 100 pixels
        tvuidescripition.setAlpha(0f);
        tvuidescripition.setTranslationY(100f);

        // Define the delay before starting the animation in milliseconds
        int startDelay = 1500; // 1.5 seconds

        // Define the duration of the fade-in and move-up effects in milliseconds
        int fadeInDuration = 2000; // 2 seconds
        int moveUpDuration = 1500;

        // Create a ShapeDrawable with a background color (e.g., light gray)
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RectShape());
        shapeDrawable.getPaint().setColor(Color.parseColor("#90D3D3D3")); // Replace this with the desired color resource or an actual color

        // Set the ShapeDrawable as the background of the TextView
        tvuidescripition.setBackground(shapeDrawable);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create ObjectAnimator for fade-in effect
                ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(tvuidescripition, "alpha", 0f, 1f);
                fadeInAnimator.setDuration(fadeInDuration);

                // Create ObjectAnimator for move-up effect
                ObjectAnimator moveUpAnimator = ObjectAnimator.ofFloat(tvuidescripition, "translationY", 100f, 0f);
                moveUpAnimator.setDuration(moveUpDuration);

                // Start the fade-in and move-up animations together
                fadeInAnimator.start();
                moveUpAnimator.start();
            }
        }, startDelay);

//        career_vv1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.setLooping(false);
//            }
//        });


        career_ux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUIDesigner.this,
                        CareerActivityUXDesigner.class);
                startActivity(i);
            }
        });

        career_develop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUIDesigner.this,
                        CareerActivityDeveloper.class);
                startActivity(i);
            }
        });

        career_analyst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUIDesigner.this,
                        CareerActivitySystemAnalyst.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onPostResume() {
        career_vv1.resume();
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        career_vv1.resume();
        super.onResume();
    }


    @Override
    protected void onPause() {
        career_vv1.suspend();
        super.onPause();
    }

    //@Override
//    protected void onDestroy() {
//        career_vv1.stopPlayback();
//        super.onDestroy();
//    }

//    protected void onDestroy() {
//        super.onDestroy();
//        robotActivity.onDestroy();
//    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Say say1 = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("vrrrrrroooooooooommm! We have arrived at the career opportunities! Oh look! we have landed in career of UI Designer!") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        Say say2 = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("hmmmmmmmmmmmm please be gentle when you touch me.") // Set the text to say.
                .build();

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.wolf_a001)
                .build();
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        animate.run();
        say1.run();

        // Get the Touch service from the QiContext.
        Touch touch = qiContext.getTouch();

        // Get the head touch sensor.
        headTouchSensor = touch.getSensor("Head/Touch");

        // Add onStateChanged listener.
        headTouchSensor.addOnStateChangedListener(touchState -> {
            Log.i(TAG, "Sensor " + (touchState.getTouched() ? "touched" : "released") + " at " + touchState.getTime());
            say2.run();
        });

//
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
//        if(chat!=null){
//            chat.removeAllOnStartedListeners();
//        }
        // Remove onStateChanged listeners.
        if (headTouchSensor != null) {
            headTouchSensor.removeAllOnStateChangedListeners();
        }
    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}