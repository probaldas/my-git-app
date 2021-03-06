package com.funnow.mygitapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class GitCommits {

    @SerializedName("commit")
    @Expose
    private Commit commit;

    @SerializedName("sha")
    @Expose
    private String sha;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitCommits that = (GitCommits) o;
        return sha.equals(that.sha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha);
    }
}
