package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dodatek implements Serializable {
    //atrybuty
    String nazwa;
    String tworzywo;
    String trwaloscDodatku;

    //kompozycja Kositium-całość, Dodatek-część
    private Kostium kostium;

    //ekstencja
    static List<Dodatek> wszystkieDodatki =new ArrayList<>();

    //konstruktor
    Dodatek(Kostium kostium, String nazwa, String tworzywo){
        this.kostium=kostium;
        this.nazwa=nazwa;
        this.tworzywo=tworzywo;
        wszystkieDodatki.add(this);
    }

    //kompozycja z Kostiumem
    public static Dodatek utworzDodatek(Kostium kostium,String nazwa,String tworzywo) throws Exception{
        if(kostium==null){
            throw new Exception("Kostium nie istnieje");
        }
        Dodatek d=new Dodatek(kostium, nazwa, tworzywo);
        kostium.dodajDodatek(d);

        return d;

    }

    //metoda
    public String obliczTrwalosc(String tworzywo){
        switch (tworzywo){
            case "material":
                trwaloscDodatku = "dość trwały";
                break;
            case "plastik":
                trwaloscDodatku ="średnio trwały";
                break;
            case "drewno":
                trwaloscDodatku ="trwały";
                break;
            case "metal":
                trwaloscDodatku ="bardzo trwały";
                break;
            default:
                System.out.println("nie ma takiego materiału");
        }
        return trwaloscDodatku;
    }

    //ekstenja i jej trwałość- zapis, odczyt z pliku
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Dodatek.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja dodatek:: " + theClass.getSimpleName());
        List<Dodatek> ekstensja = Dodatek.wszystkieDodatki;
        for (Dodatek d : ekstensja) {
            System.out.println(d.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wszystkieDodatki);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wszystkieDodatki = (List<Dodatek>) stream.readObject();
        System.out.println("Odczytana lista wszytskich klientow - czytajEkstensje()");
        for (Dodatek dodatek : wszystkieDodatki) {
            System.out.println(dodatek.toString());
        }
    }

    //metoda toString()
    @Override
    public String toString(){
        return "Dodatek: "+"\nnazwa: "+nazwa+"\ntworzywo: "+tworzywo+"\ntrwałość: "+trwaloscDodatku;
    }

}
