package com.example.android.joylin_pasteries;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.joylin_pasteries.Adapter.RecipesIngredientsandStepsAdapter;
import com.example.android.joylin_pasteries.Adapter.StepsViewHolder;
import com.example.android.joylin_pasteries.Model.Ingredient;
import com.example.android.joylin_pasteries.Model.Recipe;
import com.example.android.joylin_pasteries.Model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {

    private boolean mTwoPane;
    @BindView(R.id.item_list)RecyclerView recyclerView;
    private Recipe recipe;
    private List<Ingredient> recipeIngredient;
    private  List<Step> recipeSteps;
    private ArrayList<Object> recipeObjects;
    private String recipeName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            setContentView(R.layout.activity_recipe_detail);
           StepsViewHolder.isTwoPane = false;

           Toast.makeText(this, StepsViewHolder.isTwoPane.toString(), Toast.LENGTH_SHORT ).show();
        } else {

            setContentView(R.layout.recipe_detail_item_list);
           StepsViewHolder.isTwoPane = true;
            Toast.makeText(this, StepsViewHolder.isTwoPane.toString(), Toast.LENGTH_SHORT ).show();
        }
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recipeObjects = new ArrayList<Object>();
        Intent intent = getIntent();

        if(intent.hasExtra("recipeDetails")) {

            recipe = intent.getParcelableExtra("recipeDetails");
            recipeIngredient = recipe.getIngredients();
            recipeSteps = recipe.getSteps();
            recipeName = recipe.getName();

            recipeObjects.addAll(recipeIngredient);
            recipeObjects.addAll(recipeSteps);

            setTitle(recipeName);


        }
        else {

            Toast.makeText(this, "Data is not available", Toast.LENGTH_SHORT).show();
        }

      /**  if(findViewById(R.id.item_detail_container) != null){

            mTwoPane = true;
        } **/

        mTwoPane = StepsViewHolder.isTwoPane;



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipesIngredientsandStepsAdapter(recipeObjects, mTwoPane, new RecipesIngredientsandStepsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Step step) {

            }
        }));

    }
}
