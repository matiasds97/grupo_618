package com.example.tpandroid.models.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event
{

    @SerializedName("type_events")
    @Expose
    private String typeEvents;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("group")
    @Expose
    private int group;

    public Event(String tipo, String estado, String desc)
    {
        this.typeEvents = tipo;
        this.state = estado;
        this.description = desc;
        this.group = 618;
    }

    public String getTypeEvents()
    {
        return typeEvents;
    }

    public void setTypeEvents(String typeEvents)
    {
        this.typeEvents = typeEvents;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }

}