package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lcl on 2016/9/30.
 */
public class BusinessInfoListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> businessInfoList;
    private OnItemClickListener onItemClickListener;

    public BusinessInfoListAdapter(Context context, List<String> businessInfoList) {
        this.context = context;
        this.businessInfoList = businessInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
