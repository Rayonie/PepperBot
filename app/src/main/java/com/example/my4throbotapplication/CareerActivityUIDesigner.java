package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.conversation.Topic;
import com.aldebaran.qi.sdk.object.touch.Touch;
import com.aldebaran.qi.sdk.object.touch.TouchSensor;

public class CareerActivityUIDesigner extends AppCompatActivity implements RobotLifecycleCallbacks{
    VideoView career_vv1;
    ImageView career_ui, career_ux, career_develop, career_analyst;
    private Chat chat;
    // Store the head touch sensor.
    private TouchSensor headTouchSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_uidesigner);
        QiSDK.register(this, this);

        career_vv1 = findViewById(R.id.careervideo1);
        career_ui = findViewById(R.id.planet3);
        career_ux = findViewById(R.id.planet4);
        career_develop = findViewById(R.id.planet1);
        career_analyst = findViewById(R.id.planet2);

        String path = "android.resource://"+getPackageName()+"/"+R.raw.careervid;
        Uri u = Uri.parse(path);
        career_vv1.setVideoURI(u);
        career_vv1.start();

        career_vv1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false);
            }
        });


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

        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("vrrrrrroooooooooommm! We have arrived at the career opportunities! Oh look! we have landed in career of UI Designer!" +
                        "The opportunity to be creative. As a UI designer, you’ll create beautiful, impactful products by mastering color, hierarchy, and iconography, and staying on top of typography trends." +
                        "The chance to make a difference in peoples lives. UI designers have the opportunity to shape the technology all around us by ensuring products are accessible, inclusive, and solves problems." +
                        "Flexibility and variety. As a UI designer, you’ll have the chance to work in a range of sectors—from banking to fashion, e-commerce to healthcare. You’ll also have options to work as a remote UI designer, freelance, or in-house etc.") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.raise_left_hand_b004)
                .build();
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        // Get the Touch service from the QiContext.
        Touch touch = qiContext.getTouch();

        // Get the head touch sensor.
        headTouchSensor = touch.getSensor("Head/Touch");

        // Add onStateChanged listener.
        headTouchSensor.addOnStateChangedListener(touchState -> {
            Log.i(TAG, "Sensor " + (touchState.getTouched() ? "touched" : "released") + " at " + touchState.getTime());
            say.run();
        });

        // Execute the action.

        animate.run();

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