package me.franciscoigor.taskorganizer.list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import me.franciscoigor.taskorganizer.data.ItemModel;
import me.franciscoigor.taskorganizer.data.ItemStorage;

public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    Activity mActivity;
    ListItemAdapter mAdapter;
    ItemModel mItem;

    public ListItemHolder(final View itemView, Activity activity, ListItemAdapter adapter) {
        super(itemView);
        mActivity = activity;
        mAdapter = adapter;
        //mTitleTextView = itemView.findViewById(R.id.item_text);
        itemView.setOnClickListener(this);

    }

    public void deleteItem(ItemModel item){
        ItemStorage storage = ItemStorage.get(mActivity);
        ArrayList<ItemModel> items = storage.getItems();
        int pos = items.indexOf(item);
        mAdapter.notifyItemRemoved(pos);
        storage.deleteItem(mItem);

    }

    public void bindItem(ItemModel item){
        mItem = item;

    }

    @Override
    public void onClick(View view){
        //Intent intent = ItemListActivity.newIntent(getActivity(), mItem.getId());
        Intent intent = ItemActivity.newIntent(mActivity,mItem.getUUID());
        mActivity.startActivity(intent);
    }
}