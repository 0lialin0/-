package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;

/**
 * Created by lxg on 2015/9/21.
 */
public class NameRollManageListAdapter extends RecyclerView.Adapter<NameRollManageListAdapter.ShedViewHolder> {
    private Context context;
    private List<JCBlackListData> dataList;
    private OnItemClickListener onItemClickListener;

    public NameRollManageListAdapter(Context context, List<JCBlackListData> datas) {
        this.context = context;
        this.dataList = datas;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nameroll_manage_list, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {

        holder.tvVehplate.setText(dataList.get(position).getVepPlateNo());
        String title = dataList.get(position).getVepPlateNoColorName() + " | " +
                dataList.get(position).getVepColorName() + "车身 | " +
                dataList.get(position).getVehicleTypeName();
        holder.tvTitle.setText(title);
        holder.tvTime.setText(dataList.get(position).getGenDT());
        holder.tvStatus.setText(dataList.get(position).getPeccancyTypeName());
        String nameType = "黑名单";
        if (dataList.get(position).getNameType() == 0) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_black_img);
            nameType = "黑名单";
        } else if (dataList.get(position).getNameType() == 1) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_gray_img);
            nameType = "灰名单";
        } else if (dataList.get(position).getNameType() == 2) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_yellow_img);
            nameType = "黄名单";
        }
        holder.tvName.setText(nameType);

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "del");
                }
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "edit");
                }
            }
        });
        holder.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "submit");
                }
            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,0,"");
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.active_img)
        ImageView ivImg;
        @Bind(R.id.active_vehPlate)
        TextView tvVehplate;
        @Bind(R.id.active_title)
        TextView tvTitle;
        @Bind(R.id.active_time)
        TextView tvTime;
        @Bind(R.id.ll_active_tag)
        LinearLayout llActiveTag;
        @Bind(R.id.active_name)
        TextView tvName;
        @Bind(R.id.active_status)
        TextView tvStatus;
        @Bind(R.id.tv_delete)
        TextView tvDelete;
        @Bind(R.id.tv_edit)
        TextView tvEdit;
        @Bind(R.id.tv_submit)
        TextView tvSubmit;

        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
