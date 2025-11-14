package com.example;
/**
 * Exception som kastas när någon försöker skapa en order
 * utan några produkter.
 *
 * Används i Order-konstruktorn för att säkerställa att alla ordrar
 * innehåller minst en produkt. Detta förhindrar logiska fel
 * och gör koden mer robust.
 */

public class EmptyOrderProductListException extends RuntimeException {

    public EmptyOrderProductListException(String message) {
        super(message); // Skickar felmeddelandet till RuntimeException så att det kan visas vid fel

    }
}
