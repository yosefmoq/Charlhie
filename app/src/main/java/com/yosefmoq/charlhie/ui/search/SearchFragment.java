package com.yosefmoq.charlhie.ui.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.DrawerActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.SearchFragmentBinding;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;

public class SearchFragment extends BaseFragment<SearchFragmentBinding, HomeViewModel> {
    SearchAdapter searchAdapter;

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getBindingVariable() {
        return 1;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getLayoutId() {
        return R.layout.search_fragment;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initItems() {
        this.searchAdapter = new SearchAdapter(requireContext(), DrawerActivity.subCategories);
        ((SearchFragmentBinding) getViewDataBinding()).rvSearch.setAdapter(this.searchAdapter);
        ((SearchFragmentBinding) getViewDataBinding()).rvSearch.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
        ((SearchFragmentBinding) getViewDataBinding()).etSearch.addTextChangedListener(new TextWatcher() {
            /* class com.yosefmoq.charlhie.ui.search.SearchFragment.AnonymousClass1 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ((SearchFragmentBinding) SearchFragment.this.getViewDataBinding()).rvSearch.setVisibility(View.VISIBLE);
                    SearchFragment.this.searchAdapter.getFilter().filter(charSequence);
                    return;
                }
                ((SearchFragmentBinding) SearchFragment.this.getViewDataBinding()).rvSearch.setVisibility(View.GONE);
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }
}
