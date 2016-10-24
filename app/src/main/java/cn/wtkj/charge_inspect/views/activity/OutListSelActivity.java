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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.OutListSelPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/10/24.
 */
public class OutListSelActivity extends MvpBaseActivity<OutListSelPresenter> implements
        OutListSelView, View.OnClickListener, OnItemClickListener {

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
    @Bind(R.id.tv_veh_seed)
    TextView tvVehSeed;
    @Bind(R.id.tv_out_lane)
    TextView tvOutLane;
    @Bind(R.id.tv_veh_plate)
    EditText tvVehPlate;
    @Bind(R.id.tv_charge)
    EditText tvCharge;
    @Bind(R.id.tv_car_type)
    EditText tvCarType;


    private int inStationID;
    private String inStationName;
    private int vehLaneId;
    private String vehLaneName;
    private int vehTypeId;
    private String vehTypeName;
    private int vehZhongId;
    private String vehZhongName;
    private int outLaneId;
    private String outLaneName;
    private ProgressDialog progressDialog;

    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownMenu dropDownMenu4;
    private DropDownMenu dropDownMenu5;

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
        progressDialog = new ProgressDialog(this);

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
        //1：站址,2,车道，  5：车型类别，11：车种， 13：车道类型,

        //出口站址(站址)
        List<ConstAllData.MData.info> rkLoca = presenter.getConstByType(1);
        dropDownMenu = new DropDownMenu(this, rkLoca);
        dropDownMenu.setOnItemClickListener(this);
        if (rkLoca.size() > 0) {
            inStationID = rkLoca.get(0).getCode();
            inStationName = rkLoca.get(0).getName();
            //tvOutLoca.setText(rkLoca.get(0).getName());
        }

        //车道类型
        List<ConstAllData.MData.info> lane = presenter.getConstByType(13);
        dropDownMenu2 = new DropDownMenu(this, lane);
        dropDownMenu2.setOnItemClickListener(this);
        if (lane.size() > 0) {
            vehLaneId = lane.get(0).getCode();
            vehLaneName = lane.get(0).getName();
            //tvLaneType.setText(lane.get(0).getName());
        }

        //车型类别
        List<ConstAllData.MData.info> vehType = presenter.getConstByType(5);
        dropDownMenu3 = new DropDownMenu(this, vehType);
        dropDownMenu3.setOnItemClickListener(this);
        if (vehType.size() > 0) {
            vehTypeId = vehType.get(0).getCode();
            vehTypeName = vehType.get(0).getName();
            //tvVehType.setText(vehType.get(0).getName());
        }

        //车种
        List<ConstAllData.MData.info> vehZhong = presenter.getConstByType(11);
        dropDownMenu4 = new DropDownMenu(this, vehZhong);
        dropDownMenu4.setOnItemClickListener(this);
        if (vehZhong.size() > 0) {
            vehZhongId = vehZhong.get(0).getCode();
            vehZhongName = vehZhong.get(0).getName();
            //tvVehSeed.setText(vehZhong.get(0).getName());
        }

        //车道
        List<ConstAllData.MData.info> outLane = presenter.getConstByType(2);
        dropDownMenu5 = new DropDownMenu(this, vehZhong);
        dropDownMenu5.setOnItemClickListener(this);
        if (outLane.size() > 0) {
            outLaneId = outLane.get(0).getCode();
            outLaneName = outLane.get(0).getName();
            //tvVehSeed.setText(vehZhong.get(0).getName());
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_start_time, R.id.tv_end_time, R.id.rl_out_loca,
            R.id.rl_lane_type, R.id.rl_veh_type, R.id.rl_veh_seed, R.id.comit_button,
            R.id.rl_out_lane})
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
            case R.id.rl_veh_type:
                dropDownMenu3.setDownValue(tvVehType, "");
                break;
            case R.id.rl_veh_seed:
                dropDownMenu4.setDownValue(tvVehSeed, "");
                break;
            case R.id.rl_out_lane:
                dropDownMenu5.setDownValue(tvOutLane, "");
                break;
            case R.id.comit_button:
                if (TextUtils.isEmpty(tvStartTime.getText())) {
                    showToast("开始时间不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(tvEndTime.getText())) {
                    showToast("结束时间不能为空！");
                    return;
                }
                getShowData();
                break;
        }
    }

    public void getShowData() {
        Map<String, String> map = new HashMap<>();
        map.put("BEGINTIME", tvStartTime.getText().toString());
        map.put("ENDTIME", tvEndTime.getText().toString());
        map.put("OUTSTATIONID", inStationID + "");
        map.put("VEHCLASSID", vehZhongId + "");
        map.put("VEHTYPEID", vehTypeId + "");
        map.put("OUTLANEID", outLaneId + "");
        map.put("LANETYPE", vehLaneId + "");
        if (!TextUtils.isEmpty(tvVehPlate.getText())) {
            map.put("VEHPLATENO", tvVehPlate.getText().toString());
        }
        if (!TextUtils.isEmpty(tvCharge.getText())) {
            map.put("OPRNAME", tvCharge.getText().toString());
        }
        if (!TextUtils.isEmpty(tvCarType.getText())) {
            map.put("CARDNO", tvCarType.getText().toString());
        }

        presenter.startPresenter(map);
    }

    @Override
    public void onItemClick(int code, int type, String name) {
        switch (type) {
            case 1://出口站址
                inStationID = code;
                inStationName = name;
                break;
            case 2://车道
                outLaneId = code;
                outLaneName = name;
                break;
            case 5://车型类别
                vehTypeId = code;
                vehTypeName = name;
                break;
            case 11://车种
                vehZhongId = code;
                vehZhongName = name;
                break;
            case 13://车道类型
                vehLaneId = code;
                vehLaneName = name;
                break;
        }
    }


    @Override
    public void showLoding() {
        progressDialog.setMessage("正在查询，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoging() {
        progressDialog.hide();
    }

    @Override
    public void nextView(List<OutListData.MData.info> data) {
        Intent intent=new Intent(this,OutListSelShowActivity.class);

    }

    @Override
    public void showToast(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }
}
