package com.example.tpandroid.models;

public class CantidadHelado {

    private String[] cantidad;

    public CantidadHelado()
    {
        cantidad = new String[]
                {
                        "Cucurucho",
                        "1/4 Kg",
                        "1/2 Kg",
                        "1 Kg"
                };
    }

    public String[] getCantidad()
    {
        return cantidad;
    }
}
