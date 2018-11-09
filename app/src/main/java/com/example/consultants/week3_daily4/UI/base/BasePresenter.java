package com.example.consultants.week3_daily4.UI.base;

public interface BasePresenter <V extends BaseView>  {
    void attachView(V view);

    void detachView();
}
