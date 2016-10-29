package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import cn.wtkj.charge_inspect.data.rest.BusinessInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.ArticleInfoView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class ArticleInfoPresenterImpl extends MvpBasePresenter<ArticleInfoView> implements
        ArticleInfoPresenter {

    private Context context;
    List<String> businessInfoDataList;
    private BusinessInfoDataImpl businessInfoDataImpl;
    public Map<String, Integer> map;
    private List<ArticleListData.MData.info> dataList=new ArrayList<>();

    public ArticleInfoPresenterImpl(Context context) {
        this.context = context;
        businessInfoDataImpl=new BusinessInfoDataImpl();
    }

    @Override
    public void startPresenter() {

    }

    /**
     * 获取业务单列表
     */
    @Override
    public void getArticleList(int page, int pagerSize) {
        map = new HashMap<>();
        map.put("PAGENUM",page);
        map.put("PAGESIZE",pagerSize);
        businessInfoDataImpl.getBusinessData(map,new Callback<ArticleListData>() {
            @Override
            public void success(ArticleListData articleListData, Response response) {
                getView().hideLoging();
                dataList.addAll(articleListData.getMData().getInfo());
                getView().showList(dataList);
            }

            @Override
            public void failure(RetrofitError error) {
                getView().showMes(error.getMessage());
            }
        });
    }

    /**
     * 获取业务单详情
     */
    public void getBusinessDetail(String articleId) {
        businessInfoDataImpl.getBusinessDetail(articleId,new Callback<ArticleDetail>() {
            @Override
            public void success(ArticleDetail businessDetail, Response response) {
                getView().showDetail(businessDetail);
            }

            @Override
            public void failure(RetrofitError error) {
                getView().showMes(error.getMessage());
            }
        });
    }
}
