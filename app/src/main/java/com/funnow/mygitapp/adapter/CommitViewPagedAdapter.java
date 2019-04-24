package com.funnow.mygitapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.funnow.mygitapp.CommitViewModel;
import com.funnow.mygitapp.databinding.CommitItemViewBinding;
import com.funnow.mygitapp.models.GitCommits;

public class CommitViewPagedAdapter extends PagedListAdapter<GitCommits, CommitViewHolder> {

    private static DiffUtil.ItemCallback<GitCommits> diffCallback =
            new DiffUtil.ItemCallback<GitCommits>() {
                @Override
                public boolean areItemsTheSame(@NonNull GitCommits oldItem, @NonNull GitCommits newItem) {
                    return oldItem.getSha().equals(newItem.getSha());
                }

                @Override
                public boolean areContentsTheSame(@NonNull GitCommits oldItem, @NonNull GitCommits newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private LayoutInflater inflater;
    private PagedList<GitCommits> mCommitViewModels;

    public CommitViewPagedAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        CommitItemViewBinding dataBinding = CommitItemViewBinding.inflate(inflater, parent, false);
        return new CommitViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        final CommitViewModel viewModel = getData(getItem(position));
        holder.bind(viewModel);
    }

    private CommitViewModel getData(GitCommits gitCommits) {
        return new CommitViewModel(gitCommits.getCommit().getCommitter().getName(),
                gitCommits.getSha().substring(0, 7),
                gitCommits.getCommit().getMessage());
    }
}
