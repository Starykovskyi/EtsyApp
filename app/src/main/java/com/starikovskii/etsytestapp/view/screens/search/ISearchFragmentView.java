package com.starikovskii.etsytestapp.view.screens.search;

import com.starikovskii.etsytestapp.model.CategoriesModel;

import java.util.List;

/**
 * Created by FkingAlive on 12.12.2016.
 */

public interface ISearchFragmentView {
    void setupCategoriesListAdapter(List<CategoriesModel> categories);
    void replaceToListFragment(String categoryName, String keyword);
    void showProgressIndicator();
    void hideProgressIndicator();
}
