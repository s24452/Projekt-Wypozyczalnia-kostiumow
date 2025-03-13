package com.company;

import java.io.Serializable;

public class Meska extends Plec implements Serializable {
    //dziedziczenie wieloaspektowe

    //atrybut
    private boolean biologicznaSilaFizyczna;

    //konstruktor
    public Meska(Osoba osoba, boolean biologicznaSilaFizyczna){
        super(osoba);
        this.biologicznaSilaFizyczna=biologicznaSilaFizyczna;
    }
    //metoda toString()
    @Override
    public String toString(){
        return "Płeć: "+"Męska";
    }

    //getter
    public boolean getBiologicznaSilaFizyczna() {
        return biologicznaSilaFizyczna;
    }


}
