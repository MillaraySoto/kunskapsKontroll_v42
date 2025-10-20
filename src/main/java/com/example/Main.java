package com.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        // koppla ihop Repository och Analyzer
        Repository repository = new Repository(); // repository skapas
        Analyzer analyzer = new Analyzer(repository); // analyzer skapas och repository läggs in i analyzer

        System.out.println("--- ANALYS STARTAR ---");

        System.out.println("\n--- Hårvård sorterad efter pris ---");

        //Anropa metoden som hittar Hårvård och sorterar dem efter pris
        List<Product> sortedProducts = analyzer.findAndSortProductsByCategory("Hårvård");
        //Skriver ut varje produkt (namn och pris) i den nya sorterade listan
        sortedProducts.forEach(p -> System.out.println(p.getName() + " - " + p.getPrice() + " kr"));

        System.out.println("\n--- De 3 mest köpta produkterna ---");
        //Anropa metoden som räknar ut och returnerar de 3 mest populära produkterna
        List<String> mostPopular = analyzer.findMostPopularProducts();
        //Skriver ut dom tre mest populära produkterna
        mostPopular.forEach(System.out::println);

        System.out.println("--- TEST STARTAR ---");
        // testar med en kund som INTE finns (ska kasta vårt egna fel)

        // Anger villket kundnamn som ska testas
        String missingCustomer = "Lisa";

        // HÄr börjar try/catch blocket som används  för att fånga felet från Analyzer.
        try {
            // Anropa metoden som kan kasta felet för Lisa (vi vet readn att det är fel ) men den kastar om felet ärfel
            double total = analyzer.calculateCustomerTotal(missingCustomer);
            //Denna rad SKA INTE köras om felet kastas.(om lisa är rätt)
            System.out.println("Total summa för " + missingCustomer + ": " + total + " kr");


            //Här fångar vi VÅRT EGNA FÖRVÄNTADE fel (EmptyOrderException)
            // Om felet kastas, fångar vi det och skriver ut det egna meddelandet
        } catch (EmptyOrderException e) {
            // här skrivs vårt egna felmddeande ut
            System.out.println("FEL HITTADES (Eget Undantag): " + e.getMessage());

            //Här fångar vi ALLA andra OVÄNTADE fel
        } catch (Exception e) {
            //Skriver ut ett generellt felmeddelande
            System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
        }

    }
}