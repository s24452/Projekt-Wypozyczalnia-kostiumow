package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Kostium implements Serializable {
    //atrybuty
    String nazwa;
    String material;
    String trwaloscMaterialu;
    int rokProdukcji;
    String opis;

    //asocjajcja z atrybutem (Klient,Kostium, k.a Wypozyczenie)
    List<Wypozyczenie> wypożyczenia;
    //ograniczenie - ordered

    //asocjacja (Pracownik,Kostium, k.a Naprawa)
    List<Naprawa> naprawy;//brak w dok

    //kompozycja Kositium-całość, Dodatek-część
    private Vector<Dodatek> dodatki=new Vector<Dodatek>();

    //ekstencja
    public static List<Kostium> wszystkieKostiumy = new ArrayList<>();
    private static int kostiumy = 0;


    //konstruktory
    public Kostium(String nazwa,String material,int rokProdukcji,String opis){
        this.nazwa=nazwa;
        this.material=material;
        this.rokProdukcji=rokProdukcji;
        this.opis=opis;
        this.wypożyczenia=new ArrayList<>();
        wszystkieKostiumy.add(this);
    }

    public Kostium(String nazwa,String material,int rokProdukcji,String opis,Wypozyczenie wypożyczenie){
        this.nazwa=nazwa;
        this.material=material;
        this.rokProdukcji=rokProdukcji;
        this.opis=opis;
        this.wypożyczenia.add(wypożyczenie);//asocjacja z atrybutem
        this.wypożyczenia=new ArrayList<>();    //ograniczenie - ordered ArrayList-zapewnia kolejnosc
        wszystkieKostiumy.add(this);
    }
    public Kostium(String nazwa,String material,int rokProdukcji,String opis,Naprawa naprawa){
        this.nazwa=nazwa;
        this.material=material;
        this.rokProdukcji=rokProdukcji;
        this.opis=opis;
        this.naprawy.add(naprawa);
        wszystkieKostiumy.add(this);
    }

    //asocjajcja z atrybutem
    //metoda taka jak w Klient
    public void dodajWypozyczenie(Wypozyczenie noweWypozyczenie){
        if(!wypożyczenia.contains(noweWypozyczenie)){
            wypożyczenia.add(noweWypozyczenie);
        }
        wypożyczenia.add(noweWypozyczenie);
    }

    //asocjacja (Pracownik,Kostium, k.a Naprawa)
    public void dodajNaprawe(Naprawa nowaNaprawa){
        if(!naprawy.contains(nowaNaprawa)){
            naprawy.add(nowaNaprawa);
        }
    }

    //przeciązenie metody- ta sama nazwa, ale róznica w parametrach (typ,ilość)
    public String obliczTrwalosc(String material){
        switch (material){
            case "poliester":
                trwaloscMaterialu="średnio trwały";
                break;
            case "bawełna":
                trwaloscMaterialu="trwały";
                break;
            case "len":
                trwaloscMaterialu="bardzo trwały";
                break;
            default:
                System.out.println("nie ma takiego materiału");
        }
        return trwaloscMaterialu;
    }
    public String obliczTrwalosc(String material,int rokProdukcji){
        if(rokProdukcji>=2015){
            trwaloscMaterialu="trwały";
        }else if(rokProdukcji<2015 && rokProdukcji>=2000){
            if(material=="poliester"){
                trwaloscMaterialu="nie trwały";
            }else if(material=="bawełna"){
                trwaloscMaterialu="trwały";
            }else if(material=="len"){
                trwaloscMaterialu="bardzo trwały";
            }
        }else{
            if(material=="poliester"){
                trwaloscMaterialu="nie trwały";
            }else if(material=="bawełna"){
                trwaloscMaterialu="śrdenio trwały";
            }else if(material=="len"){
                trwaloscMaterialu="trwały";
            }
        }
        return trwaloscMaterialu;
    }

    //kompozycja
    public void dodajDodatek(Dodatek dodatek){
        if(!dodatki.contains(dodatek)){
            dodatki.add(dodatek);
        }
    }
    public  void usunDodatek(Dodatek dodatek){
        if (dodatki.contains(dodatek)) {
            dodatki.remove(dodatek);
        }
    }

    public static void dodajKostium (Kostium kostium){
        if (!wszystkieKostiumy.contains(kostium)) {
           wszystkieKostiumy.add(kostium);
        }
    }

    public static void usunKostium(Kostium kostium ){
        if (wszystkieKostiumy.contains(kostium)) {
            wszystkieKostiumy.remove(kostium);
        }
    }

    //ekstenja i jej trwałość- zapis, odczyt z pliku
    public static void pokazEkstensje () throws Exception {
        pokazEkstensje(Kostium.class);
    }
    public static void pokazEkstensje (Class<?> theClass) throws Exception {
        System.out.println("Ekstencja kostiumu:: " + theClass.getSimpleName());
        List<Kostium> ekstensja = Kostium.wszystkieKostiumy;
        for (Kostium k : ekstensja) {
            System.out.println(k.toString());
        }
    }
    public static void piszEkstensja (ObjectOutputStream stream) throws IOException {
        stream.writeObject(wszystkieKostiumy);
    }

    public static void czytajEkstensje (ObjectInputStream stream) throws IOException, ClassNotFoundException {
        wszystkieKostiumy = (List<Kostium>) stream.readObject();
        System.out.println("Odczytana lista wszytskich kostiumow- czytajEkstensje()");
        for (Kostium kostium : wszystkieKostiumy) {
            System.out.println(kostium.toString());
        }
    }
    //metoda toString
    @Override
    public String toString() {
        return  nazwa+ ' ' +
                material+ ' ' +
                obliczTrwalosc(material)+' '+
                rokProdukcji+' '+
                opis+' ';
    }
//    @Override
//    public String toString(){
//        String textDodatki="\ndodatki do kostiumu: ";
//        for(Dodatek d:dodatki){
//            textDodatki+=d.nazwa+", ";
//        }
//        return "Kostium\n"+"Nazwa: "+nazwa+"\nMateriał: "+material+"\nTrwałość materiału: "+trwaloscMaterialu+"\nRok produkcji: "+rokProdukcji+"\nOpis: "+opis+textDodatki;
//    }

    //gettery i settery
    public String getNazwa() {
        return nazwa;
    }

    public String getMaterial() {
        return material;
    }

    public String getTrwaloscMaterialu() {
        return trwaloscMaterialu;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public String getOpis() {
        return opis;
    }

}
