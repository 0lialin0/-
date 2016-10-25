package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
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
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class OutListSelPresenterImpl extends MvpBasePresenter<OutListSelView> implements
        OutListSelPresenter {


    private Context context;
    private ConductInfoDataImpl nameRollAddData;
    private ConstAllDb constAllDb;
    private OrganizationDb organizationDb;
    private ConductInfoData conductInfoData;
    public Map<String, String> map;

    public OutListSelPresenterImpl(Context context) {
        this.context = context;
        nameRollAddData = new ConductInfoDataImpl(context);
        constAllDb = new ConstAllDb(context);
        organizationDb = new OrganizationDb(context);
        conductInfoData = new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter(OutListParamData data) {
        /*getView().showLoding();
        map.put("json", ResponeUtils.dataToJson(data));
        conductInfoData.outListSel(map, new Callback<OutListData>() {
            @Override
            public void success(OutListData outListData, Response response) {
                getView().hideLoging();
                if (outListData.getMData().getState() == outListData.SUCCESS) {
                    List<OutListData.MData.info> data = outListData.getMData().getInfo();
                } else {
                    getView().showToast(outListData.getMData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().hideLoging();
                getView().showToast(ResponeData.NET_ERROR);
            }
        });*/
    }




    @Override
    public List<ViewOrganizationData.MData.info> getOrg(int orgId, String orgLevel) {
        List<ViewOrganizationData.MData.info> list = organizationDb.getCheckUnit(orgId, orgLevel);
        return list;
    }

    @Override
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type = constAllDb.getConstList(type);
        return list_type;
    }


}
