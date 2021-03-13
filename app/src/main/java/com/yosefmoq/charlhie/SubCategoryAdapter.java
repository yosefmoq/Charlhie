package com.yosefmoq.charlhie;

import android.content.Context;
import android.content.Intent;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yosefmoq.charlhie.ui.ItemsActivity;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    Context context;
    ArraySet<String> strings;

    public SubCategoryAdapter(Context context2, ArraySet<String> strings2) {
        this.context = context2;
        this.strings = strings2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_sub_category, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s = this.strings.valueAt(position);
        holder.tvSubCategory.setText(s);
        holder.clParent.setOnClickListener(v -> {
//            ((DrawerActivity)context).drawer.closeDrawers();
            this.context.startActivity(new Intent(this.context, ItemsActivity.class).putExtra("cat", s));

        });
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.strings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clParent;
        TextView tvSubCategory;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvSubCategory = (TextView) itemView.findViewById(R.id.tvSubCategory);
            this.clParent = (ConstraintLayout) itemView.findViewById(R.id.clParent);
        }
    }
}
