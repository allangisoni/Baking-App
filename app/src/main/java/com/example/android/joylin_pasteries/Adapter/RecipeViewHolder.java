package com.example.android.joylin_pasteries.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.joylin_pasteries.Model.Recipe;
import com.example.android.joylin_pasteries.R;
import com.example.android.joylin_pasteries.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

   @BindView(R.id.ivRecipe) ImageView ivRecipeImage;
   @BindView(R.id.tvRecipeName) TextView  tvRecipeName;
    public RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(final Recipe recipe, final RecipesAdapter.OnItemClickListener listener) {


        if(recipe.getImage() != null){
            Picasso.with(itemView.getContext()).load(R.drawable.recipekitchenking).into(ivRecipeImage);
        }else {
            Picasso.with(itemView.getContext()).load(recipe.getImage()).placeholder(R.drawable.myrecipe).error(R.drawable.myrecipe).into(ivRecipeImage);
        }
        tvRecipeName.setText(recipe.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(recipe);
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(itemView.getContext(), RecipeDetailActivity.class);
                    intent.putExtra("recipeDetails", recipe);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(intent);
                   //Toast.makeText(itemView.getContext(), "You clicked" + " " + getAdapterPosition(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
