package com.funnow.mygitapp.adapter;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.databinding.CommitItemViewBinding;

import androidx.recyclerview.widget.RecyclerView;

public class CommitViewHolder extends RecyclerView.ViewHolder {

    private CommitItemViewBinding mDataBinding;

    CommitViewHolder(CommitItemViewBinding dataBinding) {
        super(dataBinding.getRoot());
        this.mDataBinding = dataBinding;
    }

    void bind(CommitViewModel viewModel) {
        this.mDataBinding.setViewModel(viewModel);
    }

    public CommitItemViewBinding getDataBinding() {
        return mDataBinding;
    }
}
