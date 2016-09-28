package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.LoginPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.LoginPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.LoginView;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/14.
 */
public class LoginActivity extends MvpBaseActivity<LoginPresenter> implements
        LoginView, View.OnClickListener {

    @Bind(R.id.login_root)
    FrameLayout loginRoot;
    @Bind(R.id.et_user)
    EditText etUser;
    @Bind(R.id.et_pwd)
    EditText etPwd;

    private View loding;
    private View error;


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenterImpl(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        loding = LayoutInflater.from(this).inflate(R.layout.login_loding, null, false);
        error = LayoutInflater.from(this).inflate(R.layout.login_error, null, false);
        initNetErrorListener(error);
    }


    //设置网络异常时界面的监听
    private void initNetErrorListener(View view) {
        view.findViewById(R.id.loding_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.startLogin(imsi);
            }
        });
    }

    @Override
    public void showLoding() {
        loginRoot.addView(loding);
    }

    @Override
    public void hideLoging() {
        loginRoot.removeView(loding);
    }

    @Override
    public void showNetError() {
        loginRoot.addView(error);
    }

    @Override
    public void hideNetError() {
        loginRoot.removeView(error);
    }

    @Override
    public void nextView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void viewDestroy() {
        this.finish();
    }

    @Override
    public void showPass() {

    }

    @Override
    public void setCode(String code) {

    }

    private Long lastTime = 0L;

    @OnClick({R.id.login_comit_bnt})
    @Override
    public void onClick(View view) {
        if (System.currentTimeMillis() - lastTime < 1 * 1000) {
            return;
        }
        lastTime = System.currentTimeMillis();

        switch (view.getId()) {
            case R.id.login_comit_bnt:
                if (TextUtils.isEmpty(etUser.getText())) {
                    showMes("用户名不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText())) {
                    showMes("密码不能为空！");
                    return;
                }
                presenter.login(etUser.getText().toString(), etPwd.getText().toString());
                //nextView();
                break;
        }
    }
}
