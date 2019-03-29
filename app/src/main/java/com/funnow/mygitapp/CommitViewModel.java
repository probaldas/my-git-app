package com.funnow.mygitapp;

import androidx.lifecycle.ViewModel;

public class CommitViewModel extends ViewModel {

    private String committerName;
    private String commitHash;
    private String commitMsg;

    public String getCommitterName() {
        return committerName;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getCommitMsg() {
        return commitMsg;
    }

    public void setCommitMsg(String commitMsg) {
        this.commitMsg = commitMsg;
    }
}
