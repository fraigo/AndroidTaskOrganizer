package me.franciscoigor.taskorganizer.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.franciscoigor.taskorganizer.R;
import me.franciscoigor.taskorganizer.data.ItemModel;

import java.util.ArrayList;


public class ListItemAdapter extends RecyclerView.Adapter<ListItemHolder> {

    private ArrayList<ItemModel> mItems;
    private Activity mActivity;

    public ListItemAdapter(ArrayList<ItemModel> items, Activity activity) {
        mActivity = activity;
        mItems = items;
    }

    public ListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(R.layout.layout_item_list, parent, false);
        return new ListItemHolder(view,mActivity,this);
    }
    @Override
    public void onBindViewHolder(ListItemHolder holder, int position) {
        ItemModel item = mItems.get(position);
        holder.bindItem(item);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(ArrayList<ItemModel> items) {
        mItems = items;
    }


}