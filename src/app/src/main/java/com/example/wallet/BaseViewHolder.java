package com.example.wallet;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        // ButterKnife.bind(this, itemView);
    }
    public abstract void bind(T type);

}
