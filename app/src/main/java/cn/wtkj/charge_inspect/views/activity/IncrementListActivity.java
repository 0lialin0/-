package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementListPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementListPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.util.CustomDialog;
import cn.wtkj.charge_inspect.views.Adapter.GreenRecordListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.IncrementListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/20.
 */
public class IncrementListActivity extends MvpBaseActivity<IncrementListPresenter> implements
        IncrementListView, SwipeRefreshLayout.OnRefreshListener,
        RecyClerRefresh.RefreshData, View.OnClickListener, OnItemClickListener {

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

    @Bind(R.id.shed_list_refresh)
    SwipeRefreshLayout shedRefresh;
    @Bind(R.id.shed_list_recy)
    RecyClerRefresh shedRecy;

    private IncrementListAdapter adapter;
    private List<JCEscapeBookData> mList;
    public static final String DATA_TAG = "DataInfo";
    private ProgressDialog progressDialog;


    @Override
    protected IncrementListPresenter createPresenter() {
        return new IncrementListPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increment_list);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.increment_name);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setImageResource(R.drawable.increment_add);
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        progressDialog = new ProgressDialog(this);
        // 设置下拉组件动画偏移量
        shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        shedRefresh.setRefreshing(true);// 显示动画
        presenter.attachContextIntent(this);
        presenter.startPresenter();
        shedRecy.setRefreshData(this);

    }

    @Override
    public void showList(String[] items, int[] imgs) {

    }

    @Override
    public void showLoding() {
        progressDialog.setMessage("正在保存，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoging() {
        shedRefresh.setRefreshing(false);
    }

    @Override
    public void hideDialog() {
        progressDialog.hide();
    }

    @Override
    public void nextView() {
        //this.finish();
        onRefresh();
    }


    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void setList(List<JCEscapeBookData> list) {
        if (adapter == null) {
            mList = list;
            adapter = new IncrementListAdapter(this, list);
            shedRecy.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        } else {
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onRefresh() {
        if (mList != null) {
            mList.clear();
        }
        presenter.startPresenter();
    }

    @Override
    public void onRefreshData() {
        onRefresh();
    }

    @OnClick({R.id.iv_left, R.id.iv_more})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.iv_more:
                Intent intent = new Intent(this, IncrementAddActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }


    @Override
    public void onItemClick(int code, int type, String name) {
        if (name.equals("del")) {
            //showMes("删除");
            showConfirm(code);

        } else if (name.equals("edit")) {
            Intent intent = new Intent(this, IncrementAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (name.equals("submit")) {
            //showMes("提交");
            presenter.sendData(mList.get(code));
        } else {
            Intent intent = new Intent(this, IncrementAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }


    public void showConfirm(final int code) {
        final CustomDialog showDialog = new CustomDialog(this);
        showDialog.setText("是否确认要删除此条信息");
        showDialog.setNegativeText("确定");
        showDialog.setPositiveText("取消");
        showDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
                presenter.deleteById(mList.get(code).getEscapeBookID());
            }
        });
        showDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });
    }
}
