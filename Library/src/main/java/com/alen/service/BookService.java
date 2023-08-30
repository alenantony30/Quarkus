package com.alen.service;

import com.alen.entity.Books;
import io.quarkus.runtime.configuration.SystemOnlySourcesConfigBuilder;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookService {


    public static Books book1 = new Books(111, "Da vinci code", "dan brown", 100);
    public static Books book2 = new Books(222, "angels and demons", "dan brown", 150);
    public static Books book3 = new Books(333, "Origin", "dan brown", 100);
    public static Books book4 = new Books(444, "half girl friend", "chetan bhagat", 100);
    public static List<Books> listOfBooks = new ArrayList<>();

    public BookService() {
        BookService.addData();
    }

    public static void addData() {

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);
    }


    public List<Books> getAll() {

        System.out.println(listOfBooks);
        return listOfBooks;
    }

    public Books getByID(int id) {

        for (Books book : listOfBooks) {
            if (book.getId() == id) {
                return book; // Found the book
            }
        }
        return null; // Book not found
    }

}
