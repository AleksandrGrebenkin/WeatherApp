package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.HistoryInfoAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.HistoryInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    public static final String HISTORY_INFO = "com.github.alexandrgrebenkin.weatherapp.HISTORY_INFO";

    private RecyclerView historyInfoRecyclerView;

    private List<HistoryInfoViewModel> historyInfoViewModelList;

    public static HistoryFragment newInstance(ArrayList<HistoryInfoViewModel> historyInfoViewModelList) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(HISTORY_INFO, historyInfoViewModelList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historyInfoViewModelList = getArguments().getParcelableArrayList(HISTORY_INFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        updateHistory();
    }

    private void init() {
        View view = getView();
        if (view != null) {
            historyInfoRecyclerView = view.findViewById(R.id.fragment_history__history_info);
        }
    }

    private void updateHistory() {
        if (historyInfoViewModelList != null) {
            HistoryInfoAdapter historyInfoAdapter = new HistoryInfoAdapter(historyInfoViewModelList);
            historyInfoRecyclerView.setAdapter(historyInfoAdapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);

            historyInfoRecyclerView.setLayoutManager(layoutManager);
        } else {
            Toast.makeText(getContext(), "History not found", Toast.LENGTH_SHORT).show();
        }
    }
}