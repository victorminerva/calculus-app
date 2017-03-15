package com.minervavi.app.workcalcapp.mvp.app;

/**
 * Created by victo on 13/03/2017.
 */

public class AppModel implements IApp.IAppModel {

    private IApp.IAppPresenter appPresenter;

    public AppModel(IApp.IAppPresenter appPresenter) {
        this.appPresenter = appPresenter;
    }

}
