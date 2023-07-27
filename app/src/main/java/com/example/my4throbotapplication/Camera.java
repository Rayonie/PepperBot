package com.example.my4throbotapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

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

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.TakePictureBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.camera.TakePicture;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.image.EncodedImage;
import com.aldebaran.qi.sdk.object.image.EncodedImageHandle;
import com.aldebaran.qi.sdk.object.image.TimestampedImageHandle;

import java.nio.ByteBuffer;

public class Camera extends AppCompatActivity implements RobotLifecycleCallbacks {
    Button TakePicButton;
    // The button used to start take picture action.
    private Button button;
    // An image view used to show the picture.
    private ImageView pictureView;
    // The QiContext provided by the QiSDK.
    private QiContext qiContext;
    // TimestampedImage future.
    private Future<TimestampedImageHandle> timestampedImageHandleFuture;

    private Future<TakePicture> takePictureFuture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Register the RobotLifecycleCallbacks to this Activity.
            QiSDK.register(this, this);
            setContentView(R.layout.activity_camera);

            TakePicButton = findViewById(R.id.take_pic_button);
            pictureView = findViewById(R.id.picture_view);

            TakePicButton.setOnClickListener(v -> takePicture());

        }

    public void takePicture() {
        // Check that the Activity owns the focus.
        if (qiContext == null) {
            return;
        }

        // Disable the button.
        TakePicButton.setEnabled(false);

        Future<TimestampedImageHandle> timestampedImageHandleFuture = takePictureFuture.andThenCompose(takePicture -> {
            Log.i(TAG, "take picture launched!");
            return takePicture.async().run();
            });

        timestampedImageHandleFuture.andThenConsume(timestampedImageHandle -> {
            // Consume take picture action when it's ready
            Log.i(TAG, "Picture taken");
            // get picture
            EncodedImageHandle encodedImageHandle = timestampedImageHandle.getImage();

            EncodedImage encodedImage = encodedImageHandle.getValue();
            Log.i(TAG, "PICTURE RECEIVED!");

            // get the byte buffer and cast it to byte array
            ByteBuffer buffer = encodedImage.getData();
            buffer.rewind();
            final int pictureBufferSize = buffer.remaining();
            final byte[] pictureArray = new byte[pictureBufferSize];
            buffer.get(pictureArray);

            Log.i(TAG, "PICTURE RECEIVED! (" + pictureBufferSize + " Bytes)");
            // display picture
            Bitmap pictureBitmap = BitmapFactory.decodeByteArray(pictureArray, 0, pictureBufferSize);
            runOnUiThread(() -> pictureView.setImageBitmap(pictureBitmap));
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
            // Store the provided QiContext.
            this.qiContext = qiContext;

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
            // Remove the QiContext.
            this.qiContext = null;
        }

        @Override
        public void onRobotFocusRefused(String reason) {
            // The robot focus is refused.
        }

    }