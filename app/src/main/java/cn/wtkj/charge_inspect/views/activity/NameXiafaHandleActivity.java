package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.BlackXiafaHandleData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameXiafaHandlePresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameXiafaHandlePresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameXiafaHandleView;
import cn.wtkj.charge_inspect.util.Setting;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/10/17.
 */
public class NameXiafaHandleActivity extends MvpBaseActivity<NameXiafaHandlePresenter>
        implements NameXiafaHandleView, View.OnClickListener {

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

    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.ed_bujiao)
    EditText edBujiao;
    @Bind(R.id.ed_xingshi)
    EditText edXingshi;
    @Bind(R.id.ed_jiashi)
    EditText edJiashi;
    @Bind(R.id.ed_chiliren)
    EditText edChiliren;
    @Bind(R.id.ed_chili_info)
    EditText edChiliInfo;

    private String id;
    private BlackXiafaHandleData data;
    private ProgressDialog progressDialog;

    @Override
    protected NameXiafaHandlePresenter createPresenter() {
        return new NameXiafaHandlePresenterImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_xiafa_handle);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        presenter.attachContextIntent(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText("黑名单处理");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
    }

    public void initView() {
        progressDialog = new ProgressDialog(this);
    }


    @Override
    public void showLoding() {
        progressDialog.setMessage("正在保存，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoging() {
        progressDialog.hide();
    }

    @Override
    public void nextView() {
        this.finish();
        Intent intent = new Intent(this, NameRollXiafaActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_SHORT);
    }

    @OnClick({R.id.iv_left, R.id.comit_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.comit_button:
                if (TextUtils.isEmpty(edName.getText())) {
                    showMes("当事人姓名不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edBujiao.getText())) {
                    showMes("补缴金额不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edChiliren.getText())) {
                    showMes("处理人不能为空！");
                    return;
                }
                getShowData();
                presenter.startPresenter(data);
                break;
        }
    }

    public void getShowData() {
        data = new BlackXiafaHandleData();
        data.setBLACKLISTID(id);
        data.setUSERID(Setting.USERID);
        data.setPARTYNAME(edName.getText().toString());
        data.setPAYMONEY(edBujiao.getText() + "");
        if (TextUtils.isEmpty(edXingshi.getText())) {
            data.setRUNCARDNUM("");
        } else {
            data.setRUNCARDNUM(edXingshi.getText() + "");
        }
        if (TextUtils.isEmpty(edJiashi.getText())) {
            data.setDRIVECARDNUM("");
        } else {
            data.setDRIVECARDNUM(edJiashi.getText() + "");
        }
        data.setDISPOSENAME(edChiliren.getText().toString());

        if (TextUtils.isEmpty(edChiliInfo.getText())) {
            data.setREMARK("");
        } else {
            data.setREMARK(edChiliInfo.getText().toString());
        }
    }
}
