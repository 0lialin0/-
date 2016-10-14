package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.MainPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.MainPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.MainRecyAdapter;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements
        MainView, View.OnClickListener {

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.tv_more)
    ImageView tvMore;
    @Bind(R.id.id_start_recy)
    RecyclerView startPartolRecy;


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startPartolRecy.setLayoutManager(new GridLayoutManager(this, 2));
        presenter.attachContextIntent(this, this.getIntent());
        presenter.startPresenter();
        initToolBar();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.app_name);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }


    @Override
    public void showList(String[] items, int[] imgs) {
        MainRecyAdapter mainRecyAdapter = new MainRecyAdapter(this, items, imgs, (MainPresenterImpl) presenter);
        startPartolRecy.setAdapter(mainRecyAdapter);
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
        show(this, msg, Toast.LENGTH_LONG);
    }

    @OnClick({R.id.iv_left,R.id.tv_more})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                Intent intent = new Intent();
                intent.setClass(this, UserInfoActivity.class);
                this.startActivity(intent);
                break;
            case R.id.tv_more:
                Intent intent2 = new Intent();
                intent2.setClass(this, ContactListActivity.class);
                this.startActivity(intent2);
                break;
        }
    }
}
