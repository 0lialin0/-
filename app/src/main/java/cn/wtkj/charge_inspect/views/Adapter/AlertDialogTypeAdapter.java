package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;


/**
 * Created by ghj on 2016/4/19.
 */
public class AlertDialogTypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<KeyValueData> mList;
    private int mPosition=-1;

    public AlertDialogTypeAdapter(List<KeyValueData> list, Context context)
    {
        mContext=context;
        mList=list;
    }

    public void updateSelectedItem(int position)
    {
        mPosition=position;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }
    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_down_menu,null);
        TextView textView=(TextView) convertView.findViewById(R.id.down_menu_item);
        textView.setText(mList.get(position).getValue());
        if (mPosition==position)
        {
            textView.setSelected(true);
        }
        return convertView;
    }

}
