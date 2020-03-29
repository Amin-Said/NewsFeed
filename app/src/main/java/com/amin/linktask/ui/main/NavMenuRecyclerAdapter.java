package com.amin.linktask.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amin.linktask.R;
import com.amin.linktask.databinding.NavMenuItemBinding;
import com.amin.linktask.pojo.NavDataModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NavMenuRecyclerAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {

    private final List<NavDataModel> mValues;
    private boolean isMultiSelectionEnabled = false;
    private SelectableViewHolder.OnItemSelectedListener listener;
    private Context mContext;


    public NavMenuRecyclerAdapter(SelectableViewHolder.OnItemSelectedListener listener,
                                  List<NavDataModel> items, boolean isMultiSelectionEnabled, Context context) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;
        this.mContext = context;
        this.mValues = items;

    }

    @Override
    public SelectableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NavMenuItemBinding navMenuItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.nav_menu_item, parent, false);
        return new SelectableViewHolder(navMenuItemBinding, this, mContext);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        NavDataModel selectableItem = mValues.get(position);
        holder.navMenuItemBinding.setNavDataModel(selectableItem);
        holder.mItem = selectableItem;
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<NavDataModel> getSelectedItems() {

        List<NavDataModel> selectedItems = new ArrayList<>();
        for (NavDataModel item : mValues) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (isMultiSelectionEnabled) {
            return SelectableViewHolder.MULTI_SELECTION;
        } else {
            return SelectableViewHolder.SINGLE_SELECTION;
        }
    }

    @Override
    public void onItemSelected(NavDataModel item) {
        if (!isMultiSelectionEnabled) {

            for (NavDataModel selectableItem : mValues) {
                if (!selectableItem.equals(item)
                        && selectableItem.isSelected()) {
                    selectableItem.setSelected(false);
                } else if (selectableItem.equals(item)
                        && item.isSelected()) {
                    selectableItem.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }
}