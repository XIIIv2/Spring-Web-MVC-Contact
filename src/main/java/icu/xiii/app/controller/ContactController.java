package icu.xiii.app.controller;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/not-found")
    public String contactNotFound(Model model) {
        model.addAttribute("title", "Contact not found");
        model.addAttribute("fragmentName", "not-found");
        return "layout";
    }

    @GetMapping("/contacts")
    public String fetchAllContacts(Model model) {
        model.addAttribute("title", "Contacts");
        List<Contact> contacts = service.fetchAll();
        if (contacts.isEmpty()) {
            model.addAttribute("contactsInfo", "No contacts found. You can create some and try again.");
        } else {
            model.addAttribute("contacts", contacts);
        }
        model.addAttribute("fragmentName", "contact-list");
        return "layout";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createContact(Model model) {
        model.addAttribute("title", "New Contact");
        model.addAttribute("fragmentName", "contact-add");
        return "layout";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createContact(@ModelAttribute ContactDtoRequest request) {
        RedirectView redirectView = new RedirectView("./contacts");
        service.create(request);
        return redirectView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateContact(@PathVariable("id") Long id, Model model) {
        Contact contact = service.fetchById(id);
        if (contact == null) {
            return "redirect:/not-found";
        }
        model.addAttribute("title", "Edit Contact #%d - %s".formatted(contact.getId(), contact.getFullName()));
        model.addAttribute("contact", contact);
        model.addAttribute("fragmentName", "contact-update");
        return "layout";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RedirectView updateContact(@ModelAttribute ContactDtoRequest request) {
        service.update(request.id(), request);
        return new RedirectView("./contacts");
    }

    @RequestMapping("/delete/{id}")
    public RedirectView deleteContact(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return new RedirectView("../contacts");
    }
}
