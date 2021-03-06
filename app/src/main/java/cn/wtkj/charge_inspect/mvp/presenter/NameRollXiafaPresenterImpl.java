package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.data.dataBase.BlackListDb;
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
    public BlackListData.MData.info infoData;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        conductInfoData = new ConductInfoDataImpl(context);
    }

    @Override
    public BlackListData.MData.info getBlackData(String id, int type) {
        BlackListData blackListData = new BlackListData();
        BlackListData.MData mData = blackListData.new MData();
        infoData = mData.new info();
        map = new HashMap<>();
        if (type == 1) {
            map.put("BLACKLISTID", id);
        } else if (type == 2) {
            map.put("VEHICLEID", id);
        } else if (type == 3) {
            map.put("YLISTID", id);
        }
        conductInfoData.sendXiafaInfo(map, type, new Callback<BlackListData>() {
            @Override
            public void success(BlackListData data, Response response) {
                if (data.getMData().getState() == data.SUCCESS) {
                    infoData = data.getMData().getInfo();
                } else {
                    getView().showMes(data.getMData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().hideLoging();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });

        return infoData;
    }

    @Override
    public void startPresenter(String keyword,int page, int pagerSize) {
        if (!keyword.replaceAll(" ", "").equals("")) {
            //getView().showLoding();
            map = new HashMap<>();
            map.put("PAGENUM",page+"");
            map.put("PAGESIZE",pagerSize+"");
            map.put("VEHPLATENO", keyword);
            conductInfoData.selNameXiafa(map, new Callback<NameRollXiafaData>() {
                @Override
                public void success(NameRollXiafaData nameRollXiafaData, Response response) {
                    getView().hideLoging();
                    if (nameRollXiafaData.getMData().getState() == nameRollXiafaData.SUCCESS) {
                        List<NameRollXiafaData.MData.info> data = nameRollXiafaData.getMData().getInfo();
                        getView().setList(data);
                    } else {
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


}
