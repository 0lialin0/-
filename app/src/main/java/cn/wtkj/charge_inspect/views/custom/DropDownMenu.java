package cn.wtkj.charge_inspect.views.custom;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.views.Adapter.DownMenuAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;

/**
 * Created by lxg on 2015/9/18.
 */
public class DropDownMenu {
    private Context mContext;
    private List<ConstAllData.MData.info> list;
    private int id;
    private int type = -1;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private PopupWindow popupwindow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DropDownMenu(Context context, List<ConstAllData.MData.info> list) {
        this.list = list;
        this.mContext = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDownValue(final TextView textView, String title) {
        final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.down_menu_layout, null);
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.down_menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        DownMenuAdapter adapter = new DownMenuAdapter(list, mContext);
        recyclerView.setAdapter(adapter);
        if (popupwindow != null && popupwindow.isShowing()) {
            popupwindow.dismiss();
            return;
        } else {
            popupwindow = new PopupWindow(linearLayout, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            popupwindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
            popupwindow.setBackgroundDrawable(new ColorDrawable(0x000000));
            popupwindow.setFocusable(true);
            popupwindow.showAsDropDown(textView, 5, 5);
        }
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int code,int types,String value) {
                id = list.get(code).getCode();
                String name=list.get(code).getName();

                if (type == -1){
                    type=list.get(code).getType();
                }

                textView.setText(list.get(code).getName());
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(id,type,name);
                }
            }
        });
    }
}
