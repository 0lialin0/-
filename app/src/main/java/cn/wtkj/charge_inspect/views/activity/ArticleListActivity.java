package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.ArticleInfoPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.ArticleInfoPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.ArticleInfoView;
import cn.wtkj.charge_inspect.views.Adapter.ArticleListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;

/**
 * Created by ghj on 2016/9/19.
 */
public class ArticleListActivity extends MvpBaseActivity<ArticleInfoPresenter> implements
        ArticleInfoView, View.OnClickListener,OnItemClickListener3 {


    ArticleListData articleListData;

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;

    @Bind(R.id.laws_news_list)
    RecyclerView lawsNewsList;

    @Override
    protected ArticleInfoPresenter createPresenter() {
        return new ArticleInfoPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);
        //presenter.attachContextIntent(this, this.getIntent());
        presenter.getArticleList();
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.information);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        ivMore.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);


    }


    @Override
    public void showList(ArticleListData articleListData) {
        this.articleListData = articleListData;
        ArticleListAdapter adapter = new ArticleListAdapter(this, articleListData.getMData().getInfo());
        lawsNewsList.setLayoutManager(new LinearLayoutManager(this));
        lawsNewsList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoging() {

    }

    @Override
    public void nextView(String phone) {

    }

    @Override
    public void showMes(String msg) {

    }

    @Override
    public void showDetail(ArticleDetail businessDetail) {

    }

    @OnClick({R.id.iv_left})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;

        }
    }

    @Override
    public void onItemClick(String name, int id) {
        String articleId = articleListData.getMData().getInfo().get(id).getArticleId();

        Intent intent = new Intent();
        intent.setClass(this, ArticleDetailActivity.class);
        intent.putExtra("articleId", articleId);
        this.startActivity(intent);
    }
}
