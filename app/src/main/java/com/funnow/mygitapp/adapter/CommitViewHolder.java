package com.funnow.mygitapp.adapter;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.databinding.CommitDataBinding;

import androidx.recyclerview.widget.RecyclerView;

public class CommitViewHolder extends RecyclerView.ViewHolder {

    private CommitDataBinding mDataBinding;

    public CommitViewHolder(CommitDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.mDataBinding = dataBinding;
    }

    public void bind(CommitViewModel viewModel) {
        this.mDataBinding.setViewModel(viewModel);
    }

    public CommitDataBinding getDataBinding() {
        return mDataBinding;
    }
}
