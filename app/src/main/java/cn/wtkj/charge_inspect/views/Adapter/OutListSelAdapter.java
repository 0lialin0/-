package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;

/**
 * Created by lxg on 2015/9/21.
 */
public class OutListSelAdapter extends RecyclerView.Adapter<OutListSelAdapter.ShedViewHolder> {
    private Context context;
    private List<OutListData.MData.info> dataList;
    private OnItemClickListener onItemClickListener;
    private ConstAllDb constAllDb;
    private OrganizationDb organizationDb;
    private String but = "0";

    public OutListSelAdapter(Context context, List<OutListData.MData.info> data) {
        this.context = context;
        this.dataList = data;
        constAllDb = new ConstAllDb(context);
        organizationDb = new OrganizationDb(context);
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_out_list_sel, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {

        holder.tvVehPlate.setText(dataList.get(position).getInvehplateNo());
        holder.tvOutVehPlate.setText(dataList.get(position).getVehplateNo());
        holder.tvEntLoca.setText(dataList.get(position).getInstationName());
        holder.tvCharge.setText(dataList.get(position).getOprId());
        String outLoca=dataList.get(position).getOutstationName()+"/"+
                dataList.get(position).getOutlaneName();
        holder.tvOutLoca.setText(outLoca);
        holder.tvOutTime.setText(dataList.get(position).getOperateOn());
        holder.tvVehType.setText(dataList.get(position).getVehTypeName());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //名单
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "name");
                }
            }
        });
        holder.tvGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绿通
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "green");
                }
            }
        });
        holder.tvIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增收
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "increment");
                }
            }
        });
        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.active_vehPlate)
        TextView tvVehPlate;
        @Bind(R.id.active_out_vehPlate)
        TextView tvOutVehPlate;
        @Bind(R.id.active_ent_loca)
        TextView tvEntLoca;
        @Bind(R.id.active_charge)
        TextView tvCharge;
        @Bind(R.id.active_out_loca)
        TextView tvOutLoca;
        @Bind(R.id.active_out_time)
        TextView tvOutTime;
        @Bind(R.id.active_veh_type)
        TextView tvVehType;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_green)
        TextView tvGreen;
        @Bind(R.id.tv_increment)
        TextView tvIncrement;
        @Bind(R.id.rl_content)
        RelativeLayout rlContent;


        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
