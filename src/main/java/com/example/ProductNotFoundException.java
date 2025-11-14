package com.example;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message); // Skickar felmeddelandet till RuntimeException så att det kan visas vid fel
        // skickar det det står när en produkt inte finns eller inte hittas

    }
}
