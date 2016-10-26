package cn.wtkj.charge_inspect.views.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

import cn.wtkj.charge_inspect.R;

/**
 * Created by lxg on 2015/9/18.
 */
public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bitmap> mList;
    private int count;
    private OnItemClickListener onItemClickListener;

    public PhotoAdapter(Context context, List<Bitmap> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(this.context).inflate(
                    R.layout.item, viewGroup, false);
            return new ItemHolde(view);
        } else {
            View view = LayoutInflater.from(this.context).inflate(
                    R.layout.item_add, viewGroup, false);
            return new AddItemHoler(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (i == count - 1) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick();
                }
            });
        } else {
            ((ItemHolde) viewHolder).imageView.setImageBitmap(mList.get(i));
            ((ItemHolde) viewHolder).imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDeletePics(i);
                }
            });
            ((ItemHolde) viewHolder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(i);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position != count - 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return this.count = mList == null ? 1 : mList.size() + 1;
    }

    public class ItemHolde extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageDelete;

        public ItemHolde(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.img);
            imageDelete = (ImageView) view.findViewById(R.id.delete_pics);
        }
    }

    public class AddItemHoler extends RecyclerView.ViewHolder {
        public AddItemHoler(View view) {
            super(view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick();
        void onItemClick(int id);
        void onDeletePics(int id);
    }
}
