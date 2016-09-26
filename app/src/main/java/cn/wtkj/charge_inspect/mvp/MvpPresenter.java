package cn.wtkj.charge_inspect.mvp;

import cn.wtkj.charge_inspect.mvp.MvpView;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView(boolean retainInstance);
}
