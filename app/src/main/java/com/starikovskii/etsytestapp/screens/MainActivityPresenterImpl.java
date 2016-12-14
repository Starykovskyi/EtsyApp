package com.starikovskii.etsytestapp.screens;

import javax.inject.Inject;

public class MainActivityPresenterImpl implements IMainActivityPresenter {
    private IMainActivityView view;

    @Inject
    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
    }

    @Override
    public void onBackPressed() {
        view.popFragmentFromStack();
    }
}
