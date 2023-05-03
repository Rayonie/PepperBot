package com.example.my4throbotapplication;

//import com.aldebaran.qi.sdk;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Say;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    Button Coursebtn,Careerbtn, Gamebtn , Internbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
        setContentView(R.layout.activity_main);

        Coursebtn =findViewById(R.id.Coursebtn);
        Careerbtn =findViewById(R.id.Careerbtn);
        Gamebtn =findViewById(R.id.Gamebtn);
        Internbtn =findViewById(R.id.Internbtn);

        Coursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        CourseActivity.class);
                startActivity(i);
            }
        });

        Careerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        CareerActivity.class);
                startActivity(i);
            }
        });

        Gamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        GameActivity.class);
                startActivity(i);
            }
        });

        Internbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        InternActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // The robot focus is gained.
        // Create a new say action.
        //Say say = SayBuilder.with(qiContext) // Create the builder with the context.
        //        .withText("Hello human! how are you?") // Set the text to say.
        //        .build(); // Build the say action.// Create a new say action.

        // Create the second action.
        // Create an animation object.
        //Animation myAnimation = AnimationBuilder.with(qiContext)
        //        .withResources(R.raw.elephant_a001)
        //        .build();
        //Animate animate = AnimateBuilder.with(qiContext)
        //        .withAnimation(myAnimation)
        //        .build();

        // Execute the action.
        //say.run();
        //animate.run();
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}