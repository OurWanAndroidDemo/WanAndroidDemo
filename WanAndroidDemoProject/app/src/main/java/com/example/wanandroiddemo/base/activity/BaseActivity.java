package com.example.wanandroiddemo.base.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.wanandroiddemo.base.ContractProxy;
import com.example.wanandroiddemo.base.presenter.BasePresenter;
import com.example.wanandroiddemo.base.view.IBaseView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: BaseActivity
 * @Description: activity基类
 * @date 2021/5/30 23:17
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity {
    public static final String TAG = "BaseActivity";

    protected Unbinder mUnbinder;

    protected P mPresenter;

    /**
     * 是否使用eventBus，（子类继承的时候实现）后面可以用rxBus来注册事件
     *
     * @return true
     */
    protected abstract boolean useEventBus();

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        mUnbinder = ButterKnife.bind(this);
        bindMvp();
        initView();
        initData(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        unBindMVP();
    }

    /**
     * 绑定mvp中的p层和M层。
     */
    private void bindMvp() {
        //获取运行中MVP的实际P层
        mPresenter = ContractProxy.getInstance().getPresenterObject(ContractProxy.getPresenterClazz(getClass(), 0));
        if (mPresenter != null && mPresenter.getView() != null) {
            ContractProxy.getInstance().bindView(mPresenter.getView(), mPresenter);
        }
        if (mPresenter != null && mPresenter.getModel() != null) {
            ContractProxy.getInstance().bindModel(mPresenter.getModel(), mPresenter);
        }
    }

    /**
     * 解绑MVP
     */
    private void unBindMVP(){
        if (mPresenter != null && mPresenter.getView() != null) {
            ContractProxy.getInstance().unBindView(mPresenter.getView(), mPresenter);
        }
        if (mPresenter != null && mPresenter.getModel() != null) {
            ContractProxy.getInstance().unBindModel(mPresenter.getModel(), mPresenter);
        }
    }
}
