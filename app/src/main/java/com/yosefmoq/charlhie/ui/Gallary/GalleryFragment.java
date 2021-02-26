package com.yosefmoq.charlhie.ui.Gallary;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.CartAdapter;
import com.yosefmoq.charlhie.DialogCallBack;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.FragmentGalleryBinding;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.repository.local.LocalSave;
import com.yosefmoq.charlhie.repository.local.MyDatabase;
import com.yosefmoq.charlhie.ui.Auth.AuthActivity;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;
import com.yosefmoq.charlhie.ui.payment.PaymentActivity;
import com.yosefmoq.charlhie.utils.BR;
import com.yosefmoq.charlhie.utils.Utils;

import java.util.ArrayList;

public class GalleryFragment extends BaseFragment<FragmentGalleryBinding, HomeViewModel> implements DialogCallBack {
    CartAdapter cartAdapter;
    ArrayList<Category> categoryArrayList;

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getBindingVariable() {
        return BR._all;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initItems() {
        this.categoryArrayList = new ArrayList<>();
        CartAdapter cartAdapter2 = new CartAdapter(requireContext(), this.categoryArrayList);
        this.cartAdapter = cartAdapter2;
        cartAdapter2.onDialogConfirm(this);
        ((FragmentGalleryBinding) getViewDataBinding()).rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        ((FragmentGalleryBinding) getViewDataBinding()).rvItems.setAdapter(this.cartAdapter);
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
         getViewDataBinding().btnCheckout.setOnClickListener(v -> {
            RigesterRequest rigesterRequest = LocalSave.getInstance(requireContext()).getCurrentUser();
            if (rigesterRequest==null||rigesterRequest.getEmail() == null || rigesterRequest.getEmail().equalsIgnoreCase("")){
                startActivity(new Intent(requireActivity(), AuthActivity.class));
                Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(new Intent(requireActivity(), PaymentActivity.class));
                Toast.makeText(requireContext(), "false", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (new MyDatabase(requireContext()).getProducts().size() > 0) {
            getViewDataBinding().ivNotFound.setVisibility(View.GONE);
            getViewDataBinding().llFound.setVisibility(View.VISIBLE);
            cartAdapter.notifyData(new MyDatabase(requireContext()).getProducts());
            getViewDataBinding().tvAantal.setText(new MyDatabase(requireContext()).getAnnal() + "");
            getViewDataBinding().tvTotal.setText(Utils.displayDoubleValue(new MyDatabase(requireContext()).getPrice()) + " €");
            return;
        }
        getViewDataBinding().ivNotFound.setVisibility(View.VISIBLE);
        getViewDataBinding().llFound.setVisibility(View.GONE);
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override // com.yosefmoq.charlhie.DialogCallBack
    public void onConfirm() {
        this.cartAdapter.notifyData(new MyDatabase(requireContext()).getProducts());
        if (new MyDatabase(requireContext()).getProducts().size() > 0) {
            getViewDataBinding().ivNotFound.setVisibility(View.GONE);
            getViewDataBinding().llFound.setVisibility(View.VISIBLE);
            TextView textView = getViewDataBinding().tvAantal;
            textView.setText(new MyDatabase(requireContext()).getAnnal() + "");
            TextView textView2 = getViewDataBinding().tvTotal;
            textView2.setText(new MyDatabase(requireContext()).getPrice() + "");
            return;
        }
        getViewDataBinding().ivNotFound.setVisibility(View.VISIBLE);
        getViewDataBinding().llFound.setVisibility(View.GONE);
    }
}
