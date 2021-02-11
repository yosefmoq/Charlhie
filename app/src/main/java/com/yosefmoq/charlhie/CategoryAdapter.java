package com.yosefmoq.charlhie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    int selectedCategory = -1;
    ArrayList<CategoryModel> strings;

    public CategoryAdapter(Context context2, ArrayList<CategoryModel> strings2) {
        this.context = context2;
        this.strings = strings2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_categories, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryModel categoryModel = this.strings.get(position);
        holder.tvCategory.setText(categoryModel.getCategoryName());
        if (this.selectedCategory != position) {
            holder.rvSubCategory.setVisibility(View.GONE);
        } else if (categoryModel.getSubCategories() == null || categoryModel.getSubCategories().size() <= 0) {
            holder.rvSubCategory.setVisibility(View.GONE);
            holder.ivArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        } else {
            holder.rvSubCategory.setVisibility(View.VISIBLE);
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(this.context, categoryModel.subCategories);
            holder.rvSubCategory.setLayoutManager(new LinearLayoutManager(this.context));
            holder.rvSubCategory.setAdapter(subCategoryAdapter);
            holder.ivArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        }
        holder.clParent.setOnClickListener(v -> {
            if (this.selectedCategory == position) {
                holder.rvSubCategory.setVisibility(View.GONE);
                holder.ivArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                this.selectedCategory = -1;
            } else {
                this.selectedCategory = position;
            }
            notifyDataSetChanged();

        });
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clParent;
        ImageView ivArrow;
        RecyclerView rvSubCategory;
        TextView tvCategory;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            this.rvSubCategory = (RecyclerView) itemView.findViewById(R.id.rvSubCategory);
            this.clParent = (ConstraintLayout) itemView.findViewById(R.id.parent);
            this.ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
        }
    }

    public void update(ArrayList<CategoryModel> categoryModels) {
        this.strings.clear();
        this.strings.addAll(categoryModels);
        notifyDataSetChanged();
    }
}
