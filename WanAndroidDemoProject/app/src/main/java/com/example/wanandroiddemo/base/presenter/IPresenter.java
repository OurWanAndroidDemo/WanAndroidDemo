package com.example.wanandroiddemo.base.presenter;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: IPresenter
 * @Description: presenter接口类
 * @date 2021/5/30 22:57
 */
public interface IPresenter<V,M> {
    /**
     * P层绑定V
     *
     * @param view view
     */
    void attachView(V view);

    /**
     * P层解绑View
     */
    void detachView();

    /**
     * P层绑定M
     *
     * @param model model
     */
    void attachModel(M model);

    /**
     * P层解绑Model
     */
    void detachModel();
}
