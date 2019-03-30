package com.funnow.mygitapp;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.repositories.GitApiRespository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CommitViewModel extends ViewModel {

    private String committerName;
    private String commitHash;
    private String commitMsg;

    private GitApiRespository apiRespository;

    CommitViewModel() {
        apiRespository = new GitApiRespository();
    }

    LiveData<List<GitCommits>> getAllCommits() {
        return apiRespository.getAllCommits();
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
