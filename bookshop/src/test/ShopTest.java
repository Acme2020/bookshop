package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bookshop.Book;
import bookshop.Customer;
import bookshop.Shop;

class ShopTest
{
    Shop shop;

    Customer customer;

    @BeforeEach
    void setUp()
    {
        shop = new Shop("myBookShop", new ArrayList<Book>(), 0);
        customer = new Customer("John Doe", 125.13);
        Book book1 = new Book("Harry Potter", 25.90, 578, "Fantasy", "978-1603090254");
        Book book2 = new Book("Che", 14.90, 223, "Biography", "978-1603090476");
        Book book3 = new Book("Per Anhalter durch die Galaxis", 11.90, 319, "Adventure", "978-1891830853");
        Book book4 = new Book("Herr der Ringe", 19.90, 745, "Fantasy", "978-1603090162");
        Book book5 = new Book("Herr der Ringe", 19.90, 745, "Fantasy", "978-1603090162");
        Book book6 = new Book("Nicht Lustig", 9.90, 123, "Comic", "978-1603090773");
        Book book7 = new Book("Nicht Lustig", 9.90, 123, "Comic", "978-1603090773");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        shop.setBooks(books);

    }

    @Test
    @DisplayName("After selling a book there should be one less in stock")
    void testSellBook()
    {
        int oldStock = shop.getBooks().size();
        shop.sellBook(shop.getBooks().get(0), customer);
        assertEquals(oldStock - 1, shop.getBooks().size());
    }

    @Test
    @DisplayName("After selling a book the revenue should increase accordingly")
    void testSellBookIncreaseRevenue()
    {
        double oldRevenue = shop.getRevenue();
        Book bookToSell = shop.getBooks().get(0);
        shop.sellBook(bookToSell, customer);
        assertEquals(oldRevenue + bookToSell.getPrice(), shop.getRevenue());
    }

    @Test
    @DisplayName("Filter should return only books of the genre Adventure")
    void testFilterAdventureGenre()
    {
        assertEquals(1, shop.filterAdventureGenre().size());
    }

    @Test
    @DisplayName("Book list should not contain books with same names")
    void testDistinctBookList()
    {
        assertEquals(5, shop.distinctBookList().size());
    }

    @Test
    @DisplayName("Only books with correct ISBN should be added to stock")
    void testaddBookToStock()
    {
        Book book8 = new Book("Harry Potter", 25.90, 578, "Fantasy", "978-3608963762");
        Book book9 = new Book("Che", 14.90, 223, "Biography", "978-3442267747");
        Book book10 = new Book("Per Anhalter durch die Galaxis", 11.90, 319, "Adventure", "978-758245159");
        Book book11 = new Book("Herr der Ringe", 19.90, 745, "Fantasy", "978-3841335180");
        Book book12 = new Book("Nicht Lustig", 9.90, 123, "Comic", "978-3442267819");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);
        shop.addBookToStock(books);
        assertEquals(9, shop.getBooks().size());
    }

    @Test
    @DisplayName("A list of books with wrong ISBNs should be returned")
    void testAddBookToStockReturn()
    {
        Book book8 = new Book("Harry Potter", 25.90, 578, "Fantasy", "978-3608963762");
        Book book9 = new Book("Che", 14.90, 223, "Biography", "978-3442267747");
        Book book10 = new Book("Per Anhalter durch die Galaxis", 11.90, 319, "Adventure", "978-758245159");
        Book book11 = new Book("Herr der Ringe", 19.90, 745, "Fantasy", "978-3841335180");
        Book book12 = new Book("Nicht Lustig", 9.90, 123, "Comic", "978-3442267819");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);
        assertEquals(3, shop.addBookToStock(books).size());
    }

    @Test
    @DisplayName("Only books with correct ISBN should be setted")
    void testSetBooks()
    {
        Shop shop2 = new Shop("BestBooks", new ArrayList<Book>(), 0);
        Book book8 = new Book("Harry Potter", 25.90, 578, "Fantasy", "978-3608963762");
        Book book9 = new Book("Che", 14.90, 223, "Biography", "978-3442267747");
        Book book10 = new Book("Per Anhalter durch die Galaxis", 11.90, 319, "Adventure", "978-758245159");
        Book book11 = new Book("Herr der Ringe", 19.90, 745, "Fantasy", "978-3841335180");
        Book book12 = new Book("Nicht Lustig", 9.90, 123, "Comic", "978-3442267819");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);
        shop2.setBooks(books);
        assertEquals(2, shop2.getBooks().size());
    }

    // Exception Testing

    @Test
    @DisplayName("If an input is missing to create a shop an exception should be thrown")
    void testNoInputException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Shop shop = new Shop("BestShop", new ArrayList<Book>(), -123456);
        });
        assertTrue(exception.getMessage().contains("Please give a valid input"));
    }

    @Test
    @DisplayName("If a book should be sold that ist not in stock an exception should be thrown")
    void testBookIsNotInStockException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Book bookToSell = new Book("Medicus", 10.90, 645, "Adventure", "978-1603090254");
            shop.sellBook(bookToSell, customer);
        });
        assertTrue(exception.getMessage().contains("Book is not in Stock"));
    }

    @Test
    @DisplayName("If a book should be sold that ist not in stock an exception should be thrown")
    void testCheckISBNException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            shop.checkISBN("!?! -36G8963762");
        });
        assertTrue(exception.getMessage().contains("Book is not in Stock"));
    }

}
