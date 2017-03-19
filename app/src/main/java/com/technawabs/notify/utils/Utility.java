package com.technawabs.notify.utils;

import android.support.annotation.NonNull;
import android.view.View;

public class Utility {

    public static void hideProgressBar(@NonNull View progressBar) {
            progressBar.setVisibility(View.INVISIBLE);
    }

    public static void showProgressBar(@NonNull View progressBar) {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }

    }

}
