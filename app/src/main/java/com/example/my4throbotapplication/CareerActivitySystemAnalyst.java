package com.example.my4throbotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class CareerActivitySystemAnalyst extends CareerActivityUIDesigner implements RobotLifecycleCallbacks {

    ImageView career_ui, career_ux, career_develop, career_analyst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_systemanalyst);
        QiSDK.register(this, this);

        career_ui = findViewById(R.id.planet3);
        career_ux = findViewById(R.id.planet4);
        career_develop = findViewById(R.id.planet1);
        career_analyst = findViewById(R.id.planet2);


        career_ux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivitySystemAnalyst.this,
                        CareerActivityUXDesigner.class);
                startActivity(i);
            }
        });

        career_develop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivitySystemAnalyst.this,
                        CareerActivityDeveloper.class);
                startActivity(i);
            }
        });

        career_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivitySystemAnalyst.this,
                        CareerActivityUIDesigner.class);
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
                .withText("wvooooouuuuuuuuuuuuuuuuu! Here, we can learn about being a System Analyst!" +
                        "System analysts play an absolutely essential part in the economy by ensuring the organizations have well designed, well maintained and functioning IT equipment. They help organizations to be more efficient, more secure and to reduce costs. Without them, businesses would be stuck and not able to progress and, therefore, the work of each and every systems analyst is incredibly meaningful!") // Set the text to say.
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