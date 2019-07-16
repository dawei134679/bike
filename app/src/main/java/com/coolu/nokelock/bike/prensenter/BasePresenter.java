package com.coolu.nokelock.bike.prensenter;


import com.coolu.nokelock.bike.view.impl.MainViewImpl;

/**
 * 作者: Sunshine
 * 时间: 2017/4/20.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class BasePresenter<T extends MainViewImpl> implements Presenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView(T view) {
        mView = null;
    }



    public T getView() {
        return mView;
    }
}
