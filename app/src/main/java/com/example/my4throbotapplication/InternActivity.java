package com.example.my4throbotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
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
import com.example.my4throbotapplication.databinding.ActivityInternBinding;
import com.example.my4throbotapplication.intern.MenuDetailsActivity;
import com.example.my4throbotapplication.intern.utils.AnimationUtils;

import java.util.Locale;

public class InternActivity extends AppCompatActivity  implements RobotLifecycleCallbacks {
    protected ActivityInternBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInternBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sta= getResources().getConfiguration().locale.getLanguage();
                translateText(sta);
            }
        });
        binding.tvMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtils.startAppearanceAnimation(binding.ivRocket);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(InternActivity.this, MenuDetailsActivity.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                    }
                }, 800);

            }
        });
        binding.tvMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtils.startAppearanceAnimation(binding.ivRocket);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(InternActivity.this, MenuDetailsActivity.class);
                        intent.putExtra("type", 2);
                        startActivity(intent);
                    }
                }, 800);

            }
        });
        binding.tvMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtils.startAppearanceAnimation(binding.ivRocket);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(InternActivity.this, MenuDetailsActivity.class);
                        intent.putExtra("type", 3);
                        startActivity(intent);
                    }
                }, 800);

            }
        });

        AnimationUtils.ufoAnimation(binding.ivUfo,0.5f,0.8f);

    }
    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Welcome to space intern part, in here you may know more information about your future.") // Set the text to say.
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

//
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

    @Override
    protected void onStart() {
        super.onStart();
        AnimationUtils.rotateAnimation(binding.ivStar1,20000);
        AnimationUtils.rotateAnimation(binding.ivStar2,15000);
        AnimationUtils.rotateAnimation(binding.ivStar3,30000);
        AnimationUtils.rotateAnimation(binding.ivStar4,10000);
        AnimationUtils.Translate(binding.ivMeteor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.ivStar1.clearAnimation();
        binding.ivStar2.clearAnimation();
        binding.ivStar3.clearAnimation();
        binding.ivStar4.clearAnimation();
        binding.ivMeteor.clearAnimation();

    }

    public void translateText(String sta){
        if (!sta.equals("en")){
            Resources resources = this.getResources();// 获得res资源对象
            Configuration config = resources.getConfiguration();// 获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.US; // 英文
            resources.updateConfiguration(config, dm);
            this.recreate();
        }else {
            //转换为中文
            Resources resources = this.getResources();// 获得res资源对象
            Configuration config = resources.getConfiguration();// 获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.SIMPLIFIED_CHINESE; // 英文
            resources.updateConfiguration(config, dm);
            this.recreate();
        }
    }
}