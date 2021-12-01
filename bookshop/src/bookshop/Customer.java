package bookshop;

import java.util.ArrayList;
import java.util.Collections;

public class Customer
{
    private String name;

    private ArrayList<Book> myBooks = new ArrayList<>();

    private double budget;

    public Customer(String name, double budget)
    {
        if (name == "" || budget < 0)
        {
            throw new IllegalArgumentException("Please give a valid input");
        }

        else
        {
            this.name = name;
            this.budget = budget;
        }

    }

    public Customer(String name, ArrayList<Book> books, double budget)
    {
        if (name == "" || budget < 0)
        {
            throw new IllegalArgumentException("Please give a valid input");
        }

        else
        {
            this.name = name;
            myBooks = books;
            this.budget = budget;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Book> getBooks()
    {
        return myBooks;
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.myBooks = books;
    }

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(double budget)
    {
        if (budget >= 0)
        {
            this.budget = budget;
        }
        else
        {
            throw new IllegalArgumentException("Budget can't be negative");
        }
    }

    public void addBook(Book book)
    {
        if (book.getPrice() <= budget)
        {
            myBooks.add(book);
            budget -= book.getPrice();
        }
        else
        {
            throw new IllegalArgumentException("Not enough budget");
        }
    }

    public boolean compareShops(Shop shop1, Shop shop2)
    {
        ArrayList<String> bookTitlesShop1 = new ArrayList<>();
        ArrayList<String> bookTitlesShop2 = new ArrayList<>();

        for (Book book : shop1.distinctBookList())
        {
            bookTitlesShop1.add(book.getTitle());

        }

        for (Book book : shop2.distinctBookList())
        {
            bookTitlesShop2.add(book.getTitle());

        }

        Collections.sort(bookTitlesShop1);
        Collections.sort(bookTitlesShop2);

        return bookTitlesShop1.equals(bookTitlesShop2);
    }
}
