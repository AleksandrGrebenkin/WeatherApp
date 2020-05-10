package com.github.alexandrgrebenkin.weatherapp.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;

import java.util.List;

public class DayInfoAdapter extends RecyclerView.Adapter<DayInfoAdapter.ViewHolder> {

    private List<WeatherInfo> weatherInfoList;

    public DayInfoAdapter(List<WeatherInfo> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    @NonNull
    @Override
    public DayInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_info_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayInfoAdapter.ViewHolder holder, int position) {
        WeatherInfo weatherInfo = weatherInfoList.get(position);
        holder.bind(weatherInfo);
        holder.itemView.setTag(weatherInfo);
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeek;
        private TextView dayTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.day_info_item__tv_day_of_week);
            dayTemperature = itemView.findViewById(R.id.day_info_item__tv_day_temperature);
        }

        private void bind(WeatherInfo weatherInfo) {
            dayOfWeek.setText(weatherInfo.getDayOfWeek());
            dayTemperature.setText(weatherInfo.getTemperature());
        }
    }
}
