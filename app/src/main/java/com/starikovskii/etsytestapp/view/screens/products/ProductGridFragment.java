package com.starikovskii.etsytestapp.view.screens.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.model.PaginationModel;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.network.response.ProductResponseModel;
import com.starikovskii.etsytestapp.utils.listeners.ProductItemClickListener;
import com.starikovskii.etsytestapp.view.adapters.ProductRecyclerViewAdapter;
import com.starikovskii.etsytestapp.view.base.BaseFragment;
import com.starikovskii.etsytestapp.view.screens.details.DetailsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductGridFragment extends BaseFragment implements IProductGridFragmentView {
    private static final String CATEGORY_NAME = "category_name";
    private static final String KEYWORD = "keyword";
    private static final String TAG = ProductGridFragment.class.getSimpleName();

    @Inject
    IProductGridFragmentPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvProductsGrid)
    RecyclerView rvProductsGrid;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.dpbProgress)
    DotProgressBar dpbProgress;

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
            initRefresher();
            initRecyclerView();
            presenter.loadProducts(getArguments().getString(CATEGORY_NAME), getArguments().getString(KEYWORD));
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initRefresher() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                recyclerViewAdapter.clear();
                presenter.loadProducts(getArguments().getString(CATEGORY_NAME), getArguments().getString(KEYWORD));
            }
        });

    }

    private void initToolbar() {
        toolbar.setTitle("Products");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new ProductRecyclerViewAdapter(new ProductItemClickListener() {
            @Override
            public void toDetailsFragment(ProductModel product) {
                presenter.onProductClick(product);
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
    public void onLoadNewProducts(ProductResponseModel response) {
        isCanLoading = true;
        recyclerViewAdapter.addProductList(response.getResults());
        swipeRefreshLayout.setRefreshing(false);
        this.currentPagination = response.getPagination();
        productCount = response.getCount();
    }

    @Override
    public void replaceToDetailFragment(ProductModel product) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.rlMainContainer, DetailsFragment.newInstance(product, false))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showProgressIndicator() {
        dpbProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressIndicator(){
        dpbProgress.setVisibility(View.GONE);
    }

    @Override
    public void onEmptyResult() {
        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.product_fragment_empty_error))
                .setConfirmText(getString(R.string.product_fragment_confirm))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                }). show();
    }
}
