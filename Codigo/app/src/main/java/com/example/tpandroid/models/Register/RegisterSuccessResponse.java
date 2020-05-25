package com.example.tpandroid.models.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterSuccessResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("env")
    @Expose
    private String env;
    @SerializedName("token")
    @Expose
    private String token;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}