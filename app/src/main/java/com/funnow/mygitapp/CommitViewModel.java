package com.funnow.mygitapp;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.repositories.GitApiRespository;
import com.funnow.mygitapp.repositories.NetworkRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CommitViewModel extends ViewModel {

    private String committerName;
    private String commitHash;
    private String commitMsg;

    private GitApiRespository apiRespository;

    @Inject
    NetworkRepository networkRepository;

    CommitViewModel() {
        apiRespository = new GitApiRespository();

        MyGitApplication.getApplication().getDataComponent().inject(this);
    }

    LiveData<List<GitCommits>> getAllCommits() {
        return apiRespository.getAllCommits();
    }

    LiveData<List<GitCommits>> getCommits() {
        return networkRepository.getAllCommits();
    }

    public String getCommitterName() {
        return committerName;
    }

    void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public String getCommitHash() {
        return commitHash;
    }

    void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getCommitMsg() {
        return commitMsg;
    }

    void setCommitMsg(String commitMsg) {
        this.commitMsg = commitMsg;
    }
}
