package com.funnow.mygitapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.databinding.CommitDataBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitViewAdapter extends RecyclerView.Adapter<CommitViewHolder> {

    private ArrayList<CommitViewModel> commitViewModels;
    private LayoutInflater inflater;

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
        final CommitViewModel viewModel = commitViewModels.get(position);
        holder.bind(viewModel);
    }

    @Override
    public int getItemCount() {
        return commitViewModels.size();
    }

}
