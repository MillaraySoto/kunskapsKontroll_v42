package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
// innehåller all affärslogistisk

public class Analyzer {


    // Referens till Repository om jag har förstått det rätt är det en typ länk eller adress till repistory datan i analyzer
    // alltså anvnds för att hitta data då Anaöyzer inte har någon så vi måste hämta från repisitory
    // tar in repository datan

    private final Repository repository;

    // Konstruktor-Sparar länken till Repository så Analyzer kan HÄMTA dat
    // använder Repository datan
    public Analyzer(Repository repository) {
        this.repository = repository;
    }

    // metod för att hitta produkter i kategori & sortera efter pris (stigande)
    public List<Product> findAndSortProductsByCategory(String category) {
        //Hämta alla produkter från datalagret (repository)
        return repository.getProducts().stream()

                // filtrerar och tar bort produkter som inte är innm den kattegorin vi letar feter
                //equalsIgnoreCase() =Jämför två textsträngar och se om de är exakt likadana, men strunta i om det är stora eller små bokstäver.
                .filter(p -> p.getCategory().equalsIgnoreCase(category))

                //ordnar det produketr som är kvar efter pris (Billigast först
                .sorted(Comparator.comparing(Product::getPrice))

                // tar in all data igen och gör om det till en ny lista
                .collect(Collectors.toList());
    }

    // metod som räknar ut kundens beställnings totala summa
    public double calculateCustomerTotal(String customerName) {

        //  Hämtar hela orderhistoriken (Map) från repository.
        Map<String, List<Order>> history = repository.getCustomerOrderHistory();
        // Hämtar kundens specifika orderlista med customerName
        List<Order> customerOrders = history.get(customerName);

        // Hitta ett sätt att hantera om kunden inte finns (t.ex. returnera 0)
        //  detta skrivs ut om det inte finns en kustomer order
        // ifsatsen kommer före den andra rerurn för att stoppa programmet i fall att customer order är noll
        // allts för att förhindra att skriav ut onödig kod
        // glöm inte att göra om vilkoren
        //(om customerOrders är lika med noll eller om customer order är tom)
        if (customerOrders == null || customerOrders.isEmpty()) {
            // kastar vi in detta felmeddelandet
            throw new EmptyOrderException("Kunden " + customerName + " hittades inte i orderhistoriken eller har ingen order.");        }

        // metod för att snabbt räkna ut totalsumman av en kunds alla ordrar
        //Tar listan av ordrar och sätter dem på ett löpande band Stream
        // detta skrivs ut om det finns en kustomer order
        return customerOrders.stream()

                // Går igenom varje order på bandet och byter ut den mot dess totalpris (som ett tal)
                .mapToDouble(Order::getTotalOrderPrice)

                // Lägger ihop alla priserna på bandet till en enda slutsumma och skickar ut svaret
                .sum();

    }

        // metod för att hitta dom 3 mest köpta produkterna
        // returnerar en List STRIng med produkt namn
    public List<String> findMostPopularProducts() {

        //  Hämtar hela orderhistoriken (Map) från repository varje värde är en orderlista
        Map<String, List<Order>> history = repository.getCustomerOrderHistory();

        // gör om mappen till en lista av ALLA ordrar
        // nya listan
        List<Order> allOrders =
                //tar ut all orderhistroik ignorerar kundens namn
                history.values()
                        // sätter listorna på rullande band igen
                        .stream().
                        // Klistrar ihop ALLA små order-listor till EN enda stor lista för att kunna räkna alla på en gång
                        flatMap(List::stream).
                        // gör om allt tillbak till en lista
                        collect(Collectors.toList());

        // Tar in allObjekts listan och  returnerar  hur många gånger varje produkt har sålts
        return allOrders.stream()

                // Plockar ut alla ENSTAKA Produkter från ordrarna
                //Denna rad är avgörande för att omvandla en lista av köp till en lista av sålda varor som går att räkna
                .flatMap(order -> order.getProducts().stream())

                // Räknar hur många gånger varje ProduktNAMN har blivit sålt?
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting()))

                // Gör räknesvaret rörligt (som ett nytt löpande band) för att kunna sorteras.
                .entrySet().stream()

                //  Jämför antalet och sätter de största (mest sålda) först
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))

                //  Väljer bara de 3 första
                .limit(3)

                //  Plockar ut bara Produktnamnet (Strängen)
                .map(Map.Entry::getKey)

                //  Ger tillbaka den nya Listan (List<String>)
                .collect(Collectors.toList());
    }

    }


