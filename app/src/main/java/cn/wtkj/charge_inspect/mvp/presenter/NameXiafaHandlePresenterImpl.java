package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.BlackXiafaHandleData;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.mvp.views.NameXiafaHandleView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.activity.ArticleListActivity;
import cn.wtkj.charge_inspect.views.activity.GreenRecordListActivity;
import cn.wtkj.charge_inspect.views.activity.IncrementListActivity;
import cn.wtkj.charge_inspect.views.activity.NameRollManageActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class NameXiafaHandlePresenterImpl extends MvpBasePresenter<NameXiafaHandleView>
        implements NameXiafaHandlePresenter {

    private Context context;
    private ConductInfoData conductInfoData;
    private Map<String, String> map;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        conductInfoData = new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter(BlackXiafaHandleData data) {
        getView().showLoding();
        map = new HashMap<>();
        map.put("json", ResponeUtils.dataToJson(data));
        conductInfoData.sendXiafaHandle(map, new Callback<ResponeData>() {
            @Override
            public void success(ResponeData responeData, Response response) {
                getView().hideLoging();
                if (responeData.getData().getState() == responeData.SUCCESS) {
                    getView().nextView();
                } else {
                    getView().showMes(responeData.getData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().hideLoging();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }


}
