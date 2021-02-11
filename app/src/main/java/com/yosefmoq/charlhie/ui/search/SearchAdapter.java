package com.yosefmoq.charlhie.ui.search;

import android.content.Context;
import android.content.Intent;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ItemSearchBinding;
import com.yosefmoq.charlhie.ui.ItemsActivity;

import java.util.Iterator;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArraySet<String> filterStrings;
    ArraySet<String> strings;

    public SearchAdapter(Context context2, ArraySet<String> strings2) {
        this.context = context2;
        this.strings = strings2;
        this.filterStrings = strings2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder((ItemSearchBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.item_search, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s = this.strings.valueAt(position);
        holder.itemSearchBinding.tvSearchText.setText(s);
        holder.itemSearchBinding.clParent.setOnClickListener(view -> context.startActivity(new Intent(context, ItemsActivity.class).putExtra("cat", s)));
    }



    @Override
    public int getItemCount() {
        return this.strings.size();
    }

    public Filter getFilter() {
        return new Filter() {
            public FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                if (query.isEmpty()) {
                    SearchAdapter.this.filterStrings.clear();
                } else {
                    ArraySet<String> filteredStrings = new ArraySet<>();
                    Iterator<String> it = SearchAdapter.this.strings.iterator();
                    while (it.hasNext()) {
                        String s = it.next();
                        if (s.toLowerCase().contains(query)) {
                            filteredStrings.add(s);
                        }
                    }
                    SearchAdapter.this.strings = filteredStrings;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = SearchAdapter.this.strings;
                return filterResults;
            }


            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                SearchAdapter.this.strings = (ArraySet) filterResults.values;
                SearchAdapter.this.notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSearchBinding itemSearchBinding;

        public MyViewHolder(ItemSearchBinding itemSearchBinding2) {
            super(itemSearchBinding2.getRoot());
            this.itemSearchBinding = itemSearchBinding2;
        }
    }

    public void update(ArraySet<String> strings2) {
        this.strings.clear();
        this.strings.addAll((ArraySet<? extends String>) strings2);
        notifyDataSetChanged();
    }
}
