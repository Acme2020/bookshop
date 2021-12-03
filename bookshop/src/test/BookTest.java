package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bookshop.Book;

class BookTest
{

    @Test
    @DisplayName("If an input to create a book is missing or wrong an exception should be thrown")
    void testNoInputException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Book book = new Book("Harry Potter", 0, 578, "Fantasy", "978-1603090254");
        });
        assertTrue(exception.getMessage().contains("Please give a valid input"));
    }

    @Test
    @DisplayName("If a book with an unknown genre is created an exception should be thrown")
    void testWrongGenreException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Book book = new Book("Harry Potter", 25.90, 578, "Horror", "978-1603090254");
        });

        assertTrue(exception.getMessage().contains("Genre must be either Adventure, Fantasy, Biography or Comic"));
    }

}
