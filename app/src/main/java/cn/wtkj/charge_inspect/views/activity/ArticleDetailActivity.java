package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

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


/**
 * Created by ghj on 2016/9/19.
 */
public class ArticleDetailActivity extends MvpBaseActivity<ArticleInfoPresenter> implements
        ArticleInfoView, View.OnClickListener {

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

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.addTime)
    TextView addTime;
    @Bind(R.id.htmlText)
    TextView htmlText;
    @Bind(R.id.checkAttachment)
    TextView checkAttachment;

    String articleId;
    ArticleDetail articleDetail;
    @Override
    protected ArticleInfoPresenter createPresenter() {
        return new ArticleInfoPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        articleId = intent.getStringExtra("articleId");
        //presenter.attachContextIntent(this, this.getIntent());
        presenter.getBusinessDetail(articleId);
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

    }

    @Override
    public void showDetail(ArticleDetail articleDetail) {
        this.articleDetail = articleDetail;

        title.setText(articleDetail.getMData().getInfo().getTitle());
        addTime.setText(articleDetail.getMData().getInfo().getTitle());
        htmlText.setText(Html.fromHtml(articleDetail.getMData().getInfo().getHtmlText()));
        List<ArticleDetail.MData.info.files> files = articleDetail.getMData().getInfo().getFiles();

        if (files.size() > 0) {
            checkAttachment.setVisibility(View.VISIBLE);
        }
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

    @OnClick({R.id.iv_left})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.checkAttachment:
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("fileList", (Serializable) articleDetail.getMData().getInfo().getFiles());

                Intent intent = new Intent();
                intent.setClass(ArticleDetailActivity.this, ArticleAttachmentActivity.class);
                intent.putExtras(mBundle);
                ArticleDetailActivity.this.startActivity(intent);
                break;
        }
    }
}
