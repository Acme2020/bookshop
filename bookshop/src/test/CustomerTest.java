package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bookshop.Book;
import bookshop.Customer;
import bookshop.Shop;

class CustomerTest
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
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        shop.setBooks(books);

    }

    @Test
    @DisplayName("The customer should have one book more after one has been added")
    void testAddingNumberOfBooks()
    {
        int oldNumberOfBooks = customer.getBooks().size();
        customer.addBook(shop.getBooks().get(0));
        assertEquals(oldNumberOfBooks + 1, customer.getBooks().size());
    }

    @Test
    @DisplayName("The book that was sold by the shop should be added to the customer")
    void testAddBookFromShop()
    {
        Book bookToSell = shop.getBooks().get(0);
        shop.sellBook(bookToSell, customer);
        assertEquals(customer.getBooks().get(customer.getBooks().size() - 1), bookToSell);
    }

    @Test
    @DisplayName("Budget of customer should be deducted by the price of the sold book")
    void testDeductPriceFromBudget()
    {
        double oldBudget = customer.getBudget();
        Book bookToAdd = shop.getBooks().get(0);
        shop.sellBook(shop.getBooks().get(0), customer);
        assertEquals(oldBudget - bookToAdd.getPrice(), customer.getBudget());
    }

    @Test
    @DisplayName("Check if two shops have the same book titles in stock")
    void testCompareShopsTrue()
    {
        Shop shop2 = new Shop("BestBooks", new ArrayList<Book>(), 0);
        shop2.setBooks(shop.getBooks());
        assertTrue(customer.compareShops(shop, shop2));
    }

    @Test
    @DisplayName("Fail if two shops do not have the same book titles in stock")
    void testCompareShopsFalse()
    {
        Shop shop2 = new Shop("BestBooks", new ArrayList<Book>(), 0);
        Book book1 = new Book("Harry Potter", 25.90, 578, "Fantasy", "978-3608963762");
        Book book2 = new Book("Che", 14.90, 223, "Biography", "978-3442267747");
        Book book3 = new Book("Per Anhalter durch die Galaxis", 11.90, 319, "Adventure", "978-758245159");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        shop2.setBooks(books);
        assertFalse(customer.compareShops(shop, shop2));
    }

}
