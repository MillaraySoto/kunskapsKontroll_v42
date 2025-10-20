package com.example;
// Detta är vår egena felkod för när en kund saknas eller inte har någon order
// RuntimeExeption är en egen ibyggd class
public class EmptyOrderException extends RuntimeException{

    // konstrukotn
    public EmptyOrderException(String message) {
        // skickar felmeddelandet till Javas inbyggda felsystem- classen  " RuntimeExeption"
        super(message);






    }
}
