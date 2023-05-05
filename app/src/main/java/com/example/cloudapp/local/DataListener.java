package com.example.cloudapp.local;

public interface DataListener {
    void onDataLoaded(User user);
    void onDataNotFound();
    void onDataLoadFailure(String errorMessage);
    void onDataSaved();
    void onDataSaveFailure(String errorMessage);
}
