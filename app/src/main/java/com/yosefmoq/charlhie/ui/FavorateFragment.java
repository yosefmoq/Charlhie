package com.yosefmoq.charlhie.ui;

import androidx.lifecycle.ViewModelProviders;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.FavorateFragmentBinding;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;

public class FavorateFragment extends BaseFragment<FavorateFragmentBinding, HomeViewModel> {
    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getBindingVariable() {
        return 1;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getLayoutId() {
        return R.layout.favorate_fragment;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initItems() {
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }
}
