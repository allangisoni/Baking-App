package com.example.android.joylin_pasteries;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.joylin_pasteries.Adapter.RecipesAdapter;
import com.example.android.joylin_pasteries.Model.Recipe;
import com.example.android.joylin_pasteries.REST.RecipeApiClient;
import com.example.android.joylin_pasteries.REST.RecipeApiInterface;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecipesAdapter recipesAdapter;

   @BindView(R.id.rvRecipe)  RecyclerView recyclerView;

    private List<Recipe> recipeList;

   final RecipeApiInterface recipeApiInterface = RecipeApiClient.getClient().create(RecipeApiInterface.class);

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getRecipes();


    }


    private  void getRecipes(){

        Call<List <Recipe>> call = recipeApiInterface.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    recipeList = response.body();

                    recipesAdapter = new RecipesAdapter(recipeList, getApplicationContext(), new RecipesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Recipe recipeItems) {

                        }
                    });

                    Log.d(TAG, "Number of Recipes found is:" + recipeList.size());

                    recyclerView.setAdapter(recipesAdapter);
                } else {

                    Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {


                if (t instanceof IOException) {
                    Log.e(TAG, t.toString());
                    Toast.makeText(MainActivity.this, "Please check your internet Connection", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MainActivity.this, "Conversion error encountered", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
