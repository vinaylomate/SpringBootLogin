package com.example.demo.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(path = "/api/v1/")
public class LoginV2Controller {
    @GetMapping(path = "login")
    public RedirectView login() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/vinay/login.html");
        return redirectView;
    }

    @GetMapping(path = "success")
    public RedirectView success() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/vinay/success.html");
        return redirectView;
    }

    @GetMapping(path = "logout")
    public RedirectView logout() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/vinay/logout.html");
        return redirectView;
    }

    @GetMapping(path = "invalid")
    public RedirectView invalid() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/vinay/invalid.html");
        return redirectView;
    }
}
