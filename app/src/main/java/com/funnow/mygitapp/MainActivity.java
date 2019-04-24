package com.funnow.mygitapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.funnow.mygitapp.adapter.CommitViewPagedAdapter;
import com.funnow.mygitapp.models.GitCommits;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private long backPressedTime;
    private Toast backPressedToast;

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_title);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getDataFromServer();

        swipeRefresh.setOnRefreshListener(this::getDataFromServer);
    }

    private void getDataFromServer() {
        swipeRefresh.setRefreshing(true);

        final CommitViewPagedAdapter adapter = new CommitViewPagedAdapter();

        mainViewModel.getCommitsPagedList().observe(this, new Observer<PagedList<GitCommits>>() {
            @Override
            public void onChanged(PagedList<GitCommits> gitCommits) {
                Log.i(TAG, "onChanged called and list size is: " + gitCommits.size() + ".");
                adapter.submitList(gitCommits);
                swipeRefresh.setRefreshing(false);
            }
        });

        recyclerView.setAdapter(adapter);
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
