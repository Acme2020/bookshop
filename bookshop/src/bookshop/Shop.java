package bookshop;

import java.util.ArrayList;

public class Shop
{
    private String name;

    private ArrayList<Book> books = new ArrayList<>();

    private double revenue;

    public Shop(String name, ArrayList<Book> books, double revenue)
    {
        if (name == "" || books == null || revenue < 0)
        {
            throw new IllegalArgumentException("Please give a valid input");
        }
        else
        {
            this.name = name;
            this.revenue = revenue;
            addBookToStock(books);
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
        return books;
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books.clear();
        addBookToStock(books);
    }

    public double getRevenue()
    {
        return revenue;
    }

    public void setRevenue(double revenue)
    {
        this.revenue = revenue;
    }

    public void sellBook(Book bookToSell, Customer customer)
    {
        if (books.contains(bookToSell))
        {
            customer.addBook(bookToSell);
            revenue += bookToSell.getPrice();
            books.remove(bookToSell);

        }
        else
        {
            throw new IllegalArgumentException("Book is not in Stock");
        }
    }

    public ArrayList<Book> filterAdventureGenre()
    {

        ArrayList<Book> filteredBooks = new ArrayList<>();
        for (Book book : books)
        {
            if (book.getGenre() == "Adventure")
            {
                filteredBooks.add(book);
            }

        }
        return filteredBooks;
    }

    public ArrayList<Book> distinctBookList()
    {
        // duplicate book are characterized by the same name
        ArrayList<Book> distinctBookList = new ArrayList<>();
        ArrayList<String> bookTitleList = new ArrayList<>();
        for (Book book : books)
        {
            if (!bookTitleList.contains(book.getTitle()))
            {
                bookTitleList.add(book.getTitle());
                distinctBookList.add(book);
            }

        }
        return distinctBookList;
    }

    public ArrayList<Book> addBookToStock(ArrayList<Book> books)
    {
        ArrayList<Book> booksWithWrongISBN = new ArrayList<>();

        for (Book book : books)
        {
            if (checkISBN(book.getIsbn()))
            {
                this.books.add(book);
            }
            else
            {
                booksWithWrongISBN.add(book);
            }
        }
        // Return books with wrong ISBN for further processing
        return booksWithWrongISBN;
    }

    public boolean checkISBN(String isbn)
    {
        if (isbn == null)
        {
            return false;
        }
        isbn = isbn.replaceAll("-", "");
        if (isbn.length() != 13)
        {
            return false;
        }
        try
        {
            int sum = 0;
            for (int i = 0; i < 12; i++)
            {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                sum += (i % 2 == 0) ? digit * 1 : digit * 3;
            }
            // checkSum must be 0-9. If calculated as 10 then = 0
            int checkSum = 10 - (sum % 10);
            if (checkSum == 10)
            {
                checkSum = 0;
            }
            return checkSum == Integer.parseInt(isbn.substring(12));
        }
        catch (NumberFormatException e)
        {
            // catch invalid ISBNs with non-numeric characters
            return false;
        }
    }
}
