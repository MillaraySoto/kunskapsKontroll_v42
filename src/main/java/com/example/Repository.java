package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

        // Ansvarar endast för att lagra och hämta all data!
public class Repository {

        // valt array och list då list bevarar insättningsordern och tillåter dubletter som t.ex fler av samma varor
        // hashmap för att snabbare hitta order baserat på produkt

        // Lagrar ALLA produkter. List är bra för att ha ordning och tillåta dubbletter.
    private List<Product> products = new ArrayList<>();

        // Lagrar ALLA ordrar. List bevarar ordningen (kronologisk).
    private List<Order> orders = new ArrayList<>();

        //Lagrar kundens orderhistorik. String är kundnamn (Key).
        // Värdet (List<Order>) lagrar kundens alla ordrar.
    Map<String, List<Order>> customerOrderHistory = new HashMap<>();


            // Konstruktor - Fyller produktlistan vid start.

            public Repository() {
                // Självaste produkterna (Skapar produkterna här )

                //HÅRVÅRD
                Product p1 = new Product("Leave in balsam KEUNE ", "Hårvård", 149);
                Product p2 = new Product("Shampoo maria nila", "Hårvård", 239.90);
                //HUDVÅRD
                Product p3 = new Product("Gel cleanser HARUHARU ", "Hudvård", 199.39);
                Product p4 = new Product("Toner Centella Skin 1004", "Hudvård", 259.39);
                //KROPSVÅRD
                Product p5 = new Product("Shower gel Magnolia ", "kropsvård", 129.39);
                Product p6 = new Product("Body oil Lavendel", "Kropsvård", 139.39);


                // Lägger in varje ENSTAKA produkt (p1, p2, ...) i den stora listan (products)!
                this.products.add(p1);
                this.products.add(p2);
                this.products.add(p3);
                this.products.add(p4);
                this.products.add(p5);
                this.products.add(p6);


                // Skapar olika ordrar

                Order O1 = new Order(1, List.of(p1, p2), "Alice");
                Order O2 = new Order(2, List.of(p3, p4), "Sarah");
                Order O3 = new Order(3, List.of(p5, p6), "Bella");

                // lägger till varje order i orders-listan
                this.orders.add(O1);
                this.orders.add(O2);
                this.orders.add(O3);

                // lägger till varje order i customerOrderHistory
                this.addOrderToHistory(O1);
                this.addOrderToHistory( O2);
                this.addOrderToHistory( O3);

            }

            // Den här metoden är som en automatisk sorteringsmaskin som ser till att varje order hamnar i rätt kunds historik-lista inuti den stora mappen.
            public void addOrderToHistory(Order order) { // tar in ordern som har nyss gjorts
                String customerName = order.getCustomerName(); // Plockar ut kundens namn direkt från ordern
                // Söker upp kunden i Mappen, skapar lista om den saknas, och lägger till ordern.
                customerOrderHistory.computeIfAbsent(customerName, k -> new ArrayList<>()).add(order);
            }


           // Getters för att hämta product info
            public List<Product> getProducts() {
                return new ArrayList<>(products); // Returnerar en kopia för att skydda den interna listan
            }
            // Getters för att hämta ordrar
            public List<Order> getOrders() {
                return new ArrayList<>(orders); // Returnerar en kopia för att skydda den interna listan
            }
            // Getter för att kunna räkna ut summan på ordern
            public Map<String, List<Order>> getCustomerOrderHistory() {
                return customerOrderHistory;
            }
}
