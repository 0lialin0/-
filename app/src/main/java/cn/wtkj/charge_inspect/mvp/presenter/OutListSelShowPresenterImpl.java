package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.bean.OutListParamData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.data.dataBase.BlackListDb;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.OutListSelShowView;
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class OutListSelShowPresenterImpl extends MvpBasePresenter<OutListSelShowView> implements
        OutListSelShowPresenter {


    private Context context;
    private ConductInfoData conductInfoData;
    public Map<String, String> map;

    public OutListSelShowPresenterImpl(Context context) {
        this.context = context;
        conductInfoData = new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter(int page, int pagerSize,OutListParamData data) {
        //getView().showLoding();
        map = new HashMap<>();
        data.setPAGENUM(page);
        data.setPAGESIZE(pagerSize);
        map.put("json", ResponeUtils.dataToJson(data));
        conductInfoData.outListSel(map, new Callback<OutListData>() {
            @Override
            public void success(OutListData outListData, Response response) {
                //getView().hideDialog();
                getView().hideLoging();
                if (outListData.getMData().getState() == outListData.SUCCESS) {
                    List<OutListData.MData.info> data = outListData.getMData().getInfo();
                    getView().setList(data);
                } else {
                    getView().showToast(outListData.getMData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //getView().hideDialog();
                getView().hideLoging();
                getView().showToast(ResponeData.NET_ERROR);
            }
        });
    }



}
