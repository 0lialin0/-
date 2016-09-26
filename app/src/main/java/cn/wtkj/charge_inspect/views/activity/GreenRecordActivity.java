package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
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
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;

/**
 * Created by ghj on 2016/9/18.
 */
public class GreenRecordActivity extends MvpBaseActivity<GreenRecordPresenter> implements
        GreenRecordView, MyPhotos.OnClickAddImgListener, View.OnClickListener {

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.myphoto)
    MyPhotos myphoto;

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;

    @Override
    protected GreenRecordPresenter createPresenter() {
        return new GreenRecordPresenterImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_record);
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
        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file = myphoto.setImage(requestCode, resultCode, data);
        if (file != null) {
            files.add(file);
        }
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
        myphoto.clear();
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
                intent.setClass(this,GreenRecordListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.iv_left:
                this.finish();
                break;
        }
    }
}
