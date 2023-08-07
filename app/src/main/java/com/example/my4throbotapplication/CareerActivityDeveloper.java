package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.aldebaran.qi.sdk.object.touch.TouchSensor;

public class CareerActivityDeveloper extends CareerActivityUIDesigner implements RobotLifecycleCallbacks {

    ImageView career_ui, career_ux, career_develop, career_analyst;
    TextView tvdeveloperdescription;

    private TouchSensor headTouchSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_developer);
        QiSDK.register(this, this);

        career_ui = findViewById(R.id.planet3);
        career_ux = findViewById(R.id.planet4);
        career_develop = findViewById(R.id.planet1);
        career_analyst = findViewById(R.id.planet2);
        tvdeveloperdescription = findViewById(R.id.tvdeveloperdescription);

        // Set the initial alpha to 0 (fully transparent) and translationY to 100 pixels
        tvdeveloperdescription.setAlpha(0f);
        tvdeveloperdescription.setTranslationY(100f);

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
        tvdeveloperdescription.setBackground(shapeDrawable);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create ObjectAnimator for fade-in effect
                ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(tvdeveloperdescription, "alpha", 0f, 1f);
                fadeInAnimator.setDuration(fadeInDuration);

                // Create ObjectAnimator for move-up effect
                ObjectAnimator moveUpAnimator = ObjectAnimator.ofFloat(tvdeveloperdescription, "translationY", 100f, 0f);
                moveUpAnimator.setDuration(moveUpDuration);

                // Start the fade-in and move-up animations together
                fadeInAnimator.start();
                moveUpAnimator.start();
            }
        }, startDelay);

        career_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityDeveloper.this,
                        CareerActivityUIDesigner.class);
                startActivity(i);
            }
        });

        career_ux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityDeveloper.this,
                        CareerActivityUXDesigner.class);
                startActivity(i);
            }
        });

        career_analyst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityDeveloper.this,
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

    @Override
    protected void onDestroy() {
        career_vv1.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Here, we can learn about being a Mobile and Web Developer") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.raise_left_hand_b004)
                .build();
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        // Execute the action.
        say.run();
        animate.run();

        Touch touch = qiContext.getTouch();

        // Get the head touch sensor.
        headTouchSensor = touch.getSensor("Head/Touch");

        // Add onStateChanged listener.
        headTouchSensor.addOnStateChangedListener(touchState -> {
            Log.i(TAG, "Sensor " + (touchState.getTouched() ? "touched" : "released") + " at " + touchState.getTime());
            Intent i = new Intent(CareerActivityDeveloper.this,
                    MainActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
//        if(chat!=null){
//            chat.removeAllOnStartedListeners();
//        }
    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}