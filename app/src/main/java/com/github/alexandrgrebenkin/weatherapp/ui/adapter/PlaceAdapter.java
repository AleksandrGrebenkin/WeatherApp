package com.github.alexandrgrebenkin.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<PlaceViewModel> placeViewModelList;
    private OnItemClickListener itemClickListener;

    public PlaceAdapter(List<PlaceViewModel> placeViewModelList) {
        this.placeViewModelList = placeViewModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder holder, int position) {
        PlaceViewModel placeViewModel = placeViewModelList.get(position);
        holder.bind(placeViewModel);
        holder.itemView.setTag(placeViewModel);
    }

    @Override
    public int getItemCount() {
        return placeViewModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city_item__tv_city);
            startOnClickListener();
        }

        private void bind(PlaceViewModel placeViewModel) {
            city.setText(placeViewModel.getDisplayName());
        }

        private void startOnClickListener() {
            city.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }

    }
}
