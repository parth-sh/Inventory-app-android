package com.parth8421.proj1.recycler_view_components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.parth8421.proj1.R;
import com.parth8421.proj1.data.Product;
import com.parth8421.proj1.viewModel.ProductViewModel;

import java.util.Date;

public class ProductCustomAdapter extends ListAdapter<Product, RecyclerView.ViewHolder> {

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, cost, count;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.recycler_view_item_tv_product_name);
            this.cost = itemView.findViewById(R.id.recycler_view_item_tv_product_cost);
            this.count = itemView.findViewById(R.id.recycler_view_item_tv_product_count);
        }

        public void bind(int id, String name, int cost, int count) {
            this.name.setText(name);
            this.cost.setText("₹ " + cost);
            this.count.setText("" + count);
        }

        static ProductViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_product, parent, false);
            return new ProductViewHolder(view);
        }
    }

    public ProductCustomAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }

    static public class EntryDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product current = this.getItem(position);
        ((ProductViewHolder) holder).bind(
                current.getId(),
                current.getName(),
                current.getCost(),
                current.getCount()
        );
    }
}
