package com.starikovskii.etsytestapp.view.base;

import android.support.v4.app.Fragment;

import com.starikovskii.etsytestapp.injection.IHasComponent;

/**
 * Created by FkingAlive on 12.12.2016.
 */
public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>)getActivity()).getComponent());
    }
}