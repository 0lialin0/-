package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.wtkj.charge_inspect.data.bean.BusinessInfoData;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.rest.BusinessInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.BusinessInfoView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class BusinessInfoPresenterImpl extends MvpBasePresenter<BusinessInfoView> implements
        BusinessInfoPresenter {

    private Context context;
    List<String> businessInfoDataList;
    private BusinessInfoDataImpl businessInfoDataImpl;
    public BusinessInfoPresenterImpl(Context context) {
        this.context = context;
        businessInfoDataImpl=new BusinessInfoDataImpl();
    }

    @Override
    public void startPresenter() {

    }

    public void getBusinessList() {
        businessInfoDataImpl.getBusinessData(new Callback<BusinessInfoData>() {
            @Override
            public void success(BusinessInfoData businessInfoData, Response response) {
                getView().showList(businessInfoData);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
