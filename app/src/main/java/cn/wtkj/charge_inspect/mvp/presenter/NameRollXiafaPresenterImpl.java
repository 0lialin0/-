package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.data.dataBase.EscapeBookDb;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.mvp.views.NameRollXiafaView;
import cn.wtkj.charge_inspect.util.Setting;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class NameRollXiafaPresenterImpl extends MvpBasePresenter<NameRollXiafaView> implements
        NameRollXiafaPresenter {


    private Context context;
    private Intent intent;
    private Map<String, String> map;
    private ConductInfoData conductInfoData;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        conductInfoData=new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter(String keyword) {
        getView().showLoding();
        map = new HashMap<>();
        map.put("VEHPLATENO", keyword);

        conductInfoData.selNameXiafa(map, new Callback<NameRollXiafaData>() {
            @Override
            public void success(NameRollXiafaData nameRollXiafaData, Response response) {
                getView().hideLoging();
                if (nameRollXiafaData.getMData().getState() == nameRollXiafaData.SUCCESS) {
                    List<NameRollXiafaData.MData.info> data = nameRollXiafaData.getMData().getInfo();
                    getView().setList(data);
                }else {
                    getView().showMes(nameRollXiafaData.getMData().getInfo().toString());
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
