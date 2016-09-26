package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;

/**
 * Created by lxg on 2015/9/21.
 */
public class GreenRecordListAdapter extends RecyclerView.Adapter<GreenRecordListAdapter.ShedViewHolder> {
    private Context context;
    private List<String> patrolProjectDatas;
    private OnItemClickListener2 onItemClickListener;

    public GreenRecordListAdapter(Context context, List<String> patrolProjectDatas) {
        this.context = context;
        this.patrolProjectDatas = patrolProjectDatas;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shed_list, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {


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
        return patrolProjectDatas.size();
    }

    public void setOnItemClickListener(OnItemClickListener2 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.active_img)
        ImageView activeImg;
        @Bind(R.id.active_status)
        TextView tvStatus;
        @Bind(R.id.active_title)
        TextView tvTitle;
        @Bind(R.id.active_content)
        TextView tvContent;
        @Bind(R.id.active_time)
        TextView tvTime;

        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
