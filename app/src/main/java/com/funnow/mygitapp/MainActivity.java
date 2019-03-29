package com.funnow.mygitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.funnow.mygitapp.adapter.CommitViewAdapter;
import com.funnow.mygitapp.models.GitCommits;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_title);

        viewModel = ViewModelProviders.of(this).get(CommitViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel.getAllCommits().observe(this, new Observer<List<GitCommits>>() {
            @Override
            public void onChanged(List<GitCommits> gitCommits) {
                recyclerView.setAdapter(new CommitViewAdapter(getData(gitCommits)));
            }
        });
    }

    private ArrayList<CommitViewModel> getData(List<GitCommits> gitCommits) {
        ArrayList<CommitViewModel> viewModels = new ArrayList<>();
        CommitViewModel viewModel;

        for (GitCommits commits: gitCommits) {
            viewModel = new CommitViewModel();

            viewModel.setCommitterName(commits.getCommit().getCommitter().getName());
            viewModel.setCommitHash(commits.getSha().substring(0, 7));
            viewModel.setCommitMsg(commits.getCommit().getMessage());

            viewModels.add(viewModel);
        }

        return viewModels;
    }
}
