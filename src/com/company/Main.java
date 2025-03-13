package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static KlientWypozyczalni wybranyKlientWypozyczalni;
    static Kostium wybranyKostium;
    static int numer;
    static double koszt=150;
    static int numerWypozyczenia=1;


    public static void main(String[] args) {
        ArrayList<Integer> numerTel=new ArrayList<>();
        for (int i = 0; i <9 ; i++) {
            numerTel.add((i%2==0)? 5:i);
        }
        Wypozyczalnia wypozyczalniaSuperK =new Wypozyczalnia(001,"SuperK","Aleje Jerozolimskie 80",numerTel,0,0);
        Wypozyczalnia wypozyczalniaHero =new Wypozyczalnia(002,"Hero","Marszałkowska 67",numerTel,0,0);
        Wypozyczalnia wypozyczalniaKrasnoludek =new Wypozyczalnia(003,"Krasnoludek","Polna 13",numerTel,0,0);

        DaneOsobowe dOk1 = new DaneOsobowe("Marzena", "Dębek", "1980-10-10");
        DaneOsobowe dOk2 = new DaneOsobowe("Klaudia", "Nowak", "1989-06-09");
        DaneOsobowe dOk3 = new DaneOsobowe("Stanisław", "Mazur", "1965-10-03");
        DaneOsobowe dOk4 = new DaneOsobowe("Patryk", "Kowalski", "1987-01-22");
        DaneOsobowe dOk5 = new DaneOsobowe("Barbara", "Wesołowska", "1995-07-12");
        DaneOsobowe dOk6 = new DaneOsobowe("Kinga", "Wieczorek", "2000-04-04");
        DaneOsobowe dOk7 = new DaneOsobowe("Mariusz", "Kozłowski", "1977-08-12");
        DaneOsobowe dOk8 = new DaneOsobowe("Wiktor", "Mazurek", "2002-03-22");
        DaneOsobowe dOk9 = new DaneOsobowe("Bartosz", "Szymański", "2001-07-12");
        DaneOsobowe dOk10 = new DaneOsobowe("Dawid", "Włoch", "1988-01-11");

        Klient k1 = new Klient(dOk1);
        Klient k2 = new Klient(dOk2);
        Klient k3 = new Klient(dOk3);
        Klient k4 = new Klient(dOk4);
        Klient k5 = new Klient(dOk5);
        Klient k6 = new Klient(dOk6);
        Klient k7 = new Klient(dOk7);
        Klient k8 = new Klient(dOk8);
        Klient k9 = new Klient(dOk9);
        Klient k10 = new Klient(dOk10);

        wypozyczalniaSuperK.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk1,k1, wypozyczalniaSuperK));
        wypozyczalniaSuperK.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk2,k2, wypozyczalniaSuperK));
        wypozyczalniaSuperK.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk4,k4, wypozyczalniaSuperK));
        wypozyczalniaSuperK.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk5,k5, wypozyczalniaSuperK));

        wypozyczalniaHero.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk3,k3, wypozyczalniaHero));
        wypozyczalniaHero.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk6,k6, wypozyczalniaHero));
        wypozyczalniaHero.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk7,k7, wypozyczalniaHero));

        wypozyczalniaKrasnoludek.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk8,k8, wypozyczalniaKrasnoludek));
        wypozyczalniaKrasnoludek.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk9,k9, wypozyczalniaKrasnoludek));
        wypozyczalniaKrasnoludek.dodajKlientaWypozyczalni(new KlientWypozyczalni(dOk10,k10, wypozyczalniaKrasnoludek));

        DefaultListModel<Wypozyczalnia> wypozyczalniaModel = new DefaultListModel<>();
        wypozyczalniaModel.addElement(wypozyczalniaSuperK);
        wypozyczalniaModel.addElement(wypozyczalniaHero);
        wypozyczalniaModel.addElement(wypozyczalniaKrasnoludek);

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("klienciWypozyczalni.txt"));
            KlientWypozyczalni.piszEkstensja(outputStream);
            outputStream.close();

            KlientWypozyczalni.wszyscyKlienciWypozyczalni.clear();

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("klienciWypozyczalni.txt"));
            KlientWypozyczalni.czytajEkstensje(inputStream);
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Kostium spiderman=new Kostium("SpiderMan","poliester",2019,"-brak-");
        Kostium superman=new Kostium("SuperMan","poliester",2021,"-brak-");
        Kostium ksiezniczka=new Kostium("Księżniczka","bawełna",2020,"rózowa");
        Kostium czarownica=new Kostium("Czarownica","len",2022,"czarno-fioletowa");


        try {
            Kostium.pokazEkstensje();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("kostiumy.txt"));
            Kostium.piszEkstensja(outputStream);
            outputStream.close();

            Kostium.wszystkieKostiumy.clear();

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("kostiumy.txt"));
            Kostium.czytajEkstensje(inputStream);
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("------------GUI---------------");
        SwingUtilities.invokeLater(() -> przypadekWypozyczanieKostiumu(wypozyczalniaModel));
    }

    private static void przypadekWypozyczanieKostiumu(DefaultListModel<Wypozyczalnia> wypozyczalniaModel) {
        JFrame frame = new JFrame("Wypożyczanie kostiumu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int wysokosc=500;
        int szerokosc=700;
        frame.setSize(szerokosc,wysokosc);
        frame.setLayout(new GridLayout(1,2));


        // Główny panel
        JPanel mainPanel = new JPanel(new CardLayout());

        //-----------STRONA1--------------
        // Tworzenie pierwszego panelu (pierwsza strona)
        // nagłówek
        JPanel naglowek = new JPanel();
        naglowek.setBackground(new Color(73, 159, 195));
        JLabel przywitanie = new JLabel("Witaj w wypożyczaniu kostiumu!");
        przywitanie.setHorizontalAlignment(SwingConstants.CENTER);
        przywitanie.setFont(new Font("Verdana", Font.BOLD, 30));
        przywitanie.setForeground(Color.WHITE);
        naglowek.setLayout(new BorderLayout());
        naglowek.add(przywitanie, BorderLayout.CENTER);

        JPanel stronaP = new JPanel();
        stronaP.setLayout(new BorderLayout());
        stronaP.setBackground(new Color(91, 90, 104));

        //środek strony
        java.net.URL imgURL = Main.class.getResource("/grafika.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);

        JLabel srodek = new JLabel(imageIcon);
        srodek.setHorizontalAlignment(SwingConstants.CENTER);
        stronaP.add(srodek, BorderLayout.CENTER);

        //dolny pasek
        JButton rozpocznijW = new JButton("Rozpocznij wypożyczanie");
        rozpocznijW.setBackground(new Color(73, 159, 195));
        rozpocznijW.setFont(new Font("Verdana", Font.BOLD, 20));
        rozpocznijW.setForeground(Color.WHITE);
        stronaP.add(rozpocznijW, BorderLayout.SOUTH);

        // Panel zawierający całą stronę pierwszą
        JPanel pierwszaStrona = new JPanel();
        pierwszaStrona.setLayout(new BorderLayout());
        pierwszaStrona.add(naglowek, BorderLayout.NORTH);
        pierwszaStrona.add(stronaP, BorderLayout.CENTER);

        //-----------STRONA2--------------
        // Tworzenie drugiego panelu (druga strona)
        // nagłówek
        JPanel naglowekD = new JPanel();
        naglowekD.setBackground(new Color(73, 159, 195));
        JLabel akcjaD = new JLabel("Wprowadzanie id");
        akcjaD.setHorizontalAlignment(SwingConstants.CENTER);
        akcjaD.setFont(new Font("Verdana", Font.BOLD, 30));
        akcjaD.setForeground(Color.WHITE);
        naglowekD.setLayout(new BorderLayout());
        naglowekD.add(akcjaD, BorderLayout.CENTER);

        JPanel stronaD = new JPanel();
        stronaD.setLayout(new BorderLayout());
        stronaD.setBackground(new Color(91, 90, 104));

        //środek strony
        JLabel komendaNumer=new JLabel("Podaj swój numer pracowniczy: ");
        komendaNumer.setFont(new Font("Verdana", Font.BOLD, 20));
        komendaNumer.setForeground(Color.WHITE);

        JTextField wprowadzanieNumeruPracownika=new JTextField();
        stronaD.add(komendaNumer,BorderLayout.WEST);
        stronaD.add(wprowadzanieNumeruPracownika,BorderLayout.CENTER);

        //dolny pasek
        JPanel przyciski2 = new JPanel(new BorderLayout());
        przyciski2.setBackground(new Color(91, 90, 104));

        JButton powrot2= new JButton("Powrót");
        powrot2.setBackground(new Color(73, 159, 195));
        powrot2.setFont(new Font("Verdana", Font.BOLD, 20));
        powrot2.setForeground(Color.WHITE);
        przyciski2.add(powrot2, BorderLayout.WEST);

        JButton dalej2 = new JButton("Przejdz do wyboru klienta");
        dalej2.setBackground(new Color(73, 159, 195));
        dalej2.setFont(new Font("Verdana", Font.BOLD, 20));
        dalej2.setForeground(Color.WHITE);
        przyciski2.add(dalej2);

        stronaD.add(przyciski2,BorderLayout.SOUTH);

        // Panel zawierający całą stronę druga
        JPanel drugaStrona = new JPanel();
        drugaStrona.setLayout(new BorderLayout());
        drugaStrona.add(naglowekD, BorderLayout.NORTH);
        drugaStrona.add(stronaD, BorderLayout.CENTER);

        //-----------STRONA3--------------
        // Tworzenie trzeciego panelu (tzrecia strona)
        //naglowek trzeciej strony
        JPanel naglowekT =new JPanel();
        naglowekT.setBackground(new Color(73, 159, 195));
        JLabel akcjaT =new JLabel("Wybór klienta z konkretnej wypożyczalni");
        akcjaT.setHorizontalAlignment(SwingConstants.CENTER);
        akcjaT.setFont(new Font("Verdana", Font.BOLD, 30));
        akcjaT.setForeground(Color.WHITE);
        naglowekT.setLayout(new BorderLayout());
        naglowekT.add(akcjaT, BorderLayout.NORTH);

        JPanel stronaT = new JPanel();
        stronaT.setLayout(new BorderLayout());
        stronaT.setBackground(new Color(91, 90, 104));

        JList<Wypozyczalnia> listaWypożyczalni = new JList<>(wypozyczalniaModel);
        listaWypożyczalni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListModel<KlientWypozyczalni> klientWypożyczalniModel = new DefaultListModel<>();
        JList<KlientWypozyczalni> klienciWypożyczalni = new JList<>(klientWypożyczalniModel);


        listaWypożyczalni.setForeground(Color.WHITE);
        listaWypożyczalni.setBackground(new Color(91, 90, 104));

        klienciWypożyczalni.setForeground(Color.WHITE);
        klienciWypożyczalni.setBackground(new Color(91, 90, 104));

        listaWypożyczalni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Wypozyczalnia wybranaWypożyczalnia = listaWypożyczalni.getSelectedValue();
                if (wybranaWypożyczalnia != null) {
                    List<KlientWypozyczalni> klienciW = wybranaWypożyczalnia.getKlienciWypozyczalni();
                    klientWypożyczalniModel.clear();
                    for (KlientWypozyczalni k : klienciW) {
                        klientWypożyczalniModel.addElement(k);
                    }
                    klienciWypożyczalni.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            if (!e.getValueIsAdjusting()) {
                                wybranyKlientWypozyczalni = klienciWypożyczalni.getSelectedValue();
                            }
                        }
                    });
                }
            }
        });

        stronaT.add(new JScrollPane(listaWypożyczalni),BorderLayout.CENTER);
        stronaT.add(new JScrollPane(klienciWypożyczalni),BorderLayout.EAST);
        //frame.setVisible(true);


        //dolny pasek
        JPanel przyciski = new JPanel(new BorderLayout());
        przyciski.setBackground(new Color(91, 90, 104));

        JButton powrot3 = new JButton("Powrót");
        powrot3.setBackground(new Color(73, 159, 195));
        powrot3.setFont(new Font("Verdana", Font.BOLD, 20));
        powrot3.setForeground(Color.WHITE);
        przyciski.add(powrot3, BorderLayout.WEST);

        JButton dalej3 = new JButton("Przejdz do wyboru kostiumu");
        dalej3.setBackground(new Color(73, 159, 195));
        dalej3.setFont(new Font("Verdana", Font.BOLD, 20));
        dalej3.setForeground(Color.WHITE);
        przyciski.add(dalej3);

        stronaT.add(przyciski,BorderLayout.SOUTH);

        // Panel zawierający całą stronę trzecią
        JPanel tzreciaStrona = new JPanel();
        tzreciaStrona.setLayout(new BorderLayout());
        tzreciaStrona.add(stronaT, BorderLayout.CENTER);
        tzreciaStrona.add(naglowekT, BorderLayout.NORTH);
        tzreciaStrona.add(stronaT, BorderLayout.CENTER);


