package com.starikovskii.etsytestapp.view.screens.details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.starikovskii.etsytestapp.R;
import com.starikovskii.etsytestapp.injection.component.IEtsyPresentersComponent;
import com.starikovskii.etsytestapp.model.ProductModel;
import com.starikovskii.etsytestapp.view.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by starikovskii.valentine on 12/16/16.
 */

public class DetailsFragment extends BaseFragment implements IProductDetailFragmentView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivProductImage)
    ImageView ivProductImage;
    @BindView(R.id.ibtnSaveProduct)
    ImageButton ibtnSaveProduct;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindDrawable(R.drawable.ic_done)
    Drawable icDone;
    private View rootView;

    @Inject
    IProductDetailFragmentPresenter presenter;
    private ProductModel selectedProduct;
    private static final String PRODUCT_KEY = "product";

    private static final String IS_SAVED_KEY = "saved";

    public static DetailsFragment newInstance(ProductModel product, boolean isSaved) {

        Bundle args = new Bundle();
        args.putSerializable(PRODUCT_KEY, product);
        args.putBoolean(IS_SAVED_KEY, isSaved);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
            ButterKnife.bind(this, rootView);
            selectedProduct = (ProductModel) getArguments().getSerializable(PRODUCT_KEY);
            initToolbar();
            initPresenter();
            initUI();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initToolbar() {
        toolbar.setTitle("Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initUI() {
        presenter.setImageToView(selectedProduct.getMainImage().getUrlFullxfull(), ivProductImage);
        tvProductName.setText(selectedProduct.getTitle());
        tvDescription.setText(selectedProduct.getDescription());
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
        tvPrice.setText(String.format("%s %s", selectedProduct.getPrice(), selectedProduct.getCurrencyCode()));
        if(getArguments().getBoolean(IS_SAVED_KEY)){
            ibtnSaveProduct.setEnabled(false);
            ibtnSaveProduct.setImageDrawable(icDone);
        }

    }

    private void initPresenter() {
        this.getComponent(IEtsyPresentersComponent.class).inject(this);
        presenter.init(this);
    }

    @OnClick(R.id.ibtnSaveProduct)
    public void onClick() {
        presenter.saveProduct(selectedProduct);
    }

    @Override
    public void onProductSavedSuccess() {

        SweetAlertDialog sad =  new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText(getString(R.string.details_fragment_product_added))
                .setConfirmText(getString(R.string.details_fragment_confirm))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });

        sad.getProgressHelper().setBarColor(R.color.colorPink);
        sad.show();
        ibtnSaveProduct.setEnabled(false);
        ibtnSaveProduct.setAlpha(0.2f);
    }

    @Override
    public void onProductSavedError() {
        new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText(getString(R.string.details_fragment_error_adding))
                .setConfirmText(getString(R.string.details_fragment_confirm))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
        //TODO show error message
    }
}
