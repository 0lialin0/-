package cn.wtkj.charge_inspect.mvp.presenter;


import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.ArticleInfoView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface ArticleInfoPresenter extends MvpPresenter<ArticleInfoView> {
    void startPresenter();
    void getArticleList();
    void getBusinessDetail(String articleId);
}
