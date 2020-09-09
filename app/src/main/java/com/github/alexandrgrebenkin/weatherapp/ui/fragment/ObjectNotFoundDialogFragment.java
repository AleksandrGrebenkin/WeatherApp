package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ObjectNotFoundDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle(R.string.not_found_error_title)
                .setMessage(R.string.not_found_error)
                .setPositiveButton("OK", (dialog, which) -> {});
        return builder.create();
    }
}
