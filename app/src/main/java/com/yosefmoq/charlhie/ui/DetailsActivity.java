package com.yosefmoq.charlhie.ui;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.yosefmoq.charlhie.Base.BaseActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ActivityDetailsBinding;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.repository.local.MyDatabase;
import com.yosefmoq.charlhie.ui.home.HomeViewModel;
import com.yosefmoq.charlhie.utils.BR;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, HomeViewModel> {
    Category category;
    Intent intent;

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getBindingVariable() {
        return BR._all;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initItems() {
        Intent intent2 = getIntent();
        this.intent = intent2;
        category = (Category) intent2.getParcelableExtra("category");
        Picasso.get().load(category.getImage()).placeholder(R.drawable.account).into(((ActivityDetailsBinding) getViewDataBinding()).ivProductImage);
        TextView textView = ((ActivityDetailsBinding) getViewDataBinding()).tvPrice;
        textView.setText(category.getPrice() + "  €");
        if (new MyDatabase(DetailsActivity.this).isFav(category.getId())) {
            getViewDataBinding().ivAddToFav.setImageResource(R.drawable.ic_baseline_favorite_selected_24);
        } else {
            getViewDataBinding().ivAddToFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        ((ActivityDetailsBinding) getViewDataBinding()).tvCategory.setText(category.getCategory());
        ((ActivityDetailsBinding) getViewDataBinding()).tvSubCategory.setText(category.getSubCategory());
        ((ActivityDetailsBinding) getViewDataBinding()).tvDescription.setText(category.getDescription());
        ((ActivityDetailsBinding) getViewDataBinding()).tvLongDes.setText(category.getLongDescription());
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initClicks() {
        getViewDataBinding().ivAddToCart.setOnClickListener((View.OnClickListener) view -> initDialog());
        getViewDataBinding().ivCancel.setOnClickListener((View.OnClickListener) view -> finish());
        getViewDataBinding().ivAddToFav.setOnClickListener((View.OnClickListener) view -> {
            if (new MyDatabase(DetailsActivity.this).isFav(category.getId())) {
                new MyDatabase(DetailsActivity.this).deleteFavById(category.getId());
                Toast.makeText(this, "Removed From Favorite", Toast.LENGTH_SHORT).show();
                getViewDataBinding().ivAddToFav.setImageResource(R.drawable.ic_baseline_favorite_24);

            } else {
                new MyDatabase(DetailsActivity.this).addProduct(category.getSubCategory(), category.getDescription(), category.getCategory(), 0, category.getImage(), 0, category.getPrice(), category.getLongDescription(), category.getId(), true, category.getDescription());
                Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show();
                getViewDataBinding().ivAddToFav.setImageResource(R.drawable.ic_baseline_favorite_selected_24);

            }
        });
    }


    private void initDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.item_aantal, (ViewGroup) null);
        dialog.setContentView(view);
        EditText etAantal = view.findViewById(R.id.etAantal);

        view.findViewById(R.id.btnOk).setOnClickListener((View.OnClickListener) v -> {
            if (etAantal.getText().toString().isEmpty() || etAantal.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(DetailsActivity.this, "Geef een aantal op", Toast.LENGTH_SHORT).show();
                return;
            }
            new MyDatabase(DetailsActivity.this).addProduct(category.getSubCategory(), category.getDescription(), category.getCategory(), category.getPrice() * ((double) Integer.parseInt(etAantal.getText().toString())), category.getImage(), Integer.parseInt(etAantal.getText().toString()), category.getPrice(), category.getLongDescription(), category.getId(), false, category.getDescription());
            dialog.dismiss();
            finish();

        });
        view.findViewById(R.id.btnCancel).setOnClickListener((View.OnClickListener) view1 -> dialog.dismiss());
        dialog.show();
    }


    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public HomeViewModel getViewModel() {
        return (HomeViewModel) ViewModelProviders.of(this).get(HomeViewModel.class);
    }
}
