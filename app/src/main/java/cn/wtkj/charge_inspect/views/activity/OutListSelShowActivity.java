package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.bean.OutListParamData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenterImpl;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelShowPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelShowPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.OutListSelShowView;
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.IncrementListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.Adapter.OutListSelAdapter;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/10/24.
 */
public class OutListSelShowActivity extends MvpBaseActivity<OutListSelShowPresenter> implements
        OutListSelShowView, SwipeRefreshLayout.OnRefreshListener,
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

    private OutListSelAdapter adapter;
    private List<OutListData.MData.info> mList;
    public static final String DATA_TAG = "DataInfo";
    private ProgressDialog progressDialog;
    private OutListParamData paramData;
    int pager, size = 10;

    @Override
    protected OutListSelShowPresenter createPresenter() {
        return new OutListSelShowPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlist_sel_show);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText("流水");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
    }

    public void initView() {
        paramData= (OutListParamData) getIntent().getSerializableExtra("param");

        progressDialog = new ProgressDialog(this);
        // 设置下拉组件动画偏移量
        shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        shedRefresh.setRefreshing(true);// 显示动画
        presenter.startPresenter(++pager,size,paramData);
        shedRecy.setRefreshData(this);

        /*List<OutListData.MData.info> data = (List<OutListData.MData.info>) getIntent().getSerializableExtra(
                OutListSelActivity.DATA_TAG);
        if (data.size() > 0) {
            setList(data);
        }*/
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
    public void onItemClick(int code, int type, String name) {
        if (name.equals("name")) {
            this.finish();
            Intent intent = new Intent(this, NameRollAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            bundle.putString("type","outlist");
            intent.putExtra("nameType", 0+"");
            intent.putExtra("nameTitle", "添加黑名单");
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(name.equals("green")){
            this.finish();
            Intent intent = new Intent(this, GreenRecordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            bundle.putString("type","outlist");
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(name.equals("increment")){
            this.finish();
            Intent intent = new Intent(this, IncrementAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            bundle.putString("type","outlist");
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, OutListSelInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            intent.putExtras(bundle);
            startActivity(intent);
        }
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
        onRefresh();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setList(List<OutListData.MData.info> list) {
        if (adapter == null) {
            mList = list;
            adapter = new OutListSelAdapter(this, list);
            shedRecy.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        } else {
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showToast(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void onRefresh() {
        pager = 0;
        if (mList != null) {
            mList.clear();
         }
        presenter.startPresenter(++pager,size,paramData);
    }

    //下拉加载回调函数
    @Override
    public void onRefreshData() {
        shedRefresh.setRefreshing(true);
        presenter.startPresenter(++pager,size,paramData);
    }


}
