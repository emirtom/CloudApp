package com.example.cloudapp.local;

import android.util.Log;

public class ConsoleDataListener implements DataListener {
    @Override
    public void onDataLoaded(User user) {
        Log.d("Local", "Data loaded: " + user.toString());
    }

    @Override
    public void onDataNotFound() {
        Log.d("Local", "Data not found.");
    }

    @Override
    public void onDataLoadFailure(String errorMessage) {
        Log.d("Local", "Data load failed. Error: " + errorMessage);
    }

    @Override
    public void onDataSaved() {
        Log.d("Local", "Data saved successfully.");
    }

    @Override
    public void onDataSaveFailure(String errorMessage) {
        Log.d("Local", "Data save failed. Error: " + errorMessage);
    }
}
