package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wypozyczalnia implements Serializable {
    //atrybuty
    int idWypozyczalni;
    String nazwa;
    String adres;

    //atrybut powtarzalny-posiada jedną lub wiecej wartości
    ArrayList<Integer> numerTelefonu=new ArrayList<Integer>();
    String nt=zmianaNaNumer(numerTelefonu);

    //atrybuty klasowe-ma tę samą wartość dla wszytskich obiektów danej klasy
    static int godzinaOtwarcia;
    static int godzinaZamkniecia;


    //asocjacja zwykła z Klient 1 do wielu
    List<Klient> klienci =new ArrayList<>();

    //asocjajcja z atrybutem (Klient,Wypożyczalnia, k.a KlientWypożyczalni)
    ArrayList<KlientWypozyczalni>klienciWypozyczalni;

    //asocjacja kwlifikowana
    private ArrayList<Pracownik> pracownicy=new ArrayList<Pracownik>();

    //ekstencja
    public static List<Wypozyczalnia> wypozyczalnia =new ArrayList<>();

    //konstruktor
    public Wypozyczalnia(int idWypozyczalni, String nazwa, String adres, ArrayList<Integer> numerTelefonu, int godzinaOtwarcia, int godzinaZamkniecia){
        this.idWypozyczalni=idWypozyczalni;
        this.nazwa=nazwa;
        this.adres=adres;
        this.numerTelefonu=numerTelefonu;
        Wypozyczalnia.godzinaOtwarcia=10;
        Wypozyczalnia.godzinaZamkniecia=20;
        this.klienciWypozyczalni=new ArrayList<>();//PRZYPADEK UŻYCIA - GUI
        wypozyczalnia.add(this); //ekstencja
    }
    //PRZYPADEK UŻYCIA - GUI
    public List<KlientWypozyczalni> getKlienciWypozyczalni() {
        return klienciWypozyczalni;
    }
    //asocjajca Klient -KlientWypozyczalni-Wypozyczalnia
    //metoda taka sama jak w wypozyczalni
    public void dodajKlientaWypozyczalni (KlientWypozyczalni nowyKlientWypozyczalni){
        if (!klienciWypozyczalni.contains(nowyKlientWypozyczalni)) {
            klienciWypozyczalni.add(nowyKlientWypozyczalni);

            nowyKlientWypozyczalni.setWypożyczalnia(this);
        }
    }

    //asocjajcja kalifikowana
    public void dodajPracownika(Pracownik nowyPracownik){
        if(!pracownicy.contains(nowyPracownik)){
            pracownicy.add(nowyPracownik);
            nowyPracownik.dodajWypozyczalniaKwalifikator(this);
        }
    }
    //asocjacja zwykła z Klient 1 do wielu
    public void dodajKlient(Klient nowyKlient){
        if(!klienci.contains(nowyKlient)){
            klienci.add(nowyKlient);

            nowyKlient.setWypożyczalnia(this);
        }
    }


    //metoda do atrybutu numerTelefonu
    public String zmianaNaNumer(ArrayList<Integer> numerTelefonu){
        StringBuilder sb=new StringBuilder();
        for(Integer i:numerTelefonu){
            sb.append(i).append("");
        }
        return sb.toString();
    }
    public void usunKlienta(Klient klient) {
        if (klienci.contains(klient)) {
            klienci.remove(klient);
        }
    }


    //ekstenja i jej trwałość- zapis, odczyt z pliku
    public static void dodajWypozyczalnie (Wypozyczalnia nowaWypozyczalnia){
        if (!wypozyczalnia.contains(nowaWypozyczalnia)) {
            wypozyczalnia.add(nowaWypozyczalnia);
        }
    }

    public static void usunWypozyczalnie (Wypozyczalnia w){
        if (wypozyczalnia.contains(w)) {
            wypozyczalnia.remove(w);
        }
    }
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Wypozyczalnia.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja wypozyczalni: " + theClass.getSimpleName());
        List<Wypozyczalnia> ekstensja = Wypozyczalnia.wypozyczalnia;
        for (Wypozyczalnia w : ekstensja) {
            System.out.println(w.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wypozyczalnia);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wypozyczalnia = (List<Wypozyczalnia>) stream.readObject();
        System.out.println("Odczytana lista wszytskich wypożyczalni - czytajEkstensje()");
        for (Wypozyczalnia w : wypozyczalnia) {
            System.out.println(w.toString());
        }
    }

    //metoda toString()
    //przysłonięcie metody- ta sama sygnatura(nazwa,typ,ilosc srgumentów)
    @Override
    public String toString(){
        return nazwa ;
    }


}
