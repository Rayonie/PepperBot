package com.example.my4throbotapplication;

//import com.aldebaran.qi.sdk;
import static android.content.ContentValues.TAG;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.TakePictureBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.camera.TakePicture;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.image.EncodedImage;
import com.aldebaran.qi.sdk.object.image.EncodedImageHandle;
import com.aldebaran.qi.sdk.object.image.TimestampedImageHandle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.nio.ByteBuffer;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {


    ImageButton Coursebtn,Careerbtn, Gamebtn , Internbtn;
    ImageView Cambtn;
    Button Askbtn;
    private Chat chat;
    // The button used to start take picture action.
    private Button button;
    // An image view used to show the picture.


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
        setContentView(R.layout.activity_main);


        Coursebtn = findViewById(R.id.Coursebtn);
        Careerbtn = findViewById(R.id.Careerbtn);
        Gamebtn = findViewById(R.id.Gamebtn);
        Internbtn = findViewById(R.id.Internbtn);
        Askbtn = findViewById(R.id.Askbtn);
        Cambtn = findViewById(R.id.Cambtn);


        Askbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        AskMeActivity.class);
                startActivity(i);
            }
        });

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
                        CareerActivityUIDesigner.class);
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

        Cambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        Camera.class);
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
        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Hello human! how are you?") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        // Create the second action.
        // Create an animation object.
        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.elephant_a001)
                .build();
        //Animate animate = AnimateBuilder.with(qiContext)
        //        .withAnimation(myAnimation)
        //        .build();

        // Execute the action.
        say.run();
        //animate.run();

        //Topic topic = TopicBuilder.with(qiContext)
        //        .withResource(R.raw.greetings)
       //         .build();

        //QiChatbot qiChatbot = QiChatbotBuilder.with(qiContext)
        //        .withTopic(topic)
        //        .build();

       // chat = ChatBuilder.with(qiContext)
        //        .withChatbot(qiChatbot)
         //       .build();
        //chat.addOnStartedListener(()-> Log.i(TAG,"Discussion Started."));
        //Future<Void> chatFuture = chat.async().run();
        //chatFuture.thenConsume(future -> {
        //    if (future.hasError()) {
        //        Log.e(TAG, "Discussion finished with error.", future.getError());
        //    }
        //});
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    //    if(chat!=null){
     //       chat.removeAllOnStartedListeners();
     //   }
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
    
    
}