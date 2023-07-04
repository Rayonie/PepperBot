package com.example.my4throbotapplication.intern.utils;

import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.Random;

public class AnimationUtils {
   /**
    * 旋转动画
    * @param view
    * @param time
    */
   public static void rotateAnimation(View view, int time) {
      RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
      rotate.setInterpolator(new LinearInterpolator());
      rotate.setDuration(time);//设置动画持续周期
      rotate.setRepeatCount(-1);//设置重复次数
      rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
      rotate.setStartOffset(10);//执行前的等待时间
      view.startAnimation(rotate);
   }

   /**
    * 火箭动画
    * @param view
    */
   public static void startAppearanceAnimation(View view) {
      RotateAnimation rotate = new RotateAnimation(0f, 60f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
      AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
      ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2);

      AnimationSet animationSet = new AnimationSet(true);
      animationSet.setFillAfter(false);
      rotate.setStartOffset(10);//执行前的等待时间
      animationSet.setDuration(1000);
      animationSet.addAnimation(rotate);
      animationSet.addAnimation(scaleAnimation);
      animationSet.addAnimation(alphaAnimation);

      view.startAnimation(animationSet);
   }
   /**
    * 飞碟动画
    * @param view
    */
   public static void ufoAnimation(View view,float x,float y) {
      float toX = new Random().nextFloat();
      float toY = new Random().nextFloat();

      TranslateAnimation translateAni = new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, x, Animation.RELATIVE_TO_PARENT,
              toX, Animation.RELATIVE_TO_PARENT, y,
              Animation.RELATIVE_TO_PARENT, toY);
      //设置动画执行的时间，单位是毫秒
      translateAni.setDuration(new Random().nextInt(3000)+1000);
      // 设置动画重复次数
      // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
      translateAni.setRepeatCount(0);
      // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
      translateAni.setRepeatMode(Animation.RESTART);

      translateAni.setAnimationListener(new Animation.AnimationListener() {
         @Override
         public void onAnimationStart(Animation animation) {

         }

         @Override
         public void onAnimationEnd(Animation animation) {
            ufoAnimation(view, toX, toY);
         }

         @Override
         public void onAnimationRepeat(Animation animation) {

         }
      });
      view.startAnimation(translateAni);

   }
   /**
    * 动画平移
    *
    * @param view
    */
   public static void Translate(View view) {
      /*
       * TranslateAnimation translateAni = new TranslateAnimation(fromXType,
       * fromXValue, toXType, toXValue, fromYType, fromYValue, toYType,
       * toYValue);
       */
      //参数1～2：x轴的开始位置
      //参数3～4：y轴的开始位置
      //参数5～6：x轴的结束位置
      //参数7～8：x轴的结束位置
      TranslateAnimation translateAni = new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_PARENT,
              -1f, Animation.RELATIVE_TO_PARENT, -1,
              Animation.RELATIVE_TO_PARENT, 1f);
      //设置动画执行的时间，单位是毫秒
      translateAni.setDuration(5000);
      // 设置动画重复次数
      // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
      translateAni.setRepeatCount(-1);
      // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
      translateAni.setRepeatMode(Animation.RESTART);
      // 启动动画
      view.startAnimation(translateAni);
   }
}
