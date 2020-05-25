package com.example.tpandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("env")
    @Expose
    private String env;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("dni")
    @Expose
    private int dni;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("commission")
    @Expose
    private int commission;
    @SerializedName("group")
    @Expose
    private int group;

    /**
     * No args constructor for use in serialization
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param password
     * @param name
     * @param commission
     * @param env
     * @param dni
     * @param email
     * @param lastname
     * @param group
     */
    public Usuario(String env, String name, String lastname, int dni, String email, String password, int commission, int group) {
        super();
        this.env = env;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.commission = commission;
        this.group = group;
    }

    public Usuario(String mail, String pass)
    {
        super();
        this.env = "DEV";
        this.email = mail;
        this.password = pass;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

}
