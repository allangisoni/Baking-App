package com.example.android.joylin_pasteries;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.android.joylin_pasteries.Model.Recipe;

public class RecipeWidgetRemoteViewsService extends RemoteViewsService {

    public static void updateWidget(Context context, Recipe recipe) {
        Prefs.saveRecipe(context, recipe);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingWidgetProvider.class));
        BakingWidgetProvider.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
