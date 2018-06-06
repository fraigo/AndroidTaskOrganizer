package me.franciscoigor.taskorganizer.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.franciscoigor.taskorganizer.R;
import me.franciscoigor.taskorganizer.data.ItemModel;
import me.franciscoigor.taskorganizer.data.ItemStorage;


public class ListContainerFragment extends Fragment {

    private static final String ARG_ITEMS = "items";
    private String mParamItems;
    private RecyclerView mContainer;
    private ListItemAdapter mAdapter;

    public ListContainerFragment() {

    }


    public static ListContainerFragment newInstance(ArrayList<String> items) {
        ListContainerFragment fragment = new ListContainerFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_ITEMS, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamItems = getArguments().getString(ARG_ITEMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_container, container, false);
        mContainer = view.findViewById(R.id.list_item_container);
        mContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateView(true);
        return view;
    }


    private void updateView(boolean notify){
        ArrayList<ItemModel> items= ItemStorage.get(getContext()).getItems();

        if (mAdapter==null){
            mAdapter = new ListItemAdapter(items, getActivity());
            mContainer.setAdapter(mAdapter);
        }else{
            mAdapter.setItems(items);
            if (notify){
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
