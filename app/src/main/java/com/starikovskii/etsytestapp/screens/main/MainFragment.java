package com.starikovskii.etsytestapp.screens.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.view.adapters.ViewPagerAdapter;
import com.starikovskii.etsytestapp.view.base.BaseFragment;
import com.starikovskii.etsytestapp.screens.search.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FkingAlive on 13.12.2016.
 */

public class MainFragment extends BaseFragment implements IMainFragmentView {

    private View rootView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tlMainTabs)
    TabLayout tlMainTabsLayout;
    @BindView(R.id.vpMainViewPager)
    ViewPager vpMainViewPager;

    @Inject
    MainFragmentPresenterImpl presenter;

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IEtsyPresentersComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        setupViewPager(vpMainViewPager);
        toolbar.setTitle("EtsyApp");
        tlMainTabsLayout.setupWithViewPager(vpMainViewPager);
        return rootView;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(SearchFragment.newInstance(), "ONE");
        adapter.addFragment(new Fragment(), "TWO");
        viewPager.setAdapter(adapter);
    }
}
