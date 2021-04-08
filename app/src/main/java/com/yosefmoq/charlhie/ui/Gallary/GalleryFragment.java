package com.yosefmoq.charlhie.ui.Gallary;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.CartAdapter;
import com.yosefmoq.charlhie.DialogCallBack;
import com.yosefmoq.charlhie.DrawerActivity;
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
        CartAdapter cartAdapter2 = new CartAdapter(requireContext(), this.categoryArrayList,true);
        this.cartAdapter = cartAdapter2;
        cartAdapter2.onDialogConfirm(this);
        ((FragmentGalleryBinding) getViewDataBinding()).rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        ((FragmentGalleryBinding) getViewDataBinding()).rvItems.setAdapter(this.cartAdapter);
        getViewModel().getCategories();
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
         getViewDataBinding().btnCheckout.setOnClickListener(v -> {
            RigesterRequest rigesterRequest = LocalSave.getInstance(requireContext()).getCurrentUser();
            if (rigesterRequest==null||rigesterRequest.getEmail() == null || rigesterRequest.getEmail().equalsIgnoreCase("")){
                startActivity(new Intent(requireActivity(), AuthActivity.class));
            }else {
                startActivityForResult(new Intent(requireActivity(), PaymentActivity.class),301);
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

            if(new MyDatabase(requireContext()).getPrice()<50){
                getViewDataBinding().tvExtra.setVisibility(View.VISIBLE);
                getViewDataBinding().tvUnderExtra.setVisibility(View.VISIBLE);
                getViewDataBinding().tvUnderExtra.setText(getReminingText(new MyDatabase(requireContext()).getPrice()));
                getViewDataBinding().tvTotal.setText(Utils.displayDoubleValue(new MyDatabase(requireContext()).getPrice()+10) + " €");

            }else {
                getViewDataBinding().tvExtra.setVisibility(View.INVISIBLE);
//                getViewDataBinding().tvUnderExtra.setVisibility(View.GONE);
                getViewDataBinding().tvUnderExtra.setText("FREE SHIPPING");
                getViewDataBinding().tvTotal.setText(Utils.displayDoubleValue(new MyDatabase(requireContext()).getPrice()) + " €");
            }

            return;
        }
//        getViewDataBinding().rvItems.setVisibility(View.GONE);
        getViewDataBinding().ivNotFound.setVisibility(View.VISIBLE);
        getViewDataBinding().llFound.setVisibility(View.GONE);
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override // com.yosefmoq.charlhie.DialogCallBack
    public void onConfirm() {
        ((DrawerActivity)requireActivity()).refresh();
        this.cartAdapter.notifyData(new MyDatabase(requireContext()).getProducts());
        if (new MyDatabase(requireContext()).getProducts().size() > 0) {
            getViewDataBinding().ivNotFound.setVisibility(View.GONE);
            getViewDataBinding().llFound.setVisibility(View.VISIBLE);
            if(new MyDatabase(requireContext()).getPrice()<50){
                getViewDataBinding().tvExtra.setVisibility(View.VISIBLE);
//                getViewDataBinding().tvUnderExtra.setVisibility(View.VISIBLE);
                getViewDataBinding().tvUnderExtra.setText(getReminingText(new MyDatabase(requireContext()).getPrice()));
            }else {
                getViewDataBinding().tvExtra.setVisibility(View.INVISIBLE);
//                getViewDataBinding().tvUnderExtra.setVisibility(View.GONE);
                getViewDataBinding().tvUnderExtra.setText("FREE SHIPPING");

            }
            TextView textView = getViewDataBinding().tvAantal;
            textView.setText(new MyDatabase(requireContext()).getAnnal() + "");
            TextView textView2 = getViewDataBinding().tvTotal;
            textView2.setText(new MyDatabase(requireContext()).getPrice() + "");
            return;
        }
        getViewDataBinding().ivNotFound.setVisibility(View.VISIBLE);
        getViewDataBinding().llFound.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==301){
            if(data.getStringExtra("done").equalsIgnoreCase("done")){
//                ((DrawerActivity)requireActivity()).showSuccess("Your betaling has been received, Thank you!");
            }
        }
    }
    private String getReminingText(double amount){
        double newAmount = 50 -(amount);
        return "Nog "+Utils.displayDoubleValue(newAmount)+" € to gaan";
    }
}
