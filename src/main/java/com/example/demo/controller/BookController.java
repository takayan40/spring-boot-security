package com.example.demo.controller;

import com.example.demo.data.Entity.Book;
import com.example.demo.data.Repository.BookRepository;
import com.example.demo.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/books")
    public String books(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
        model.addAttribute("books", bookRepository.findByUserId(myUserDetails.getUser().getId()));
        return "books";
    }


    @PostMapping("/bookNew")
    public String add(@RequestParam String title, @RequestParam String status, @RequestParam String site, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
        Book book = new Book();
        book.setTitle(title);
        book.setDone(status);
        book.setSite(site);
        book.setUserId(myUserDetails.getUser().getId());
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findByUserId(myUserDetails.getUser().getId()));
        return "redirect:/books";
    }

    @PostMapping("/bookDelete/{id}")
    public String delete(@PathVariable long id, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
        bookRepository.deleteById(id);
        model.addAttribute("books", bookRepository.findByUserId(myUserDetails.getUser().getId()));
        return "redirect:/books";
    }

    @PostMapping("/bookUpdate/{id}")
    public String update(@PathVariable long id, Model model) {
        Book book = bookRepository.findById(id).get();
        if ("完了".equals(book.getDone())) {
            book.setDone("未完了");
        } else {
            book.setDone("完了");
        }
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/books";
    }

    @GetMapping(path = "/user")
    public String user() {
        return "user";
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(path = "/all")
    public String all() {
        return "all";
    }
}
