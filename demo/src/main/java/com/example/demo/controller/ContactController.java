package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/addContact")
    public String showAddContactForm() {
        return "addContact"; // Return the addContact.html page
    }


    @PostMapping("/addContact")
    public String addContact(@RequestParam String name, @RequestParam String phone, @RequestParam String email) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);

        contactRepository.save(contact); // Save the new contact to the database

        return "redirect:/"; // Redirect back to the home page after adding the contact
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        model.addAttribute("contact", contact);
        return "editContact"; // The view where the edit form is shown
    }

    @PostMapping("/updateContact")
    public String updateContact(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String phone,
                                @RequestParam String email) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid contact ID"));

        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contactRepository.save(contact);

        return "redirect:/"; // redirect to homepage or contact list
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        contactRepository.delete(contact);
        return "redirect:/"; // Redirect to home page after deletion
    }


    @GetMapping("/confirmDelete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
        model.addAttribute("contact", contact);
        return "confirmDelete"; // The view where the user will confirm deletion
    }

    @GetMapping("/sort")
public String viewHomePage(@RequestParam(required = false, defaultValue = "name") String sortField, Model model) {
    List<Contact> contactList = contactRepository.findAll(Sort.by(sortField).ascending());
    model.addAttribute("contacts", contactList);
    model.addAttribute("sortField", sortField);
    return "index"; // your main contact list page
}

    @GetMapping("/search")
public String searchContacts(@RequestParam("keyword") String keyword, Model model) {
    List<Contact> results = contactRepository.findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
        keyword, keyword, keyword);
    model.addAttribute("contacts", results);
    return "index";
}


    
}
