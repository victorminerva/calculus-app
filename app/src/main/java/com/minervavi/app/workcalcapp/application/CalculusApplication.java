package com.minervavi.app.workcalcapp.application;

import android.app.Application;

import com.minervavi.app.workcalcapp.util.IabHelper;

/**
 * Created by victo on 02/04/2017.
 */

public class CalculusApplication extends Application {

    private IabHelper mHelper;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public IabHelper getmHelper() {
        return mHelper;
    }

    public void setmHelper(IabHelper mHelper) {
        this.mHelper = mHelper;
    }
}
