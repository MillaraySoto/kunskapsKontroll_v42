package com.example;
// denna klassen visar EN  enda product !
public class Product {
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
        this.ID = nextID++; // Tilldelar ett unikt ID och ökar räknaren för nästa produkt.
        this.name = name;
        this.category = category;
        this.price = price; // för att kunna beräkna priset i Analyzer
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
