package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;

/**
 * Created by ghj on 2016/9/30.
 */
public class WelComeActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goMain(3000);
    }


    public synchronized void goMain(long delay) {
        // 闪屏就进入主页
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(WelComeActivity.this, LoginActivity.class);

                startActivity(intent);  // 首页
                WelComeActivity.this.finish();   // 结束闪屏页面
            }
        }, delay);
    }




    @OnClick({R.id.iv_loading})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_loading:
                Intent intent=new Intent(this,LoginActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
