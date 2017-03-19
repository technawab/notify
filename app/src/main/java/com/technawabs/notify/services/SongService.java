package com.technawabs.notify.services;

import android.util.Log;

import com.google.common.util.concurrent.ListenableFuture;
import com.technawabs.notify.concurrency.ExecutorUtils;
import com.technawabs.notify.constants.AppAPI;
import com.technawabs.notify.constants.AppConstant;
import com.technawabs.notify.network.RequestGenerator;
import com.technawabs.notify.network.RequestHandler;
import com.technawabs.notify.utils.StringUtils;

import org.json.JSONArray;

import java.util.concurrent.Callable;

import okhttp3.Request;

public class SongService {

    private final String TAG = this.getClass().getSimpleName();

    public ListenableFuture<JSONArray> getSongs() {
        return ExecutorUtils.getBackgroundPool().submit(new Callable<JSONArray>() {
            @Override
            public JSONArray call() throws Exception {
                Request request = RequestGenerator.get(AppAPI.SONGS_URL);
                Log.d(TAG, request.toString());
                String result = RequestHandler.makeRequestAndValidate(request);
                Log.d(TAG, result);
                JSONArray score = new JSONArray(result);
                try {
                    Log.d(TAG, score.toString());
                } catch (Exception exception) {
                    if ((exception != null) && (!StringUtils.isNull(exception.getMessage()))) {
                        Log.d(TAG, exception.getMessage());
                    } else {
                        Log.d(TAG, AppConstant.EXCEPTION.EXCEPTION);
                    }
                } finally {
                    return score;
                }
            }
        });
    }
}
