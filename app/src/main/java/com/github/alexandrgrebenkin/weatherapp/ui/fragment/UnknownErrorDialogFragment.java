package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class UnknownErrorDialogFragment extends DialogFragment {

    public static final String ERROR = "com.github.alexandrgrebenkin.weatherapp.ERROR";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String error = getArguments().getString(ERROR);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle(R.string.unknown_error)
                .setMessage(error)
                .setPositiveButton("ОК", (dialog, which) -> {});
        return builder.create();
    }

    public static UnknownErrorDialogFragment newInstance(String exception) {
        UnknownErrorDialogFragment unknownErrorDialogFragment = new UnknownErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString(ERROR, exception);
        unknownErrorDialogFragment.setArguments(args);
        return unknownErrorDialogFragment;
    }
}
