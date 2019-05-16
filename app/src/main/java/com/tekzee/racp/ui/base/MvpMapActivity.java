package com.tekzee.racp.ui.base;

import android.os.Bundle;

public abstract class MvpMapActivity<P extends BasePresenter> extends BaseMapActivity{
    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

}
