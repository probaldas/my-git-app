package com.funnow.mygitapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.funnow.mygitapp.MyGitApplication;

public class CommitViewModel extends ViewModel {

    private String committerName;
    private String commitHash;
    private String commitMsg;

    public CommitViewModel(String committerName, String commitHash, String commitMsg) {
        MyGitApplication.getApplication().getDataComponent().inject(this);
        this.committerName = committerName;
        this.commitHash = commitHash;
        this.commitMsg = commitMsg;
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
