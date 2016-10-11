package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.dataBase.EscapeBookDb;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class IncrementListPresenterImpl extends MvpBasePresenter<IncrementListView> implements
        IncrementListPresenter {


    private Context context;
    private Intent intent;
    private EscapeBookDb escapeBookDb;
    private Map<String, String> map;
    private ConductInfoData conductInfoData;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        escapeBookDb=new EscapeBookDb(context);
        conductInfoData=new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter() {
        List<JCEscapeBookData> list=escapeBookDb.getEscapeBook(Setting.USERID);
        if(list.size()>0){
            getView().setList(list);
            getView().hideLoging();
        }else{
            getView().hideLoging();
            getView().showMes("暂无数据");
        }

    }


    @Override
    public void deleteById(String id) {
        escapeBookDb.delData(id);
        getView().nextView();
    }

    @Override
    public void sendData(final JCEscapeBookData data) {
        getView().showLoding();
        map = new HashMap<>();
        map.put("json", ResponeUtils.dataToJson(data));

        conductInfoData.sendIncrement(map, new Callback<ResponeData>() {
            @Override
            public void success(ResponeData responeData, Response response) {
                getView().hideDialog();
                if (responeData.getData().getState() == responeData.SUCCESS) {
                    deleteById(data.getEscapeBookID());
                }else{
                    getView().showMes(responeData.getData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().hideDialog();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }


}
