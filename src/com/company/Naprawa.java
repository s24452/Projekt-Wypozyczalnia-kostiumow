package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Naprawa {
    //asocjacja miedzy Krawcem a Kostiumem
    Pracownik pracownik;
    Kostium kostium;

    //atrybuty
    String odKiedy;
    String doKiedy;
    String opis; //krótki

    //ekstencja
    public static List<Naprawa> wszytskieNaprawy =new ArrayList<>();


    //kostruktor
    public  Naprawa(String odKiedy, String doKiedy, String opis,Pracownik pracownik,Kostium kostium){
        this.odKiedy=odKiedy;
        this.doKiedy=doKiedy;
        this.opis=opis;
        pracownik.dodajNaprawe(this);
        kostium.dodajNaprawe(this);
    }

    //ekstenja i jej trwałość- zapis, odczyt z pliku
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Naprawa.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja napraw: " + theClass.getSimpleName());
        List<Naprawa> ekstensja = Naprawa.wszytskieNaprawy;
        for (Naprawa naprawa : ekstensja) {
            System.out.println(naprawa.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wszytskieNaprawy);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wszytskieNaprawy = (List<Naprawa>) stream.readObject();
        System.out.println("Odczytana lista wszytskich wypozyczen- czytajEkstensje()");
        for (Naprawa naprawa : wszytskieNaprawy) {
            System.out.println(naprawa.toString());
        }
    }
    //metoda toString()
    @Override
    public String toString() {
        return "Naprawa: \nOd kiedy: "+odKiedy+"\nDo kiedy: "+doKiedy+"Opis: "+opis;
    }
}
