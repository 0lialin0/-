package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;

/**
 * Created by lxg on 2015/9/21.
 */
public class IncrementListAdapter extends RecyclerView.Adapter<IncrementListAdapter.ShedViewHolder> {
    private Context context;
    private List<JCEscapeBookData> dataList;
    private OnItemClickListener2 onItemClickListener;
    private ConstAllDb constAllDb;

    public IncrementListAdapter(Context context, List<JCEscapeBookData> patrolProjectDatas) {
        this.context = context;
        this.dataList = patrolProjectDatas;
        constAllDb=new ConstAllDb(context);
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_increment_list, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {

        holder.tvVehplate.setText(dataList.get(position).getVehPlate());
        String loca=constAllDb.getConstName(dataList.get(position).getInStationID(),1);
        holder.tvEntranceLoca.setText(loca);
        String entrance=constAllDb.getConstName(dataList.get(position).getInDecision(),5);
        holder.tvEntranceType.setText(entrance);
        String out=constAllDb.getConstName(dataList.get(position).getOutDecision(),5);
        holder.tvExitType.setText(out);
        holder.tvTime.setText(dataList.get(position).getFindDT());
        holder.tvUnit.setText(dataList.get(position).getOrgLevel());
        holder.tvMoney.setText(dataList.get(position).getRealityMoney());
        String zsType=constAllDb.getConstName(dataList.get(position).getPeccancyTypeID(),9);
        holder.tvStatus.setText(zsType);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener2 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.active_vehPlate)
        TextView tvVehplate;
        @Bind(R.id.active_entrance_loca)
        TextView tvEntranceLoca;
        @Bind(R.id.active_entrance_type)
        TextView tvEntranceType;
        @Bind(R.id.active_exit_type)
        TextView tvExitType;
        @Bind(R.id.active_time)
        TextView tvTime;
        @Bind(R.id.active_unit)
        TextView tvUnit;
        @Bind(R.id.active_money)
        TextView tvMoney;
        @Bind(R.id.active_status)
        TextView tvStatus;


        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
