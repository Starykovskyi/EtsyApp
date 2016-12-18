package com.starikovskii.etsytestapp.view.screens.savedproduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.utils.listeners.SavedProductItemListener;
import com.starikovskii.etsytestapp.view.adapters.SavedProductRecyclerViewAdapter;
import com.starikovskii.etsytestapp.view.base.BaseFragment;
import com.starikovskii.etsytestapp.view.screens.details.DetailsFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by starikovskii.valentine on 12/16/16.
 */

public class SavedProductFragment extends BaseFragment implements ISavedProductFragmentView {

    @BindView(R.id.rvProductsGrid)
    RecyclerView rvProductsGrid;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.dpbProgress)
    DotProgressBar dpbProgress;
    private View rootView;
    private boolean isCanLoading = true;
    private Long productCount = 0L;
    @Inject
    ISavedProductFragmentPresenter presenter;

    private SavedProductRecyclerViewAdapter recyclerViewAdapter;

    public static SavedProductFragment newInstance() {
        Bundle args = new Bundle();
        SavedProductFragment fragment = new SavedProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_saved_products, container, false);
            ButterKnife.bind(this, rootView);
            initPresenter();
            initRecyclerView();
            initRefresher();
            presenter.getProductCount();
            presenter.loadProducts();
        }
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        hideProgressIndicator();
    }



    private void initRefresher() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                recyclerViewAdapter.clear();
                presenter.loadProducts();
            }
        });

    }


    private void initRecyclerView() {
        recyclerViewAdapter = new SavedProductRecyclerViewAdapter(new SavedProductItemListener() {

            @Override
            public void deleteProduct(ProductModel product, int position) {
                presenter.deleteProduct(product, position);
            }

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
                    if (isCanLoading && (recyclerViewAdapter.getItemCount() < productCount)) {
                        if ((layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition()) >= layoutManager.getItemCount()) {
                            isCanLoading = false;
                            presenter.loadMoreProducts(recyclerViewAdapter.getItemCount());
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
    public void onLoadNewProducts(List<ProductModel> products) {
        isCanLoading = true;
        recyclerViewAdapter.addProductList(products);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void replaceToDetailFragment(ProductModel product) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.rlMainContainer, DetailsFragment.newInstance(product, true))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showProgressIndicator() {
        dpbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        dpbProgress.setVisibility(View.GONE);
    }

    @Override
    public void onDeleteProduct(int position) {
        recyclerViewAdapter.deleteProduct(position);
    }

    @Override
    public void onProductCountReceived(Long response) {
        this.productCount = response;
    }


}
