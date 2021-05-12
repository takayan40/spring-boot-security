package com.example.demo.controller;

import com.example.demo.data.SignupForm;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @PostMapping(value = "signup")
    public String signup(@ModelAttribute("signup") SignupForm signupForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録が完了しました");
        myUserDetailsService.registerUser(signupForm.getName(), signupForm.getPassword(), signupForm.getPassword());
        return "redirect:/";
    }

    // ログイン画面アクセス時はgetでリクエストが飛んでくる(デフォルト時も同様)
    @GetMapping(path = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model, HttpSession session) {
        if (error != null && session != null) {
            model.addAttribute("showErrorMsg", true);
        } else {
            model.addAttribute("showErrorMsg", false);
        }
        return "login";
    }
}