//        //środek - dodanie listy klientów z ekstesji
//        JList<KlientWypozyczalni> listaKlientowW = new JList<>(KlientWypozyczalni.wszyscyKlienciWypozyczalni.toArray(new KlientWypozyczalni[0]));
//        listaKlientowW.setForeground(Color.WHITE);
//        listaKlientowW.setBackground(new Color(91, 90, 104));
//        JLabel opisKolumnKlient=new JLabel("Imie Nazwisko");
//        opisKolumnKlient.setFont(new Font("Verdana", Font.BOLD, 20));
//        opisKolumnKlient.setForeground(Color.WHITE);
//
//        //zapamietanie co wybral uzytkownik za pomocą  ListSelectionListener
//        listaKlientowW.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                   wybranyKlientWypozyczalni = listaKlientowW.getSelectedValue();
//                }
//            }
//        });
//

        //-----------STRONA4--------------
        //Tworzenie czwartej strony
        // nagłówek
        JPanel naglowekCZ = new JPanel();
        naglowekCZ.setBackground(new Color(73, 159, 195));
        JLabel akcjaCZ = new JLabel("Wybór kostiumu");
        akcjaCZ.setHorizontalAlignment(SwingConstants.CENTER);
        akcjaCZ.setFont(new Font("Verdana", Font.BOLD, 30));
        akcjaCZ.setForeground(Color.WHITE);
        naglowekCZ.setLayout(new BorderLayout());
        naglowekCZ.add(akcjaCZ, BorderLayout.CENTER);

        JPanel stronaCZ = new JPanel();
        stronaCZ.setLayout(new BorderLayout());
        stronaCZ.setBackground(new Color(91, 90, 104));


        //środek - dodanie listy kostiumów z ekstesji
        JList<Kostium> listaKo = new JList<>(Kostium.wszystkieKostiumy.toArray(new Kostium[0]));
        listaKo.setForeground(Color.WHITE);
        listaKo.setBackground(new Color(91, 90, 104));

        JLabel opisKolumnKostium =new JLabel("Nazwa  Materiał  Trałość-materiału Rok-produkcji  Opis");
        opisKolumnKostium.setFont(new Font("Verdana", Font.BOLD, 20));
        opisKolumnKostium.setForeground(Color.WHITE);

        //zapamietanie co wybral uzytkownik za pomocą  ListSelectionListener
        listaKo.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    wybranyKostium = listaKo.getSelectedValue();
                }
            }
        });

        JScrollPane scrollPane2 = new JScrollPane(listaKo);
        stronaCZ.add(opisKolumnKostium,BorderLayout.NORTH);
        stronaCZ.add(scrollPane2, BorderLayout.CENTER);

        //dolny pasek
        JPanel przyciski4 = new JPanel(new BorderLayout());
        przyciski4.setBackground(new Color(91, 90, 104));

        JButton powrot4 = new JButton("Powrót");
        powrot4.setBackground(new Color(73, 159, 195));
        powrot4.setFont(new Font("Verdana", Font.BOLD, 20));
        powrot4.setForeground(Color.WHITE);
        przyciski4.add(powrot4, BorderLayout.WEST);

        JButton dalej4 = new JButton("Przejdz do podsumowania");
        dalej4.setBackground(new Color(73, 159, 195));
        dalej4.setFont(new Font("Verdana", Font.BOLD, 20));
        dalej4.setForeground(Color.WHITE);
        przyciski4.add(dalej4);

        stronaCZ.add(przyciski4,BorderLayout.SOUTH);

        // Panel zawierający całą stronę czwartą
        JPanel czwartaStrona = new JPanel();
        czwartaStrona.setLayout(new BorderLayout());
        czwartaStrona.add(naglowekCZ, BorderLayout.NORTH);
        czwartaStrona.add(stronaCZ, BorderLayout.CENTER);

