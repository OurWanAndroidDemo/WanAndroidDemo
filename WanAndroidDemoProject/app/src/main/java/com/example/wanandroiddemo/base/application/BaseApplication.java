package com.example.wanandroiddemo.base.application;

import android.app.Application;
import android.content.Context;

import com.example.wanandroiddemo.base.crashhandler.CrashHandlerException;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: BaseApplication
 * @Description: application基类
 * @date 2021/5/30 22:52
 */
public class BaseApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        //初始化crashHandler
        CrashHandlerException.getInstance().init(sContext);
    }

    /**
     * 获取app的context
     *
     * @return app的Context
     */
    public static Context getAppContext() {
        return sContext;
    }
}
