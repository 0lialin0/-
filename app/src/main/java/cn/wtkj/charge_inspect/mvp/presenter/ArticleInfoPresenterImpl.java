package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.util.List;

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
    public void getArticleList() {
        businessInfoDataImpl.getBusinessData(new Callback<ArticleListData>() {
            @Override
            public void success(ArticleListData articleListData, Response response) {
                getView().showList(articleListData);
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
