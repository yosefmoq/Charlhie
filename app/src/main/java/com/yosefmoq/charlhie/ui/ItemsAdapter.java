package com.yosefmoq.charlhie.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ItemItemsBinding;
import com.yosefmoq.charlhie.models.Category;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    ArrayList<Category> categoryArrayList;
    Context context;

    public ItemsAdapter(Context context2, ArrayList<Category> categoryArrayList2) {
        this.context = context2;
        this.categoryArrayList = categoryArrayList2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder((ItemItemsBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.item_items, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category category = this.categoryArrayList.get(position);
        Picasso.get().load(category.getImage()).into(holder.itemItemsBinding.ivItemImage);
        holder.itemItemsBinding.tvTitle.setText(category.getCategory());
        holder.itemItemsBinding.tvDescription.setText(category.getDescription());
        TextView textView = holder.itemItemsBinding.tvPrice;
        textView.setText(category.getPrice() + " €");
        holder.itemItemsBinding.clParent.setOnClickListener(v -> {
            context.startActivity(new Intent(this.context, DetailsActivity.class).putExtra("category", category));

        });
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return categoryArrayList !=null? categoryArrayList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemItemsBinding itemItemsBinding;

        public MyViewHolder(ItemItemsBinding itemItemsBinding2) {
            super(itemItemsBinding2.getRoot());
            this.itemItemsBinding = itemItemsBinding2;
        }
    }
}
