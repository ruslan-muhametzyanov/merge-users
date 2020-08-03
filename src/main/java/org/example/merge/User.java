package org.example.merge;

import java.util.HashSet;

public class User {

    private String name;
    private HashSet<String> mails;
    private boolean processed = false;

    public User(String name, HashSet<String> mails) {
        this.name = name;
        this.mails = mails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<String> getMails() {
        return mails;
    }

    public void setMails(HashSet<String> mails) {
        this.mails = mails;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isProcessed() {
        return processed;
    }

}
