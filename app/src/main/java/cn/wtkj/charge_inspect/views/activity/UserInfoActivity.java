package cn.wtkj.charge_inspect.views.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.util.CustomDialog;
import cn.wtkj.charge_inspect.util.Setting;

/**
 * Created by ghj on 2016/9/29.
 */
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_login_num)
    TextView tvLoginNum;
    @Bind(R.id.tv_unit)
    TextView tvUnit;
    @Bind(R.id.tv_email)
    TextView tvEmail;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_mphone)
    TextView tvMphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.user_info);
        mToolbar.setTitle("");

        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
    }

    private void initView() {
        tvName.setText(Setting.USERID);
        tvLoginNum.setText(Setting.OPRID);
        tvUnit.setText(Setting.ORGANIZATION_NAME);
        tvEmail.setText(Setting.EMAIL);
        tvPhone.setText(Setting.OFFICEPHONE);
        tvMphone.setText(Setting.MOBILEPHONE);
    }

    @OnClick({R.id.iv_left, R.id.exit_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.exit_button:
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("确定吗？");
                dialog.setMessage("确定退出吗？");
                dialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                UserInfoActivity.this.finish();
                            }
                        });
                dialog.setNeutralButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });
                dialog.show();*/

                showConfirm();
                break;
        }
    }

    public void showConfirm() {
        final CustomDialog showDialog = new CustomDialog(this,"userInfo");
        showDialog.setText("是否确认注销");
        showDialog.setNegativeText("确定");
        showDialog.setPositiveText("取消");
        showDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
                Intent logoutIntent = new Intent(UserInfoActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
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
