package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }


    @PostMapping("/addContact")
    public String addContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact); 
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        model.addAttribute("contact", contact);
        return "editContact"; // The view where the edit form is shown
    }

    @PostMapping("/edit/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        existingContact.setName(contact.getName());
        existingContact.setPhone(contact.getPhone());
        existingContact.setEmail(contact.getEmail());
        contactRepository.save(existingContact);
        return "redirect:/"; // Redirect back to home page after update
    }


    
}
