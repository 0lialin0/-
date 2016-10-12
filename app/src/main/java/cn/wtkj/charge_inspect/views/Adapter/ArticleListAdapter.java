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
import cn.wtkj.charge_inspect.data.bean.ArticleListData;

/**
 * Created by lcl on 2016/9/30.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ShedViewHolder> {
    private Context context;
    private List<ArticleListData.MData.info> businessInfoList;
    private OnItemClickListener3 onItemClickListener;

    public ArticleListAdapter(Context context, List<ArticleListData.MData.info> businessInfoList) {
        this.context = context;
        this.businessInfoList = businessInfoList;
    }

    public void setOnItemClickListener(OnItemClickListener3 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article_list, parent, false);
        return new ArticleListAdapter.ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {
        holder.title.setText(businessInfoList.get(position).getTextTitle());
        holder.addTime.setText(businessInfoList.get(position).getCreateDt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(businessInfoList.get(position).getTextTitle(),position);
                }
            }
        });
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
