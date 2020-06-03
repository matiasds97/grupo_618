package com.example.tpandroid.models.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event
{
    @SerializedName("env")
    @Expose
    private String env;
    @SerializedName("type_events")
    @Expose
    private String typeEvents;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    /**
     *
     * @param typeEvents
     * @param description
     * @param state
     * @param env
     */
    public Event(String env, String typeEvents, String state, String description) {
        super();
        this.env = env;
        this.typeEvents = typeEvents;
        this.state = state;
        this.description = description;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTypeEvents() {
        return typeEvents;
    }

    public void setTypeEvents(String typeEvents) {
        this.typeEvents = typeEvents;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}