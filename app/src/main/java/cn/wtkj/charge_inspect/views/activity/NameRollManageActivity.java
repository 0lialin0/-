package cn.wtkj.charge_inspect.views.activity;

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
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollManagePresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollManagePresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.custom.AlertDialogType;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/29.
 */
public class NameRollManageActivity extends MvpBaseActivity<NameRollManagePresenter> implements
        NameRollManageView, SwipeRefreshLayout.OnRefreshListener,
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

    /*@Bind(R.id.shed_list_refresh)
    SwipeRefreshLayout shedRefresh;
    @Bind(R.id.shed_list_recy)
    RecyClerRefresh shedRecy;*/

    private AlertDialogType alertDialogType;

    @Override
    protected NameRollManagePresenter createPresenter() {
        return new NameRollManagePresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_roll_manage);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.name_roll_manage_title);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setImageResource(R.drawable.name_add_img);
        setSupportActionBar(mToolbar);
    }

    public void initView() {

       /* // 设置下拉组件动画偏移量
        shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        shedRefresh.setRefreshing(true);// 显示动画
        presenter.attachContextIntent(this);
        presenter.startPresenter();
        shedRecy.setRefreshData(this);
*/

        alertDialogType = new AlertDialogType(this);
        alertDialogType.setOnItemClickListener(this);
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
        show(this, msg, Toast.LENGTH_LONG);
    }

    @OnClick({R.id.iv_left, R.id.iv_more ,R.id.ll_xiafa_btn ,R.id.iv_name_a})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.iv_more:
                alertDialogType.setAlertDialog("");
                break;
            case R.id.ll_xiafa_btn:
                Intent intent=new Intent(this,NameRollXiafaActivity.class);
                this.startActivity(intent);
                break;
            case R.id.iv_name_a:
                Intent intent2=new Intent(this,NameRollAddActivity.class);
                this.startActivity(intent2);
                break;
        }
    }

    @Override
    public void onRefresh() {
        //shedRefresh.setRefreshing(false);
    }

    @Override
    public void onRefreshData() {
        //shedRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(int code, int type, String name) {
        Intent intent = new Intent();
        if(name.equals("balck")){
            intent.setClass(this, NameRollAddActivity.class);
        }else if(name.equals("grey")){
            intent.setClass(this, NameRollAddGreyActivity.class);
        }else{
            intent.setClass(this, NameRollAddYellowActivity.class);
        }
        this.startActivity(intent);
    }
}
