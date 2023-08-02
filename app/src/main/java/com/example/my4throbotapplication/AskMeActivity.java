package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

public class AskMeActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private Chat chat;

    private TouchSensor headTouchSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QiSDK.register(this, this);
        setContentView(R.layout.activity_ask_me);


    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Hello fellow astronaut! how are you? Ask me anything about school and I will answer you!") // Set the text to say.
                .build(); // Build the say action.// Create a new say action.

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.raise_left_hand_b004)
                .build();
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        // Execute the action.
        say.run();
        animate.async().run();

        Topic topic = TopicBuilder.with(qiContext)
                .withResource(R.raw.chat_about_school)
                 .build();

        QiChatbot qiChatbot = QiChatbotBuilder.with(qiContext)
                .withTopic(topic)
                .build();

        chat = ChatBuilder.with(qiContext)
                .withChatbot(qiChatbot)
               .build();
        chat.addOnStartedListener(()-> Log.i(TAG,"Discussion Started."));
        Future<Void> chatFuture = chat.async().run();

        chatFuture.thenConsume(future -> {
            if (future.hasError()) {
                Log.e(TAG, "Discussion finished with error.", future.getError());
            }
        });

        Touch touch = qiContext.getTouch();

        // Get the head touch sensor.
        headTouchSensor = touch.getSensor("Head/Touch");

        // Add onStateChanged listener.
        headTouchSensor.addOnStateChangedListener(touchState -> {
            Log.i(TAG, "Sensor " + (touchState.getTouched() ? "touched" : "released") + " at " + touchState.getTime());
            Intent i = new Intent(AskMeActivity.this,
                    MainActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
            if(chat!=null){
               chat.removeAllOnStartedListeners();
           }
    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}