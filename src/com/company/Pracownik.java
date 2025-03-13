package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

enum PracownikStanowisko{Pracownik,Krawiec,Sprzedawca};

public class Pracownik extends Osoba implements Serializable{
    //atrybuty
    int numer;
    int stażPracy;

    //ograniczenia atrybuty -stawka nie może zmaleć
    double minStawkaGodzinowa=35;
    private double stawkaGodzinowa;

    //atrybut opcjonalny - w metodzie toString()
    private String poprzednieMiejscePracy;

    //overlapping
    private EnumSet<PracownikStanowisko> stanowisko= EnumSet.of(PracownikStanowisko.Pracownik);
    private double bonusZaWydajnosc;
    private boolean czyBonusZaWydajnosc;
    private double bonusZaWypozyczenie;
    private boolean czyBonusZaWypozyczenie;

    //asocjajcja kalifikowana
    //kwalifikator-atrybut(lub kombinacja atrybutów) umozliwiajacy jednoznaczne zidentyfikowanie obieku docelowego
    private Map<Integer, Wypozyczalnia> wypozyczalniaKwalifikator =new TreeMap<>();

    //asocjacja (Pracownik,Kostium, k.a Naprawa)
    List<Naprawa> naprawy;
    // ekstensja
    public static List<Pracownik> wszyscyPracownicy = new ArrayList<>();


    //konstruktory
    public Pracownik(DaneOsobowe daneOsobowe,int numer,int stażPracy, double stawkaGodzinowa, String poprzednieMiejscePracy) throws Exception {
        super(daneOsobowe);
        this.numer=numer;
        this.stażPracy=stażPracy;
        setStawkaGodzinowa(stawkaGodzinowa);
        this.poprzednieMiejscePracy=poprzednieMiejscePracy;
        wszyscyPracownicy.add(this);
    }
    public Pracownik(DaneOsobowe daneOsobowe,int numer,int stażPracy, double stawkaGodzinowa, String poprzednieMiejscePracy,Naprawa naprawa) throws Exception {
        super(daneOsobowe);
        this.numer=numer;
        this.stażPracy=stażPracy;
        setStawkaGodzinowa(stawkaGodzinowa);
        this.poprzednieMiejscePracy=poprzednieMiejscePracy;
        this.naprawy.add(naprawa);
        wszyscyPracownicy.add(this);
    }


    //ograniczenia atrybutu
    public void setStawkaGodzinowa(double stawkaGodzinowa) throws Exception{
        if(stawkaGodzinowa<this.minStawkaGodzinowa){
            throw new Exception("Stawka godzinowa nie może maleć");
        }
        this.stawkaGodzinowa = stawkaGodzinowa;
    }

    //atrybut pochodny-atrybut, który jest wyliczany za pomocą metody
    public int obliczWiek(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(daneOsobowe.dataUrodzenia);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdfRok = new SimpleDateFormat("yyyy");
        int rokUrodzenia = Integer.parseInt(sdfRok.format(date));

        int rok=2024;
        int wiek=rok- rokUrodzenia;

        return wiek;
    }
    //metoda klasowa
    public static void najdluzszyStaż(ArrayList<Pracownik> wszyscyPracownicy){
        int maxStaż=0;
        Pracownik pracownikZMaxStazem = null;
        for (Pracownik pracownik:wszyscyPracownicy) {
            if(pracownik.stażPracy>maxStaż){
                maxStaż= pracownik.stażPracy;
                pracownikZMaxStazem=pracownik;
            }
        }
        System.out.println("Nadłuży staż: "+pracownikZMaxStazem.getDaneOsobowe());
    }
    public double getPremia() {
        if (stanowisko.contains(PracownikStanowisko.Krawiec)) {
            return bonusZaWydajnosc;
        } else if (stanowisko.contains(PracownikStanowisko.Sprzedawca)) {
            return bonusZaWypozyczenie;
        } else {
            return 0;
        }
    }

    //overlapping
    public boolean czyDostajeBonusZaWydajosc() throws Exception{
        if(stanowisko.contains(PracownikStanowisko.Krawiec)){
            return czyBonusZaWydajnosc;
        }
        throw new Exception("Sprzedawca nie moze dostac bonusu za wydajnosc");
    }
    public boolean czyDostajeBonusZaWpozyczenie() throws Exception{
        if(stanowisko.contains(PracownikStanowisko.Sprzedawca)){
            return czyBonusZaWypozyczenie;
        }
        throw new Exception("Krawiec nie moze dostac bonusu za wypożyczenie");
    }

    //asocjajcja kalifikowana
    public void dodajWypozyczalniaKwalifikator(Wypozyczalnia nowaWypozyczalnia){
        if(!wypozyczalniaKwalifikator.containsKey(nowaWypozyczalnia.idWypozyczalni)){
            wypozyczalniaKwalifikator.put(nowaWypozyczalnia.idWypozyczalni,nowaWypozyczalnia);
            nowaWypozyczalnia.dodajPracownika(this);
        }
    }
    public Wypozyczalnia znajdzWypozyczalniaKwalifikator(int idWypozyczalni) throws Exception{
        if(!wypozyczalniaKwalifikator.containsKey(idWypozyczalni)){
            throw new Exception("Nie znaleziono wypożyczalni o id: "+ idWypozyczalni);
        }
        return wypozyczalniaKwalifikator.get(idWypozyczalni);
    }


    public static void usunPracownika(final Pracownik pracownik) {
        if (wszyscyPracownicy.contains(pracownik)) {
            wszyscyPracownicy.remove(pracownik);
        }
    }

    //asocjacja (Pracownik,Kostium, k.a Naprawa)
    public void dodajNaprawe(Naprawa nowaNaprawa){
        if(!naprawy.contains(nowaNaprawa)){
            naprawy.add(nowaNaprawa);
        }
    }

    //ekstensja-trwałość
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Pracownik.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja klient:: " + theClass.getSimpleName());
        List<Klient> ekstensja = Klient.wszyscyKlienci;
        for (Klient k : ekstensja) {
            System.out.println(k.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wszyscyPracownicy);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wszyscyPracownicy = (List<Pracownik>) stream.readObject();
        System.out.println("Odczytana lista wszytskich pracowników z metody czytajEkstensje()");
        for (Pracownik pracownik : wszyscyPracownicy) {
            System.out.println(pracownik.toString());
        }
    }

    //atrybut opcjonalny
    //metoda toString()
    @Override
    public String toString() {
        return daneOsobowe+"\nWiek: "+obliczWiek()+"\nStaż pracy(mierzony w latach): "+stażPracy+"\nStawka godzinowa: "+stawkaGodzinowa+"\nPoprzednie miejsce pracy: "+((poprzednieMiejscePracy == null) ? "brak poprzedniego miejsca pracy " : poprzednieMiejscePracy);
    }

    //gettery i settery
    public DaneOsobowe getDaneOsobowe() {
        return daneOsobowe;
    }

    public void setDaneOsobowe(DaneOsobowe daneOsobowe) {
        this.daneOsobowe = daneOsobowe;
    }

    public int getNumer() {
        return numer;
    }

    public int getStażPracy() {
        return stażPracy;
    }

    public void setStażPracy(int stażPracy) {
        this.stażPracy = stażPracy;
    }

    public double getStawkaGodzinowa() {
        return stawkaGodzinowa;
    }


    public String getPoprzednieMiejscePracy() {
        return poprzednieMiejscePracy;
    }

    public void setPoprzednieMiejscePracy(String poprzednieMiejscePracy) {
        this.poprzednieMiejscePracy = poprzednieMiejscePracy;
    }

}
