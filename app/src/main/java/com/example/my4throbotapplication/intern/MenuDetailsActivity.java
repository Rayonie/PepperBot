package com.example.my4throbotapplication.intern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.my4throbotapplication.R;
import com.example.my4throbotapplication.databinding.ActivityMenuDetailsBinding;
import com.example.my4throbotapplication.intern.utils.AnimationUtils;


public class MenuDetailsActivity extends AppCompatActivity {

    protected ActivityMenuDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AnimationUtils.rotateAnimation(binding.ivStar1,20000);
        AnimationUtils.rotateAnimation(binding.ivStar2,15000);
        AnimationUtils.rotateAnimation(binding.ivStar4,10000);

        int type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                replaceFragment(new Menu1Fragment());
                break;
            case 2:
                replaceFragment(new Menu2Fragment());
                break;
            case 3:
                replaceFragment(new Menu3Fragment());
                break;
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimationUtils.rotateAnimation(binding.ivStar1,20000);
        AnimationUtils.rotateAnimation(binding.ivStar2,15000);
        AnimationUtils.rotateAnimation(binding.ivStar4,10000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.ivStar1.clearAnimation();
        binding.ivStar2.clearAnimation();
        binding.ivStar4.clearAnimation();

    }

    /**
     * 切换fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        //获取Fragment的管理器类 FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //获取 FragmentManager中用于 Fragment 替换之类的类 FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //创建一个替换Fragment的事件
        transaction.replace(R.id.fl_frame,fragment);   // 替换的是fl_frame中的fragment
        //将新的Fragment对象压入一个栈内，点击back会进行回退，而非退出app
        transaction.addToBackStack(null);
        //提交事件
        transaction.commit();
    }

}