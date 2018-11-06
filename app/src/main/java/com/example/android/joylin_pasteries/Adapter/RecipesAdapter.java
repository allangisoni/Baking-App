package com.example.android.joylin_pasteries.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.joylin_pasteries.Model.Recipe;
import com.example.android.joylin_pasteries.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final List<Recipe> recipeList;
    private final Context context;

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Recipe recipeItems);
    }

    public RecipesAdapter(List<Recipe> recipeList, Context context, OnItemClickListener listener){

        this.recipeList = recipeList;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_list, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.bind(recipeList.get(position),listener);



    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
