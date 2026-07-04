package com.api.book.bootrestbook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.book.bootrestbook.entities.Book;

@RestController
public class BookController {

    // ====================================================
    // Temporary Database (Stored in RAM)
    // ====================================================
    List<Book> books = new ArrayList<>();

    // ====================================================
    // Constructor
    // Add some books when application starts
    // ====================================================
    public BookController() {

        books.add(new Book(101, "Core Java", "James Gosling"));
        books.add(new Book(102, "Spring Boot", "Durgesh"));
        books.add(new Book(103, "Hibernate", "Gavin King"));

    }

    // ====================================================
    // GET ALL BOOKS
    // URL : GET http://localhost:8080/books
    // ====================================================
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {

        if (books.isEmpty()) {

            // 204 -> No Content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        // 200 -> OK
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    // ====================================================
    // GET BOOK BY ID
    // URL : GET http://localhost:8080/books/101
    // ====================================================
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {

        for (Book b : books) {

            if (b.getId() == id) {

                // 200 -> OK
                return new ResponseEntity<>(b, HttpStatus.OK);

            }

        }

        // 404 -> Book Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // ====================================================
    // ADD NEW BOOK
    // URL : POST http://localhost:8080/books
    // ====================================================
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);

        // 201 -> Created
        return new ResponseEntity<>(book, HttpStatus.CREATED);

    }

    // ====================================================
    // UPDATE BOOK
    // URL : PUT http://localhost:8080/books/101
    // ====================================================
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,
                                           @PathVariable int id) {

        for (Book b : books) {

            if (b.getId() == id) {

                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());

                // 200 -> Updated Successfully
                return new ResponseEntity<>(b, HttpStatus.OK);

            }

        }

        // 404 -> Book Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // ====================================================
    // DELETE BOOK
    // URL : DELETE http://localhost:8080/books/101
    // ====================================================
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {

        for (Book b : books) {

            if (b.getId() == id) {

                books.remove(b);

                // 200 -> Deleted Successfully
                return new ResponseEntity<>(
                        "Book Deleted Successfully...",
                        HttpStatus.OK);

            }

        }

        // 404 -> Book Not Found
        return new ResponseEntity<>(
                "Book Not Found...",
                HttpStatus.NOT_FOUND);

    }

}