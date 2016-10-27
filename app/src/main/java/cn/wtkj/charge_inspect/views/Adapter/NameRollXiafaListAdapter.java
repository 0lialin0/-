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
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;

/**
 * Created by lxg on 2015/9/21.
 */
public class NameRollXiafaListAdapter extends RecyclerView.Adapter<NameRollXiafaListAdapter.ShedViewHolder> {
    private Context context;
    private List<NameRollXiafaData.MData.info> dataList;
    private OnItemClickListener onItemClickListener;

    public NameRollXiafaListAdapter(Context context, List<NameRollXiafaData.MData.info> datas) {
        this.context = context;
        this.dataList = datas;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nameroll_xiafa_list, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {

        holder.tvVehplate.setText(dataList.get(position).getVEPPLATENO());
        String title = dataList.get(position).getVEPPLATENOCOLORNAME() + " | " +
                dataList.get(position).getVEPCOLORNAME() + "车身  ";
        holder.tvTitle.setText(title);
        holder.tvTime.setText(dataList.get(position).getCREATEDT());
        String peccancyTypeName="";
        String nameType = "黑名单";
        if (dataList.get(position).getTYPE() == 1) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_black_img);
            nameType = "黑名单";
            peccancyTypeName=dataList.get(position).getPECCANCYNAME();
        } else if (dataList.get(position).getTYPE() == 2) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_gray_img);
            nameType = "灰名单";
            peccancyTypeName=dataList.get(position).getPECCANCYNAME();
            holder.llXiafa.setVisibility(View.GONE);
        } else if (dataList.get(position).getTYPE() == 3) {
            holder.llActiveTag.setBackgroundResource(R.drawable.name_yellow_img);
            nameType = "黄名单";
            peccancyTypeName="";
            holder.llXiafa.setVisibility(View.GONE);
        }
        holder.tvStatus.setText(peccancyTypeName);
        holder.tvName.setText(nameType);

        holder.tvXiafa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下发名单处理
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, 0, dataList.get(position).getID());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,dataList.get(position).getTYPE(),"info");
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
        @Bind(R.id.tv_xiafa)
        TextView tvXiafa;
        @Bind(R.id.ll_xiafa)
        LinearLayout llXiafa;


        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
