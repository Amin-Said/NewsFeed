package com.amin.linktask.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.amin.linktask.R;
import com.amin.linktask.databinding.ActivityDetailsBinding;
import com.amin.linktask.pojo.Article;
import com.amin.linktask.utils.Constants;

public class DetailsActivity extends AppCompatActivity {

    private DetailsViewModel detailsViewModel;
    private ActivityDetailsBinding activityDetailsBinding;
    private Article article;

    private View.OnClickListener backOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        detailsViewModel= ViewModelProviders.of(this).get(DetailsViewModel.class);

        Intent intent = getIntent();
        article = intent.getParcelableExtra(Constants.ARTICLE);

        detailsViewModel.sendData(article);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, DetailsFragment.newInstance())
                .commitNow();

        activityDetailsBinding.ivBack.setOnClickListener(backOnClickListner);

    }

}
