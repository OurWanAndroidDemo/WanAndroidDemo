package com.example.wanandroiddemo.base.crashhandler;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: CrashHandlerException
 * @Description: 异常捕获
 * @date 2021/5/30 22:54
 */
public class CrashHandlerException implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandlerException";

    /**
     * 系统默认的UncaughtExceptionHandler
     */
    private Thread.UncaughtExceptionHandler mDefaultException;

    /**
     * 上下文
     */
    private Context mContext;

    private static final Map<String, String> regexMap = new HashMap<>();

    private CrashHandlerException() {
    }

    /**
     * 单例获取当前实例
     *
     * @return CrashHanlderException
     */
    public static CrashHandlerException getInstance() {
        return SingleTon.INSTANCE;
    }

    /**
     * 初始化crashHandler
     *
     * @param context 上下文
     */
    public void init(Context context) {
        mContext = context;
        initMap();
        //获取系统的默认的异常处理
        mDefaultException = Thread.getDefaultUncaughtExceptionHandler();
        //设置该Default为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        handleException(throwable);
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex 异常
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 保存日志
        saveCrashInfo2File(ex);
        return true;
    }

    private void saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        Log.e(TAG, "crash exception" + sb.toString());
    }

    private static void initMap() {
        regexMap.put(".*NullPointerException.*", "NullPointerException");
        regexMap.put(".*ClassCastException.*", "ClassCastException");
        regexMap.put(".*ClassNotFoundException.*", "ClassNotFoundException");
        regexMap.put(".*ArithmeticException.*", "ArithmeticException");
        regexMap.put(".*ArrayIndexOutOfBoundsException.*",
                "ArrayIndexOutOfBoundsException");
        regexMap.put(".*IllegalArgumentException.*", "IllegalArgumentException");
        regexMap.put(".*IllegalAccessException.*", "IllegalAccessException");
        regexMap.put(".*SecturityException.*", "SecturityException");
        regexMap.put(".*NumberFormatException.*", "NumberFormatException");
        regexMap.put(".*OutOfMemoryError.*", "OutOfMemoryError");
        regexMap.put(".*StackOverflowError.*", "StackOverflowError");
        regexMap.put(".*RuntimeException.*", "RuntimeException");
    }

    private static class SingleTon {
        /**
         * 当前实例
         */
        private static final CrashHandlerException INSTANCE = new CrashHandlerException();
    }
}
