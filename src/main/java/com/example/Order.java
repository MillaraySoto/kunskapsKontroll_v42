package com.example;

import java.util.List;
// Denna klass representerar ETT! enda köp av en kund.

public class Order {

    // för att kunna hitta ID och identifiera ordern
    private int orderID;

    // List är vald eftersom en order kan innehålla flera av samma produkt
    private List<Product> products ;
    private String customerName;


    // konstruktorn Initierar alla nödvändiga fält vid skapandet av en order.
    public Order(int orderID, List<Product> products, String customerName) {
        this.orderID = orderID;
        this.products = products;
        this.customerName = customerName;
    }

    public double getTotalOrderPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }


    // Getters ger åtkomst till privata fält.
    public int getOrderID() {
        return orderID;}

    public List<Product> getProducts() {
        return products;}

    public String getCustomerName() {
        return customerName;}

    // gör utskfriten till en string (lättare att läsa)
    @Override
    public String toString() {
        return "Order [orderID = " + orderID + ", products = " + products + ", customerName = " + customerName + "]";
    }

}
