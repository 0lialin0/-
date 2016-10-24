package cn.wtkj.charge_inspect.views.activity;

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
public class OutListSelShowActivity extends MvpBaseActivity<OutListSelPresenter> implements
        OutListSelView, View.OnClickListener, OnItemClickListener, OnItemClickListener3 {

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

    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;

    @Bind(R.id.tv_out_loca)
    TextView tvOutLoca;
    @Bind(R.id.tv_lane_type)
    TextView tvLaneType;
    @Bind(R.id.tv_veh_type)
    TextView tvVehType;


    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;


    @Override
    protected OutListSelPresenter createPresenter() {
        return new OutListSelPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlist_sel);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText("流水查询");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
    }

    public void initView() {
        tvStartTime.setText(ResponeUtils.getTime());
        tvEndTime.setText(ResponeUtils.getTime());
        showView();
    }

    @Override
    public void setDropDown() {

    }


    @Override
    public void setView() {

    }

    @Override
    public void showView() {
        //1：站址, 2：车道,  5：车型类别

        //出口站址(站址)
        List<ConstAllData.MData.info> rkLoca = presenter.getConstByType(1);
        dropDownMenu = new DropDownMenu(this, rkLoca);
        dropDownMenu.setOnItemClickListener(this);
        if (rkLoca.size() > 0) {
            //inStationID = rkLoca.get(0).getCode();
            //inStationName = rkLoca.get(0).getName();
            tvOutLoca.setText(rkLoca.get(0).getName());
        }

        //出口站址(站址)
        List<ConstAllData.MData.info> lane = presenter.getConstByType(2);
        dropDownMenu2 = new DropDownMenu(this, lane);
        dropDownMenu2.setOnItemClickListener(this);
        if (rkLoca.size() > 0) {
            //inStationID = rkLoca.get(0).getCode();
            //inStationName = rkLoca.get(0).getName();
            tvLaneType.setText(lane.get(0).getName());
        }

        //出口站址(站址)
        List<ConstAllData.MData.info> vehType = presenter.getConstByType(5);
        dropDownMenu3 = new DropDownMenu(this, vehType);
        dropDownMenu3.setOnItemClickListener(this);
        if (rkLoca.size() > 0) {
            //inStationID = rkLoca.get(0).getCode();
            //inStationName = rkLoca.get(0).getName();
            tvVehType.setText(vehType.get(0).getName());
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_start_time, R.id.tv_end_time, R.id.rl_out_loca,
            R.id.rl_lane_type , R.id.rl_veh_type})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_start_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvStartTime, 0);
                break;
            case R.id.tv_end_time:
                DateTimePickerDialog dateTimePickerDialog2 = new DateTimePickerDialog(this);
                dateTimePickerDialog2.dateTimePicKDialog(tvEndTime, 0);
                break;
            case R.id.rl_out_loca:
                dropDownMenu.setDownValue(tvOutLoca, "");
                break;
            case R.id.rl_lane_type:
                dropDownMenu2.setDownValue(tvLaneType, "");
                break;
            case R.id.rl_veh_seed:
                dropDownMenu3.setDownValue(tvVehType, "");
                break;
        }
    }

    @Override
    public void onItemClick(int code, int type, String name) {

    }

    @Override
    public void onItemClick(String name, int id) {

    }


    @Override
    public void showLoding() {

    }

    @Override
    public void himeDialog() {

    }

    @Override
    public void nextView() {

    }

    @Override
    public void showToast(String msg) {

    }
}
