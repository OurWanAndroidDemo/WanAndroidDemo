package com.example.wanandroiddemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

/**
 * @author zt
 * @date 2021/5/30 21:56
 * @description BaseActivity
 **/
public abstract class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // 获取布局文件
        setContentView(getLayoutId());
        //初始化控件 设置了ButterKnife 初始化控件就不需要了
        initView();
        //设置数据
        initData();
        //设置监听
        initListener();
    }

    /**
     * 初始化view
     **/
    protected abstract void initView();

    /**
     * 初始化data
     */
    protected abstract void initData();

    /**
     * 初始化listener
     */
    protected abstract void initListener();

    /**
     * 布局文件
     *
     * @return 布局文件id
     */
    protected abstract int getLayoutId();
}
