package com.xeon.amar.sunshine.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by amar on 3/4/15.
 */
public class SunshineService extends IntentService {
    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public SunshineService() {
        super("sunshine");
    }
}
