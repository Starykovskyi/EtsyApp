package com.starikovskii.etsytestapp.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.IHasComponent;
import com.starikovskii.etsytestapp.injection.component.DaggerIEtsyPresentersComponent;
import com.starikovskii.etsytestapp.injection.component.IEtsyMainComponent;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.injection.module.MainActivityModule;
import com.starikovskii.etsytestapp.view.base.BaseActivity;
import com.starikovskii.etsytestapp.screens.main.MainFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainActivityView, IHasComponent<IEtsyPresentersComponent> {

    @Inject
    MainActivityPresenterImpl presenter;

    private IEtsyPresentersComponent etsyComponent;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.rlMainContainer, MainFragment.newInstance()).commit();
    }

    @Override
    protected void setupComponent(IEtsyMainComponent appComponent) {
        etsyComponent = DaggerIEtsyPresentersComponent.builder()
                .iEtsyMainComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        etsyComponent.inject(this);
    }

    @Override
    public IEtsyPresentersComponent getComponent() {
        return etsyComponent;
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();
    }
}
