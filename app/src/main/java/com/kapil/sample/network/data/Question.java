package com.kapil.sample.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kapilsharma on 11/07/17.
 */

public class Question {

    @SerializedName("title")
    public String title;

    @SerializedName("score")
    public String upvote;

    @SerializedName("owner")
    public Owner owner;

    @SerializedName("creation_date")
    public long createdDate;

    @SerializedName("question_id")
    public long questionId;

    @SerializedName("link")
    public String link;

    @SerializedName("tags")
    public List<String> tags;


}
