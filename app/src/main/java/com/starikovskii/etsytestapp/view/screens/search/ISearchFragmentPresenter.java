package com.starikovskii.etsytestapp.view.screens.search;

import com.starikovskii.etsytestapp.model.CategoriesModel;
import com.starikovskii.etsytestapp.view.base.IBaseFragmentPresenter;

public interface ISearchFragmentPresenter extends IBaseFragmentPresenter<ISearchFragmentView> {
    void loadCategories();
    void onSubmitClick(CategoriesModel category, String keyword);
}
