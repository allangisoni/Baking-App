package com.example.android.joylin_pasteries.Adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.joylin_pasteries.Model.Step;
import com.example.android.joylin_pasteries.R;
import com.example.android.joylin_pasteries.RecipeDetailActivity;
import com.example.android.joylin_pasteries.RecipeStepActivity;
import com.example.android.joylin_pasteries.StepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsViewHolder extends RecyclerView.ViewHolder {

    public static Boolean isTwoPane;
    @BindView(R.id.tvStepDescription)
    TextView tvStepDescription;


    FragmentManager fragmentManager;

    public StepsViewHolder(final View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        isTwoPane = true;


    }
        public void bind(final Step step,final RecipesIngredientsandStepsAdapter.OnItemClickListener listener)
        {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(step);

           /**
                        if (itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                            Intent intent = new Intent(itemView.getContext(), RecipeStepActivity.class);
                            intent.putExtra("recipeSteps", step);
                            itemView.getContext().startActivity(intent);
                        } else if (itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && isTwoPane == true) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable("recipeSteps", step);
                            StepFragment stepFragment = new StepFragment();
                            stepFragment.setArguments(bundle);

                            fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.item_detail_container, stepFragment).commit();

                        } else{

                        }  **/


                        DisplayMetrics metrics = new DisplayMetrics();
                        ((AppCompatActivity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

                        float yInches= metrics.heightPixels/metrics.ydpi;
                        float xInches= metrics.widthPixels/metrics.xdpi;
                        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
                        if (diagonalInches>=6.5){


                            Bundle bundle = new Bundle();
                            bundle.putParcelable("recipeSteps", step);
                            StepFragment stepFragment = new StepFragment();
                            stepFragment.setArguments(bundle);

                            fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.item_detail_container, stepFragment).commit();


                        }else{

                            Intent intent = new Intent(itemView.getContext(), RecipeStepActivity.class);
                            intent.putExtra("recipeSteps", step);
                            itemView.getContext().startActivity(intent);
                        }


                        //listener.onItemClick(step);

                        if (isTwoPane == true) {

                            Bundle bundle = new Bundle();
                            bundle.putParcelable("recipeSteps", step);
                            StepFragment stepFragment = new StepFragment();
                            stepFragment.setArguments(bundle);

                            fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.item_detail_container, stepFragment).commit();

                        } else {


                        }
                    }
                }
            });


    }



    public TextView getTvStepDescription(){
        return  this.tvStepDescription;
    }

    public void setTvStepDescription(TextView tvStepDescription){

        this.tvStepDescription = tvStepDescription;
    }

   /** public TextView getTvStepVideoUrl(){
        return this.tvStepVideoUrl;
    }

    public void setTvStepVideoUrl(TextView tvStepVideoUrl){

        this.tvStepVideoUrl = tvStepVideoUrl;
    } **/

   public void SetFragmentManager(FragmentManager fragmentManeger){
       this.fragmentManager = fragmentManeger;
    }
}
