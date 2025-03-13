package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KlientWypozyczalni extends Klient implements Serializable {
    //ekstencja
    public static ArrayList<KlientWypozyczalni> wszyscyKlienciWypozyczalni =new ArrayList<>();

    //asocjacja-klasa asocjacji
    Klient klient;
    Wypozyczalnia wypozyczalnia;

    public KlientWypozyczalni(DaneOsobowe daneOsobowe, Klient klient, Wypozyczalnia wypozyczalnia){
        super(daneOsobowe);
        klient.dodajKlientaWypozyczalni(this);
        wypozyczalnia.dodajKlientaWypozyczalni(this);
        this.wypożyczenia=new ArrayList<>();
        wszyscyKlienciWypozyczalni.add(this);
    }

    //asocjajcja z atrybutem
    //metoda taka jak w Kostium
    public void dodajWypozyczenie(Wypozyczenie noweWypozyczenie){
        if(!wypożyczenia.contains(noweWypozyczenie)){
            wypożyczenia.add(noweWypozyczenie);
        }
    }


    //ekstenja i jej trwałość- zapis, odczyt z pliku
    public static void dodajKlientaW (KlientWypozyczalni klientWypozyczalni){
        if (!wszyscyKlienciWypozyczalni.contains(klientWypozyczalni)) {
            wszyscyKlienciWypozyczalni.add(klientWypozyczalni);
        }
    }

    public static void usunKlientaW (KlientWypozyczalni klientWypozyczalni ){
        if (wszyscyKlienciWypozyczalni.contains(klientWypozyczalni)) {
            wszyscyKlienciWypozyczalni.remove(klientWypozyczalni);
        }
    }
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(KlientWypozyczalni.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja klient:: " + theClass.getSimpleName());
        List<KlientWypozyczalni> ekstensja = KlientWypozyczalni.wszyscyKlienciWypozyczalni;
        for (KlientWypozyczalni kw : ekstensja) {
            System.out.println(kw.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wszyscyKlienciWypozyczalni);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wszyscyKlienciWypozyczalni = (ArrayList<KlientWypozyczalni>)  stream.readObject();
        System.out.println("Odczytana lista wszytskich klientow - czytajEkstensje()");
        for (KlientWypozyczalni klientWypozyczalni : wszyscyKlienciWypozyczalni) {
            System.out.println(klientWypozyczalni.toString());
        }
    }
    //metoda toString
    @Override
    public String toString() {
        return  daneOsobowe.getImie() + ' ' +
                daneOsobowe.getNazwisko() + ' ' ;
    }

    public void setWypożyczalnia (Wypozyczalnia wypozyczalnia){
        this.wypozyczalnia = wypozyczalnia;
        wypozyczalnia.dodajKlientaWypozyczalni(this);
    }
}

