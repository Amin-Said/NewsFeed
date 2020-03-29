package com.amin.linktask.ui.main;

import android.content.Context;
import android.view.View;
import com.amin.linktask.databinding.NavMenuItemBinding;
import com.amin.linktask.pojo.NavDataModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectableViewHolder extends RecyclerView.ViewHolder {

    public static final int MULTI_SELECTION = 2;
    public static final int SINGLE_SELECTION = 1;
    public NavMenuItemBinding navMenuItemBinding;
    protected NavDataModel mItem;
    private OnItemSelectedListener itemSelectedListener;
    private Context mContext;

    public SelectableViewHolder(@NonNull NavMenuItemBinding navMenuItemBinding, OnItemSelectedListener listener, final Context context) {
        super(navMenuItemBinding.getRoot());
        this.navMenuItemBinding = navMenuItemBinding;
        itemSelectedListener = listener;
        mContext = navMenuItemBinding.constraintLayout.getContext();
        navMenuItemBinding.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItem.isSelected() && getItemViewType() == MULTI_SELECTION) {
                    setChecked(false);
                } else {
                    setChecked(true);
                }
                itemSelectedListener.onItemSelected(mItem);
            }
        });
    }

    public void setChecked(boolean value) {
        if (value) {
            navMenuItemBinding.ivSelected.setVisibility(View.VISIBLE);
        } else {
            navMenuItemBinding.ivSelected.setVisibility(View.GONE);
        }
        mItem.setSelected(value);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(NavDataModel item);
    }

}