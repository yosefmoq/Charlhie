package com.yosefmoq.charlhie;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yosefmoq.charlhie.databinding.ItemCartBinding;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.repository.local.MyDatabase;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private DialogCallBack dialogCallBack;
    private MyDatabase myDatabase;
    private ArrayList<Category> productResponses;

    public void onDialogConfirm(DialogCallBack dialogCallBack2) {
        this.dialogCallBack = dialogCallBack2;
    }

    public CartAdapter(Context context2, ArrayList<Category> productResponses2) {
        this.context = context2;
        this.myDatabase = new MyDatabase(context2);
        this.productResponses = productResponses2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder((ItemCartBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.item_cart, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category productResponse = this.productResponses.get(position);
        holder.itemCartBinding.tvCategory.setText(productResponse.getCategory());
        holder.itemCartBinding.tvName.setText(productResponse.getSubCategory());
        Picasso.get().load(productResponse.getImage()).into(holder.itemCartBinding.ivImage);
        TextView textView = holder.itemCartBinding.tvPrice;
        textView.setText(productResponse.getNativePrice() + " €");
        TextView textView2 = holder.itemCartBinding.tvQuantity;
        textView2.setText("Aantal " + productResponse.getQuantity());
        holder.itemCartBinding.imageButton2.setOnClickListener(v->{
            AlertDialog alertDialog = new AlertDialog.Builder(this.context).create();
            View view = View.inflate(this.context, R.layout.remove_image_dialog, null);
            alertDialog.setView(view, 10, 0, 0, 0);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            ((Button) view.findViewById(R.id.ibCancel)).setOnClickListener(v1 -> {
                alertDialog.dismiss();
            });
            ((Button) view.findViewById(R.id.ibConfirm)).setOnClickListener(v1 -> {
                this.myDatabase.deleteItemById(productResponse.getId());
                this.dialogCallBack.onConfirm();
                alertDialog.dismiss();

            });
            alertDialog.show();


        });
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.productResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding itemCartBinding;

        public MyViewHolder(ItemCartBinding itemCartBinding2) {
            super(itemCartBinding2.getRoot());
            this.itemCartBinding = itemCartBinding2;
        }
    }

    public void notifyData(ArrayList<Category> productResponses2) {
        this.productResponses.clear();
        this.productResponses = productResponses2;
        notifyDataSetChanged();
    }
}
