package com.funnow.mygitapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("author")
    @Expose
    private User author;

    @SerializedName("committer")
    @Expose
    private User committer;

    @SerializedName("message")
    @Expose
    private String message;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getCommitter() {
        return committer;
    }

    public void setCommitter(User committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
