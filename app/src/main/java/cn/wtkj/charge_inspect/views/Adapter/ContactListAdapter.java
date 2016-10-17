package cn.wtkj.charge_inspect.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.data.bean.SortModel;

/**
 * Created by lcl on 2016/9/30.
 */
public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private List<SortModel> contactList;
    private OnItemClickListener3 onItemClickListener;

    public ContactListAdapter(Context context, List<SortModel> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public void setOnItemClickListener(OnItemClickListener3 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_list, null);
        ViewHolder holder = new ViewHolder();

        holder.tvLetter = (TextView) convertView.findViewById(R.id.tvLetter);
        holder.switchBoard = (TextView) convertView.findViewById(R.id.switchBoard);
        holder.outsidePhone = (TextView) convertView.findViewById(R.id.outsidePhone);
        holder.interPhone = (TextView) convertView.findViewById(R.id.interPhone);
        holder.spotName = (TextView) convertView.findViewById(R.id.spotName);
        holder.orgName = (TextView) convertView.findViewById(R.id.orgName);
        holder.tvLetterDiver = (TextView) convertView.findViewById(R.id.tvLetterDiver);
        holder.callContact =  (ImageView) convertView.findViewById(R.id.call_contact);

        final ContactListData.MData.info contactData = contactList.get(position).getContactData();
        holder.spotName.setText(contactData.getSpotName());
        holder.orgName.setText(contactData.getOrgName());
        holder.interPhone.setText(contactData.getInterPhone());
        holder.outsidePhone.setText(contactData.getOutsidePhone());
        holder.switchBoard.setText(contactData.getSwitchBoard());


        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(contactList.get(position).getSortLetters());
            holder.tvLetterDiver.setVisibility(View.GONE);

        } else {
            holder.tvLetter.setVisibility(View.GONE);
            holder.tvLetterDiver.setVisibility(View.VISIBLE);
        }

        holder.callContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(contactData.getOrgName(),position);
                }
            }
        });
        return convertView;
    }


    public final class ViewHolder {
        public TextView tvLetter;;
        public TextView switchBoard;
        public TextView outsidePhone;
        public TextView interPhone;
        public TextView spotName;
        public TextView orgName;
        public TextView tvLetterDiver;
        public ImageView callContact;
    }

    /**
     * 得到首字母的ascii值
     */
    public int getSectionForPosition(int position) {
        return contactList.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < contactList.size(); i++) {
            String sortStr = contactList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }
}
