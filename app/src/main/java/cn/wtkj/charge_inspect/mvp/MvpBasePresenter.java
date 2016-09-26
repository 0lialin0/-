package cn.wtkj.charge_inspect.mvp;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @NonNull
    public V getView() {
        if (view == null) {
            throw new NullPointerException("MvpView reference is null. Have you called attachView()?");
        }
        return view;
    }

    //使用null-object模式，代理处一个对象
    @Override
    public void detachView(boolean retainInstance) {
        if (view != null) {
            Type[] types =
                    ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

            Class<V> viewClass = (Class<V>) types[0];
            view = NoOp.of(viewClass);
        }
    }
}
