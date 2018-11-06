package com.example.android.joylin_pasteries.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.joylin_pasteries.Model.Ingredient;
import com.example.android.joylin_pasteries.Model.Step;
import com.example.android.joylin_pasteries.R;

import java.util.ArrayList;
import java.util.List;

public class RecipesIngredientsandStepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Object> dataset;
    private static final int INGREDIENT = 0;
    private  static final int STEP = 1;
    private boolean isTwoPane;
    private final OnItemClickListener listener;

    ArrayList<Object> ingredientlist =new ArrayList<Object>();


    public interface OnItemClickListener {
        void onItemClick(Step step);
    }

    public RecipesIngredientsandStepsAdapter(List<Object>dataset, boolean isTwoPane, OnItemClickListener listener){

        this.dataset = dataset;
        this.isTwoPane = isTwoPane;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case INGREDIENT:
                View ingredientView = inflater.inflate(R.layout.ingredient_item_list, parent, false);
                viewHolder = new IngredientsViewHolder(ingredientView);
                break;
            case STEP:
                View stepView = inflater.inflate(R.layout.step_item_list, parent, false);
                viewHolder = new StepsViewHolder(stepView);
                break;
             default:
                 View defaultView = inflater.inflate(R.layout.ingredient_item_list, parent, false);
                 viewHolder = new IngredientsViewHolder(defaultView);

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()){

            case INGREDIENT:
                    IngredientsViewHolder ingredientsViewHolder = (IngredientsViewHolder) viewHolder;
            setUpIngredientsViewHolder(ingredientsViewHolder, position);

                    break;

            case STEP:
                StepsViewHolder stepsViewHolder = (StepsViewHolder) viewHolder;
                setUpStepsViewHolder(stepsViewHolder, position);

                break;
         default:
             IngredientsViewHolder defaultViewHolder = (IngredientsViewHolder) viewHolder;
             setUpIngredientsViewHolder(defaultViewHolder,position);

             break;

        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    @Override
    public int getItemViewType(int position) {

        if(dataset.get(position) instanceof Ingredient){
            return INGREDIENT;
        } else if(dataset.get(position) instanceof Step){
            return STEP;
        }
        return -1;
    }

    private void setUpIngredientsViewHolder(IngredientsViewHolder ingredientsViewHolder, int position){

            Ingredient ingredient = (Ingredient) dataset.get(position);

            ingredientlist.add(ingredient);

            if(ingredient != null){

                ingredientsViewHolder.getIngredient().setText(ingredient.getName() +" "+ "("+ (ingredient.getQuantity())+ " "+
                        ingredient.getMeasure() + ")");
            }
    }


    private void setUpStepsViewHolder(StepsViewHolder stepsViewHolder, int position){

       Step step  = (Step) dataset.get(position);

        if(step != null){

            stepsViewHolder.bind(step, listener);
            stepsViewHolder.getTvStepDescription().setText( position - ingredientlist.size() +"."+" "+step.getShortDescription());
        }
    }




}
