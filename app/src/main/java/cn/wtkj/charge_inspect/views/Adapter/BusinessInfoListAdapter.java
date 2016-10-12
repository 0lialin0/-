package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.BusinessInfoData;

/**
 * Created by lcl on 2016/9/30.
 */
public class BusinessInfoListAdapter  extends RecyclerView.Adapter<BusinessInfoListAdapter.ShedViewHolder> {
    private Context context;
    private List<BusinessInfoData.MData.info> businessInfoList;
    private OnItemClickListener onItemClickListener;

    public BusinessInfoListAdapter(Context context, List<BusinessInfoData.MData.info> businessInfoList) {
        this.context = context;
        this.businessInfoList = businessInfoList;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_info, parent, false);
        return new BusinessInfoListAdapter.ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {
        holder.title.setText(businessInfoList.get(position).getTextTitle());
        holder.addTime.setText(businessInfoList.get(position).getCreateDt());
    }

    @Override
    public int getItemCount() {
       return businessInfoList.size();
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.addTime)
        TextView addTime;

        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
