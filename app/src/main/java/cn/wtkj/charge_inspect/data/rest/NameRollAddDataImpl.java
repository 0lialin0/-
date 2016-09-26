package cn.wtkj.charge_inspect.data.rest;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.SeApiManager;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.MultipartRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import retrofit.Callback;

/**
 * Created by lxg on 2015/11/9.
 */
public class NameRollAddDataImpl implements NameRollAddData {
    private final DangerousApi dangerousApi;
    private Context context;

    public NameRollAddDataImpl(Context context) {
        this.context = context;
        dangerousApi = SeApiManager.apiMangerAdapter();
    }


    @Override
    public void nameRoll(Map<String, String> mapString, List<String> fileNames, List<File> files,
                         final DataRequester.DataCallBack<ResponeData> callBack) {
        MultipartRequester multipartRequester = new MultipartRequester(DangerousApi.END_POINT + "/restApi?businessId=pass.check",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.error();
                    }
                }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                callBack.success(gson.fromJson(response, ResponeData.class));
            }
        }, fileNames, files, mapString);
        multipartRequester.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        DataRequester.getInstance(context).add(multipartRequester);
    }
}
