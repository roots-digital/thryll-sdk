package tech.rootsdigital.thryll_sample_integration;

import android.app.Application;

import tech.rootsdigital.thryll_integration.Thryll;

public class IntegrationSampleApplication  extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Thryll.Initialize(this);
    }
}
