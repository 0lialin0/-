package cn.wtkj.charge_inspect.views.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.util.AlertBuilderUtil;
import cn.wtkj.charge_inspect.views.Adapter.AlertDialogTypeAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by ghj on 2016/4/19.
 */
public class AlertDialogType {
    private Context mContext;
    private View viewBaseInfo;
    private AlertDialogTypeAdapter adapter;
    private LayoutInflater inflater;
    private TextView viewTitle;
    private String typeName;
    private String id = "";
    private OnItemClickListener onItemClickListener;
    private String nameType;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlertDialogType(Context context) {
        this.mContext = context;
    }


    public void setAlertDialog( String title) {
        //adapter = new AlertDialogTypeAdapter(list, mContext);
        inflater = LayoutInflater.from(mContext);
        viewBaseInfo = inflater.inflate(R.layout.dialog_account, null);
        RadioGroup name = (RadioGroup) viewBaseInfo.findViewById(R.id.rg_name);
        final RadioButton balck = (RadioButton) viewBaseInfo.findViewById(R.id.rb_balck);
        final RadioButton grey = (RadioButton) viewBaseInfo.findViewById(R.id.rb_grey);
        final RadioButton yellow = (RadioButton) viewBaseInfo.findViewById(R.id.rb_yellow);
        nameType="balck";
        name.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == balck.getId()) {
                    nameType="balck";
                } else if(checkedId == grey.getId()) {
                    nameType="grey";
                }else if(checkedId == yellow.getId()){
                    nameType="yellow";
                }
            }
        });

        AlertDialog.Builder builder = AlertBuilderUtil.getBuilder(mContext);
        builder.setView(viewBaseInfo);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(1,1,nameType);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    private AdapterView.OnItemClickListener mItemClickListener(final TextView textview) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                KeyValueData expyData = (KeyValueData) parent.getAdapter().getItem(position);
                typeName = expyData.getValue();
                AlertDialogType.this.id = expyData.getId();
                if (android.os.Build.VERSION.SDK_INT < 11) {
                    textview.setText(expyData.getValue());
                }
            }
        };
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
