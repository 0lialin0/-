package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;

/**
 * Created by ghj on 2015/10/13.
 */
public class MainRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] items;
    private int[] imgs;
    private Context context;
    private OnItemClickListener2 onItemClickListener;

    public MainRecyAdapter(Context context, String[] items, int[] imgs, OnItemClickListener2 onItemClickListener){
        this.context = context;
        this.imgs = imgs;
        this.items = items;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return items.length;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(context).inflate(R.layout.item_main_recy, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myHolder, final int i) {
            ((MyHolder)myHolder).imgLeft.setImageResource(imgs[i]);
            ((MyHolder)myHolder).tvLeftName.setText(items[i]);
            ((MyHolder)myHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(items[i]);
                }
            });
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_img)
        ImageView imgLeft;
        @Bind(R.id.tv_name)
        TextView tvLeftName;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
