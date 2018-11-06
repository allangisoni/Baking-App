package com.example.android.joylin_pasteries.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.joylin_pasteries.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {

     @BindView(R.id.tvIngredients)
    TextView tvIngredients;
    public IngredientsViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public  TextView getIngredient(){

        return this.tvIngredients;
    }

    public  void  setIngredient (TextView ingredient){
        this.tvIngredients = ingredient;
    }
}
