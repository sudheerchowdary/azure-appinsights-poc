package com.sudheer.azure.azuredemoapp.controller;

import com.sudheer.azure.azuredemoapp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("get-books")
    public ResponseEntity<String> getBooks() {
        final String resp = booksService.getBooks();
        if (resp != null)
            return new ResponseEntity<>(resp, HttpStatus.resolve(200));
        else
            return new ResponseEntity<>(resp, HttpStatus.resolve(500));
    }
}
