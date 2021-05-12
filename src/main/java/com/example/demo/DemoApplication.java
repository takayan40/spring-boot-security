package com.example.demo;

import com.example.demo.data.Repository.BookRepository;
import com.example.demo.data.Repository.UserRepository;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    public BookRepository todoRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myUserDetailsService.registerAdmin("hoge", "hogehoge", "hoge@hoge.hoge");
    }
}
