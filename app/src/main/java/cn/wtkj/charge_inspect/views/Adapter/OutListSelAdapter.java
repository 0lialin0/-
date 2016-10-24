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
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;

/**
 * Created by lxg on 2015/9/21.
 */
public class OutListSelAdapter extends RecyclerView.Adapter<OutListSelAdapter.ShedViewHolder> {
    private Context context;
    private List<JCEscapeBookData> dataList;
    private OnItemClickListener onItemClickListener;
    private ConstAllDb constAllDb;
    private OrganizationDb organizationDb;
    private String but="0";

    public OutListSelAdapter(Context context, List<JCEscapeBookData> patrolProjectDatas) {
        this.context = context;
        this.dataList = patrolProjectDatas;
        constAllDb=new ConstAllDb(context);
        organizationDb=new OrganizationDb(context);
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_out_list_sel, parent, false);
        return new ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {



        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,0,"del");
                }
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,0,"edit");
                }
            }
        });
        holder.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,0,"submit");
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,0,"");
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
