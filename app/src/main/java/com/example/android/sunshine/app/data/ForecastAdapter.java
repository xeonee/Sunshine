package com.example.android.sunshine.app.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeon.amar.sunshine.ForcastFragment;
import com.xeon.amar.sunshine.R;

/**
 * {@link com.example.android.sunshine.app.data.ForecastAdapter} exposes a list of weather forecasts
 * from a {@link Cursor} to a {@link android.widget.ListView}.
 */
public class ForecastAdapter extends CursorAdapter {
//    public ForecastAdapter(Context context, Cursor c, int flags) {
//        super(context, c, flags);
//    }
    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;
    /**
     * Prepare the weather high/lows for presentation.
     */
//    private String formatHighLows(double high, double low) {
//        boolean isMetric = Utility.isMetric(mContext);
//        String highLowStr = Utility.formatTemperature(high, isMetric)  "/"  Utility.formatTemperature(low, isMetric);
//        return highLowStr;
//    }

    /*
        This is ported from FetchWeatherTask --- but now we go straight from the cursor to the
        string.
     */
//    private String convertCursorRowToUXFormat(Cursor cursor) {
//        // get row indices for our cursor
////        int idx_max_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
////        int idx_min_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
////        int idx_date = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
////        int idx_short_desc = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
//
//        String highAndLow = formatHighLows(
////                cursor.getDouble(idx_max_temp),
////                cursor.getDouble(idx_min_temp));
//                cursor.getDouble(ForcastFragment.COL_WEATHER_MAX_TEMP),
//                cursor.getDouble(ForcastFragment.COL_WEATHER_MIN_TEMP));
//
////        return Utility.formatDate(cursor.getLong(idx_date)) 
////                " - "  cursor.getString(idx_short_desc) 
//        return Utility.formatDate(cursor.getLong(ForcastFragment.COL_WEATHER_DATE)) 
//                " - "  cursor.getString(ForcastFragment.COL_WEATHER_DESC) 
//                " - "  highAndLow;
//    }

    /*
        * Cache of the children views for a forecast list item.
        *
     */

    public static class ViewHolder {
                public final ImageView iconView;
                public final TextView dateView;
                public final TextView descriptionView;
                public final TextView highTempView;
                public final TextView lowTempView;

                        public ViewHolder(View view) {
                        iconView = (ImageView) view.findViewById(R.id.list_item_icon);
                        dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
                        descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
                        highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
                        lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
                    }
            }

                public ForecastAdapter(Context context, Cursor c, int flags) {
                super(context, c, flags);
            }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(R.layout.list_item_forcast, parent, false);
//
//        return view;

        // Choose the layout type
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        switch (viewType) {
        case VIEW_TYPE_TODAY: {
        layoutId = R.layout.list_item_forecast_today;
        break;
        }
        case VIEW_TYPE_FUTURE_DAY: {
        layoutId = R.layout.list_item_forcast;
        break;
        }
        }
//        return LayoutInflater.from(context).inflate(layoutId, parent, false);
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    /*
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Read weather icon ID from cursor
//                int weatherId = cursor.getInt(ForcastFragment.COL_WEATHER_ID);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
                // Use placeholder image for now
//        ImageView iconView = (ImageView) view.findViewById(R.id.list_item_icon);
//                iconView.setImageResource(R.drawable.ic_launcher);
//        viewHolder.iconView.setImageResource(R.drawable.ic_launcher);
        int viewType = getItemViewType(cursor.getPosition());
        switch (viewType) {
        case VIEW_TYPE_TODAY: {
        // Get weather icon
        viewHolder.iconView.setImageResource(Utility.getArtResourceForWeatherCondition(
        cursor.getInt(ForcastFragment.COL_WEATHER_CONDITION_ID)));
        break;
        }
        case VIEW_TYPE_FUTURE_DAY: {
        // Get weather icon
        viewHolder.iconView.setImageResource(Utility.getIconResourceForWeatherCondition(
        cursor.getInt(ForcastFragment.COL_WEATHER_CONDITION_ID)));
        break;
        }
        }


        // Read date from cursor
        long dateInMillis = cursor.getLong(ForcastFragment.COL_WEATHER_DATE);
                // Find TextView and set formatted date on it
//        TextView dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
//        dateView.setText(Utility.getFriendlyDayString(context, dateInMillis));
        viewHolder.dateView.setText(Utility.getFriendlyDayString(context, dateInMillis));

        // Read weather forecast from cursor
        String description = cursor.getString(ForcastFragment.COL_WEATHER_DESC);
                // Find TextView and set weather forecast on it
//        TextView descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
//        descriptionView.setText(description);
        viewHolder.descriptionView.setText(description);
        // Read user preference for metric or imperial temperature units
        boolean isMetric = Utility.isMetric(context);

        // Read high temperature from cursor
        double high = cursor.getDouble(ForcastFragment.COL_WEATHER_MAX_TEMP);
//                TextView highView = (TextView) view.findViewById(R.id.list_item_high_textview);
//                highView.setText(Utility.formatTemperature(high, isMetric));
        viewHolder.highTempView.setText(Utility.formatTemperature(context, high, isMetric));
        // Read low temperature from cursor
        double low = cursor.getDouble(ForcastFragment.COL_WEATHER_MIN_TEMP);
//                TextView lowView = (TextView) view.findViewById(R.id.list_item_low_textview);
//                lowView.setText(Utility.formatTemperature(low, isMetric));
        viewHolder.lowTempView.setText(Utility.formatTemperature(context, low, isMetric));
//        TextView tv = (TextView)view;
//        tv.setText(convertCursorRowToUXFormat(cursor));
    }

    @Override
    public int getItemViewType(int position) {
    return position == 0 ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
               return VIEW_TYPE_COUNT;
       }
}