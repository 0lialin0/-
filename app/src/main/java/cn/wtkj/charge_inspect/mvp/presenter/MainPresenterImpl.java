package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.activity.GreenRecordActivity;
import cn.wtkj.charge_inspect.views.activity.IncrementListActivity;
import cn.wtkj.charge_inspect.views.activity.LawsNewsActivity;
import cn.wtkj.charge_inspect.views.activity.NameRollAddActivity;


/**
 * Created by lxg on 2015/11/5.
 */
public class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter,
        OnItemClickListener2 {

    private String[] items;
    private int[] imgs;
    private final String readCard = "读卡";
    private final String checkCar = "查车";
    private final String greenInfo = "绿通";
    private final String increment = "增收";
    private final String nameRoll = "名单";
    private final String lawsNews = "资料";


    private Context context;
    private Intent intent;

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void startPresenter() {
        items = new String[]{readCard, checkCar, greenInfo, increment, nameRoll, lawsNews};
        imgs = new int[]{R.drawable.read_card, R.drawable.check_car, R.drawable.green_info,
                R.drawable.increment, R.drawable.name_list, R.drawable.laws};
        getView().showList(items, imgs);
    }

    @Override
    public void getUserState() {

    }

    @Override
    public void onItemClick(String tags) {
        switch (tags) {
            case readCard:
                break;
            case checkCar:
                getView().showMes("正在开发中.....");
                break;
            case greenInfo://绿通
                intent.setClass(context, GreenRecordActivity.class);
                context.startActivity(intent);
                break;
            case increment://增收
                intent.setClass(context, IncrementListActivity.class);
                context.startActivity(intent);
                break;
            case nameRoll://名单
                intent.setClass(context, NameRollAddActivity.class);
                context.startActivity(intent);
                break;
            case lawsNews://资料
                intent.setClass(context, LawsNewsActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
