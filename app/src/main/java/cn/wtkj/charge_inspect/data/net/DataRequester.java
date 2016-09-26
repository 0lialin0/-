package cn.wtkj.charge_inspect.data.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class DataRequester {

    private static RequestQueue queue;

    private DataRequester() {
    }

    public static RequestQueue getInstance(Context context) {
        if (queue == null) {
            synchronized (DataRequester.class) {
                if (queue == null) {
                    queue = Volley.newRequestQueue(context
                            .getApplicationContext());
                }
            }
        }
        return queue;
    }

    public interface DataCallBack<T> {
        void success(T t);

        void error();
    }
}
