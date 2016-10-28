package cn.wtkj.charge_inspect.views.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.util.AlertBuilderUtil;
import cn.wtkj.charge_inspect.views.Adapter.AlertDialogTypeAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by ghj on 2016/4/19.
 */
public class AlertDialogContactSelect {
    private Context mContext;
    private View viewBaseInfo;
    private AlertDialogTypeAdapter adapter;
    private LayoutInflater inflater;
    private TextView viewTitle;
    private String typeName;
    private String id = "";
    private OnItemClickListener onItemClickListener;
    private String contactPhone;

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

    public AlertDialogContactSelect(Context context) {
        this.mContext = context;
    }


    public void setAlertDialog(final ContactListData.MData.info info) {
        //adapter = new AlertDialogTypeAdapter(list, mContext);
        inflater = LayoutInflater.from(mContext);
        viewBaseInfo = inflater.inflate(R.layout.dialog_contact_select, null);

        TextView dialogTitle = (TextView) viewBaseInfo.findViewById(R.id.tv_dialog_title);
        RadioGroup contactList = (RadioGroup) viewBaseInfo.findViewById(R.id.rg_contact_list);
        final RadioButton innerPhone = (RadioButton) viewBaseInfo.findViewById(R.id.phone_inner);
        final RadioButton outsidePhone = (RadioButton) viewBaseInfo.findViewById(R.id.phone_outside);
        final RadioButton switchPhone = (RadioButton) viewBaseInfo.findViewById(R.id.phone_switch);

        dialogTitle.setText(info.getOrgName()+"-"+info.getSpotName());
        innerPhone.setText("内线：" + info.getInterPhone());
        outsidePhone.setText("外线：" + info.getOutsidePhone());
        switchPhone.setText("总线：" + info.getSwitchBoard());

        contactPhone = info.getInterPhone();
        contactList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == innerPhone.getId()) {
                    contactPhone = info.getInterPhone();
                } else if(checkedId == outsidePhone.getId()) {
                    contactPhone = info.getOutsidePhone();
                }else if(checkedId == switchPhone.getId()){
                    contactPhone =  info.getSwitchBoard();
                }
            }
        });

        AlertDialog.Builder builder = AlertBuilderUtil.getBuilder(mContext);
        builder.setView(viewBaseInfo);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(new Intent(Intent.ACTION_CALL)
                        .setData(Uri.parse("tel:" + contactPhone)));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
