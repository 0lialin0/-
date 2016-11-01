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
import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.bean.OutListInfoData;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.MultipartRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import retrofit.Callback;

/**
 * Created by lxg on 2015/11/9.
 */
public class ConductInfoDataImpl implements ConductInfoData {
    private final DangerousApi dangerousApi;
    private Context context;

    public ConductInfoDataImpl(Context context) {
        this.context = context;
        dangerousApi = SeApiManager.apiMangerAdapter();
    }


    @Override
    public void nameRoll(Map<String, String> mapString, List<String> fileNames, List<File> files,
                         int type, final DataRequester.DataCallBack<ResponeData> callBack) {
        String url = "";
        if (type == 1) {
            url = DangerousApi.END_POINT + "/restApi?businessId=gray.grayAct";
        } else if (type == 2) {
            url = DangerousApi.END_POINT + "/restApi?businessId=yellow.yellowAct";
        } else {
            url = DangerousApi.END_POINT + "/restApi?businessId=black.blackAct";
        }

        MultipartRequester multipartRequester = new MultipartRequester(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.error(error);
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


    //增收上传
    @Override
    public void sendIncrement(Map<String, String> map, Callback<ResponeData> callback) {
        dangerousApi.sendIncrement(map, callback);
    }

    //下发名单查询
    @Override
    public void selNameXiafa(Map<String, String> map, Callback<NameRollXiafaData> callback) {
        dangerousApi.selNameXiafa(map, callback);
    }

    @Override
    public void sendXiafaHandle(Map<String, String> map, Callback<ResponeData> callback) {
        dangerousApi.sendXiafaHandle(map, callback);
    }

    @Override
    public void sendXiafaInfo(Map<String, String> map, int type, Callback<BlackListData> callback) {
        if (type == 0) {
            dangerousApi.getBlackInfo(map, callback);
        } else if (type == 1) {
            dangerousApi.getGrayInfo(map, callback);
        } else if (type == 2) {
            dangerousApi.getYellowInfo(map, callback);
        }

    }

    //绿通档案
    @Override
    public void greenRecord(Map<String, String> map, List<String> fileNames, List<File> files,
                            final DataRequester.DataCallBack<ResponeData> callBack) {
        MultipartRequester multipartRequester = new MultipartRequester(DangerousApi.END_POINT + "/restApi?businessId=green.greenRecordAct",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.error(error);
                    }
                }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                callBack.success(gson.fromJson(response, ResponeData.class));
            }
        }, fileNames, files, map);
        multipartRequester.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        DataRequester.getInstance(context).add(multipartRequester);
    }

    //流水查询
    @Override
    public void outListSel(Map<String, String> map, Callback<OutListData> callback) {
        dangerousApi.outListSel(map, callback);
    }

    //流水查询详情
    @Override
    public void outListSelInfo(Map<String, String> map, Callback<OutListInfoData> callback) {
        dangerousApi.outListSelInfo(map, callback);
    }
}
