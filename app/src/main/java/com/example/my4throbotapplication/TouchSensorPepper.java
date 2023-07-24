package com.example.my4throbotapplication;

//import com.aldebaran.qi.sdk;
import static android.content.ContentValues.TAG;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.conversation.Topic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TouchSensorPepper extends RobotActivity implements RobotLifecycleCallbacks {

    private Chat chat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
        setContentView(R.layout.activity_touch_sensor_pepper);

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