package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordListPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordListPresenterImpl;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.views.Adapter.GreenRecordListAdapter;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

/**
 * Created by ghj on 2016/9/18.
 */
public class GreenRecordListActivity extends MvpBaseActivity<GreenRecordListPresenter> implements
        GreenRecordListView, MyPhotos.OnClickAddImgListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        RecyClerRefresh.RefreshData {

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_more)
    ImageView ivMore;

    @Bind(R.id.shed_list_refresh)
    SwipeRefreshLayout shedRefresh;
    @Bind(R.id.shed_list_recy)
    RecyClerRefresh shedRecy;

    private ProgressDialog progressDialog;
    private AlertDialog alter;


    @Override
    protected GreenRecordListPresenter createPresenter() {
        return new GreenRecordListPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_record_list);
        ButterKnife.bind(this);
        presenter.attachContextIntent(this, this.getIntent());
        presenter.startPresenter();
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.green_name);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }


    public void initView() {

        // 设置下拉组件动画偏移量
        shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        //presenter.attachContext(this);
        //presenter.stratPesenter(Setting.USER_ID, ++pager, size, MvpPatrolListPresenterImpl.DRIPSUBSTANCE);
        shedRefresh.setRefreshing(true);// 显示动画
        shedRecy.setRefreshData(this);

    }


    @Override
    public void setList() {
        List<String> str=new ArrayList<>();
        GreenRecordListAdapter adapter=new GreenRecordListAdapter(this,str);
        shedRecy.setAdapter(adapter);
    }

    @Override
    public void showList(String[] items, int[] imgs) {

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
    public void OnClickAddImg() {

    }

    @OnClick({R.id.iv_more, R.id.iv_left, R.id.iv_phone})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_more:
                Intent intent=new Intent();
                break;
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
}
