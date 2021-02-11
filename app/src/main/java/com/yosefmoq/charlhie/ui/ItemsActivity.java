package com.yosefmoq.charlhie.ui;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.yosefmoq.charlhie.Base.BaseActivity;
import com.yosefmoq.charlhie.DrawerActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ActivityItemsBinding;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;
import com.yosefmoq.charlhie.utils.BR;

public class ItemsActivity extends BaseActivity<ActivityItemsBinding, HomeViewModel> {
    ItemsAdapter itemsAdapter;

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getLayoutId() {
        return R.layout.activity_items;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getBindingVariable() {
        return BR._all;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initItems() {
        this.itemsAdapter = new ItemsAdapter(this, DrawerActivity.subCategoryHashMap.get(getIntent().getStringExtra("cat")));
        ((ActivityItemsBinding) getViewDataBinding()).rvItems.setAdapter(this.itemsAdapter);
        ((ActivityItemsBinding) getViewDataBinding()).rvItems.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initClicks() {
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }
}
