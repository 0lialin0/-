package cn.wtkj.charge_inspect.views.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class RecyClerRefresh extends RecyclerView {
    private int last;
    private RefreshData refreshData;

    public RecyClerRefresh(Context context) {
        super(context);
    }

    public RecyClerRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(new MyScrollListener());
    }

    public RecyClerRefresh(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRefreshData(RefreshData refreshData) {
        this.refreshData = refreshData;
    }

    private class MyScrollListener extends OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            last = ((LinearLayoutManager) recyclerView
                    .getLayoutManager()).findLastVisibleItemPosition();
            if (RecyClerRefresh.this.getAdapter() != null) {
                if (last == RecyClerRefresh.this.getAdapter().getItemCount() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (refreshData != null)
                        refreshData.onRefreshData();
                }
            }
        }
    }

    public interface RefreshData {
        void onRefreshData();
    }
}