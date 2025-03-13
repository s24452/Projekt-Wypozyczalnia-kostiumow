package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Wypozyczenie implements Serializable {
    //ekstencja
    public static List<Wypozyczenie> ewszytskieWypozyczenia=new ArrayList<>();

    //asocjaja z atrybutem - klasa asocjacji
    Kostium kostium;
    Klient klient;

    KlientWypozyczalni klientWypozyczalni;

    //ograniczenie -unique
    private static Set<Integer> unikalneNumery=new HashSet<>();
    private int numer;

    //inne atrybuty
    int numerSprzedawcy;
    double koszt;
    int czasWypożyczenia;

    //ograniczenie bag
    private Map<Klient, List<Kostium>> wypozyczeniaKlienta;


    //konstruktor
    public Wypozyczenie(int numer,int numerSprzedawcy,double koszt,int czasWypożyczenia,Kostium kostium,KlientWypozyczalni klientWypozyczalni){
        setNumer(numer);
        this.numerSprzedawcy=numerSprzedawcy;
        this.koszt=koszt;
        this.czasWypożyczenia=czasWypożyczenia;
        kostium.dodajWypozyczenie(this);
        klientWypozyczalni.dodajWypozyczenie(this);//GUI
        ewszytskieWypozyczenia.add(this);
    }
    public Wypozyczenie(int numer,int numerSprzedawcy,double koszt,int czasWypożyczenia,Kostium kostium,Klient klient){
        setNumer(numer);
        this.numerSprzedawcy=numerSprzedawcy;
        this.koszt=koszt;
        this.czasWypożyczenia=czasWypożyczenia;
        kostium.dodajWypozyczenie(this);
        klient.dodajWypozyczenie(this);
        ewszytskieWypozyczenia.add(this);
    }
    public Wypozyczenie(int numer,int numerSprzedawcy,double koszt,int czasWypożyczenia,Kostium kostium,Klient klient,Map<Klient,Kostium> wypozyczeniaKlienta){
        this.numer=numer;
        this.numerSprzedawcy=numerSprzedawcy;
        this.koszt=koszt;
        this.czasWypożyczenia=czasWypożyczenia;
        kostium.dodajWypozyczenie(this);
        klient.dodajWypozyczenie(this);
        this.wypozyczeniaKlienta =new HashMap<>();
        ewszytskieWypozyczenia.add(this);
    }
    //ograniczenie -unique metody
    public int setNumer(int numer) {
        if(!unikalneNumery.contains(numer)){
            unikalneNumery.add(numer);
        }else{
            throw new IllegalArgumentException("Numer jest wartoscia unikalna");
        }
        return numer;
    }
    //ograniczenie - ordered - zachowana kolejnosc dodawania
    public  void dodajWypozyczenie(Klient klient,Kostium kostium){
        if(!wypozyczeniaKlienta.containsKey(klient)){
            wypozyczeniaKlienta.put(klient,new ArrayList<>());
        }
        wypozyczeniaKlienta.get(klient).add(kostium);
    }

    //ograniczenie - bag - przechowuje liste kostiumow ktore dany klient wypozyczyl
    //dok: zliczanieWypozyczen -> zmiana w celu zwiekszenia sensu nazwy metody
    public List<Kostium> metodaWypozyczeniaKlienta(Klient klient) throws Exception{
        if (!wypozyczeniaKlienta.containsKey(klient)) {
            throw new Exception("Nie ma klienta na liście");
        }
        List<Kostium> wypozyczoneKosiumy = wypozyczeniaKlienta.get(klient);
        return wypozyczoneKosiumy;
    }

    //ekstenja i jej trwałość- zapis, odczyt z pliku

    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Wypozyczenie.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja wypozyczenia: " + theClass.getSimpleName());
        List<Wypozyczenie> ekstensja = Wypozyczenie.ewszytskieWypozyczenia;
        for (Wypozyczenie wypozyczenie : ekstensja) {
            System.out.println(wypozyczenie.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(ewszytskieWypozyczenia);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        ewszytskieWypozyczenia = (List<Wypozyczenie>) stream.readObject();
        System.out.println("Odczytana lista wszytskich wypozyczen z metody czytajEkstensje()");
        for (Wypozyczenie wypozyczenie : ewszytskieWypozyczenia) {
            System.out.println(wypozyczenie.toString());
        }
    }

    @Override
    public String toString(){
        return String.valueOf(numer + " "+
                numerSprzedawcy + " "+
                koszt + " "+
                czasWypożyczenia) + " ";
//                kostium.getNazwa()+" "+
//                klientWypozyczalni.getDaneOsobowe();
    }


}
