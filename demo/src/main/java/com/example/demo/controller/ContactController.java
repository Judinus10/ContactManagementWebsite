package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    // Display the login form
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Process the login form
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

         User user = userRepository.findByUsername(username);

         if (user != null && user.getPassword().equals(password)) {
             session.setAttribute("loggedIn", true);
             session.setAttribute("username", user.getUsername());
             return "redirect:/";
         } else {
             model.addAttribute("error", "Invalid username or password!");
             return "login";
         }
    }

    // Logout and invalidate the session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Process the registration form
    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {
        // Simple validation for passwords matching
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        // Check if the username already exists (You can add more complex logic here)
        if (userRepository.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        // Save the user to the database (Use your actual user entity and repository)
        User newUser = new User(username, password);
        userRepository.save(newUser);

        // Redirect to login page after successful registration
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Ends the session
        return "redirect:/login"; // Redirects to login page
    }
    
    // Home page - requires login
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/addContact")
    public String showAddContactForm(HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        return "addContact";
    }

    @PostMapping("/addContact")
    public String addContact(@RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contactRepository.save(contact);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        model.addAttribute("contact", contact);
        return "editContact";
    }

    @PostMapping("/updateContact")
    public String updateContact(@RequestParam Long id,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact ID"));
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contactRepository.save(contact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        contactRepository.delete(contact);
        return "redirect:/";
    }

    @GetMapping("/confirmDelete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        model.addAttribute("contact", contact);
        return "confirmDelete";
    }

    @GetMapping("/sort")
    public String viewHomePage(@RequestParam(defaultValue = "name") String sortField,
            Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        List<Contact> contactList = contactRepository.findAll(Sort.by(sortField).ascending());
        model.addAttribute("contacts", contactList);
        model.addAttribute("sortField", sortField);
        return "index";
    }

    @GetMapping("/search")
    public String searchContacts(@RequestParam("keyword") String keyword,
            Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        List<Contact> results = contactRepository
                .findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword, keyword, keyword);
        model.addAttribute("contacts", results);
        return "index";
    }
}
