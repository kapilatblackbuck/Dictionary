package com.kapil.sample.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kapilsharma on 04/05/18.
 */

public class Scenario {
    @SerializedName("scenarios")
    public List<String> scenarios;

    @SerializedName("usages")
    public List<String> usages;

    @SerializedName("answer")
    public String answer;
    private int usage;

    public String getUsage() {
        StringBuilder result = new StringBuilder();
        for(String str: usages) {
            result.append(str+ "\n");
        }
        return result.toString();
    }

    public String getScenarios() {
        StringBuilder result = new StringBuilder();
        for(String str: scenarios) {
            result.append(str+ "\n");
        }
        return result.toString();
    }
}
