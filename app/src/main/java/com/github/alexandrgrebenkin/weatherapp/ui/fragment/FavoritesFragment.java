package com.github.alexandrgrebenkin.weatherapp.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.PlaceAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoritesFragment extends Fragment {

    public static final String FAVORITE_SET = "com.github.alexandrgrebenkin.weatherapp.FAVORITE_SET";

    private RecyclerView favorites;

    private List<PlaceViewModel> placeViewModelList;

    private Listener listener;

    public interface Listener {
        void favoriteClick(PlaceViewModel placeViewModel);
    }

    public static FavoritesFragment newInstance(Set<PlaceViewModel> placeViewModelSet) {
        ArrayList<PlaceViewModel> placeViewModelList = new ArrayList<>(placeViewModelSet);
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(FAVORITE_SET, placeViewModelList);
        favoritesFragment.setArguments(args);
        return favoritesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeViewModelList = getArguments().getParcelableArrayList(FAVORITE_SET);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        initFavorites();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    private void init() {
        View view = getView();
        if (view != null) {
            favorites = view.findViewById(R.id.fragment_favorites__rv_favorite);
        }
    }

    private void initFavorites() {
        PlaceAdapter placeAdapter = new PlaceAdapter(placeViewModelList);
        favorites.setAdapter(placeAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favorites.setLayoutManager(layoutManager);

        placeAdapter.setOnItemClickListener((view, position) -> {
            listener.favoriteClick(placeViewModelList.get(position));
        });
    }
}