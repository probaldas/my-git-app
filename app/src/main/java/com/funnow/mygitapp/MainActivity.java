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
import com.funnow.mygitapp.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int BACK_PRESSED_INTERVAL = 2000;

    private long backPressedTime;
    private Toast backPressedToast;

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set custom title for app
        setTitle(R.string.app_title);

        // get object for MainViewModel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // Setup recycler view
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Setup swipe refresh to reload data
        swipeRefresh = findViewById(R.id.swipe_refresh);
        getDataFromServer();
        swipeRefresh.setOnRefreshListener(this::getDataFromServer);
    }

    /* Get Git Commits data from backend */
    private void getDataFromServer() {
        swipeRefresh.setRefreshing(true);       // start app refresh

        // Instantiate PagedAdapter
        final CommitViewPagedAdapter adapter = new CommitViewPagedAdapter();

        /*
        * LiveData callback for gitCommits
        * Once data is available from backed webservice the observer's onChanged
        * method will be automatically called.
        * */
        mainViewModel.getCommitsPagedList().observe(this, gitCommits -> {
            Log.i(TAG, "onChanged called and list size is: " + gitCommits.size() + ".");
            adapter.submitList(gitCommits);
            swipeRefresh.setRefreshing(false);      // stop app refersh
        });

        // Attach adapter to recycler view
        recyclerView.setAdapter(adapter);
    }

    /*
    * Override back button pressed, to implement 'Press back again to exit'
    * */
    @Override
    public void onBackPressed() {
        if (backPressedTime + BACK_PRESSED_INTERVAL > System.currentTimeMillis()) {
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
