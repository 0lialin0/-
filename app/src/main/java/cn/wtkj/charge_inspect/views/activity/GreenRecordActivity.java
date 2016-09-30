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
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownKeyValue;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;

/**
 * Created by ghj on 2016/9/18.
 */
public class GreenRecordActivity extends MvpBaseActivity<GreenRecordPresenter> implements
        GreenRecordView, MyPhotos.OnClickAddImgListener, View.OnClickListener,
        OnItemClickListener3, OnItemClickListener {

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
    @Bind(R.id.iv_phone)
    ImageView ivPhone;

    @Bind(R.id.tv_check_time)
    TextView tvCheckTime;
    @Bind(R.id.tv_car_type)
    TextView tvCarType;
    @Bind(R.id.tv_out_loca)
    TextView tvOutLoca;
    @Bind(R.id.tv_out_lane)
    TextView tvOutLane;
    @Bind(R.id.tv_green_type)
    TextView tvGreenType;


    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;
    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValue2;
    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;

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
        ivPhone.setVisibility(View.GONE);
    }


    public void initView() {
        tvCheckTime.setText(ResponeUtils.getTime());

        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();

        /*List<KeyValueData> list = new ArrayList<>();
        list.add(new KeyValueData("1", "小型货车"));
        list.add(new KeyValueData("2", "中型货车"));
        list.add(new KeyValueData("3", "重型货车"));
        downKeyValue = new DropDownKeyValue(this, list);
        downKeyValue.setId(1);
        downKeyValue.setOnItemClickListener(this);
        tvCarType.setText(list.get(0).getValue());*/

        List<KeyValueData> type = new ArrayList<>();
        type.add(new KeyValueData("0", "正常"));
        type.add(new KeyValueData("1", "混装"));
        type.add(new KeyValueData("2", "不合法装载"));
        downKeyValue2 = new DropDownKeyValue(this, type);
        downKeyValue2.setId(1);
        downKeyValue2.setOnItemClickListener(this);
        tvGreenType.setText(type.get(0).getValue());

        showView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file = myphoto.setImage(requestCode, resultCode, data);
        if (file != null) {
            files.add(file);
        }
    }


    @Override
    public void showView() {
        //1：站址, 2：车道， 6：车辆类别

        //站址
        List<ConstAllData.MData.info> dlType = presenter.getConstByType(1);
        dropDownMenu = new DropDownMenu(this, dlType);
        dropDownMenu.setOnItemClickListener(this);
        if (dlType.size() > 0) {
            tvOutLoca.setText(dlType.get(0).getName());
        }


        //车道
        List<ConstAllData.MData.info> lane = presenter.getConstByType(2);
        dropDownMenu2 = new DropDownMenu(this, lane);
        dropDownMenu2.setOnItemClickListener(this);
        if (lane.size() > 0) {
            tvOutLane.setText(lane.get(0).getName());
        }

        //车辆类别
        List<ConstAllData.MData.info> cartype = presenter.getConstByType(6);
        dropDownMenu3 = new DropDownMenu(this, cartype);
        dropDownMenu3.setOnItemClickListener(this);
        if (cartype.size() > 0) {
            tvCarType.setText(cartype.get(0).getName());
        }
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

    @OnClick({R.id.iv_more, R.id.iv_left, R.id.iv_phone, R.id.tv_check_time, R.id.rl_car_type,
            R.id.rl_out_loca, R.id.rl_out_lane, R.id.rl_green_type})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_more:
                Intent intent = new Intent();
                intent.setClass(this, GreenRecordListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_check_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvCheckTime, 0);
                break;
            case R.id.rl_car_type:
                dropDownMenu3.setDownValue(tvCarType, "");
                break;
            case R.id.rl_out_loca:
                dropDownMenu.setDownValue(tvOutLoca, "");
                break;
            case R.id.rl_out_lane:
                dropDownMenu2.setDownValue(tvOutLane, "");
                break;
            case R.id.rl_green_type:
                downKeyValue2.setDownValue(tvGreenType, "");
                break;
        }
    }

    @Override
    public void onItemClick(String name, int id) {

    }

    @Override
    public void onItemClick(int code, int type, String name) {

    }
}
