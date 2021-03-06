package com.github.jenkins.lastchanges.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rmpestano on 6/26/16.
 */
public class CommitInfo implements Serializable {

    public static final String newLine = System.getProperty("line.separator");

    private String commitId;
    private String commitMessage;
    private String commiterName;
    private String commiterEmail;
    private String commitDate;
    private DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);


    public String getCommiterName() {
        return commiterName;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getCommiterEmail() {
        return commiterEmail;
    }

    public String getCommitMessage() {
        return commitMessage;
    }


    public String format(Date date, TimeZone tz) {
        dateFormat.setTimeZone(tz);
        return dateFormat.format(date);
    }

    public CommitInfo setCommitDate(String commitDate) {
        this.commitDate = commitDate;
        return this;
    }

    public CommitInfo setCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public CommitInfo setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
        return this;
    }

    public CommitInfo setCommiterName(String commiterName) {
        this.commiterName = commiterName;
        return this;
    }

    public CommitInfo setCommiterEmail(String commiterEmail) {
        this.commiterEmail = commiterEmail;
        return this;
    }

    public String getFormatedCommitId() {
        try {
            Long.parseLong(commitId);
            return commitId;//if its numeric (SVN) dont truncate
        }catch (NumberFormatException nfe) {
            //alphanumeric (GIT) then truncate
            return truncate(commitId,8);
        }
    }

    private String truncate(String value, int length) {
        if (value == null || value.length() <= length) {
            return value;
        }
        return value.substring(0, length - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().
                append("Commit: ").append(commitId).append(newLine).
                append("Author: " + commiterName).append(newLine).
                append("E-mail: ").append(commiterEmail).append(newLine).
                append("Date: ").append(commitDate).append(newLine).
                append("Message: ").append(commitMessage).append(newLine).append(newLine);

        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitInfo that = (CommitInfo) o;

        return commitId != null ? commitId.equals(that.commitId) : that.commitId == null;
    }

    @Override
    public int hashCode() {
        return commiterName != null ? commiterName.hashCode() : 0;
    }
}
