package com.example.android.joylin_pasteries;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.joylin_pasteries.Model.Ingredient;
import com.example.android.joylin_pasteries.Model.Recipe;
import com.example.android.joylin_pasteries.REST.RecipeApiClient;

public class RecipeWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;

    Recipe recipe = new Recipe();



    public RecipeWidgetRemoteViewsFactory(Context mContext, Intent intent){

        this.mContext = mContext;
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {



        recipe = Prefs.loadRecipe(mContext);


    }

    @Override
    public void onDestroy() {


    }

    @Override
    public int getCount() {
        return recipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Ingredient ingredient = recipe.getIngredients().get(position);

        if (position == AdapterView.INVALID_POSITION) {
            return null;
        }

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.collection_widget_list_item);
        rv.setTextViewText(R.id.widgetRecipeIngredientLabel, ingredient.getName());

        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
