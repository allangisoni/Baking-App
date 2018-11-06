package com.example.android.joylin_pasteries;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.android.joylin_pasteries.Model.Step;

import butterknife.BindView;

public class RecipeStepActivity  extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.step_fragment);

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){

            Bundle bundle = new Bundle();
            bundle.putParcelable("recipeSteps", getIntent().getParcelableExtra("recipeSteps"));
           // Step step = getIntent().getParcelableExtra("recipeSteps");
            //Toast.makeText(this, step.getDescription(), Toast.LENGTH_SHORT).show();
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, stepFragment).commit();
        }

    }


}
