package cn.wtkj.charge_inspect.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;

/**
 * Created by ghj on 2016/10/24.
 */
public class OutListSelInfoActivity extends MvpBaseActivity<OutListSelPresenter> implements
        OutListSelView, View.OnClickListener {

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

    @Bind(R.id.tv_entrance_loca)
    TextView tvEntranceLoca;
    @Bind(R.id.tv_entrance_lane)
    TextView tvEntranceLane;
    @Bind(R.id.tv_entrance_vehplate)
    TextView tvEntranceVehplate;
    @Bind(R.id.tv_veh_seed)
    TextView tvVehSeed;
    @Bind(R.id.tv_veh_type)
    TextView tvVehType;
    @Bind(R.id.tv_oprid)
    TextView tvOprid;
    @Bind(R.id.tv_entrance_time)
    TextView tvEntranceTime;
    @Bind(R.id.tv_out_loca)
    TextView tvOutLoca;

    @Bind(R.id.tv_out_lane)
    TextView tvOutLane;
    @Bind(R.id.tv_out_vehplate)
    TextView tvOutVehplate;
    @Bind(R.id.tv_out_seed)
    TextView tvOutSeed;
    @Bind(R.id.tv_out_vehtype)
    TextView tvOutVehtype;

    @Bind(R.id.tv_yufu_vehplate)
    TextView tvYufuVehplate;
    @Bind(R.id.tv_zhekou_money)
    TextView tvZhekouMoney;
    @Bind(R.id.tv_yingshou_money)
    TextView tvYingshoMoney;
    @Bind(R.id.tv_shishou_money)
    TextView tvShishouMoney;

    @Bind(R.id.tv_veh_num)
    TextView tvVehNum;
    @Bind(R.id.tv_licheng)
    TextView tvLicheng;
    @Bind(R.id.tv_zhoushu)
    TextView tvZhoushu;
    @Bind(R.id.tv_zhongliang)
    TextView tvZhongliang;
    @Bind(R.id.tv_tong_car_num)
    TextView tvTongCarNum;
    @Bind(R.id.tv_special_event)
    TextView tvSpecialEvent;


    public static final String DATA_TAG = "DataInfo";
    public OutListData.MData.info data;

    @Override
    protected OutListSelPresenter createPresenter() {
        return new OutListSelPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_list_sel_info);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        tvTitle.setText("流水详情");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setView();
    }



    @Override
    public void setView() {
        data = (OutListData.MData.info) getIntent().getSerializableExtra(
                OutListSelShowActivity.DATA_TAG);

        if (data != null) {
            showViewData(data);
        }
    }

    //显示值在页面上
    private void showViewData(OutListData.MData.info data) {
        tvEntranceLoca.setText(data.getInstationName());
        tvEntranceLane.setText(data.getInlaneName());
        tvEntranceVehplate.setText(data.getInvehplateNo());
        tvVehSeed.setText(data.getInvehclass());
        tvVehType.setText(data.getInvehTypeName());
        tvOprid.setText(data.getInoprId());
        tvEntranceTime.setText(data.getInoperateOn());
        tvOutLoca.setText(data.getOutstationName());
        tvOutLane.setText(data.getOutlaneName());
        tvOutVehplate.setText(data.getVehplateNo());
        tvOutSeed.setText(data.getVehclass());
        tvOutVehtype.setText(data.getVehTypeName());
        tvYufuVehplate.setText(data.getCardvehpLate());
        tvZhekouMoney.setText(data.getUcrmoney());
        tvYingshoMoney.setText(data.getCrmoney());
        tvShishouMoney.setText(data.getPaymoney());
        tvVehNum.setText(data.getInvoiceNo()+"");
        tvLicheng.setText(data.getMiles()+"");
        tvZhoushu.setText(data.getAxleNumber()+"轴");
        tvZhongliang.setText(data.getWeight()+"kg");
        tvTongCarNum.setText(data.getCardNo()+"");
        tvSpecialEvent.setText(data.getSpevent());
    }

    @Override
    public void showView() {
    }

    @OnClick({R.id.iv_left, R.id.tv_add_name, R.id.tv_add_green, R.id.tv_add_increment})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_add_name:
                Intent intent = new Intent(this, NameRollAddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DATA_TAG, data);
                bundle.putString("type","outlist");
                intent.putExtra("nameType", 0+"");
                intent.putExtra("nameTitle", "添加黑名单");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_add_green:
                Intent intent2 = new Intent(this, GreenRecordActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable(DATA_TAG, data);
                bundle2.putString("type","outlist");
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.tv_add_increment:
                Intent intent3 = new Intent(this, IncrementAddActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable(DATA_TAG, data);
                bundle3.putString("type","outlist");
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoging() {

    }

    @Override
    public void nextView() {

    }


    @Override
    public void showToast(String msg) {

    }
}
