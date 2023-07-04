package com.example.my4throbotapplication.intern;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my4throbotapplication.databinding.ActivityWebViewBinding;


public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String url = getIntent().getStringExtra("url");

        //设置支持js否则有些网页无法打开
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new MyClient());
        binding.webView.setWebChromeClient(new MyWebChromeClient());
        //加载网络url
        binding.webView.loadUrl(url);
    }

    class MyClient extends WebViewClient{
        //监听到页面发生跳转的情况，默认打开web浏览器
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //在webView中加载要打开的链接
            view.loadUrl(request.getUrl().toString());
            return true;
        }
        //页面开始加载
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e("onPageStarted", "onPageStarted: ");
        }
        //页面加载完成的回调方法
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e("onPageFinished", "onPageFinished: ");
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        //监听网页进度 newProgress进度值在0-100
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        //设置Activity的标题与 网页的标题一致
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果用户按的是返回键 并且WebView页面可以返回
        if (keyCode==event.KEYCODE_BACK&&binding.webView.canGoBack()){
            //让WebView返回
            binding.webView.goBack();
            return true;
        }
        //如果WebView不能返回 则调用默认的onKeyDown方法 退出Activity
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 返回按钮监听
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}