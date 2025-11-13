package com.example;

/**
 * Exception som kastas när någon försöker skapa en produkt
 * med ett negativt pris.
 *
 * Används i Product-konstruktorn för att säkerställa att alla produkter
 * har ett giltigt pris.
 */


public class NegativePriceException extends RuntimeException {

    public NegativePriceException(String message) {
        super(message); // // Skickar felmeddelandet till RuntimeException så att det kan visas vid fel

    }
}
