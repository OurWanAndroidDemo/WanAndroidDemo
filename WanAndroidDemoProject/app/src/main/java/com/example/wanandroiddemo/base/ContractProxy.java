package com.example.wanandroiddemo.base;

import android.util.Log;

import com.example.wanandroiddemo.base.model.IBaseModel;
import com.example.wanandroiddemo.base.presenter.BasePresenter;
import com.example.wanandroiddemo.base.view.IBaseView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: ContractProxy
 * @Description: ContractProxy
 * @date 2021/5/30 23:02
 */
public class ContractProxy {
    private static final String TAG = "ContractProxy";

    /**
     * 单例
     *
     * @return ContractProxy
     */
    public static ContractProxy getInstance() {
        return SingleTon.INSTANCE;
    }

    /**
     * Presenter
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings("unchecked")
    public static Class<BasePresenter> getPresenterClazz(final Class clazz, final int index) {

        //带泛型返回父类的类型
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BasePresenter.class;
        }

        //返回实际的参数类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return BasePresenter.class;
        }
        if (!(params[index] instanceof Class)) {
            return BasePresenter.class;
        }
        return (Class) params[index];
    }

    /***
     * 获取泛型对应的实际Presenter类型
     * @param <T>
     * @return
     */
    public <T> T getPresenterObject(Class clazz) {
        if (clazz == null) {
            return null;
        }
        BasePresenter basePresenter = null;
        try {
            basePresenter = (BasePresenter) clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.e(TAG,"P==" + basePresenter);
        }
        return (T) basePresenter;
    }

    /***
     * 将实际中的V层和P层绑定在一起
     * @param view view
     * @param presenter presenter
     * @param <V> V
     * @return V
     */
    @Nullable
    public <V> V bindView(IBaseView view, BasePresenter presenter) {
        if (view == null || presenter == null) {
            return null;
        }
        presenter.attachView(view);
        return (V) view;
    }

    /**
     * 将P和V层进行分离
     *
     * @param baseView view
     * @param presenter presenter
     */
    public void unBindView(IBaseView baseView, BasePresenter presenter) {
        if (presenter != null) {
            if (baseView != presenter.getView()) {
                presenter.detachView();
            }
        }
    }

    /**
     * P与M层绑定
     *
     * @param baseModel baseModel
     * @param presenter presenter
     * @param <M> M
     * @return M
     */
    @Nullable
    public <M> M bindModel(IBaseModel baseModel, BasePresenter presenter) {
        if (baseModel == null || presenter == null) {
            return null;
        }
        presenter.attachModel(baseModel);
        return (M)baseModel;
    }

    /**
     * 将P和M层进行分离
     *
     * @param baseModel baseModel
     * @param presenter presenter
     */
    public void unBindModel(IBaseModel baseModel, BasePresenter presenter) {
        if (presenter != null) {
            if (baseModel != presenter.getView()) {
                presenter.detachModel();
            }
        }
    }

    /**
     * 单例
     */
    private static class SingleTon {
        private static final ContractProxy INSTANCE = new ContractProxy();
    }
}
