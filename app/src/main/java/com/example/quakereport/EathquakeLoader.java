package com.example.quakereport;

import android.content.Context;

import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class EathquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;

    public EathquakeLoader(Context context, String url) {
        super(context);
        this.url =  url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        return QueryUtils.fetchEarthquakeData(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
