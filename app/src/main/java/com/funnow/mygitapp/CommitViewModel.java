package com.funnow.mygitapp;

import com.funnow.mygitapp.models.GitCommits;
import com.funnow.mygitapp.services.GitApiRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CommitViewModel extends ViewModel {

    @Inject
    GitApiRepository gitApiRepository;
    private String committerName;
    private String commitHash;
    private String commitMsg;

    CommitViewModel() {
        MyGitApplication.getApplication().getDataComponent().inject(this);
    }

    LiveData<List<GitCommits>> getCommits() {
        return gitApiRepository.getAllCommits();
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
