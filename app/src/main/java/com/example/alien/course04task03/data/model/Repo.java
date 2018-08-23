package com.example.alien.course04task03.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Repo {

    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("private")
    private boolean jsonMemberPrivate;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("pushed_at")
    private String pushedAt;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("size")
    private int size;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("name")
    private String name;

    @SerializedName("watchers_count")
    private int watchersCount;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("forks_count")
    private int forksCount;

    public void setJsonMemberPrivate(boolean jsonMemberPrivate) {
        this.jsonMemberPrivate = jsonMemberPrivate;
    }

    public boolean isJsonMemberPrivate() {
        return jsonMemberPrivate;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    @Override
    public String toString() {
        return
                "Repo{" +
                        "private = '" + jsonMemberPrivate + '\'' +
                        ",stargazers_count = '" + stargazersCount + '\'' +
                        ",pushed_at = '" + pushedAt + '\'' +
                        ",description = '" + description + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",full_name = '" + fullName + '\'' +
                        ",size = '" + size + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",watchers_count = '" + watchersCount + '\'' +
                        ",node_id = '" + nodeId + '\'' +
                        ",forks_count = '" + forksCount + '\'' +
                        "}";
    }
}