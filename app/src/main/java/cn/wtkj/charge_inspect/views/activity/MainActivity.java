package cn.wtkj.charge_inspect.views.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.MainPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.MainPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.MainRecyAdapter;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainView{

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
        presenter.attachContextIntent(this,this.getIntent());
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
        MainRecyAdapter mainRecyAdapter=new MainRecyAdapter(this,items,imgs,(MainPresenterImpl)presenter);
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

    }
}
