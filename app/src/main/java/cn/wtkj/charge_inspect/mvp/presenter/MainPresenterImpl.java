package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.activity.GreenRecordActivity;
import cn.wtkj.charge_inspect.views.activity.GreenRecordListActivity;
import cn.wtkj.charge_inspect.views.activity.IncrementListActivity;
import cn.wtkj.charge_inspect.views.activity.ArticleListActivity;
import cn.wtkj.charge_inspect.views.activity.NameRollManageActivity;
import cn.wtkj.charge_inspect.views.activity.OutListSelActivity;


/**
 * Created by lxg on 2015/11/5.
 */
public class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter,
        OnItemClickListener2 {

    private String[] items;
    private int[] imgs;
    private final String readCard = "读卡";
    private final String outList = "流水";
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
        items = new String[]{readCard, outList, greenInfo, increment, nameRoll, lawsNews};
        imgs = new int[]{R.drawable.read_card, R.drawable.liushui, R.drawable.green_info,
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
                getView().showMes("正在开发中.....");
                break;
            case outList: //流水
                intent.setClass(context, OutListSelActivity.class);
                context.startActivity(intent);
                break;
            case greenInfo://绿通
                intent.setClass(context, GreenRecordListActivity.class);
                context.startActivity(intent);
                break;
            case increment://增收
                intent.setClass(context, IncrementListActivity.class);
                context.startActivity(intent);
                break;
            case nameRoll://名单
                intent.setClass(context, NameRollManageActivity.class);
                context.startActivity(intent);
                break;
            case lawsNews://资料
                intent.setClass(context, ArticleListActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
