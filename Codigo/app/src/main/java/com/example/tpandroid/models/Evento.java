package com.example.tpandroid.models;

public class Evento
{
    String env;
    String type_events;
    String state;
    String description;

    public Evento(String env, String type_events, String state, String description) {
        this.env = env;
        this.type_events = type_events;
        this.state = state;
        this.description = description;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
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
