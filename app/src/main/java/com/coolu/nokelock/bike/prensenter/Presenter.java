package com.coolu.nokelock.bike.prensenter;


import com.coolu.nokelock.bike.view.impl.MainViewImpl;

/**
 * 作者: Sunshine
 * 时间: 2017/4/20.
 * 邮箱: 44493547@qq.com
 * 描述: 解析器
 */

public interface Presenter <V extends MainViewImpl>{
    void attachView(V view);
    void detachView(V view);

}
