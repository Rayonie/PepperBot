package com.example.my4throbotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.my4throbotapplication.databinding.ActivityInternBinding;
import com.example.my4throbotapplication.databinding.ActivityMainBinding;
import com.example.my4throbotapplication.intern.MenuDetailsActivity;
import com.example.my4throbotapplication.intern.utils.AnimationUtils;

import java.util.Locale;

public class InternActivity extends AppCompatActivity {
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