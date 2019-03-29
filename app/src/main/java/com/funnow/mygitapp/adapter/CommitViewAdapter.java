package com.funnow.mygitapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.databinding.CommitDataBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitViewAdapter extends RecyclerView.Adapter<CommitViewHolder> {

    private ArrayList<CommitViewModel> mCommitViewModels;
    private LayoutInflater inflater;

    public CommitViewAdapter(ArrayList<CommitViewModel> commitViewModels) {
        this.mCommitViewModels = commitViewModels;
    }

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        CommitDataBinding dataBinding = CommitDataBinding.inflate(inflater, parent, false);
        return new CommitViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        final CommitViewModel viewModel = mCommitViewModels.get(position);
        holder.bind(viewModel);
    }

    @Override
    public int getItemCount() {
        return mCommitViewModels.size();
    }

}
