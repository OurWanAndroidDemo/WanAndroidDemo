package com.example.wanandroiddemo.base.presenter;

import com.example.wanandroiddemo.base.model.IBaseModel;
import com.example.wanandroiddemo.base.view.IBaseView;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: BasePresenter
 * @Description: presenter基类
 * @date 2021/5/30 22:59
 */
public class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IPresenter<V,M> {
    /**
     * 弱引用view
     */
    protected WeakReference<V> mWeakView;
    /**
     * 弱引用model
     */
    protected WeakReference<M> mWeakModel;
    @Override
    public void attachView(V view) {
        mWeakView = new WeakReference(view);
    }

    @Override
    public void detachView() {
        if (mWeakView != null && mWeakView.get() != null) {
            mWeakView.clear();
            mWeakView = null;
        }
    }

    /**
     * P层获取View
     *
     * @return
     */
    @Nullable
    public V getView() {
        if (mWeakView != null) {
            return mWeakView.get();
        }
        return null;
    }

    @Override
    public void attachModel(M model) {
        mWeakModel = new WeakReference(model);
    }

    @Override
    public void detachModel() {
        if (mWeakModel != null && mWeakModel.get() != null) {
            mWeakModel.clear();
            mWeakModel = null;
        }
    }

    /**
     * 获取model
     *
     * @return model
     */
    @Nullable
    public M getModel() {
        if (mWeakModel != null) {
            return mWeakModel.get();
        }
        return null;
    }
}
