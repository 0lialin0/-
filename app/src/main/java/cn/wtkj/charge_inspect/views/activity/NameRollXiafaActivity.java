package cn.wtkj.charge_inspect.views.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollXiafaPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollXiafaPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollXiafaView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.views.custom.AlertDialogType;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

/**
 * Created by ghj on 2016/9/30.
 */
public class NameRollXiafaActivity extends MvpBaseActivity<NameRollXiafaPresenter> implements
        NameRollXiafaView, SwipeRefreshLayout.OnRefreshListener,
        RecyClerRefresh.RefreshData, View.OnClickListener {

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

    /*@Bind(R.id.shed_list_refresh)
    SwipeRefreshLayout shedRefresh;
    @Bind(R.id.shed_list_recy)
    RecyClerRefresh shedRecy;*/


    @Override
    protected NameRollXiafaPresenter createPresenter() {
        return new NameRollXiafaPresenterImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_roll_xiafa);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.name_roll_manage_xiafa);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
    }

    public void initView() {

        // 设置下拉组件动画偏移量
      /*  shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        shedRefresh.setRefreshing(true);// 显示动画
        presenter.attachContextIntent(this);
        presenter.startPresenter();
        shedRecy.setRefreshData(this);*/

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
    public void onRefresh() {

    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public void showList() {

    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoging() {
        //shedRefresh.setRefreshing(false);
    }

    @Override
    public void nextView() {

    }

    @Override
    public void showMes(String msg) {

    }
}
