package com.github.alexandrgrebenkin.weatherapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.DayWeatherInfo;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class WeekInfo extends RecyclerView.Adapter<WeekInfo.ViewHolder> {

    private List<DayWeatherInfo> weekForecastList;

    public WeekInfo(List<DayWeatherInfo> weekForecastList) {
        this.weekForecastList = weekForecastList;
    }

    @NonNull
    @Override
    public WeekInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_info_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekInfo.ViewHolder holder, int position) {
        DayWeatherInfo dayWeatherInfo = weekForecastList.get(position);
        holder.bind(dayWeatherInfo);
        holder.itemView.setTag(dayWeatherInfo);
    }

    @Override
    public int getItemCount() {
        return weekForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeek;
        private TextView date;
        private TextView maxTemperature;
        private TextView minTemperature;
        private Locale locale;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.day_info_item__tv_day_of_week);
            date = itemView.findViewById(R.id.day_info_item__tv_date);
            maxTemperature = itemView.findViewById(R.id.day_info_item__tv_max_temperature);
            minTemperature = itemView.findViewById(R.id.day_info_item__tv_min_temperature);
            locale = itemView.getResources().getConfiguration().getLocales().get(0);
        }

        private void bind(DayWeatherInfo dayWeatherInfo) {
            dayOfWeek.setText(dayWeatherInfo.getDate().getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, locale));
            date.setText(dayWeatherInfo.getDate()
                    .format(DateTimeFormatter.ofPattern("dd MMMM", locale)));
            maxTemperature.setText(dayWeatherInfo.getTempMaxC());
            minTemperature.setText(dayWeatherInfo.getTempMinC());
        }
    }
}
