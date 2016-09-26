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
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.views.Adapter.DownKeyValueAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;


/**
 * Created by lxg on 2015/9/18.
 */
public class DropDownKeyValue {
    private Context mContext;
    private List<KeyValueData> list;
    private String id;
    private OnItemClickListener2 onItemClickListener;

    public OnItemClickListener2 getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener2 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private PopupWindow popupwindow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DropDownKeyValue(Context context, List<KeyValueData> list) {
        this.list = list;
        this.mContext = context;
    }

    public void setDownValue(final TextView textView, String title) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.down_menu_layout, null);
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.down_menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        DownKeyValueAdapter adapter = new DownKeyValueAdapter(list, mContext);
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
        adapter.setOnItemClickListener(new OnItemClickListener2() {
            @Override
            public void onItemClick(String tags) {
                id = list.get(Integer.parseInt(tags)).getId();
                // textView.setText(list.get(Integer.parseInt(tags)).getValue());
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(id);
                }
            }
        });
    }
}
