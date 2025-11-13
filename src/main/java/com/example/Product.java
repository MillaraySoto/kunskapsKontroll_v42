package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// denna klassen visar EN  enda product !
public class Product {
    private static final Logger logger = LoggerFactory.getLogger(Product.class);// tar in logger i Product


    // för att själv skriva ut id
    // static efter som det ska räknas in vid varje ny produkt oavsätt vart
    private static int nextID = 1000;

    // fält som beskriver produkterna
    private int ID; // denna går ej att ända ? kolla upp
    private String name;
    private String category;
    private double price;

    // konstrukter skapar en product och ger den ett specifitk ID
    public Product( String name, String category, double price) {

        if (price < 0) {
            throw new NegativePriceException("Priset kan inte vara negativt"); // för att kolla så att man inte skrver in en produkt utan ett pris
        }
        this.ID = nextID++; // Tilldelar ett unikt ID och ökar räknaren för nästa produkt.
        this.name = name;
        this.category = category;
        this.price = price; // för att kunna beräkna priset i Analyzer
        logger.info("Ny produkt skapad: " + this); // loggar att en ny product har skapats om det inte blev fel där uppe i if
    }
    // Getters ger åtkomst till privata fält.
    public int getID() {
        return ID;}

    public String getName() {
        return name;}

    public String getCategory() {
        return category;}

    public double getPrice() {
        return price;
    }
    //Gör att Product-objekt kan skrivas ut på ett läsbart sätt
    @Override
    public String toString() {
        return "Product [ID = " + ID + ", name = " + name + ", category = " + category + ", price = " + price + "]";
    }
}
