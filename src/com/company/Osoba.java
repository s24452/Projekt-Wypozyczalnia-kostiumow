package com.company;

import java.io.Serializable;
import java.util.Vector;

abstract class Osoba implements Serializable {
    //atrybut złożony-składa się z atrybutów prostych lub innych złożonych
    DaneOsobowe daneOsobowe;

    //kompozycja z Płeć
    private Vector<Plec> plec=new Vector<Plec>();

    //konstrukor
    public Osoba(DaneOsobowe daneOsobowe){
        this.daneOsobowe=daneOsobowe;
    }

    //kompozycja z Płeć
    public void dodajPlec(Plec nowaPlec) throws Exception{
        if(!plec.contains(nowaPlec)){
            plec.add(nowaPlec);
        }
    }

    //metoda toString
    @Override
    public String toString(){
        return "Osoba: "+daneOsobowe.getImie()+ ' '+
                daneOsobowe.getNazwisko()+ ' '+
                daneOsobowe.getDataUrodzenia()+' ';
    }
    //gettery i settery
    public DaneOsobowe getDaneOsobowe() {
        return daneOsobowe;
    }
    public void setDaneOsobowe(DaneOsobowe daneOsobowe) {
        this.daneOsobowe = daneOsobowe;
    }
}
