package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;

/**
 * Created by lxg on 2015/9/21.
 */
public class GreenRecordListAdapter extends RecyclerView.Adapter<GreenRecordListAdapter.ShedViewHolder> {
    private Context context;
    private List<JCGreenChannelRecData> dataList;
    private OnItemClickListener onItemClickListener;

    public GreenRecordListAdapter(Context context, List<JCGreenChannelRecData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shed_list, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {

        holder.tvVehplate.setText(dataList.get(position).getVehPlateNo());
        String title = dataList.get(position).getTonnage() + "吨 | " +
                dataList.get(position).getInStationName() + " | " +
                dataList.get(position).getGoodsName();
        holder.tvTitle.setText(title);
        holder.tvTime.setText(dataList.get(position).getCheckDate());
        int isMix=dataList.get(position).getIsMix();
        if(isMix==0){
            holder.llActiveTag.setBackgroundResource(R.drawable.green);
        }else{
            holder.llActiveTag.setBackgroundResource(R.drawable.red);
        }
        holder.tvStatus.setText(dataList.get(position).getIsMixName());

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

        /*holder.rlContent.setOnClickListener(new View.OnClickListener() {
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
        @Bind(R.id.rl_content)
        RelativeLayout rlContent;
        @Bind(R.id.ll_active_tag)
        LinearLayout llActiveTag;
        @Bind(R.id.active_img)
        ImageView activeImg;
        @Bind(R.id.active_vehPlate)
        TextView tvVehplate;
        @Bind(R.id.active_title)
        TextView tvTitle;
        @Bind(R.id.active_time)
        TextView tvTime;
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
            ButterKnife.bind(this,itemView);
        }
    }
}
