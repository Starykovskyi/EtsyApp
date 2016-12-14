package com.starikovskii.etsytestapp.screens.search;

import com.starikovskii.etsytestapp.model.CategoriesModel;
import com.starikovskii.etsytestapp.utils.IBaseFragmentPresenter;

public interface ISearchFragmentPresenter extends IBaseFragmentPresenter<ISearchFragmentView> {
    void loadCategories();
    void onSubmitClick(CategoriesModel category, String keyword);
}
