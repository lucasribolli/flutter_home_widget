package com.example.my_home_widget;

import static android.provider.Settings.System.getString;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import es.antonborri.home_widget.HomeWidgetPlugin;
import es.antonborri.home_widget.HomeWidgetProvider;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends HomeWidgetProvider {

    public static String REFRESH_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";
    static private RemoteViews views;
    static private int counter = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                        int[] appWidgetIds, SharedPreferences widgetData
    ) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
//
//            Intent intent = new Intent(context, NewAppWidget.class);
//            intent.setAction(REFRESH_ACTION);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            views.setOnClickPendingIntent(R.id.bt_update, pendingIntent);
//
//            int counter = widgetData.getInt("_counter", 0);
//            String counterText = "Your counter value is: " + counter;
//
//            if (counter == 0) {
//                counterText = "You have not pressed the counter button";
//            }
//
//            views.setTextViewText(R.id.tv_counter, counterText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equalsIgnoreCase(REFRESH_ACTION)) {
            views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
            counter++;
            views.setTextViewText(R.id.tv_counter, counter + "");
            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }
}