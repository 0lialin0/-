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
import cn.wtkj.charge_inspect.data.bean.ContactListData;

/**
 * Created by lcl on 2016/9/30.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ShedViewHolder> {
    private Context context;
    private List<ContactListData.MData.info> contactList;
    private OnItemClickListener3 onItemClickListener;

    public ContactListAdapter(Context context, List<ContactListData.MData.info> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public void setOnItemClickListener(OnItemClickListener3 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ShedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact_list, parent, false);
        return new ContactListAdapter.ShedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShedViewHolder holder, final int position) {
        holder.spotName.setText(contactList.get(position).getSpotName());
        holder.orgName.setText(contactList.get(position).getOrgName());
        holder.interPhone.setText(contactList.get(position).getInterPhone());
        holder.outsidePhone.setText(contactList.get(position).getOutsidePhone());
        holder.switchBoard.setText(contactList.get(position).getSwitchBoard());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(contactList.get(position).getOrgName(),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
       return contactList.size();
    }

    public class ShedViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.switchBoard)
        TextView switchBoard;
        @Bind(R.id.outsidePhone)
        TextView outsidePhone;
        @Bind(R.id.interPhone)
        TextView interPhone;
        @Bind(R.id.spotName)
        TextView spotName;
        @Bind(R.id.orgName)
        TextView orgName;

        public ShedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