////        //-----------STRONA5--------------
////        //Tworzenie piatej strony
////        // nagłówek
////        JPanel naglowekPi = new JPanel();
////        naglowekPi.setBackground(new Color(73, 159, 195));
////        JLabel akcjaPi = new JLabel("Wybór dodatku do kostiumu");
////        akcjaPi.setHorizontalAlignment(SwingConstants.CENTER);
////        akcjaPi.setFont(new Font("Verdana", Font.BOLD, 30));
////        akcjaPi.setForeground(Color.WHITE);
////        naglowekPi.setLayout(new BorderLayout());
////        naglowekPi.add(akcjaPi, BorderLayout.CENTER);
////
////        JPanel stronaPi = new JPanel();
////        stronaPi.setLayout(new BorderLayout());
////        stronaPi.setBackground(new Color(91, 90, 104));


        //-----------STRONA6--------------
        //Tworzenie szostej strony
        // nagłówek
        JPanel naglowekSz = new JPanel();
        naglowekSz.setBackground(new Color(73, 159, 195));
        JLabel akcjaSz = new JLabel("Zatwierdzanie wypożyczenia");
        akcjaSz.setHorizontalAlignment(SwingConstants.CENTER);
        akcjaSz.setFont(new Font("Verdana", Font.BOLD, 30));
        akcjaSz.setForeground(Color.WHITE);
        naglowekSz.setLayout(new BorderLayout());
        naglowekSz.add(akcjaSz, BorderLayout.CENTER);

        JPanel stronaSz = new JPanel();
        stronaSz.setLayout(new BorderLayout());
        stronaSz.setBackground(new Color(91, 90, 104));

        //srodek
        JLabel daneWypozyczenia=new JLabel("Dane wypozyczenia :");
        daneWypozyczenia.setFont(new Font("Verdana", Font.BOLD, 30));
        daneWypozyczenia.setForeground(Color.WHITE);


        stronaSz.add(daneWypozyczenia,BorderLayout.NORTH);
        JButton info = new JButton("Pokaż");
        info.setBackground(new Color(91, 90, 104));
        info.setFont(new Font("Verdana", Font.BOLD, 20));
        info.setForeground(Color.WHITE);
        stronaSz.add(info,BorderLayout.WEST);

        JTextArea daneW = new JTextArea();
        JScrollPane jScrollPane=new JScrollPane(daneW);
        daneW.setFont(new Font("Verdana", Font.BOLD, 20));
        daneW.setForeground(Color.WHITE);
        daneW.setBackground(new Color(91, 90, 104));
        stronaSz.add(jScrollPane,BorderLayout.EAST);

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numerT = wprowadzanieNumeruPracownika.getText();
                try {
                    numer = Integer.parseInt(numerT);
                } catch (NumberFormatException ne) {
                    System.out.println("Wprowadź numer pracownika");
                    return;
                }
                try {
                    daneW.append("Numer wypożyczenia: 1\n");
                    daneW.append("Sprzedawca: " + numer+ "\n");
                    daneW.append("Koszt: "+koszt+"zł\n");
                    daneW.append("Klient: " + wybranyKlientWypozyczalni + "\n");
                    daneW.append("Kostium: " +wybranyKostium + "\n");

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });


        //dolny pasek
        JPanel przyciski6 = new JPanel(new BorderLayout());
        przyciski6.setBackground(new Color(91, 90, 104));

        JButton powrot6 = new JButton("Powrót");
        powrot6.setBackground(new Color(73, 159, 195));
        powrot6.setFont(new Font("Verdana", Font.BOLD, 20));
        powrot6.setForeground(Color.WHITE);
        przyciski6.add(powrot6, BorderLayout.WEST);

        JButton dalej6 = new JButton("Zatwierdź");
        dalej6.setBackground(new Color(73, 159, 195));
        dalej6.setFont(new Font("Verdana", Font.BOLD, 20));
        dalej6.setForeground(Color.WHITE);
        przyciski6.add(dalej6);


        dalej6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numerT = wprowadzanieNumeruPracownika.getText();
                try {
                    numer = Integer.parseInt(numerT);
                } catch (NumberFormatException ne) {
                    System.out.println("Wprowadź numer pracownika");
                    return;
                }
                try {
                    if (wybranyKlientWypozyczalni != null && wybranyKostium != null) {
                        Wypozyczenie wypozyczenieGUI = new Wypozyczenie(numerWypozyczenia, numer, koszt, 3, wybranyKostium, wybranyKlientWypozyczalni);

                        System.out.println("Tworzy sie wypozyczenie GUI");

                        //zapis do pliku
                        try {
                            Wypozyczenie.pokazEkstensje();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }

                        try {
                            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("wypozyczenia.txt"));
                            Wypozyczenie.piszEkstensja(outputStream);
                            outputStream.close();

                            Wypozyczenie.ewszytskieWypozyczenia.clear();

                            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("wypozyczenia.txt"));
                            Wypozyczenie.czytajEkstensje(inputStream);
                            inputStream.close();

                        } catch (IOException | ClassNotFoundException ee) {
                            ee.printStackTrace();
                        }
                        System.out.println("Zatwierdzono wypozyczenie i zapisano do pliku "+"'"+"wypozyczenia.txt"+"'");
                        System.out.println("Wypożyczenie: numer wypożyczenia  - " + numerWypozyczenia+" numer sprzedawcy - "+numer + " kostium - " + wybranyKostium + " klient - " + wybranyKlientWypozyczalni);
                        Window window =SwingUtilities.getWindowAncestor((Component) e.getSource());
                        if(window !=null){
                            window.dispose();
                        }
                    } else {
                        System.out.println("Nalezy wybrac zarówno klienta jak i kostium");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });



        stronaSz.add(przyciski6,BorderLayout.SOUTH);

        // Panel zawierający nagłówek i stronę trzecią
        JPanel szostaStrona = new JPanel();
        szostaStrona.setLayout(new BorderLayout());
        szostaStrona.add(naglowekSz, BorderLayout.NORTH);
        szostaStrona.add(stronaSz, BorderLayout.CENTER);

        // Dodanie paneli do głównego panelu z użyciem CardLayout
        mainPanel.add(pierwszaStrona, "pierwsza");
        mainPanel.add(drugaStrona, "druga");
        mainPanel.add(tzreciaStrona, "trzecia");
        mainPanel.add(czwartaStrona, "czwarta");
        // mainPanel.add(piataStrona, "piata");
        mainPanel.add(szostaStrona, "szosta");

        // Listener do przycisków zmieniających strony
        rozpocznijW.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "druga");
        });

        powrot2.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "pierwsza");
        });
        powrot3.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "druga");
        });
        powrot4.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "trzecia");
        });
//        powrot5.addActionListener(e -> {
//            CardLayout cl = (CardLayout) (mainPanel.getLayout());
//            cl.show(mainPanel, "czwarta");
//        });
        powrot6.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "czwarta");
        });

        dalej2.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "trzecia");
        });
        dalej3.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "czwarta");
        });
        dalej4.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "szosta");
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

