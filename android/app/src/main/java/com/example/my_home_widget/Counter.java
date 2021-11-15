package com.example.my_home_widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

public class Counter {
    private SharedPreferences pref;
    private RemoteViews views;
    private Context context;

    public Counter(Context context, RemoteViews views) {
        this.pref = PreferenceManager.getDefaultSharedPreferences(context);
        this.views = views;
        this.context = context;
    }

    public void increment() {
        int counter = pref.getInt("_counter", 0);

        int newCounter = counter + 1;
//
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt("_counter", newCounter);
//        editor.apply();

        String counterText = "Your counter value is: " + newCounter;
        if (newCounter == 0) {
            counterText = "You have not pressed the counter button";
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget.class));

        views.setTextViewText(R.id.tv_counter, counterText);

        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }
}
