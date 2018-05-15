package com.kapil.sample.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kapilsharma on 11/07/17.
 */

public class QuestionData {

    @SerializedName("items")
   public List<Question> questionList;
}
