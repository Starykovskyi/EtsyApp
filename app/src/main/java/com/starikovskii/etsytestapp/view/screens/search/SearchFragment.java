package com.starikovskii.etsytestapp.view.screens.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.model.CategoriesModel;
import com.starikovskii.etsytestapp.view.base.BaseFragment;
import com.starikovskii.etsytestapp.view.screens.products.ProductGridFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by FkingAlive on 13.12.2016.
 */

public class SearchFragment extends BaseFragment implements ISearchFragmentView {
//
    @Inject
    ISearchFragmentPresenter presenter;

    @BindView(R.id.etKeyword)
    EditText etKeyword;
    @BindView(R.id.spinnerCategories)
    MaterialSpinner spinnerCategories;

    private View rootView;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private SweetAlertDialog sad;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search, container, false);
            ButterKnife.bind(this, rootView);
            initPresenter();
            etKeyword.setEnabled(true);
            presenter.loadCategories();
        }
        return rootView;
    }

    private void initPresenter() {
        this.getComponent(IEtsyPresentersComponent.class).inject(this);
        presenter.init(this);
    }

    @Override
    public void setupCategoriesListAdapter(List<CategoriesModel> categories) {
        ArrayAdapter<CategoriesModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);
    }

    @Override
    public void replaceToListFragment(String categoryName, String keyword) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ProductGridFragment productGridFragment = ProductGridFragment.newInstance(categoryName, keyword);
        fragmentManager.beginTransaction()
                .replace(R.id.rlMainContainer, productGridFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showProgressIndicator() {
        if(sad == null) {
            sad = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(getString(R.string.search_fragment_loading));
            sad.getProgressHelper().setBarColor(R.color.colorPink);
        }
        sad.show();
    }

    @Override
    public void hideProgressIndicator() {
        sad.dismissWithAnimation();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitClick() {
        try {
            presenter.onSubmitClick((CategoriesModel) spinnerCategories.getSelectedItem(),etKeyword.getText().toString());
        } catch (ClassCastException ex){
            spinnerCategories.setError(getString(R.string.search_fragment_no_cathegory_selected));
        }
    }



    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }
}
