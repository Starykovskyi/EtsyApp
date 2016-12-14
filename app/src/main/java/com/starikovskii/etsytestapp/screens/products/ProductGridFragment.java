package com.starikovskii.etsytestapp.screens.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.model.PaginationModel;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.model.ProductResponseModel;
import com.starikovskii.etsytestapp.view.adapters.ProductRecyclerViewAdapter;
import com.starikovskii.etsytestapp.view.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductGridFragment extends BaseFragment implements IProductGridFragmentView {
    private static final String CATEGORY_NAME = "category_name";
    private static final String KEYWORD = "keyword";

    @Inject
    IProductGridFragmentPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvProductsGrid)
    RecyclerView rvProductsGrid;

    private ProductRecyclerViewAdapter recyclerViewAdapter;
    private View rootView;
    private PaginationModel currentPagination;
    private int productCount = 0;
    private boolean isCanLoading = true;

    public static ProductGridFragment newInstance(String categoryName, String keyword) {
        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, categoryName);
        args.putString(KEYWORD, keyword);
        ProductGridFragment fragment = new ProductGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_product_grid, container, false);
            ButterKnife.bind(this, rootView);
            initToolbar();
            initPresenter();
            initRecyclerView();
            presenter.loadProducts(getArguments().getString(CATEGORY_NAME), getArguments().getString(KEYWORD));
        }
        return rootView;
    }

    private void initToolbar() {
        toolbar.setTitle("Products");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new ProductRecyclerViewAdapter(new ProductRecyclerViewAdapter.ProductItemClickListener() {
            @Override
            public void onClick(ProductModel product) {
                presenter.onItemClick(product);
            }
        });
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        rvProductsGrid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    if (isCanLoading && (currentPagination.getNextOffset() < productCount)) {
                        if ((layoutManager.getItemCount() + layoutManager.findFirstVisibleItemPosition()) >= layoutManager.getItemCount()) {
                            isCanLoading = false;
                            presenter.loadMoreProducts(
                                    getArguments().getString(CATEGORY_NAME),
                                    getArguments().getString(KEYWORD),
                                    currentPagination.getNextOffset());
                        }
                    }
                }
            }
        });
        rvProductsGrid.setLayoutManager(layoutManager);
        rvProductsGrid.setAdapter(recyclerViewAdapter);
    }

    private void initPresenter() {
        this.getComponent(IEtsyPresentersComponent.class).inject(this);
        presenter.init(this);
    }

    @Override
    public void onLoadNewProduct(ProductResponseModel response) {
        isCanLoading = true;
        recyclerViewAdapter.addProductList(response.getResults());
        this.currentPagination = response.getPagination();
        productCount = response.getCount();
    }

    @Override
    public void replaceToDetailFragment(ProductModel product) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.rlMainContainer, new Fragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }
}
