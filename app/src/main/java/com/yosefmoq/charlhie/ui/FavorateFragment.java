package com.yosefmoq.charlhie.ui;

import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.CartAdapter;
import com.yosefmoq.charlhie.DialogCallBack;
import com.yosefmoq.charlhie.DrawerActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.FavorateFragmentBinding;
import com.yosefmoq.charlhie.repository.local.MyDatabase;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;
import com.yosefmoq.charlhie.utils.BR;

public class FavorateFragment extends BaseFragment<FavorateFragmentBinding, HomeViewModel> implements DialogCallBack {
    CartAdapter cartAdapter;

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getBindingVariable() {
        return BR._all;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getLayoutId() {
        return R.layout.favorate_fragment;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initItems() {
        refresh();
        cartAdapter = new CartAdapter(requireContext(), new MyDatabase(requireContext()).getFavorite(), false);
        cartAdapter.onDialogConfirm(this);
        getViewDataBinding().rvFavorites.setAdapter(cartAdapter);
        getViewDataBinding().rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onConfirm() {
        refresh();
        ((DrawerActivity)requireActivity()).refresh();
    }

    public void refresh() {
        if (new MyDatabase(requireContext()).getFavorite().size() > 0) {
            getViewDataBinding().ivBg.setVisibility(View.GONE);
            getViewDataBinding().textGallery.setVisibility(View.GONE);
            getViewDataBinding().rvFavorites.setVisibility(View.VISIBLE);
        } else {
            getViewDataBinding().ivBg.setVisibility(View.VISIBLE);
            getViewDataBinding().textGallery.setVisibility(View.VISIBLE);
            getViewDataBinding().rvFavorites.setVisibility(View.GONE);
        }

    }
}
