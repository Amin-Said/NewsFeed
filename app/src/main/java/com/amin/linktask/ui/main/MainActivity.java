package com.amin.linktask.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.amin.linktask.R;
import com.amin.linktask.databinding.ActivityMainBinding;
import com.amin.linktask.pojo.NavDataModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener  {

    private String TAG = "MainActivity";
    private ActivityMainBinding mActivityMainBinding;
    private NavigationViewModel navigationViewModel;
    private NavMenuRecyclerAdapter mNavigationAdapter;

    // listener for navigation menu
    private SelectableViewHolder.OnItemSelectedListener listener;

    // listener for menu icon click
    private View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mActivityMainBinding.drawerLayout.openDrawer(GravityCompat.START);
            if (mActivityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
    };

    // observer for navigation menu items
    private Observer<ArrayList<NavDataModel>> navigationMenuObserver = new Observer<ArrayList<NavDataModel>>() {
        @Override
        public void onChanged(ArrayList<NavDataModel> navDataModels) {
            mNavigationAdapter = new NavMenuRecyclerAdapter(listener,navDataModels,false,
                    MainActivity.this);
            mActivityMainBinding.RVDrawer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mActivityMainBinding.RVDrawer.setAdapter(mNavigationAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // listener for navigation menu items
        listener = this;

        // init view model
        navigationViewModel = ViewModelProviders.of(this).get(NavigationViewModel.class);

        // init fragment
         getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commitNow();

        // listener for navigation menu icon
        mActivityMainBinding.ivMenu.setOnClickListener(menuClickListener);

        // observer for navigation drawer
        navigationViewModel.navMenuLiveData.observe(this, navigationMenuObserver);

        // get navigation drawer items
        navigationViewModel.getNavMenuItems(this);
    }

    @Override
    public void onBackPressed() {
        if (mActivityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            finish();
        }
    }

    // listener for navigation menu selection
    @Override
    public void onItemSelected(NavDataModel item) {
        Toast.makeText(this, item.getName(),
                Toast.LENGTH_SHORT).show();
    }

}
