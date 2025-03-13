package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Klient  extends Osoba implements Serializable {
    //asocjacja zwykła z Wypozyczalnia 1 do wielu
    Wypozyczalnia wypozyczalnia;

    //asocjajcja z atrybutem (Klient,Kostium, k.a Wypozyczenie)
    ArrayList<Wypozyczenie> wypożyczenia;

    //asocjajcja z atrybutem (Klient,Wypożyczalnia, k.a KlientWypożyczalni)
    ArrayList<KlientWypozyczalni> klienciWypozyczalni;//brak w dok

    //ekstencja
    public static List<Klient> wszyscyKlienci = new ArrayList<>();

    //konstruktory
    public Klient(DaneOsobowe daneOsobowe){
        super(daneOsobowe);
        wszyscyKlienci.add(this);
        this.klienciWypozyczalni=new ArrayList<>();
    }

    public Klient(DaneOsobowe daneOsobowe, Wypozyczalnia wypozyczalnia){
        super(daneOsobowe);
        this.wypożyczenia=new ArrayList<>();//ograniczenie - ordered
        this.klienciWypozyczalni=new ArrayList<>();
        setWypożyczalnia(wypozyczalnia);//asocjacja zwykła
    }

        //asocjacja zwykła
        public void setWypożyczalnia (Wypozyczalnia wypozyczalnia){
            this.wypozyczalnia = wypozyczalnia;
            wypozyczalnia.dodajKlient(this);
        }

        //asocjajcja z atrybutem
        //metoda taka jak w Kostium
        public void dodajWypozyczenie (Wypozyczenie noweWypozyczenie){
            if (!wypożyczenia.contains(noweWypozyczenie)) {
                wypożyczenia.add(noweWypozyczenie);
            }
        }

        //asocjajca Klient -KlientWypozyczalni-Wypozyczalnia
       //metoda taka sama jak w Wypozyczalni
        public void dodajKlientaWypozyczalni (KlientWypozyczalni nowyKlientWypozyczalni){
            if (!klienciWypozyczalni.contains(nowyKlientWypozyczalni)) {
                klienciWypozyczalni.add(nowyKlientWypozyczalni);
            }
        }

    //ekstenja i jej trwałość- zapis, odczyt z pliku
        public static void dodajKlienta (Klient klient){
            if (!wszyscyKlienci.contains(klient)) {
                wszyscyKlienci.add(klient);
            }
        }

        public static void usunKlienta (Klient klient ){
            if (wszyscyKlienci.contains(klient)) {
                wszyscyKlienci.remove(klient);
            }
        }
        public static void pokazEkstensje () throws Exception {
            pokazEkstensje(Klient.class);
        }
        public static void pokazEkstensje (Class<?> theClass) throws Exception {
            System.out.println("Ekstencja klient:: " + theClass.getSimpleName());
            List<Klient> ekstensja = Klient.wszyscyKlienci;
            for (Klient k : ekstensja) {
                System.out.println(k.toString());
            }
        }
        public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
            stream.writeObject(wszyscyKlienci);
        }

        public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
            wszyscyKlienci = (List<Klient>) stream.readObject();
            System.out.println("Odczytana lista wszytskich klientow - czytajEkstensje()");
            for (Klient klient : wszyscyKlienci) {
                System.out.println(klient.toString());
            }
        }
    //metoda toString
    @Override
    public String toString() {
        return  daneOsobowe.getImie() + ' ' +
                daneOsobowe.getNazwisko() + ' ' ;
    }

        //gettery i settery
        public DaneOsobowe getDaneOsobowe () {
            return daneOsobowe;
        }

        public void setDaneOsobowe (DaneOsobowe daneOsobowe){
            this.daneOsobowe = daneOsobowe;
        }

    }

