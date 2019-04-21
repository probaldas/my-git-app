package com.funnow.mygitapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.funnow.mygitapp.adapter.CommitViewPagedAdapter;
import com.funnow.mygitapp.models.GitCommits;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private long backPressedTime;
    private Toast backPressedToast;

    private SwipeRefreshLayout swipeRefersh;
    private ConstraintLayout errorLayout;
    private RecyclerView recyclerView;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_title);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        swipeRefersh = findViewById(R.id.swipe_refresh);
        errorLayout = findViewById(R.id.error_layout);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getDataFromServer();

        swipeRefersh.setOnRefreshListener(this::getDataFromServer);
    }

    private void getDataFromServer() {
        swipeRefersh.setRefreshing(true);

        final CommitViewPagedAdapter adapter = new CommitViewPagedAdapter();

        mainViewModel.getCommitsPagedList().observe(this, new Observer<PagedList<GitCommits>>() {
            @Override
            public void onChanged(PagedList<GitCommits> gitCommits) {
                Log.i(TAG, "onChanged called and list size is: " + gitCommits.size() + ".");
                adapter.submitList(gitCommits);
                swipeRefersh.setRefreshing(false);
                /*if (gitCommits != null && gitCommits.size() > 0) {
                    Log.i(TAG, "if gitCommits is not null");
                    errorLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    Log.i(TAG, "if gitCommits is null");
                    errorLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }*/
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /*private ArrayList<CommitViewModel> getData(List<GitCommits> gitCommits) {
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
    }*/

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
