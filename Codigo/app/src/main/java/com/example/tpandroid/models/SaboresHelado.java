package com.example.tpandroid.models;

public class SaboresHelado {

    private String[] sabores;

    public SaboresHelado()
    {
        sabores = new String[]
                {
                  "Chocolate",
                  "Sambayón",
                  "Menta Granizada",
                  "Kinotos al Whisky",
                  "Crema del Cielo",
                        "Frutilla a la crema",
                        "Banana a la crema",
                        "Almendrado",
                        "Banana Split",
                        "Vainilla",
                        "Granizado",
                        "Dulce de Leche",
                        "Dulce de Leche Granizado",
                        "Chocolate con Almendras",
                        "Chocolate Suizo",
                        "Chocolate Amargo",
                        "Frutilla al Agua",
                        "Melón",
                        "Manzana",
                        "Frambuesa",
                        "Mousse Chocolate",
                        "Crema Americana",
                        "Limón",
                        "Tramontana"

                };

        sabores = this.OrdenarArray(sabores);
    }

    private static String[] OrdenarArray(String s[])
    {
        int size = s.length;

        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<s.length; j++) {
                if(s[i].compareTo(s[j])>0) {
                    String temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
        return s;
    }

    public String[] getSabores()
    {
        return this.sabores;
    }

}
