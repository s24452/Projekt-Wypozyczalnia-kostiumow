package com.company;

import java.io.Serializable;

public class Plec implements Serializable {
    private static int dwiePlcie=0;//brak w dok

    //kompozycja z Osobą
    private  Osoba osoba;

    //konstrukor
    public Plec(Osoba osoba){
        this.osoba=osoba;
    }

    //kompozycja z Osoba
    public static Plec uwtorzPlec(Osoba osoba) throws Exception{
        if(osoba==null){
            throw new Exception("Osoba nie istnieje");
        }else {
            if(dwiePlcie>=2){
                throw new Exception("Isnieja tylko 2 dostepne plci");
            }
            Plec plec = new Plec(osoba);
            osoba.dodajPlec(plec);
            return plec;
        }
    }
    //metoda toString
    @Override
    public String toString(){
        return "Płeć: ";
    }
}
