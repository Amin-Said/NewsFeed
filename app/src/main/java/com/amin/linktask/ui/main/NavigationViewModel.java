package com.amin.linktask.ui.main;

import android.content.Context;
import com.amin.linktask.R;
import com.amin.linktask.pojo.NavDataModel;
import java.util.ArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationViewModel extends ViewModel {
    private String TAG = "NavigationViewModel";
    protected MutableLiveData<ArrayList<NavDataModel>> navMenuLiveData = new MutableLiveData<>();

    public void getNavMenuItems(Context context) {
        String[] drawerTitles = context.getResources().getStringArray(R.array.drawer_titles);
        ArrayList<NavDataModel> navDataModels = new ArrayList<>();
        navDataModels.add(new NavDataModel(context.getResources().getDrawable(R.drawable.explore), drawerTitles[0],
                false));
        navDataModels.add(new NavDataModel(context.getResources().getDrawable(R.drawable.live), drawerTitles[1],
                false));
        navDataModels.add(new NavDataModel(context.getResources().getDrawable(R.drawable.gallery), drawerTitles[2],
                false));
        navDataModels.add(new NavDataModel(context.getResources().getDrawable(R.drawable.wishlist), drawerTitles[3],
                false));
        navDataModels.add(new NavDataModel(context.getResources().getDrawable(R.drawable.magazine), drawerTitles[4],
                false));
        navMenuLiveData.setValue(navDataModels);
    }
}
