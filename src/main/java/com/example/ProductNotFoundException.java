package com.example;

// vår egen felkod för när en produkt vi letar efter inte finns i listan
public class ProductNotFoundException extends RuntimeException {

    // konstrukorn
    public ProductNotFoundException(String message) {
        // skickar felmeddelandet till Javas inbyggda felsystem- classen  " RuntimeExeption"
        super(message);
    }
}