package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

        // Ansvarar endast för att lagra produkter och ordrar
        // butikens lager och arkiv

public class Repository {

    // tar in logger i Repository för att kunna visa felen och skriva ner dom i loggern
    private static final Logger logger = LoggerFactory.getLogger(Repository.class);


    // valt array och list då list bevarar insättningsordern och tillåter dubletter som t.ex fler av samma varor
    // hashmap för att snabbare hitta order baserat på produkt

    // Lista med alla produkter. List = bra för att behålla ordning och tillåta dubbletter.
    private List<Product> products = new ArrayList<>();

    // Lista med alla ordrar. List bevarar ordningen (kronologisk).
    private List<Order> orders = new ArrayList<>();

    //Lagrar kundens orderhistorik. String är kundnamn (Key).
    // Värdet (List<Order>) lagrar kundens alla ordrar.
    // Kopplar kundnamn till deras orderistorik
    Map<String, List<Order>> customerOrderHistory = new HashMap<>();


    // Konstruktor - Fyller produktlistan vid start.
    // körs automatiskt när Repository skapas.
    // Det är här vi SKAPAR alla produkter och ordrar.

    public Repository() {

        logger.info("REPOSITORY: Startar initiering av produkter och ordrar.");
        // Självaste produkterna (Skapar produkterna här )

        // try catch för att fånga fel om något går fel
        try {
            //HÅRVÅRD
            Product p1 = new Product("Leave in balsam KEUNE ", "Hårvård", 149);
            products.add(p1);
            // loggar när en produkt läggs ttill
            logger.info("Product tillagd i Repository: " + p1);

            Product p2 = new Product("Shampoo maria nila", "Hårvård", 239.90);
            products.add(p2);
            // loggar när en produkt läggs ttill
            logger.info("Product tillagd i Repository: " + p2);

            //HUDVÅRD
            Product p3 = new Product("Gel cleanser HARUHARU ", "Hudvård", 199.39);
            products.add(p3);
            // loggar när en produkt läggs ttill
            logger.info("Product tillagd i Repository: " + p3);

            Product p4 = new Product("Toner Centella Skin 1004", "Hudvård", 259.39);
            products.add(p4);
            // loggar när en produkt läggs ttill
            logger.info("Product tillagd i Repository: " + p4);

            //KROPSVÅRD

            Product p5 = new Product("Shower gel Magnolia ", "kropsvård", 129.39);
            products.add(p5);
            logger.info("Product tillagd i Repository: " + p5);

            Product p6 = new Product("Body oil Lavendel", "Kropsvård", 139.39);
            products.add(p6);
            logger.info("Product tillagd i Repository: " + p6);

            // första testet i fall producterna har ett -pris (negativt pris )
            // Programmet stoppas här och hoppar till 'catch (NegativePriceException e)'
            Product pFailPris = new Product("TestProdukt Med Fel", "Test", -10);
            products.add(pFailPris); // Denna rad körs ej



            // Skapar olika ordrar och lägger till ordrarna i orderlistan

            Order one1 = new Order(1, List.of(p1, p2), "Alice");
            orders.add(one1);
            logger.info("Order tillagd : " + one1);

            Order tow2 = new Order(2, List.of(p3, p4), "Sarah");
            orders.add(tow2);
            logger.info("Order tillagd : " + tow2);

            Order three3 = new Order(3, List.of(p5, p6), "Bella");
            orders.add(three3);
            logger.info("Order tillagd : " + three3);

            // andra test om order listan är tom
            //  koden hoppar till 'catch (EmptyOrderProductListException e)'
            Order oFailEmpty = new Order(99, List.of(), "Cassandra");
            orders.add(oFailEmpty); // Denna rad körs ej


            // lägger till varje order i customerOrderHistory
            this.addOrderToHistory(one1);
            this.addOrderToHistory(tow2);
            this.addOrderToHistory(three3);

            // detta fångar felet o en product försöker få negatovt pris

        } catch (NegativePriceException e) {
            logger.error("Misslyckades att skapa en produkt: " + e.getMessage());

            // detta fångar felet om en order har en tom produktlista

        } catch(EmptyOrderProductListException e){
                logger.error("Misslyckades att skapa order: " + e.getMessage());
            }
    }

         // den här metoden kollar om det finns en product

        public void addProduct(Product product){
           try{
        if (product == null) {
            throw new ProductNotFoundException("Produkten hittades inte.");
        }
         products.add(product);
               logger.info("Produkt tillagd i Repository: " + product);

           } catch (Exception e) {
               logger.error("Misslyckades att lägga till produkt: " + e.getMessage());
               throw e;
           }
        }

    public void addOrder(Order order) {
        try {
            if (order == null) {
                throw new EmptyOrderException("Order-objekt är null.");
            }

            orders.add(order);
            logger.info("Order tillagd i Repository: " + order);

            addOrderToHistory(order);

        } catch (Exception e) {
            logger.error("Misslyckades att lägga till order: " + e.getMessage());
            throw e;
        }
    }




    // Den här metoden är som en automatisk sorteringsmaskin som ser till att varje order hamnar i rätt kunds historik-lista inuti den stora mappen.
            public void addOrderToHistory (Order order){ // tar in ordern som har nyss gjorts
                String customerName = order.getCustomerName(); // Plockar ut kundens namn direkt från ordern
                // Söker upp kunden i Mappen, skapar lista om den saknas, och lägger till ordern.
                customerOrderHistory.computeIfAbsent(customerName, k -> new ArrayList<>()).add(order);
            }


            // Getters för att hämta product info
            public List<Product> getProducts () {
                return new ArrayList<>(products); // Returnerar en kopia för att skydda den interna listan
            }
            // Getters för att hämta ordrar
            public List<Order> getOrders () {
                return new ArrayList<>(orders); // Returnerar en kopia för att skydda den interna listan
            }
            // Getter för att kunna räkna ut summan på ordern
            public Map<String, List<Order>> getCustomerOrderHistory () {
                return customerOrderHistory;
            }
        }