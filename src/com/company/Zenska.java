package com.company;

import java.io.Serializable;

public class Zenska extends Plec implements Serializable {
    //dziedziczenie wieloaspektowe

    //atrybut
    private boolean mozliwoscporodu;

    //konstrukotr
    public Zenska(Osoba osoba, boolean mozliwoscporodu){
        super(osoba);
        this.mozliwoscporodu=mozliwoscporodu;
    }
    //toString
    @Override
    public String toString(){
        return "Płeć: "+"Żeńska";
    }

    //getter
    public boolean getMozliwoscporodu() {
        return mozliwoscporodu;
    }

}
