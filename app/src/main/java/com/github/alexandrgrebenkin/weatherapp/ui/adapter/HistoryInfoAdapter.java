package com.github.alexandrgrebenkin.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.HistoryInfoViewModel;

import java.util.List;

public class HistoryInfoAdapter extends RecyclerView.Adapter<HistoryInfoAdapter.ViewHolder> {

    private List<HistoryInfoViewModel> historyInfoViewModelList;

    public HistoryInfoAdapter(List<HistoryInfoViewModel> historyInfoViewModelList) {
        this.historyInfoViewModelList = historyInfoViewModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_info_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryInfoAdapter.ViewHolder holder, int position) {
        HistoryInfoViewModel historyInfoViewModel = historyInfoViewModelList.get(position);
        holder.bind(historyInfoViewModel);
        holder.itemView.setTag(historyInfoViewModel);
    }

    @Override
    public int getItemCount() {
        return historyInfoViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView cityName;
        private TextView date;
        private TextView temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(@NonNull View itemView) {
            cardView = itemView.findViewById(R.id.history_info__card_view);
            cityName = itemView.findViewById(R.id.history_info_item__city_name);
            date = itemView.findViewById(R.id.history_info_item__date);
            temperature = itemView.findViewById(R.id.history_info_item__temperature);
        }

        public void bind(HistoryInfoViewModel historyInfoViewModel) {
            cityName.setText(historyInfoViewModel.getCityName());
            date.setText(historyInfoViewModel.getDate());
            temperature.setText(historyInfoViewModel.getTemperature());
        }

    }
}
