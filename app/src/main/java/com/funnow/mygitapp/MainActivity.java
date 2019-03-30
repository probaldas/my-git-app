package com.funnow.mygitapp;

import android.os.Bundle;
import android.widget.Toast;

import com.funnow.mygitapp.adapter.CommitViewAdapter;
import com.funnow.mygitapp.models.GitCommits;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backPressedToast;

    private SwipeRefreshLayout swipeRefersh;
    private RecyclerView recyclerView;
    private CommitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_title);

        viewModel = ViewModelProviders.of(this).get(CommitViewModel.class);

        swipeRefersh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getDataFromServer();

        swipeRefersh.setOnRefreshListener(this::getDataFromServer);
    }

    private void getDataFromServer() {
        swipeRefersh.setRefreshing(true);

        viewModel.getCommits().observe(this, gitCommits -> {
            recyclerView.setAdapter(new CommitViewAdapter(getData(gitCommits)));
            swipeRefersh.setRefreshing(false);
        });
    }

    private ArrayList<CommitViewModel> getData(List<GitCommits> gitCommits) {
        ArrayList<CommitViewModel> viewModels = new ArrayList<>();
        CommitViewModel viewModel;

        for (GitCommits commits : gitCommits) {
            viewModel = new CommitViewModel();

            viewModel.setCommitterName(commits.getCommit().getCommitter().getName());
            viewModel.setCommitHash(commits.getSha().substring(0, 7));
            viewModel.setCommitMsg(commits.getCommit().getMessage());

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            backPressedToast.cancel();
            return;
        } else {
            backPressedToast = Toast.makeText(getBaseContext(), R.string.exit_alert, Toast.LENGTH_SHORT);
            backPressedToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
