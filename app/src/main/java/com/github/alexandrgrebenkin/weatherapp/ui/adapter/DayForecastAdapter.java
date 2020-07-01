package com.github.alexandrgrebenkin.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.DayWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;

import java.util.Locale;

public class DayForecastAdapter extends RecyclerView.Adapter<DayForecastAdapter.ViewHolder> {

    private ForecastWeatherViewModel forecastWeather;

    public DayForecastAdapter(ForecastWeatherViewModel forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    @NonNull
    @Override
    public DayForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_info_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayForecastAdapter.ViewHolder holder, int position) {
        DayWeatherViewModel dayWeather = forecastWeather.getDayWeatherViewModelList().get(position);
        holder.bind(dayWeather);
        holder.itemView.setTag(dayWeather);
    }

    @Override
    public int getItemCount() {
        return forecastWeather.getDayWeatherViewModelList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeek;
        private TextView date;
        private TextView maxTemperature;
        private TextView minTemperature;
        private Locale locale;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.day_info_item__tv_day_of_week);
            date = itemView.findViewById(R.id.day_info_item__tv_date);
            maxTemperature = itemView.findViewById(R.id.day_info_item__tv_max_temperature);
            minTemperature = itemView.findViewById(R.id.day_info_item__tv_min_temperature);
            locale = itemView.getResources().getConfiguration().getLocales().get(0);
        }

        private void bind(DayWeatherViewModel dayWeather) {
            /*
            LocalDate localDate = LocalDate.parse(dayWeather.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String dayOfWeekStr = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
            this.dayOfWeek.setText(dayOfWeekStr);
            String dateStr = localDate.format(DateTimeFormatter.ofPattern("dd MMMM", locale));
            this.date.setText(dateStr);
            String maxTempC = Utils.getRoundValueFromFloat(dayWeather.getTempMaxC());
            if (dayWeather.getTempMaxC() > 0) {
                maxTempC = "+" + maxTempC;
            }
            this.maxTemperature.setText(maxTempC);
            String minTempC = Utils.getRoundValueFromFloat(dayWeather.getTempMinC());
            if (dayWeather.getTempMinC() > 0) {
                minTempC = "+" + minTempC;
            }
            this.minTemperature.setText(minTempC);
            */
            date.setText(dayWeather.getDate());
            dayOfWeek.setText(dayWeather.getDayOfWeek());
            maxTemperature.setText(dayWeather.getMaxTemperature());
            minTemperature.setText(dayWeather.getMinTemperature());
        }
    }


}
